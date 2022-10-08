package edu.northeastern.numad22fa_jiyoonjeong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class PrimeDetector extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Handler textHandler = new Handler();
    TextView statusText;
    TextView current;
    private volatile boolean stopThread = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_detector);
        statusText = findViewById(R.id.primeStatus);
        current = findViewById(R.id.currentNumber);
    }

    public void onBackPressed(){
        if(stopThread == false){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Warning");
            builder.setMessage("Do you really want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.85);
            int height = (int)(getResources().getDisplayMetrics().widthPixels*0.35);
            alertDialog.getWindow().setLayout(width, height);
        }
        else{
            finish();
        }

    }


    //Method which runs on a different thread which uses the Runnable interface.
    public void runOnRunnableThread(View view) {
        stopThread = false;
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();
    }

    public void setStopThread(View view){
        stopThread = true;
    }

    //Class which implements the Runnable interface.
    class runnableThread implements Runnable {

        @Override
        public void run() {
            for (int i = 3; i <= Double.POSITIVE_INFINITY; i=i+2) {
                if(stopThread){
                    return;
                }
                boolean finalI = true;
                final int finalPrime = i;
                //The handler changes the TextView running in the UI thread.

                if(i % 3 == 0){
                    textHandler.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            current.setText("Current Number being checked : " + finalPrime);

                        }
                    });
                }


                for (int j = 2; j <= i/2; j++){
                    if( i % j == 0){
                        finalI = false;
                        break;
                    }
                }
                if(finalI == true){
                    textHandler.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            statusText.setText("Latest Prime Number : " + finalPrime);
//                            if (finalI == 10) {
//                                statusText.setText("");
//                            }
                        }
                    });
                }

                Log.d(TAG, "Running on a different thread using Runnable Interface: " + i);
                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList("linklist", (ArrayList<? extends Parcelable>) linkList);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        savedInstanceState.getParcelableArrayList("linklist");
//    }

}