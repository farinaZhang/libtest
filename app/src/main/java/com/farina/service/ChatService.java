package com.farina.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by FarinaZhang on 2016/7/20.
 */
public class ChatService extends Service  {

    @Override
    public void onCreate(){

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
