package com.farina.farinaimagelib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by FarinaZhang on 2016/5/25.
 */
public class CircleImageView extends ImageView {
    private int borderWidth=0; //the image 's border width
    private int borderColor= Color.BLACK; //the image 's border color
    private boolean useDefaultStyle =false;//draw a common imageview
    private Paint mPaint=null;
    private Bitmap mBitmap=null;
    private static final Xfermode MASK_XFERMODE;

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public CircleImageView(Context context){
        super(context);
        //init(context, null, 0);
    }
    public CircleImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        //init(context, attrs, 0);
    }
    public CircleImageView(Context context,AttributeSet attrs,int defStyle){
        super(context, attrs, defStyle);
        //init(context, attrs, defStyle);
    }

  /* public void init(Context context,AttributeSet attrs,int defStyle){


        if(attrs!=null){
            TypedArray a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.CircularImage, defStyle, 0);

            borderWidth=5;//(int)a.getDimension(R.styleable.CircularImage_border_width,borderWidth);
            borderColor =Color.RED;// a.getColor(R.styleable.CircularImage_border_color, borderColor);

            a.recycle();

        }
   }*/

    public void setDrawCommonImage(boolean isCommon){
        useDefaultStyle=isCommon;
    }

    public void setBorderWidth(int width){
        borderWidth = width;
    }

    public void setBorderColor(int color){
        borderColor = color;
    }
   @Override
   protected void onDraw(Canvas canvas){
       if(useDefaultStyle){
           super.onDraw(canvas);
           return;
       }
       final Drawable localDrawable=getDrawable();
       if(localDrawable==null)return;
       if(localDrawable instanceof NinePatchDrawable)return;

       if(mPaint==null){
           mPaint = new Paint();
           mPaint.setFilterBitmap(false);
           mPaint.setAntiAlias(true);
           mPaint.setXfermode(MASK_XFERMODE);
       }

       final int width=getWidth();
       final int height=getHeight();

       int layer=canvas.saveLayer(0.0f,0.0f,width,height,null,Canvas.ALL_SAVE_FLAG);
       localDrawable.setBounds(0,0,width,height);
       /*将drawable绑定到bitmap（this.mask）上面（drawable 只能通过bitmap显示出来）*/
       localDrawable.draw(canvas);

       if((this.mBitmap ==null)||(this.mBitmap.isRecycled()==true)){
           mBitmap = creatOvalBitmap(width,height);
       }

       /*将bitmap 画到canvas上*/
       canvas.drawBitmap(this.mBitmap,0.0f,0.0f,this.mPaint);
       /*将画布复制到layer上*/
       canvas.restoreToCount(layer);

       if(borderWidth!=0){
           drawBorder(canvas,width,height);
       }
       canvas =null;


   }
    private Bitmap creatOvalBitmap(int width,int height){
        Bitmap.Config localConfig=Bitmap.Config.ARGB_8888;
        Bitmap localBitmap=Bitmap.createBitmap(width,height,localConfig);
        Canvas localCanvas=new Canvas(localBitmap);
        Paint localPaint = new Paint();
        localPaint.setAntiAlias(true);
        int padding=borderWidth;
        int radio = width/2-padding;
        RectF localRectF=new RectF(padding, padding, width-padding*2, height-padding*2);
        localCanvas.drawCircle(width/2,height/2, radio, localPaint);
        localCanvas=null;
        return localBitmap;


    }
    private void drawBorder(Canvas canvas, int width,int height){
        Paint localPaint = new Paint();
        localPaint.setColor(borderColor);
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setStrokeWidth(borderWidth);
        localPaint.setAntiAlias(true);

        int radio= width/2-borderWidth/2;
        canvas.drawCircle(width/2, height/2,radio,localPaint);
    }
}
