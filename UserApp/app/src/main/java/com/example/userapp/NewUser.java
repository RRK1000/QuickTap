package com.example.userapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userapp.Confirm;
import com.example.userapp.R;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;

import info.androidhive.barcode.BarcodeReader;

public class NewUser extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;
    String rresult="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);

    }


    @Override
    public void onScanned(Barcode barcode) {
        // play beep sound
        barcodeReader.playBeep();
        rresult=barcode.displayValue;
        Intent int1 = new Intent(this, Confirm.class);
        int1.putExtra("rresult", rresult);
        startActivity(int1);
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
}
