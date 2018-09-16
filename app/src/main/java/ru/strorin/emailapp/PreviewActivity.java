package ru.strorin.emailapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {
    private static final String EMAIL_TEXT = "EMAIL_TEXT";

    private static final String[] EMAIL_ADDRESSES = {"strorinw@gmail.com"};
    private static final String EMAIL_SUBJECT = "Hello, Android Academy, MSK!";
    private String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        TextView previewText = findViewById(R.id.preview_text);
        emailText = getIntent().getStringExtra(EMAIL_TEXT);
        previewText.setText(emailText);

        Button emailBtn = findViewById(R.id.email_button);
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail(EMAIL_ADDRESSES, EMAIL_SUBJECT, emailText);
            }
        });
    }

    public static void start(Activity activity, String emailText){
        Intent previewActivityIntent = new Intent(activity, PreviewActivity.class);
        previewActivityIntent.putExtra(EMAIL_TEXT, emailText);
        activity.startActivity(previewActivityIntent);
    }

    private void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "No Email app found", Toast.LENGTH_LONG).show();
        }
    }
}
