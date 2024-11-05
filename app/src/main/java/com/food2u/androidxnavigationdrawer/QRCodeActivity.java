package com.food2u.androidxnavigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private FrameLayout scannerView;
    private FloatingActionButton fabRetry;
    private FloatingActionButton fabFlash;

    private boolean isFlashOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        mScannerView = new ZXingScannerView(this);

        scannerView = findViewById(R.id.container);
        fabRetry = findViewById(R.id.fab_retry);
        fabFlash = findViewById(R.id.fab_flash);
        scannerView.addView(mScannerView);
        fabRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScannerView.startCamera();

            }
        });
        fabFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFlashOn = !isFlashOn;
                mScannerView.setFlash(isFlashOn);

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.startCamera();
        mScannerView.setResultHandler(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        mScannerView.resumeCameraPreview(this);
        mScannerView.stopCamera();
        String decryptString=result.toString();

    }
}
