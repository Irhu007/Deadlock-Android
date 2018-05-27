package com.wiz.aman.deadlock;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                   t1.speak("Welcome to Deadlock Prototype. Please enter the number of processes!", TextToSpeech.QUEUE_FLUSH, null);

                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized(this) {
                        wait(8750);

                        Intent intent= new Intent(MainActivity.this,Loading.class);
                        startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }}
