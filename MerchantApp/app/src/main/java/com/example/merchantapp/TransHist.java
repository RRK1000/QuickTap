package com.example.merchantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransHist extends AppCompatActivity {
    String[] details;
    ArrayList myDataset= new ArrayList<String>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_hist);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        button = (Button)findViewById(R.id.button);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        sendtocloudfun();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtocloudfun();
            }
        });

    }

    String URL = "https://us-central1-quicktap-b9bc9.cloudfunctions.net/app/merchantLogs?id=00000000f6ff0922";
    public void sendtocloudfun(){
        JSONArray js = new JSONArray();
//        try {
//
//
//            js.put("usn", usn);
//            js.put("pid", pid);
//
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,URL, js,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
//                            Log.v("Response:%n %s", response.toString(4));
//                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
//                            details = response.toString().split(" ");
//                            myDataset.add(response.getJSONObject(0).toString());
                            JSONArray jArray = (JSONArray)response;
                            myDataset.clear();
                            if (jArray != null) {
                                for (int i = 0; i < jArray.length(); i++) {
                                    myDataset.add(jArray.getString(i));
                                    mAdapter.notifyDataSetChanged();
                                    recyclerView.setAdapter(mAdapter);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
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
