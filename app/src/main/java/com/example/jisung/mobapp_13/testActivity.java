package com.example.jisung.mobapp_13;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class testActivity extends AppCompatActivity {
    TextView t1;
    int index = 0;
    Handler mhandler = new Handler();//handler는 만든 곳에 따라 주인이 바뀜
    EditText e1, e2;

    Handler mainhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//-3
            super.handleMessage(msg);
            String name2 = (String)msg.obj;
            e2.setText(name2);
        }
    };

    myThread subTread = new myThread();

    class myThread extends Thread {
        SubHandler subHandler = new SubHandler();//mythread handler,

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Looper.loop();//종료할 때 반드시 종료시켜야 한다.

        }
    }

    class SubHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);//msg의 obj에 있다 (string이)-2
            String name = (String)msg.obj;
            name = "Hello "+name;
            Message msg2 = Message.obtain();
            msg2.obj = name;
            mainhandler.sendMessage(msg2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        t1 = (TextView) findViewById(R.id.text1);

        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        subTread.start();

//        Handler mhandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                t1.setText("숫자 : " + index);
//            }
//        };//handle msg를 실행하라는 것
    }

    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            String name = e1.getText().toString();
            Message msg = Message.obtain();
            msg.obj = name;//msg 만듬
            //main -sub handler로
            subTread.subHandler.sendMessageDelayed(msg,2000);//subthread안의 handler에 보냄 -1
        } else {
            MyThread th = new MyThread();
            th.start();
        }
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            for (index = 1; index < 11; index++) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subTread.subHandler.getLooper().quit();
    }
}
