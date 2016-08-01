package com.df.percentlayout.androidpercentlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/8/1.
 */
public class PercentLayout extends RelativeLayout {
    public PercentLayout(Context context) {
        super(context);
    }

    public PercentLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

    }
    //测量容器宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height=View.MeasureSpec.getSize(heightMeasureSpec);
        //测量出子控件的宽高
        int childcount=getChildCount();
        for (int i=0;i<childcount;i++){
            View child=this.getChildAt(i);
            LayoutParams params= (LayoutParams) child.getLayoutParams();
            float widthPercent=0;
            float heightPercent=0;
            if(params instanceof PercentLayout.LayoutParams){
                widthPercent=((LayoutParams) params).getWidthPercent();
                heightPercent=((LayoutParams) params).getHeightPercent();
                if(widthPercent!=0){
                  params.width= (int) (width*widthPercent);

                }
                if(heightPercent!=0){
                    params.height= (int) (height*heightPercent);

                }
            }

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }


    public  static  class LayoutParams extends RelativeLayout.LayoutParams{
        //定义属性
        private float widthPercent;
        private float heightPercent;

        public float getWidthPercent() {
            return widthPercent;
        }

        public float getHeightPercent() {
            return heightPercent;
        }

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            //解析自己定义的属性
            TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.percentLayout);
             widthPercent=array.getFloat(R.styleable.percentLayout_widthPercent,widthPercent);
             heightPercent=array.getFloat(R.styleable.percentLayout_heightPercent,getHeightPercent());
            array.recycle();

        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }


    }
    //用于对子控件进行布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
