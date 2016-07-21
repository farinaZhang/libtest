package com.farina.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FarinaZhang on 2016/7/8.
 */

public class ShowInOtherStyleActivity extends AppCompatActivity implements View.OnClickListener{
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.back)
    TextView mBack;
    @InjectView(R.id.user_img)
    ViaImageView mUserPic;
    @InjectView(R.id.pic_shap)
    Spinner mPicShapSpinner;
    @InjectView(R.id.border_weight)
    Spinner mBorderWeightSpinner;
    @InjectView(R.id.border_color)
    Spinner mBorderColorSpinner;

    private Context mContext;
    private SpinnerAdapter mAdapter1,mAdapter2,mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_in_other_style);

        mContext = this.getBaseContext();
        ButterKnife.inject(this);

        InitView();
    }

    private void InitView(){
        mTitle.setText("更改样式");
        mBack.setOnClickListener(this);
        //String iconPath = LoadedUserData.userInfo.getIconPath();
        mUserPic.setImageResource(R.mipmap.user_img);

        mPicShapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    mUserPic.setImageViewShape(0);//圆形
                }else if(position == 1){
                    mUserPic.setImageViewShape(1);//三角形
                }else if(position == 2){
                    mUserPic.setImageViewShape(2);//五角星形
                }else if(position == 3){
                    mUserPic.setImageViewShape(3);//六边形
                }else if(position == 4){
                    mUserPic.setImageViewShape(4);//矩形
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /***
        //mPicShapSpinner
        List<String> listDataBorderWidth=new ArrayList<String>();
        listDataBorderWidth.add("0");
        listDataBorderWidth.add("1");
        listDataBorderWidth.add("2");
        listDataBorderWidth.add("3");
        listDataBorderWidth.add("4");
        listDataBorderWidth.add("5");

        ArrayAdapter<String> borderWeightAdapter;
        borderWeightAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDataBorderWidth);
        borderWeightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBorderWeightSpinner.setAdapter(borderWeightAdapter);
        **/


        mBorderWeightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    mUserPic.setImageBorderWidth(0);
                }else if(position ==1){
                    mUserPic.setImageBorderWidth(1);
                }else if(position ==3){
                    mUserPic.setImageBorderWidth(3);
                }else if(position ==5){
                    mUserPic.setImageBorderWidth(5);
                }

                //mUserPic.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBorderColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    mUserPic.setImageBorderColor(Color.BLACK);
                }else if(position==1) {
                    mUserPic.setImageBorderColor(Color.RED);
                }else if(position==2) {
                    mUserPic.setImageBorderColor(Color.BLUE);
                }
                //mUserPic.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:{
                finish();
                overridePendingTransition(R.anim.alpha_visible,R.anim.translate_right_out);
                break;
            }
        }
    }

}
