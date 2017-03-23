package com.mah.countdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private CountDown mCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountDown = (CountDown) findViewById(R.id.count);

        mCountDown.setHour(9);
        mCountDown.setMinute(10);
        mCountDown.setSeconds(18);
        mCountDown.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDown.clear();
    }
}
