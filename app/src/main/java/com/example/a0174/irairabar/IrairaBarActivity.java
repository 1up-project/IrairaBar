package com.example.a0174.irairabar;

import android.app.Activity;
import android.os.Bundle;

public class IrairaBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameSurfaceView(this));
    }
}
