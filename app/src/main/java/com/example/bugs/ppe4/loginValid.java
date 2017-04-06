package com.example.bugs.ppe4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

/**
 * Created by bugs on 16/03/17.
 */

public class loginValid extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid);

        TextView textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra(login.nom));
    }

}
