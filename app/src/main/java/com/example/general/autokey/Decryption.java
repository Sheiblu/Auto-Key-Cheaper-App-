package com.example.general.autokey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Decryption extends AppCompatActivity {

    Button btDecrypt,btClean,btBack;
    TextView tbKey2,tbPlain2,tbCipher2;

    int key , psValues[], keyStrem[],csValue[];
    String mainCiphertext, ciphertext;
    String plainText="";
    int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        btDecrypt = (Button) findViewById(R.id.btDecrypt);
        btClean = (Button) findViewById(R.id.btClean1);
        btBack = (Button) findViewById(R.id.btBack1);

        tbKey2 = (TextView) findViewById(R.id.tbKey1);
        tbPlain2 =  (TextView) findViewById(R.id.tbPlain1);
        tbCipher2 = (TextView) findViewById(R.id.tbCipher1);

        btDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check();

                if (keyCheck()==0){
                    Toast.makeText(getApplicationContext(), "Key range must be ( 1 - 26 )",
                            Toast.LENGTH_SHORT).show();
                }

                else if(a == 1) {
                    try {
                        key = Integer.valueOf(tbKey2.getText().toString());
                        decript();
                        tbPlain2.setText(plainText);
                        plainText = "";

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Fill up all field correctly ",
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
                Intent i = new Intent(Decryption.this,AutoKey.class);
                startActivity(i);
            }
        });
    }

    public void clean(){
        tbKey2.setText("");
        tbPlain2.setText("");
        tbCipher2.setText("");
    }

    public void decript() {


        psValues = new int[ciphertext.length()];
        keyStrem = new int[ciphertext.length()];
        csValue = new int[ciphertext.length()];


        keyStrem[0] = key;
    //    ciphertext = mainCiphertext.toUpperCase();

        // C's Values define
                for (int i = 0 ; i<ciphertext.length(); i++){
                    csValue[i] = (int) ciphertext.charAt(i)-65;
                }

        // keyStrem define
                for (int i = 1 ; i<ciphertext.length(); i++){

                    if(csValue[i-1]>keyStrem[i-1])
                        keyStrem[i] = csValue[i-1]-keyStrem[i-1];
                    else
                        keyStrem[i] = (csValue[i-1]+26)-keyStrem[i-1];
                }

        // P's Values Find
                for (int i = 0 ; i<ciphertext.length(); i++){
                    if((csValue[i]<keyStrem[i]))
                        psValues[i] = ((csValue[i]+26)-keyStrem[i]);
                    else
                        psValues[i] = ((csValue[i])-keyStrem[i]);
                }

        //plainText
                for (int valu : psValues) {
                    valu += 65;
                    plainText = plainText + (char)valu;
                }
    }

    public void check(){

        mainCiphertext = tbCipher2.getText().toString();
        ciphertext = mainCiphertext.replaceAll(" ","");
        ciphertext = ciphertext.toUpperCase();

        for(int i=0;i<ciphertext.length();i++){

            if ((int)ciphertext.charAt(i) >= 65 && (int) ciphertext.charAt(i)<= 90){
                a = 1;
            }
            else {
                a = 0;
                break;
            }
        }
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
