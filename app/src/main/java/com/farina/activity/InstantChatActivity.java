package com.farina.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.farina.adapter.InstantChatListAdapter;
import com.farina.data.InstantChatEntity;
import com.farina.data.InstantChatListData;
import com.farina.libtest.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by FarinaZhang on 2016/7/13.
 */

public class InstantChatActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Handler mHandler;
    private TextView mTitle;
    private TextView mBack;
    private ListView mList;
    private EditText mInput;
    private TextView mSend;
    private ImageView mSelImg;
    private ImageView mSelFile;
    private InstantChatListData mData;
    private InstantChatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_chat);

        mContext = this.getBaseContext();
        InitHandler();
        InitView();

    }

    private void InitView(){
        mBack = (TextView)findViewById(R.id.back);
        mBack.setText("消息");
        mBack.setOnClickListener(this);
        mTitle = (TextView)findViewById(R.id.title);

        mInput=(EditText)findViewById(R.id.input);
        mSend=(TextView)findViewById(R.id.send);
        mSelImg=(ImageView)findViewById(R.id.select_img);
        mSelFile=(ImageView)findViewById(R.id.select_file);
        mSend.setOnClickListener(this);
        mSelImg.setOnClickListener(this);
        mSelFile.setOnClickListener(this);

        mList = (ListView)findViewById(R.id.list);
        setData();
        mTitle.setText(mData.sideUserName);
        mAdapter = new InstantChatListAdapter(mContext,mData);
        mList.setAdapter(mAdapter);

    }

    private void InitHandler(){
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg){

            }
        };
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:{
                finish();
                overridePendingTransition(R.anim.alpha_visible,R.anim.translate_right_out);
                break;
            }
            case R.id.send:{
                String message = mInput.getText().toString();
                sendMessageToService(message);
                break;
            }
            case R.id.select_img:{
                selectImageForSend();
                break;
            }
            case R.id.select_file:{
                selectFileForSend();
                break;
            }
        }
    }

    private void setData(){
        if(mData!=null){
            mData.message.clear();
        }else{
            mData = new InstantChatListData();
        }
        mData.userName="me";
        mData.sideUserName="User1";
        mData.userIcn=mContext.getResources().getDrawable(R.mipmap.user_img);
        mData.sideUserIcn=mContext.getResources().getDrawable(R.mipmap.side_user_img);
        mData.message=new ArrayList<InstantChatEntity>();

        InstantChatEntity entity1 = new InstantChatEntity();
        entity1.message="hi";
        entity1.beSend=true;
        entity1.beReaded=true;

        /*long time=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date(time);
        String t1=format.format(d1);*/
        entity1.time =  "10:20:00";

        mData.message.add(entity1);

        InstantChatEntity entity2 = new InstantChatEntity();
        entity2.message="hi";
        entity2.beSend=false;
        entity2.beReaded=true;

        /*long time=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=new Date(time);
        String t1=format.format(d1);*/
        entity2.time =  "10:20:30";

        mData.message.add(entity2);

    }
    private void sendMessageToService (String message){
        //发送输入信息给服务器
        InstantChatEntity entity = new InstantChatEntity();
        entity.message=message;
        entity.beSend=true;
        entity.beReaded=true;

        long time=System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(time);
        String strTime=format.format(date);
        entity.time = strTime;
        mData.message.add(entity);
        mAdapter.updateData(mData.message);
    }

    private void selectImageForSend(){
        //打开图片选择，已发送图片
    }

    private void selectFileForSend(){
        //打开文件选择，已发送图片
    }
}
