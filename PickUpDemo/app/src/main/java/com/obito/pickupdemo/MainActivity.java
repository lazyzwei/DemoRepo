package com.obito.pickupdemo;

import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements PickupDetector.PickupDetectListener {

    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;
    private PickupDetector pickupDetector;

    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "TAG");
        pickupDetector = new PickupDetector(this);

        btnGo = (Button) findViewById(R.id.btn_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pickupDetector != null){
            pickupDetector.register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pickupDetector != null){
            pickupDetector.unRegister();
        }
    }

    @Override
    public void onPickupDetected(boolean isPickingUp) {
        System.out.println(" MainActivity onPickupDetected " + isPickingUp);
        if (wakeLock == null) {
            System.out.println(" No PROXIMITY_SCREEN_OFF_WAKE_LOCK");
            return;
        }
        if (isPickingUp && !wakeLock.isHeld()) {
            wakeLock.acquire();
        }
        if (!isPickingUp && wakeLock.isHeld()){
            try {
                wakeLock.setReferenceCounted(false);
                wakeLock.release();
            }catch (Exception e){

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wakeLock != null && wakeLock.isHeld()){
            wakeLock.setReferenceCounted(false);
            wakeLock.release();
        }
    }
}
