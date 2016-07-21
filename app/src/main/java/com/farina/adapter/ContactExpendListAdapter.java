package com.farina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.farina.data.FriendEntity;
import com.farina.data.FriendListData;
import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import java.util.List;

/**
 * Created by FarinaZhang on 2016/7/5.
 */
public class ContactExpendListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<FriendListData> mListData;


    public ContactExpendListAdapter(Context context, List<FriendListData> data){
        mContext = context;
        mListData = data;
    }
    @Override
    public int getGroupCount(){
        return mListData.size();
    }
    @Override
    public int getChildrenCount(int groupPosition){
        return mListData.get(groupPosition).getFriendChildList().size();
    }
    @Override
    public Object getGroup(int groupPosition){
        return mListData.get(groupPosition).getGroupName();
    }
    @Override
    public Object getChild(int groupPosition, int childPosition){
        return mListData.get(groupPosition).getFriendChildList().get(childPosition);
    }
    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        groupHolder viewHolder =null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_list_group, null);
            viewHolder = new groupHolder();
            viewHolder.icon = (ImageView)convertView.findViewById(R.id.icon);
            viewHolder.groupName = (TextView)convertView.findViewById(R.id.goup_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (groupHolder)convertView.getTag();
        }

        viewHolder.groupName.setText(mListData.get(groupPosition).getGroupName());
        if(isExpanded){
            viewHolder.icon.setImageResource(R.mipmap.arrow_down);
        }else{
            viewHolder.icon.setImageResource(R.mipmap.arrow_up);
        }

        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent){
        memberHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_list_member, null);

            viewHolder= new memberHolder();
            viewHolder.memberImg = (ViaImageView) convertView.findViewById(R.id.member_img);
            viewHolder.memberName = (TextView)convertView.findViewById(R.id.member_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder =(memberHolder)convertView.getTag();
        }
        FriendEntity entity= mListData.get(groupPosition).getFriendChildList().get(childPosition);
        String icnPath = entity.getIcnPath();
        if(icnPath == null||icnPath.length()<=0) {
            viewHolder.memberImg.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.user_img));
        }
        viewHolder.memberName.setText(entity.getName());
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition,
                                     int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    private class memberHolder{
        ViaImageView memberImg;
        TextView memberName;
    }
    private class groupHolder{
        ImageView icon;
        TextView groupName;
    }

}
