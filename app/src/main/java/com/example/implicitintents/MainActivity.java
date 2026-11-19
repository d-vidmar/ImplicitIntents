package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText websiteText = findViewById(R.id.editTextText);
        EditText locationText = findViewById(R.id.editTextText2);
        EditText sharetextText = findViewById(R.id.editTextText3);
        EditText phoneText = findViewById(R.id.editTextPhone);
        Button websiteButton = findViewById(R.id.button);
        Button locationButton = findViewById(R.id.button4);
        Button shareTextButton = findViewById(R.id.button5);
        Button callButton = findViewById(R.id.button6);

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite(websiteText.getText().toString());
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation(locationText.getText().toString());
            }
        });

        shareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(sharetextText.getText().toString());
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(phoneText.getText().toString());
            }
        });


    }

    public void openWebsite(String text) {
        Uri uri = Uri.parse(text);
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

    public void openLocation(String iskanaLokacija) {
        Uri uri = Uri.parse("geo:0,0?q="+iskanaLokacija);
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

    public void shareText(String text){
        new ShareCompat.IntentBuilder(this)
                .setType("text/plain")
                .setText(text)
                .startChooser();
    }

    public void call(String phoneNumber){
        Uri uri = Uri.parse("tel:"+phoneNumber);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }
}