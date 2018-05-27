package com.wiz.aman.deadlock;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import static com.wiz.aman.deadlock.R.id.activity_resource;

public class Resource extends AppCompatActivity {

    String value;
    int x;
    int count=0;
    TextView edit2;
    static int arr[][];
    int count2=-1;
    int a;
    Intent intent;
    String t="";
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//        android.app.ActionBar actionBar=getActionBar();
//        try {
//            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2F000000")));
//        }
//        catch(NullPointerException e){
//        }
//        setContentView(R.layout.activity_resource);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);

                }
            }
        });

         value=getIntent().getExtras().getString("Number");
        x=Integer.parseInt(value);
        edit2=(TextView) findViewById(R.id.text2);
         edit2.setText("Select Resources for "+"P"+count);
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);

        MenuItem menu1= menu.add(0,0,0,"Proceed");
        menu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        arr= new int[x][6];
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        if(count<x-1) {
            count++;
            t1.speak("Enter resources for P"+count, TextToSpeech.QUEUE_FLUSH, null);

            count2=-1;
            edit2.setText("Select Resources for "+"P"+count);
        }
        else
        {
            t1.speak("Thank you for your time", TextToSpeech.QUEUE_FLUSH, null);

//            int i,j;
//            for(i=0;i<x;i++)
//            {
//                for(j=0;j<count2;j++)
//                {
//                    String n=String.valueOf(arr[i][j]);
//                    Toast.makeText(this, n, Toast.LENGTH_SHORT).show();
//
//                }
//                }

              intent= new Intent(this,Testing.class);
            for(int i=0;i<=count;i++)
            {
                int arry[] = arr[i];
                intent.putExtra("Process"+i,arry);


//                for(int j=0;j<=count2;j++) {
//                    String m = String.valueOf(arry[j]);
//                    Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
//                intent.putExtra("PROCESS" + i, arr[i]);
//                }
            }
            intent.putExtra("TPRO", count);
            intent.putExtra("TRES",count2);

              startActivity(intent);
            t1.speak("Click on Automatic generate to create a Available and Max Matrix and click Analyse to check", TextToSpeech.QUEUE_FLUSH, null);

        }
        return true;
    }

    public void file(View v)
    {

        switch(v.getId())
        {
            case R.id.im1:
                file1();
                break;
            case R.id.im2:
                file1();
                break;
            case R.id.im3:
                file1();
                break;
            case R.id.im4:
                file1();
                break;
            case R.id.im5:
                file1();
                break;
            case R.id.im6:
                file1();
                break;
        }
    }

    public void file1()
    {
        AlertDialog.Builder myBox= new AlertDialog.Builder(this);

        myBox.setTitle("Add Resource");
        myBox.setMessage("Are you Sure?");
        myBox.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int id)
            {
                 instance();
            }});
        myBox.setNegativeButton("No",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int id)
            {
                dialog.cancel();
            }
        });

        myBox.show();
    }

    public void instance() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Instances");
        alertDialog.setMessage("Enter number of instances");

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setNeutralButton("Proceed",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inst = input.getText().toString();
                        a=Integer.parseInt(inst);
                        count2++;
                        arr[count][count2]=a;

                        Toast.makeText(Resource.this, "Resources Added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                            }
                        }
                );
        alertDialog.show();
    }
}