package com.example.general.autokey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Encryption extends AppCompatActivity {

    Button btEncript,btClean,btBack;
    TextView tbKey2,tbPlain2,tbCipher2;

    int key , psValues[], keyStrem[],csValue[];
    String mainPlainText, plainText;
    String Ciphertext="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        btEncript = (Button) findViewById(R.id.btEncrypt);
        btClean = (Button) findViewById(R.id.btClean2);
        btBack = (Button) findViewById(R.id.btBack2);

        tbKey2 = (TextView) findViewById(R.id.tbKey2);
        tbPlain2 =  (TextView) findViewById(R.id.tbPlain2);
        tbCipher2 = (TextView) findViewById(R.id.tbCipher2);

        btEncript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (keyCheck()==0){
                    Toast.makeText(getApplicationContext(), "Key range must be ( 1 - 26 )",
                            Toast.LENGTH_SHORT).show();
                }
                else if( check() == 1) {

                    try {
                        key = Integer.valueOf(tbKey2.getText().toString());
                        encript();
                        tbCipher2.setText(Ciphertext);
                        Ciphertext = "";

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Fill up all field correctly",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Input",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Encryption.this,AutoKey.class);
                startActivity(i);
            }
        });

    }

    public void clean(){
        tbKey2.setText("");
        tbPlain2.setText("");
        tbCipher2.setText("");
    }


    public void encript(){

//                plainText = mainPlainText.replaceAll(" ","");
//                plainText = plainText.toUpperCase();

                psValues = new int[plainText.length()];
                keyStrem = new int[plainText.length()];
                csValue = new int[plainText.length()];


                keyStrem[0] = key;


        // P's Values define
                for (int i = 0 ; i<plainText.length(); i++){
                    psValues[i] = (int) plainText.charAt(i)-65;
                }

        // keyStrem define
                for (int i = 1 ; i<plainText.length(); i++){
                    keyStrem[i] = psValues[i-1];
                }

        // keyStrem define
                for (int i = 0 ; i<plainText.length(); i++){
                    if((psValues[i]+keyStrem[i])>25)
                        csValue[i] = (psValues[i]+keyStrem[i])%26;
                    else
                        csValue[i] = psValues[i]+keyStrem[i];
                }

           //   Ciphertext = printCiphertext(csValue);

                     for (int valu : csValue) {
                              valu += 65;
                         Ciphertext = Ciphertext + (char)valu;
                         }

            }


            public int check(){

                int a=1;
                mainPlainText = tbPlain2.getText().toString();
                plainText = mainPlainText.replaceAll(" ","");
                plainText = plainText.toUpperCase();


                for(int i=0;i<plainText.length();i++){
                    if ((int)plainText.charAt(i) >= 65 && (int) plainText.charAt(i)<= 90){
                        a = 1;
                    }
                    else {
                        a = 0;
                        break;
                    }
                }
                return a;
            }

            public int keyCheck(){
                try {
                    key = Integer.valueOf(tbKey2.getText().toString());
                }
                catch (Exception e){
                    return 3;
                }

                if(key>0 && key<27){
                    return 1;
                }
                return 0;
            }

}

