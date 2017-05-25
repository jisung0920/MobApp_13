package com.example.jisung.mobapp_13;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class test2Activity extends AppCompatActivity {
    TextView t1;
    ProgressBar bar;
    MyTask task1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        t1 = (TextView)findViewById(R.id.t1);
        bar = (ProgressBar)findViewById(R.id.bar);

    }

    public void onClick(View v){
        if(v.getId()==R.id.start){
            task1 = new MyTask();
            task1.execute(0);//여기 변수가 asyncTask의 첫번째
        }
        else if(v.getId()==R.id.stop){
            task1.cancel(true);

        }

    }

    private class MyTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setProgress(0);
            t1.setText("진행률 : 0%");
            t1.setTextColor(Color.RED);//-1
        }

        @Override
        protected Void doInBackground(Integer... params) {//-2,4
            for(int i=params[0];i<101;i++){
                if(isCancelled()==true)return null;//cancel가 true때 는 onCancelled로 간다.
                try {
                    Thread.sleep(200);
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
            bar.setProgress(values[0]);
            t1.setText("진행률 : "+values[0]+"%");

        }

        @Override
        protected void onPostExecute(Void aVoid) {//-5
            super.onPostExecute(aVoid);
            bar.setProgress(100);
            t1.setText("완료되었습니다.");
            t1.setTextColor(Color.BLACK);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            t1.setText("cancel");
        }
    }

}
