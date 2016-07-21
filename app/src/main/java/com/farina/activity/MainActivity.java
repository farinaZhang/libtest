package com.farina.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farina.ConstMessage.DefindMessage;
import com.farina.component.ContactView;
import com.farina.component.hipHistoryView;
import com.farina.libtest.R;

import java.util.ArrayList;

/**
 * Created by FarinaZhang on 2016/7/4.
 */
public class MainActivity extends Activity implements View.OnClickListener
{
    private String TAG="MainActivity";
    private ViewPager mViewPager;
    private TextView mContactTab;
    private TextView mHipTab;
    private Handler mHandler;
    private ContactView mContactView;
    private hipHistoryView mHipHistoryView;
    private Context mContext;
    private int mCurPage = 0;
    private ArrayList<View> viewContainter = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=this.getBaseContext();
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager)findViewById(R.id.view_flipper);
        mContactTab=(TextView)findViewById(R.id.contact);
        mHipTab=(TextView)findViewById(R.id.hip);

        InitHandler();

        InitView();

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.contact:{
                if(mCurPage != 0)
                    changeToPage(0);
                break;
            }
            case R.id.hip:{
                if(mCurPage != 1)
                    changeToPage(1);
                break;
            }
        }
    }


    private void InitView(){
        mContactView = new ContactView(mContext);
        mHipHistoryView = new hipHistoryView(mContext);

        mContactView.setHandler(mHandler);
        mHipHistoryView.setHandler(mHandler);

        viewContainter.add(mContactView);
        viewContainter.add(mHipHistoryView);

        mViewPager.setAdapter(mAdapter);
        changeToPage(0);
        mContactTab.setOnClickListener(this);
        mHipTab.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.v(TAG,"onPageScrollStateChanged  state="+state);
                if(state==0){
                    mCurPage = mViewPager.getCurrentItem();
                    if(mCurPage==0){
                        mContactTab.setTextColor(Color.RED);
                        mHipTab.setTextColor(Color.BLACK);
                    }else{
                        mContactTab.setTextColor(Color.BLACK);
                        mHipTab.setTextColor(Color.RED);
                    }

                }
            }
        });

    }
    private void InitHandler(){
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg){
            switch(msg.what){
                case DefindMessage.ENTER_ACTIVITY_INSTANT_CHAT_VIEW:{
                    Intent intent = new Intent(mContext, InstantChatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    overridePendingTransition(R.anim.translate_right_in,R.anim.alpha_invisible);
                    break;
                }
            }

            }
        };
    }

    //切换页面
    private void changeToPage(int nPage){
        mCurPage = nPage;
        if(nPage ==0){
            // 联系人
            mContactTab.setTextColor(Color.RED);
            mHipTab.setTextColor(Color.BLACK);
            mViewPager.setCurrentItem(0);

        }else{
            //消息
            mContactTab.setTextColor(Color.BLACK);
            mHipTab.setTextColor(Color.RED);
            mViewPager.setCurrentItem(1);
        }
    }

    private PagerAdapter mAdapter= new PagerAdapter(){
        @Override
        public int getCount() {
            return viewContainter.size();
        }
        //滑动切换的时候销毁当前的组件
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            ((ViewPager) container).removeView(viewContainter.get(position));
        }
        //每次滑动的时候生成的组件
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(viewContainter.get(position));
            return viewContainter.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    };

}