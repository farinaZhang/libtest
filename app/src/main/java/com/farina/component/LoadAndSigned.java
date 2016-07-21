package com.farina.component;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.farina.activity.MainActivity;
import com.farina.data.LoadedUserData;
import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

/**
 * created by farinaZhang on 2016-05-26
 * the view is for user load on signIn
 * TODO: document your custom view class.
 */
public class LoadAndSigned extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private Handler mHandler;
    private ViaImageView userImg;
    private Button signedInBtn;
    private Button loginBtn;
    private Button noPswBtn;
    private Button otherMethodBtn;
    private EditText nameEdt;
    private EditText pswEdt;

    public LoadAndSigned(Context context) {
        this(context,null);

    }

    public LoadAndSigned(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public LoadAndSigned(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    public void setHandler(Handler h){
        mHandler = h;
    }

    public void init() {
        // Load attributes
        View container = LayoutInflater.from(getContext()).inflate(
                R.layout.load_and_signed, null);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        addView(container,lp);

        nameEdt = (EditText)container.findViewById(R.id.user_id);
        pswEdt = (EditText)container.findViewById(R.id.user_psw);
        userImg = (ViaImageView)container.findViewById(R.id.user_img);
        //userImg.setImageViewProperty(3,5,Color.parseColor("#ff0000"));

        signedInBtn =(Button) container.findViewById(R.id.btn_signIn);
        loginBtn = (Button)container.findViewById(R.id.login);
        noPswBtn = (Button)container.findViewById(R.id.nopsw);
        otherMethodBtn = (Button)container.findViewById(R.id.other);
        signedInBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        noPswBtn.setOnClickListener(this);
        otherMethodBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_signIn:{//signed in
                userSignIn();
                break;
            }
            case R.id.login:{//log in
                userLoadon();
                break;
            }
            case R.id.nopsw:{
                userResetPsw();
                break;
            }
            case R.id.other:{
                otherMethodSignIn();
                break;
            }
        }
    }

    private void userSignIn(){
        //登录
        String name = nameEdt.getText().toString();
        String psw = pswEdt.getText().toString();

        if(name!=null && psw!=null){
            //signin

            LoadedUserData.userInfo.setCountNumber(name);
            LoadedUserData.userInfo.setIconPath("");

            if(LoadedUserData.userInfo.getNikeName() == null||LoadedUserData.userInfo.getNikeName().length()==0){
                LoadedUserData.userInfo.setNikeName(LoadedUserData.userInfo.getCountNumber());
            }

            Intent intent  = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
    private void userLoadon(){
       //loon 注册

    }
    private void userResetPsw(){
        //忘记密码，重置密码
    }
    private void otherMethodSignIn(){
        //以其他方式登录
    }
}
