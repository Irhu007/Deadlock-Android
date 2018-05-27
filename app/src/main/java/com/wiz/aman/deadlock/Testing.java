package com.wiz.aman.deadlock;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Testing extends AppCompatActivity {
    TextView textView;
    TextView text;
    TextView text2;
    String Max;
    int tpro;
    int tres;
    int[][] process;
    int Allo[][];
    int Available[];
    int need[][];
    boolean finish[];
    boolean created;
    TextToSpeech t1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);

                }
            }
        });
        Allo = new int[10][10];
        Available = new int[10];
        need=new int[10][10];
        Max = "";

        textView = (TextView) findViewById(R.id.textView2);
        process = new int[10][10];
        tpro = getIntent().getExtras().getInt("TPRO");
        tres = getIntent().getExtras().getInt("TRES");
        for (int i = 0; i <= tpro; i++) {
            process[i] = getIntent().getExtras().getIntArray("Process" + i);
            for (int j = 0; j <= tres; j++) {
                Max = Max + " " + process[i][j];
            }
            Max += "\n";
        }
        textView.setText(Max);

    }

    public void autoCal(View v)
    {
        Allo = AlloMat();
        Available = AvailMat();
        created=true;
    }

    public int[][] AlloMat() {
        text = (TextView) findViewById(R.id.textView5);
        int max[][] = new int[10][10];
        Max = "";
        int maximum = 5;

        for (int i = 0; i <= tpro; i++) {
            for (int j = 0; j <= tres; j++) {
                Random rand = new Random();
                max[i][j] = rand.nextInt(maximum) + process[i][j];
                String m = String.valueOf(max[i][j]);
                Max = Max + " " + m;
            }
            Max += "\n";
        }
        text.setText(Max);
        return max;
    }

    public int[] AvailMat() {
        text2 = (TextView) findViewById(R.id.textView7);
        int avail[] = new int[10];
        Max = "";
        int maximum = 6;

        for (int i = 0; i <= tres; i++) {
            Random rand = new Random();
            avail[i] = rand.nextInt(maximum);
            String m = String.valueOf(avail[i]);
            Max = Max + " " + m;
        }
        text2.setText(Max);
        return avail;
    }

    public void isSafe(View v) {
       if(created){
        String sequence = "";


        for (int i = 0; i <=tpro; i++) {
            for (int j = 0; j <= tres; j++) {
                need[i][j] = Allo[i][j] - process[i][j];
            }
        }
        finish= new boolean[tpro+1];

        for (int i = 0; i <= tpro; i++) {
            finish[i] = false;
        }


        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i <=tpro; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j <= tres; j++) {
                        if (need[i][j] > Available[j]) {
                            break;
                        }
                    }

                    if (j == tres+1) {
                        for (j = 0; j <=tres; j++) {
                            Available[j] = Available[j] + process[i][j];

                        }
                        sequence += "P"+i + " ";
                        finish[i] = true;
                        check = true;

                    }
                }
            }
        }

        int i;
        for (i = 0; i <= tpro; i++) {
            if (!finish[i])
                break;
        }

        if (i == tpro+1) {
            t1.speak("Deadlock can be avoided if we follow "+sequence, TextToSpeech.QUEUE_FLUSH, null);

//            Toast.makeText(this, sequence, Toast.LENGTH_SHORT).show();

        } else {
            t1.speak("I'm afraid it's a DEADLOCK and can't be avoided", TextToSpeech.QUEUE_FLUSH, null);
//            Toast.makeText(this, "DEAD LOCK", Toast.LENGTH_SHORT).show();
        }
    }
       else
           t1.speak("Kindly generate the matrices first", TextToSpeech.QUEUE_FLUSH, null);
    }
}
