package com.farina.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FarinaZhang on 2016/7/14.
 */
public class ChatDBhelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME="chat.db";
    private static Context mContext;
    private static ChatDBhelper mInstance = null;
    private static SQLiteDatabase mChatDB=null;
    //数据库存储路径
    private static String filePath = "data/data/com.farina/databases/chat.db";
    private static String pathStr = "data/data/com.farina/databases";


    public static ChatDBhelper getInstance(Context context){
        mContext = context;
        if(mInstance==null){
            mInstance = new ChatDBhelper(mContext);
        }
        return mInstance;

    }
    private ChatDBhelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer primary key,"+
                "countNumber text,"+
                "nikeName text,"+
                "iconPath text,"+
                "phoneNumber text,"+
                "mailAddr text,"+
                "qqNumber text,"+
                "weixinNumber text)");

        db.execSQL("create table friends(id integer primary key,"+
                "hostId integer,"+
                "groupId integer,"+
                "friendName text,"+
                "iconPath text,"+
                "beLive text)");

        db.execSQL("create table groups(id integer primary key,"+
                "hostId integer,"+
                "groupName text)");

        db.execSQL("create table messages(id integer primary key,"+
                "message text,"+
                "fromId integer,"+
                "toId integer,"+
                "type text,"+
                "String dateTime)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop if table exists friends");
        db.execSQL("drop if table exists users");
        db.execSQL("drop if table groups");
        db.execSQL("drop if table messages");

        onCreate(db);
    }

    public void closeDB(){
        mChatDB.close();
    }
}

