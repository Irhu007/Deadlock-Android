package com.wiz.aman.deadlock;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class Loading extends AppCompatActivity {

    Intent intent;
    EditText edit;
    TextToSpeech t1;
    Button yourbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
//
                }
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        edit=(EditText)findViewById(R.id.et1);
        yourbutton=(Button)findViewById(R.id.button);
        if(!edit.getText().toString().isEmpty())
        {
            yourbutton.setEnabled(true);
        }
//        else
//            yourbutton.setClickable(true);
    }

    public void page1(View v)
    {
        edit=(EditText)findViewById(R.id.et1);
        intent=new Intent(this,Resource.class);
        intent.putExtra("Number",edit.getText().toString());
        startActivity(intent);
        t1.speak("Please insert the Resources and its instances for Allocation Matrix. Let us start with P0", TextToSpeech.QUEUE_FLUSH, null);

    }
}