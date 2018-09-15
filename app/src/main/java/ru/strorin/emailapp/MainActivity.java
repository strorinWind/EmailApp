package ru.strorin.emailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button previewBtn = findViewById(R.id.preview_button);

        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailText = findViewById(R.id.email_text_input);
                String email = emailText.getText().toString();
                openPreviewActivity(email);
            }
        });
    }

    private void openPreviewActivity(String message){
        PreviewActivity.start(this, message);
    }
}