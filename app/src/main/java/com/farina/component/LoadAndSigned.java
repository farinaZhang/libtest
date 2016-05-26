package com.farina.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.farina.farinaimagelib.CircleImageView;
import com.farina.libtest.R;

/**
 * TODO: document your custom view class.
 */
public class LoadAndSigned extends LinearLayout {
    private Context context;
    private CircleImageView userImg;

    public LoadAndSigned(Context context) {
        super(context);
        init(context,null, 0);
    }

    public LoadAndSigned(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs, 0);
    }

    public LoadAndSigned(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs, defStyle);
    }

    private void init(Context context,AttributeSet attrs, int defStyle) {
        // Load attributes
        this.context = context;
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.load_and_signed, null);

        addView(view);

        userImg = (CircleImageView)view.findViewById(R.id.user_img);
        userImg.setBorderWidth(5);
        userImg.setBorderColor(Color.parseColor("#ff0000"));
    }

}
