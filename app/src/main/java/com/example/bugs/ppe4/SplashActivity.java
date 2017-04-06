package com.example.bugs.ppe4;

/**
 * Created by bugs on 06/04/17.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    public Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                checkInternet testco = new checkInternet();
                if(checkInternet.isNetworkAvailable(activity.getApplicationContext())) {
                    Intent i = new Intent(SplashActivity.this, login.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
                else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                    builder1.setMessage("Intenet est nécessaire pour lancer l'application");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Reesayer",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });

                    builder1.setNegativeButton(
                            "Quitter",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    activity.finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}