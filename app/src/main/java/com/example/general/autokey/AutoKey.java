package com.example.general.autokey;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AutoKey extends AppCompatActivity {

    Button btExit, btEecryption, btDecryption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_key);

        btExit = (Button) findViewById(R.id.btExit);
        btEecryption = (Button) findViewById(R.id.btEncryption);
        btDecryption = (Button) findViewById(R.id.btDecryption);

        btDecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutoKey.this, Decryption.class);
                startActivity(i);
            }
        });

        btEecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AutoKey.this, Encryption.class);
                startActivity(i);
            }
        });

        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " Sheiblu & Mehedi \n    SWE 17 Batch\nDaffodil University",
                        Toast.LENGTH_LONG).show();
                finishAffinity();

            }
        });
    }
}
