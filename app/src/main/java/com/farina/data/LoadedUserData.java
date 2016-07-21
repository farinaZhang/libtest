package com.farina.data;

import android.content.Context;
import android.graphics.Color;

/**
 * created by farinaZhang on 016-05-26
 * the class is for save user data and state
*/

public class LoadedUserData{
    public  static UserEntity userInfo=new UserEntity();
    public  static int picStyle=0;//圆形
    public  static int picBorderWidth=1;
    public  static int pinBorderColor= Color.BLACK;

    private static  LoadedUserData loaduser;

    public static LoadedUserData getInstance(Context context){
        if(loaduser==null){
            loaduser = new LoadedUserData();
        }
        return loaduser;
    }
}