package com.farina.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.farina.component.LoadAndSigned;

/**
 * Created by FarinaZhang on 2016/5/27.
 */
public class UserLoadOnActivity extends AppCompatActivity {
    private Handler mHandler;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext = this.getBaseContext();
        LoadAndSigned view= new LoadAndSigned(mContext);
        view.setHandler(mHandler);
        view.init();
        setContentView(view);
    }

    private void setHandler(){
        mHandler=new Handler() {
            @Override
            public void handleMessage(android.os.Message msg) {
                switch(msg.what){

                }
            }

        };
    }
}
