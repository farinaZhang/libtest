package com.farina.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farina.data.LoadedUserData;
import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FarinaZhang on 2016/7/4.
 */

public class UserInfoActivity extends Activity implements View.OnClickListener{
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.back)
    TextView mBack;
    @InjectView(R.id.user_img)
    ViaImageView mUserPic; //用户头像
    @InjectView(R.id.name)
    TextView mUserName; //用户名称
    @InjectView(R.id.binding)
    RelativeLayout mBindingInfo;//绑定用户信息
    @InjectView(R.id.change_pic)
    RelativeLayout mChangePic;//修改用户头像
    @InjectView(R.id.other_style)
    RelativeLayout mOtherStyle; //其他样式显示

    private Context mContext;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ButterKnife.inject(this);
        mContext = this.getBaseContext();
        InitHandler();
        InitView();
    }


    private void InitHandler(){
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch(msg.what){
                    case 0:{
                        break;
                    }
                }
            }
        };
    }

    @Override
    public void onClick(View item){
        switch(item.getId()){
            case R.id.back:{
                finish();
                overridePendingTransition(R.anim.translate_right_in,R.anim.translate_left_out);
                break;
            }
            case R.id.name:{
                enterChangeUserName();
                break;
            }
            case R.id.binding:{
                enterBindingInfo();
                break;
            }
            case R.id.change_pic:{
                enterChangeUserPic();
                break;
            }
            case R.id.other_style:{
                enterShowOtherStyle();
                break;
            }
        }
    }
    private void InitView(){
        mTitle.setText(mContext.getResources().getText(R.string.user_info));
        mBack.setOnClickListener(this);

        //String iconPath = LoadedUserData.userInfo.getIconPath();
        mUserPic.setImageResource(R.mipmap.user_img);

        mUserName.setText(LoadedUserData.userInfo.getNikeName());
        mUserName.setOnClickListener(this);

        mBindingInfo.setOnClickListener(this);
        mChangePic.setOnClickListener(this);
        mOtherStyle.setOnClickListener(this);
    }
    private void enterChangeUserName(){
        //点击名称，进入修改用户名称view
        //调用toastlib库中的编辑框dialog显示。
    }
    private void enterBindingInfo(){
        //点击绑定信息 按钮，进入绑定用户信息view
        Intent intent=new Intent (this,BindingUserInfoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_right_in,R.anim.alpha_invisible);
    }
    private void enterChangeUserPic(){
        //点击修改头像按钮，进入修改头像view
        Intent intent=new Intent (this,ChangeUserPicActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_right_in,R.anim.alpha_invisible);
    }
    private void enterShowOtherStyle(){
        //点击其他样式按钮，进入修改头像显示样式view
        Intent intent=new Intent (this,ShowInOtherStyleActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_right_in,R.anim.alpha_invisible);
    }
}
