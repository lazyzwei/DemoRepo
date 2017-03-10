package com.obito.statusbardemo;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class StatusBarPlaceHolder extends RelativeLayout{

    private int statusHeight;

    public StatusBarPlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatusBarPlaceHolder(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            statusHeight = getStatusHeight(context);
        }else {
            statusHeight = 0;
        }
    }

    private int getStatusHeight(Context context){
        int result = 0;
        int resultId = context.getResources().getIdentifier("status_bar_height","dimen","android");
        if (resultId > 0){
            result = context.getResources().getDimensionPixelOffset(resultId);
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(statusHeight,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, height);
    }
}
