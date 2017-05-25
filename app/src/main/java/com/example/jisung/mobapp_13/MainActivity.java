package com.example.jisung.mobapp_13;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText e1;
    ImageView i1;
    MyTask task1;
    Boolean chose=false;
    int k;
    int srclist[]= {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.t1);
        e1 = (EditText)findViewById(R.id.e1);
        i1 = (ImageView)findViewById(R.id.img);



    }

    public void onClick(View v){
        if(v.getId()==R.id.img) {
            int n = Integer.parseInt(e1.getText().toString());
            if(chose) {
                task1.cancel(true);
                chose =false;
                t1.setText(k+1+"번째 설현사진");
            }
            else{
                task1 = new MyTask();
                task1.execute(n);
                chose = true;
            }
        }
        else {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        }

    }

    private class MyTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            t1.setText("시작부터 0초");
            //-1
        }

        @Override
        protected Void doInBackground(Integer... params) {//-2,4
            for(int i=1,j=0;i<101;i++){
                if(isCancelled()==true)return null;//cancel가 true때 는 onCancelled로 간다.
                try {
                    Log.d("progress",params[0]+"pa");
                    Thread.sleep(1000);
                    if(i%params[0]==0)
                        publishProgress(i,j++);
                    else
                        publishProgress(i);//pubalicProgress(1,2,3,4)이렇게 할 수 있음)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {//-3
            super.onProgressUpdate(values);//배열처럼 사용
//            Log.d("progress",values[0]+":"+values[1]);
            if(values.length==1)
                t1.setText("시작부터 "+values[0]+"초");
            else{
                t1.setText("시작부터 "+values[0]+"초");
                i1.setImageResource(srclist[values[1]%6]);
                k=values[1]%6;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {//-5
            super.onPostExecute(aVoid);
            t1.setText("완료되었습니다.");
            t1.setTextColor(Color.BLACK);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
