package net.dixq.irairabar;

import android.app.Activity;
import android.os.Bundle;

public class IrairaBarActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView( new GameSurfaceView(this) );
    }
}