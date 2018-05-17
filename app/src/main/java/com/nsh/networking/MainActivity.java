package com.nsh.networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeIntent;
import com.ibm.watson.developer_cloud.http.ServiceCallback;

public class MainActivity extends AppCompatActivity {

    Assistant assistant;
    EditText input_field;
    TextView output_field;
    String input_text;
    Button hit;
    String workspaceId = "7a7bc018-cbd9-4f7e-a5ff-a6e5ced04ffe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hit = findViewById(R.id.hit);
        input_field = findViewById(R.id.input);
        output_field = findViewById(R.id.output);

        assistant = new Assistant(
                "2018-02-16",
                "29ac1045-e0ca-4412-97b2-7656be2d6ac7",
                "PzZq8LwHY834");

        assistant.setEndPoint("https://gateway.watsonplatform.net/assistant/api");

        RuntimeIntent intent = new RuntimeIntent();
        intent.setIntent("welcome");
        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .addIntent(intent)
                .build();
        assistant.message(options).enqueue(new ServiceCallback<MessageResponse>() {
            @Override
            public void onResponse(MessageResponse response) {
                output_field.setText(response.getOutput().getText().get(0));
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        input_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_field.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_text = input_field.getText().toString();

                InputData input = new InputData.Builder(input_text).build();

                MessageOptions options = new MessageOptions.Builder(workspaceId)
                        .input(input)
                        .build();
                assistant.message(options).enqueue(new ServiceCallback<MessageResponse>() {
                    @Override
                    public void onResponse(MessageResponse response) {
                        output_field.setText(response.getOutput().getText().get(0));
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });



            }
        });

    }
}
