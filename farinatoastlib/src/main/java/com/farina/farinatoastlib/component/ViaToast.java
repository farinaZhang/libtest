package com.farina.farinatoastlib.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farina.farinatoastlib.R;
import com.farina.farinatoastlib.data.ViaToastPoperty;

public class ViaToast extends Toast  {
    private ViaToastPoperty mPoperty;
    private Context mContext;
    private TextView mTitle;
    private ImageView mIcon;
    private TextView mMessage;

    public ViaToast(Context context){
        super(context);
        mContext = context;
        mPoperty = new ViaToastPoperty();
    }

    //设置Toast 属性
    public void setViaToastViewProperty (String sTitle, String sMessage, int imgId){
        mPoperty.title = sTitle;
        mPoperty.message = sMessage;
        mPoperty.bitmapID = imgId;

    }

    //绘制Toast
    public void drawToast (){
        LayoutInflater inf = LayoutInflater.from(mContext);
        View view = inf.inflate(R.layout.via_toast_view,null);
        mTitle = (TextView)view.findViewById(R.id.title);
        mIcon = (ImageView)view.findViewById(R.id.icon);
        mMessage = (TextView)view.findViewById(R.id.message);
        this.setView(view);

        if(mPoperty.title!=null && mPoperty.title.length()>0){
            // 含title
            mTitle.setVisibility(View.VISIBLE);
            if(mPoperty.bitmapID>0){
                //含图标
                mIcon.setVisibility(View.VISIBLE);
            }else{
                //不含图标
                mIcon.setVisibility(View.INVISIBLE);
            }
        }else{
            //不含title
            mTitle.setVisibility(View.INVISIBLE);
            if(mPoperty.bitmapID>0){
                //含图标
                mIcon.setVisibility(View.VISIBLE);
            }else{
                //不含图标
                mIcon.setVisibility(View.INVISIBLE);
            }
        }

        this.setDuration(Toast.LENGTH_SHORT);
        this.show();
    }


}