package com.example.userapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Confirm extends AppCompatActivity {
    String rresult;
    String firstName;
    String lastName;
    String contactno;
    EditText firstName1;
    EditText lastName1;
    EditText contactno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        rresult=getIntent().getStringExtra("rresult");
        firstName1 = (EditText)findViewById(R.id.firstname);
        lastName1 = (EditText)findViewById(R.id.lastname);
        contactno1 = (EditText)findViewById(R.id.phone);


    }

    public void confirm(View view) {
        firstName=firstName1.getText().toString();
        lastName=lastName1.getText().toString();
        contactno=contactno1.getText().toString();
        load(rresult,firstName,lastName,contactno);

    }

    public void reject(View view) {
//        Intent int1=new Intent(this,Food.class);
//        int1.putExtra("n",1);
//        startActivity(int1);
        this.finish();
    }
    public void load(final String rresult, String firstName, String lastName, String contactno) {

        sendtocloudfun(rresult,firstName,lastName,contactno);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    String URL = "https://us-central1-quicktap-b9bc9.cloudfunctions.net/app/addUser";
    public void sendtocloudfun(String rresult,String firstName, String lastName,String contactno){
        JSONObject js = new JSONObject();
        try {


            js.put("rresult", rresult);
            js.put("firstName", firstName);
            js.put("lastName", lastName);
            js.put("contactno", contactno);

        }catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,URL, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.v("Response:%n %s", response.toString(4));
                            Toast.makeText(getApplicationContext(),"  Success",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"FAil",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", error.getMessage());
            }
        });



        MyRequestQueue.add(req);
    }



}
