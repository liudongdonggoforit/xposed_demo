package com.xposed.xposeddemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (!isModuleActive()) {
//            Toast.makeText(this, "模块未启动", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "模块已启动", Toast.LENGTH_LONG).show();
//        }
    }

    private boolean isModuleActive() {
        return false;
    }
}
