package com.nsh.networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;

public class MainActivity extends AppCompatActivity {

    Assistant assistant;
    EditText input_field;
    TextView output_field;
    String input_text;
    String workspaceId = "7a7bc018-cbd9-4f7e-a5ff-a6e5ced04ffe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assistant = new Assistant(
                "2018-02-16",
                "29ac1045-e0ca-4412-97b2-7656be2d6ac7",
                "PzZq8LwHY834");

        assistant.setEndPoint("https://gateway.watsonplatform.net/assistant/api");

        input_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                input_text = input_field.getText().toString();

                InputData input = new InputData.Builder(input_text).build();

                MessageOptions options = new MessageOptions.Builder(workspaceId)
                        .input(input)
                        .build();

                MessageResponse response = assistant.message(options).execute();

                output_field.setText(response.toString());
            }
        });


    }
}