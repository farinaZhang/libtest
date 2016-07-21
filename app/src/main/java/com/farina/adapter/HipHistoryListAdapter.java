package com.farina.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farina.data.HipHistoryItemEntity;
import com.farina.farinaimagelib.ViaImageView;
import com.farina.libtest.R;

import java.util.List;

/**
 * Created by FarinaZhang on 2016/7/12.
 */
public class HipHistoryListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HipHistoryItemEntity> mListData;

    public HipHistoryListAdapter(Context context, List<HipHistoryItemEntity> data){
        mContext = context;
        mListData = data;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView == null){
            LayoutInflater layoutIn=LayoutInflater.from(mContext);
            convertView = layoutIn.inflate(R.layout.hip_history_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.icn=(ViaImageView)convertView.findViewById(R.id.user_img);
            viewHolder.name=(TextView)convertView.findViewById(R.id.name);
            viewHolder.time=(TextView)convertView.findViewById(R.id.time);
            viewHolder.hipString=(TextView)convertView.findViewById(R.id.message);
            viewHolder.newCount = (TextView)convertView.findViewById(R.id.new_count);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        HipHistoryItemEntity entity = mListData.get(position);
        viewHolder.icn.setImageDrawable(entity.pic);
        viewHolder.name.setText(entity.name);
        viewHolder.time.setText(entity.time);
        viewHolder.hipString.setText(entity.message);
        viewHolder.newCount.setText(entity.newCount);
        if(entity.newCount.equals("0")){
            viewHolder.newCount.setVisibility(View.GONE);
        }else{
            viewHolder.newCount.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    private class ViewHolder{
        ViaImageView icn;
        TextView name;
        TextView time;
        TextView hipString;
        TextView newCount;
    }
}
