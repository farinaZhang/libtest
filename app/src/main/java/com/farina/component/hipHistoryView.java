package com.farina.component;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.farina.ConstMessage.DefindMessage;
import com.farina.adapter.HipHistoryListAdapter;
import com.farina.data.HipHistoryItemEntity;
import com.farina.libtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarinaZhang on 2016/7/4.
 */
public class hipHistoryView extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    private Handler mHandler;
    private TextView mAdd;  //添加
    private ListView mList; //消息列表
    private HipHistoryListAdapter mAdapter;

    public hipHistoryView(Context context) {
        this(context, null);
    }

    public hipHistoryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public hipHistoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        View mView = LayoutInflater.from(context).inflate(R.layout.hip_history_view,null);
        addView(mView);

        mAdd = (TextView)mView.findViewById(R.id.add);
        mAdd.setOnClickListener(this);

        mList = (ListView)mView.findViewById(R.id.list);

        //加载数据
        setData();

        mAdapter = new HipHistoryListAdapter(mContext,setData());
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickHipHistoryItem(position);
            }
        });
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.add:{
                enterAddContactFunc();
                break;
            }
        }

    }
    public void setHandler(Handler h){
        mHandler = h;
    }
    private List<HipHistoryItemEntity> setData(){
        List<HipHistoryItemEntity> listData=new ArrayList<HipHistoryItemEntity>();
        //设置消息记录的数据
        for(int i=0;i<10;i++) {
            HipHistoryItemEntity entity = new HipHistoryItemEntity();
            entity.pic = mContext.getResources().getDrawable(R.mipmap.user_img);
            entity.name="user"+i;
            entity.time=i+"s";
            entity.message="message string "+i;
            entity.newCount = String.valueOf(i);
            listData.add(entity);
        }

        return listData;
    }
    private void enterAddContactFunc(){
        //点击添加，进入添加联系人
    }

    private void onClickHipHistoryItem(int position){
        //进入与该用户聊天模块
        Message msg=new Message();
        msg.what= DefindMessage.ENTER_ACTIVITY_INSTANT_CHAT_VIEW;
        mHandler.sendMessage(msg);
    }
}
