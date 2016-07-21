package com.farina.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.farina.data.InstantChatEntity;
import com.farina.data.InstantChatListData;
import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import java.util.List;

/**
 * Created by FarinaZhang on 2016/7/13.
 */
public class InstantChatListAdapter  extends BaseAdapter {
    private Context mContext;
    private InstantChatListData mData;
    private List<InstantChatEntity> mListData;

    public InstantChatListAdapter(Context context, InstantChatListData  data){
        mContext = context;
        mData = data;
        mListData = data.message;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView == null){
            LayoutInflater layoutIn=LayoutInflater.from(mContext);
            convertView = layoutIn.inflate(R.layout.instant_chat_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.item = (LinearLayout) convertView.findViewById(R.id.item);
            viewHolder.icn=(ViaImageView)convertView.findViewById(R.id.user_img);
            viewHolder.message=(TextView)convertView.findViewById(R.id.message);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        InstantChatEntity entity = mListData.get(position);
        if(entity.beSend){
            viewHolder.icn.setImageDrawable(mData.userIcn);
            viewHolder.message.setText(entity.message);
            viewHolder.item.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            LinearLayout.LayoutParams  lp = (LinearLayout.LayoutParams)viewHolder.message.getLayoutParams();
            lp.leftMargin=0;
            lp.rightMargin=20;
            viewHolder.message.setLayoutParams(lp);
            viewHolder.message.setBackgroundColor(Color.rgb(181,230,29));
        }else{
            viewHolder.icn.setImageDrawable(mData.sideUserIcn);
            viewHolder.message.setText(entity.message);
            viewHolder.item.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            LinearLayout.LayoutParams  lp = (LinearLayout.LayoutParams)viewHolder.message.getLayoutParams();
            lp.leftMargin=20;
            lp.rightMargin=0;
            viewHolder.message.setLayoutParams(lp);
            viewHolder.message.setBackgroundColor(Color.rgb(255,128,64));
        }
        return convertView;
    }
    public void updateData(List<InstantChatEntity>  data){
        mListData = data;
        this.notifyDataSetInvalidated();
    }

    private class ViewHolder{
        LinearLayout item;
        ViaImageView icn;
        TextView message;
    }
}
