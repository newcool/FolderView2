package folderview.example.com.folderview;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

/**
 * Created by niuxuan on 16-10-17.
 */
public class ExpandAnimation extends Animation{

    View target,curView;
    private int height;
    public ExpandAnimation(View targetView,View curView){
        super();
        this.target = targetView;
        this.curView = curView;
        height = curView.getHeight();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.height = height;
        Log.i("height",width+":"+height+":"+parentWidth+":"+parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        RelativeLayout.LayoutParams tarParams = (RelativeLayout.LayoutParams) target.getLayoutParams();
        RelativeLayout.LayoutParams curParams = (RelativeLayout.LayoutParams) curView.getLayoutParams();

        curParams.height = (int) ((tarParams.height -height)*interpolatedTime+height);

        Log.i("height11",curParams.height+"");
        curView.setLayoutParams(curParams);

        curView.requestLayout();
    }
}
