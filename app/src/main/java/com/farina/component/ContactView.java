package com.farina.component;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farina.ConstMessage.DefindMessage;
import com.farina.activity.UserInfoActivity;
import com.farina.adapter.ContactExpendListAdapter;
import com.farina.data.FriendEntity;
import com.farina.data.FriendListData;
import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FarinaZhang on 2016/7/4.
 */
public class ContactView extends LinearLayout implements View.OnClickListener{
    @InjectView(R.id.user_img)
    ViaImageView mUserImage;

    @InjectView(R.id.add)
    TextView mAddContact;

    @InjectView(R.id.contact_list)
    ExpandableListView mContactList;

    private Context mContext;
    private Handler mHandler;
    private ContactExpendListAdapter mListAdapter;
    private List<FriendListData> mListData = null;

    public ContactView(Context context) {
        this(context, null);
    }

    public ContactView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContactView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        View mView = LayoutInflater.from(context).inflate(R.layout.contact_view,null);
        ButterKnife.inject(this, mView);

        addView(mView);

        InitView();

    }
    public void setHandler(Handler h){
        mHandler = h;
    }

    private void InitView(){
        mUserImage.setOnClickListener(this);
        mAddContact.setOnClickListener(this);

        InitData();
        mListAdapter = new ContactExpendListAdapter(mContext, mListData);
        mContactList.setAdapter(mListAdapter);
        mContactList.setGroupIndicator(null);
        mContactList.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id){
                onClickContactItem(groupPosition,childPosition);
                return true;

            }
        });

    }

    private void InitData(){
        mListData = new ArrayList<FriendListData>();

        List<FriendEntity> childList1= new ArrayList<FriendEntity>();
        FriendEntity member1 = new FriendEntity();
        member1.setName("name1");
        member1.setIcnPath("");//mContext.getResources().getDrawable(R.mipmap.user_img);
        childList1.add(member1);

        FriendEntity member2 = new FriendEntity();
        member2.setName("name2");
        member2.setIcnPath(""); //= mContext.getResources().getDrawable(R.mipmap.user_img);
        childList1.add(member2);

        FriendListData data1 = new FriendListData();
        data1.setGroupName("group1");
        data1.setFriendChildList(childList1);
        mListData.add(data1);
        ///////////////////////////
        List<FriendEntity> childList2= new ArrayList<FriendEntity>();
        FriendEntity member21 = new FriendEntity();
        member21.setName("name21");
        member21.setIcnPath("");//mContext.getResources().getDrawable(R.mipmap.user_img);
        childList2.add(member21);

        FriendEntity member22 = new FriendEntity();
        member22.setName("name22");
        member22.setIcnPath(""); //= mContext.getResources().getDrawable(R.mipmap.user_img);
        childList2.add(member22);

        FriendListData data2 = new FriendListData();
        data2.setGroupName("group2");
        data2.setFriendChildList(childList2);
        mListData.add(data1);
        mListData.add(data2);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.user_img:{
                enterUserInfoFunc();
                break;
            }
            case R.id.add:{
                enterAddContactFunc();
                break;
            }
        }
    }

    private void enterUserInfoFunc(){
        //点击头像 ，进入用户信息模块
        Intent intent = new Intent(mContext, UserInfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private void enterAddContactFunc(){
        //点击添加，进入添加联系人
    }
    private void onClickContactItem(int groupPos, int childPos){
        //点击contact 中的child 联系人。进入与该用户聊天模块
        Message msg=new Message();
        msg.what= DefindMessage.ENTER_ACTIVITY_INSTANT_CHAT_VIEW;
        mHandler.sendMessage(msg);

    }
}
