package com.farina.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farina.data.LoadedUserData;
import com.farina.libtest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FarinaZhang on 2016/7/4.
 */

public class BindingUserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    @InjectView(R.id.action)
    TextView mSave;
    @InjectView(R.id.back)
    TextView mBack;
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.user_count)
    EditText mUserCount;    //用户名/账号
    @InjectView(R.id.phone_number)
    EditText mPhoneNumber;  //电话号码
    @InjectView(R.id.mail_name)
    EditText mMailName;     //邮箱账号
    @InjectView(R.id.qq_number)
    EditText mQQNumber;     //qq账号
    @InjectView(R.id.weixin_number)
    EditText mWeixinNumber;     //weixin 账号
    @InjectView(R.id.merge_contact)
    LinearLayout mMergeContact;
    @InjectView(R.id.merge_check_box)
    CheckBox mMergeCheckBox;


    private Context mContext;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_user_info);

        mContext = this.getBaseContext();
        ButterKnife.inject(this);

        InitHandler();
        InitView();
    }
    private void InitHandler(){

    }
    private void InitView(){
        mSave.setText("保存");
        mSave.setOnClickListener(this);
        mTitle.setText("绑定信息");
        mBack.setOnClickListener(this);
        mMergeContact.setOnClickListener(this);
        mMergeContact.setVisibility(View.INVISIBLE);

        mUserCount.setText(LoadedUserData.userInfo.getCountNumber());
        mPhoneNumber.setText(LoadedUserData.userInfo.getPhoneNumber());
        mMailName.setText(LoadedUserData.userInfo.getMailNumber());
        mQQNumber.setText(LoadedUserData.userInfo.getQQNumber());
        mWeixinNumber.setText(LoadedUserData.userInfo.getWeixinNumber());

    }
    @Override
    public void onClick(View item){
        switch(item.getId()){
            case R.id.action:{
                saveAllInfo();
                break;
            }
            case R.id.back:{
                finish();
                overridePendingTransition(R.anim.alpha_visible,R.anim.translate_right_out);
                break;
            }
            case R.id.merge_contact:{
                if(mMergeCheckBox.isChecked()){
                    mMergeCheckBox.setChecked(false);
                }else{
                    mMergeCheckBox.setChecked(true);
                }
            }
        }
    }
    private void saveAllInfo(){
        //点击保存，保存并发送到服务器
    }
}
