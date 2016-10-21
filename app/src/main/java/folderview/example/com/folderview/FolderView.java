package folderview.example.com.folderview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by niuxuan on 16-10-14.
 */
public class FolderView extends RelativeLayout{


    public FolderView(Context context) {
        super(context);
        init();
    }

    public FolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FolderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 遍历所有子视图
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            // 获取在onMeasure中计算的视图尺寸
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();

            childView.layout(0, 0, measuredWidth,measureHeight);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("111",heightSize+":"+heightMode);

        int width,height = 0;
      /*  if(heightMode == MeasureSpec.EXACTLY){
            height = getChildAt(1).getMeasuredHeight();
            Log.i("height",height+"");
        }*/
        measureChild(getChildAt(0),widthMeasureSpec,heightMeasureSpec);
        measureChild(getChildAt(1),widthMeasureSpec,heightMeasureSpec);

        if(MeasureSpec.EXACTLY == heightMode){
            height = heightSize;
        }else {
            height = getChildAt(1).getMeasuredHeight();
        }
        width = getChildAt(1).getMeasuredWidth();
        setMeasuredDimension(width,height);
    }

    private void init() {

    }


}
