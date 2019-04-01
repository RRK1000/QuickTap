package com.example.userapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    Button user;
    Button transh;
    Button bal;
    Button block;
    TextView curbal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (Button)findViewById(R.id.newuser);
        transh = (Button)findViewById(R.id.trhst);
        block = (Button)findViewById(R.id.block);
        bal = (Button)findViewById(R.id.pay);



        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewUser.class);
                startActivity(intent);
            }
        });

        transh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransHist.class);
                startActivity(intent);
            }
        });
        bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayBal.class);
                startActivity(intent);
            }
        });
        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Block.class);
                startActivity(intent);
            }
        });
    }


}
