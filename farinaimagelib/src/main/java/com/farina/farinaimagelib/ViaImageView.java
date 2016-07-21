package com.farina.farinaimagelib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
public class ViaImageView extends ImageView {
    private int shapeType=-1;
    private int borderWidth=0; //the image 's border width
    private int borderColor= Color.BLACK; //the image 's border color
    private boolean useDefaultStyle =false; //draw a common imageview
    private static final Xfermode MASK_XFERMODE;
    private  int   AnimType=-1;  //动画类型
    private int  mWidth;    //图片的宽度
    private int  mHeight;   //图片的高度

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public static enum Shape {circle,triangle,star,hexagonal,rectangle};

    public ViaImageView(Context context){
        super(context);
        init(context, null, 0);
    }
    public ViaImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context, attrs, 0);
    }
    public ViaImageView(Context context,AttributeSet attrs,int defStyle){
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

   public void init(Context context,AttributeSet attrs,int defStyle){


        if(attrs!=null){
            TypedArray a = getContext().obtainStyledAttributes(
                    attrs,R.styleable.viaImage, defStyle, 0);
            shapeType = (int)a.getInteger(R.styleable.viaImage_shapeType,shapeType);
            borderWidth=(int)a.getDimension(R.styleable.viaImage_borderWidth,borderWidth);
            borderColor = a.getColor(R.styleable.viaImage_borderColor, borderColor);

            a.recycle();

        }
   }

    /**
    *外部接口
    *设置ImageView的属性，包括形状，边框颜色，边框宽度
    **/
    public void setImageViewProperty(int shapeType,int width,int color){
        this.shapeType = shapeType;
        this.borderWidth = width;
        this.borderColor = color;

        postInvalidate();
    }

    public void setImageViewShape(int shapeType){
        this.shapeType = shapeType;

        postInvalidate();
    }

    public void setImageBorderWidth(int width){
        this.borderWidth = width;
        postInvalidate();
    }
    public void setImageBorderColor(int color){
        this.borderColor = color;
        postInvalidate();
    }


    /** 外部接口
     * 设置是否以默认格式绘制，普通格式
    */
    public void setDrawCommonImage(boolean isCommon){
        this.useDefaultStyle=isCommon;
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

       mWidth=getWidth();
       mHeight=getHeight();

       int layer=canvas.saveLayer(0.0f,0.0f,mWidth,mHeight,null,Canvas.ALL_SAVE_FLAG);
       localDrawable.setBounds(0,0,mWidth,mHeight);
       /*将drawable绑定到bitmap（this.mask）上面（drawable 只能通过bitmap显示出来）*/
       localDrawable.draw(canvas);

       createShapeBitmap(canvas);

       /*将画布复制到layer上*/
       canvas.restoreToCount(layer);

       if(borderWidth!=0){
           drawBorder(canvas);
       }

   }
    private void createShapeBitmap(Canvas canvas){
        Paint mypaint;

        mypaint = new Paint();
        mypaint.setFilterBitmap(false);
        mypaint.setAntiAlias(true);
        mypaint.setXfermode(MASK_XFERMODE);

            Bitmap.Config localConfig=Bitmap.Config.ARGB_8888;
            Bitmap localBitmap=Bitmap.createBitmap(mWidth,mHeight,localConfig);
            Canvas localCanvas=new Canvas(localBitmap);
            Paint localPaint = new Paint();
            localPaint.setAntiAlias(true);
            int padding=borderWidth;
            int radius = ((mWidth>mHeight)?mHeight:mWidth)/2-padding;

            switch(shapeType){
                case 0://圆形；
                {
                    localCanvas.drawCircle(mWidth/2,mHeight/2, radius, localPaint);
                    break;
                }
                case 1://三角形；
                {
                    Path path = new Path();
                    path.moveTo(padding, padding);
                    path.lineTo(mWidth / 2, (float)(mWidth*Math.cos(degree2Radian(30))-padding));
                    path.lineTo(mWidth-padding, padding);

                    path.close();
                    localCanvas.drawPath(path, localPaint);
                    break;
                }
                case 2://五角星形；
                {
                    Path path = new Path();
                    float radian = degree2Radian(36);// 36为五角星的角度
                    float radius_in = (float) (radius * Math.sin(radian / 2) / Math
                            .cos(radian)); // 中间五边形的半径

                    path.moveTo((float) (radius * Math.cos(radian / 2)), 0);// 此点为多边形的起点
                    path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                                    * Math.sin(radian)),
                            (float) (radius - radius * Math.sin(radian / 2)));
                    path.lineTo((float) (radius * Math.cos(radian / 2) * 2),
                            (float) (radius - radius * Math.sin(radian / 2)));
                    path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                                    * Math.cos(radian / 2)),
                            (float) (radius + radius_in * Math.sin(radian / 2)));
                    path.lineTo(
                            (float) (radius * Math.cos(radian / 2) + radius
                                    * Math.sin(radian)), (float) (radius + radius
                                    * Math.cos(radian)));
                    path.lineTo((float) (radius * Math.cos(radian / 2)),
                            (float) (radius + radius_in));
                    path.lineTo(
                            (float) (radius * Math.cos(radian / 2) - radius
                                    * Math.sin(radian)), (float) (radius + radius
                                    * Math.cos(radian)));
                    path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                                    * Math.cos(radian / 2)),
                            (float) (radius + radius_in * Math.sin(radian / 2)));
                    path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
                    path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                                    * Math.sin(radian)),
                            (float) (radius - radius * Math.sin(radian / 2)));

                    path.close();// 使这些点构成封闭的多边形
                    localCanvas.drawPath(path, localPaint);
                    break;
                }
                case 3://正六边形；
                {
                    Path path= new Path();
                    float radian = degree2Radian(30);
                    path.moveTo((float)(radius*Math.sin(radian)), 0);
                    path.lineTo((float)(radius*Math.sin(radian)+radius), 0);
                    path.lineTo((float)(2*radius), (float)(radius*Math.cos(radian)) );
                    path.lineTo((float)(radius*Math.sin(radian)+radius),(float)(2*(radius*Math.cos(radian))));
                    path.lineTo((float)(radius*Math.sin(radian)),(float)(2*(radius*Math.cos(radian))));
                    path.lineTo(0,(float)(radius*Math.cos(radian)));
                    path.close();
                    localCanvas.drawPath(path,localPaint);
                    break;
                }
                default://方形
                {
                    int realWidth = (mWidth>mHeight)?mHeight:mWidth;
                    int x = padding +(mWidth-realWidth)/2;
                    int y = padding +(mHeight-realWidth)/2;
                    RectF localRectF=new RectF(x, y, realWidth-padding*2, realWidth-padding*2);
                    localCanvas.drawRect(localRectF, localPaint);
                    break;
                }
            }


        /*将bitmap 画到canvas上*/
        canvas.drawBitmap(localBitmap,0.0f,0.0f,mypaint);
    }

    //画边框
    private void drawBorder(Canvas canvas){
        Paint localPaint = new Paint();
        localPaint.setColor(borderColor);
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setStrokeWidth(borderWidth);
        localPaint.setAntiAlias(true);

        int radius=((mWidth>mHeight)?mHeight:mWidth)/2-borderWidth/2;
        int padding = borderWidth/2;
        switch(shapeType){
            case 0://圆形；
            {
                canvas.drawCircle(mWidth/2, mHeight/2,radius,localPaint);
                break;
            }
            case 1://三角形；
            {
                Path path = new Path();
                path.moveTo(padding, padding);
                path.lineTo(mWidth / 2, (float)(mWidth*Math.cos(degree2Radian(30))-padding));
                path.lineTo(mWidth-padding, padding);

                path.close();
                canvas.drawPath(path, localPaint);
                break;
            }
            case 2://五角星形；
            {
                Path path = new Path();
                float radian = degree2Radian(36);// 36为五角星的角度
                float radius_in = (float) (radius * Math.sin(radian / 2) / Math
                        .cos(radian)); // 中间五边形的半径

                path.moveTo((float) (radius * Math.cos(radian / 2)), 0);// 此点为多边形的起点
                path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                                * Math.sin(radian)),
                        (float) (radius - radius * Math.sin(radian / 2)));
                path.lineTo((float) (radius * Math.cos(radian / 2) * 2),
                        (float) (radius - radius * Math.sin(radian / 2)));
                path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                                * Math.cos(radian / 2)),
                        (float) (radius + radius_in * Math.sin(radian / 2)));
                path.lineTo(
                        (float) (radius * Math.cos(radian / 2) + radius
                                * Math.sin(radian)), (float) (radius + radius
                                * Math.cos(radian)));
                path.lineTo((float) (radius * Math.cos(radian / 2)),
                        (float) (radius + radius_in));
                path.lineTo(
                        (float) (radius * Math.cos(radian / 2) - radius
                                * Math.sin(radian)), (float) (radius + radius
                                * Math.cos(radian)));
                path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                                * Math.cos(radian / 2)),
                        (float) (radius + radius_in * Math.sin(radian / 2)));
                path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
                path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                                * Math.sin(radian)),
                        (float) (radius - radius * Math.sin(radian / 2)));

                path.close();// 使这些点构成封闭的多边形
                canvas.drawPath(path, localPaint);
                break;
            }
            case 3://正六边形；
            {
                Path path= new Path();
                float radian = degree2Radian(30);
                path.moveTo((float)(radius*Math.sin(radian)), 0);
                path.lineTo((float)(radius*Math.sin(radian)+radius), 0);
                path.lineTo((float)(2*radius), (float)(radius*Math.cos(radian)) );
                path.lineTo((float)(radius*Math.sin(radian)+radius),(float)(2*(radius*Math.cos(radian))));
                path.lineTo((float)(radius*Math.sin(radian)),(float)(2*(radius*Math.cos(radian))));
                path.lineTo(0,(float)(radius*Math.cos(radian)));
                path.close();
                canvas.drawPath(path,localPaint);
                break;
            }
            default://方形
            {
                int realWidth = (mWidth>mHeight)?mHeight:mWidth-borderWidth;
                int x = padding +(mWidth-realWidth)/2;
                int y = padding +(mHeight-realWidth)/2;
                RectF localRectF=new RectF(x, y, realWidth-padding*2, realWidth-padding*2);
                canvas.drawRect(localRectF, localPaint);
                break;
            }
        }
    }

    //将角度转化为弧度
    private float degree2Radian(int degree) {
        // TODO Auto-generated method stub
        return (float) (Math.PI * degree / 180);
    }



}
