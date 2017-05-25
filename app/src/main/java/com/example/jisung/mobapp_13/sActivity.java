package com.example.jisung.mobapp_13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class sActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
    }
    public void onClick(View v){
        if(v.getId() == R.id.test){
            Intent intent = new Intent(sActivity.this,test2Activity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.main){
            Intent intent = new Intent(sActivity.this,LoadingMainActivity.class);
            startActivity(intent);
        }
    }
}
