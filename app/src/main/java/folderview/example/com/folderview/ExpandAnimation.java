package folderview.example.com.folderview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

/**
 * Created by niuxuan on 16-10-17.
 */
public class ExpandAnimation extends Animation{

    int height;
    View target;
    public ExpandAnimation(View targetView,int defaultHeight){
        height = defaultHeight;
        target = targetView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) target.getLayoutParams();
        if(interpolatedTime>0) {
            layoutParams.bottomMargin = height;
        }
        target.setLayoutParams(layoutParams);
        target.getParent().requestLayout();
    }
}
