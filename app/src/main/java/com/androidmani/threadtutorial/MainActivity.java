package com.androidmani.threadtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    AppCompatButton btnStart;
    private volatile boolean stopThread = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        AppCompatButton btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopThread = false;
                new ExampleThread().start();
//                new Thread(new ExampleRunnable()).start();


            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopThread = true;

            }
        });
    }

    //Step 1 - Extend the Thread base class
    class ExampleThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Log.d(TAG, "onClick: "+i);
               if(stopThread)
               {
                   Log.d(TAG, "run: Thread Stopped");
                   return;
               }
                try {
                    if(i == 5)
                    {
                        //Step 1- UI post method
//                        btnStart.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                btnStart.setText("50% completed");
//                            }
//                        });

                        //Step 2 - RunOnUIThread method
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                btnStart.setText("50% completed");
//                            }
//                        });

                        //Step 3 - Handler with Looper
//                        Handler handler = new Handler(Looper.getMainLooper());
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                btnStart.setText("50% Complete");
//                            }
//                        });
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //Step 2- Implement Runnable class
    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Log.d(TAG, "onClick: "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
