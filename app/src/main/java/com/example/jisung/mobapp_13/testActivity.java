package com.example.jisung.mobapp_13;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class testActivity extends AppCompatActivity{
    TextView t1;
    int index = 0;
    Handler mhandler = new Handler();//handler는 만든 곳에 따라 주인이 바뀜
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        t1 = (TextView)findViewById(R.id.text1);
        Handler mhandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                t1.setText("숫자 : "+index);
            }
        };//handle msg를 실행하라는 것
    }
    public void onClick(View v){
        MyThread th = new MyThread();
        th.start();

    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for(index=1;index<11;index++){
                try {
                    Thread.sleep(1000);
                    Message msg = mhandler.obtainMessage();
                    mhandler.sendMessage(msg);

//                    mhandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            t1.setText("숫자 : "+index);
//                        }
//                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
