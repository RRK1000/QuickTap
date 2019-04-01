package com.example.userapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Block extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText str;
    String num;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        str = (EditText)findViewById(R.id.num);
        b = (Button)findViewById(R.id.sub);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first check the number
                smsSendMessage(v);
            }
        });
    }

    public void smsSendMessage(View view) {
        String smsNumber = "smsto:9900851788";
        String sms = "Block card" + num;
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        Toast.makeText(getApplicationContext(),"Message sent",Toast.LENGTH_LONG).show();
// Set the data for the intent as the phone number.
        smsIntent.setData(Uri.parse(smsNumber));
        // Add the message (sms) with the key ("sms_body").
        smsIntent.putExtra("sms_body", sms);
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Log.e(TAG, "Can't resolve app for ACTION_SENDTO Intent");
        }
    }
}
