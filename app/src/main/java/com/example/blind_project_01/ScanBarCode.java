package com.example.blind_project_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ScanBarCode extends AppCompatActivity {

    private IntentIntegrator qrScan;            //세로모드로 하기 위해 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_bar_code);


        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(true); // default(true)가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String bar_number = result.getContents();
        Intent intent = new Intent(ScanBarCode.this, ScanResultActivity.class);
        intent.putExtra("bar_number", bar_number);
        startActivity(intent);


        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
