package com.farina.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
/**
 * Created by FarinaZhang on 2016/7/8.
 */
public class ChangeUserPicActivity extends AppCompatActivity implements View.OnClickListener{
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.back)
    TextView mBack;
    @InjectView(R.id.user_img)
    ViaImageView mUserPic;
    @InjectView(R.id.location)
    RelativeLayout mLocationItem;
    @InjectView(R.id.service)
    RelativeLayout mServiveItem;

    private Context mContext;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_pic);

        mContext = this.getBaseContext();
        ButterKnife.inject(this);

        InitView();
    }
    private void InitView(){
        mTitle.setText("更改头像");
        mBack.setOnClickListener(this);

        //String iconPath = LoadedUserData.userInfo.getIconPath();
        mUserPic.setImageResource(R.mipmap.user_img);

        mLocationItem.setOnClickListener(this);
        mServiveItem.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:{
                finish();
                overridePendingTransition(R.anim.alpha_visible,R.anim.translate_right_out);
                break;
            }
            case R.id.location:{
                fromLocation();
                break;
            }
            case R.id.service:{
                fromService();
                break;
            }
        }
    }

    private void fromLocation(){
        //从本地获取头像，打开相册
    }
    private void fromService(){
        //从网络端或服务器 获取头像
    }
}
