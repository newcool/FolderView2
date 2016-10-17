package folderview.example.com.folderview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private RelativeLayout tv,rl1,rl2;
    private LinearLayout ll1;
    private FolderCardAnimation folderCardAnimation;

    private RelativeLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (RelativeLayout) findViewById(R.id.tv);
        rl1 = (RelativeLayout) findViewById(R.id.rl1);
        rl2 = (RelativeLayout) findViewById(R.id.rl2);

        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation(folderCardAnimation,tv,-180);
            }
        });
    }


    private void startAnimation(FolderCardAnimation animation, final View llyt_item, int degree) {
        if (animation != null) {
            animation.setCanContentChange();
            llyt_item.startAnimation(animation);
        } else {
            int width = llyt_item.getWidth() / 2;
            int height = llyt_item.getHeight();

            ExpandAnimation expandAnimation = new ExpandAnimation(ll1,height);
            ll1.startAnimation(expandAnimation);
            ll1.setVisibility(View.VISIBLE);

            animation = new FolderCardAnimation(0, degree, width, height);
            animation.setDuration(1000);
            animation.setFillAfter(true);
            animation.setRepeatCount(0);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
//                    ll1.setVisibility(View.VISIBLE);
                    layoutParams = (RelativeLayout.LayoutParams) ll1.getLayoutParams();
                    layoutParams.bottomMargin = rl2.getHeight();
                    ll1.setLayoutParams(layoutParams);
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    rl2.setVisibility(View.VISIBLE);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                    ((FolderCardAnimation)animation).setCanContentChange();
                }
            });
            animation.setOnContentChangeListener(new FolderCardAnimation.OnContentChangeListener() {
                @Override
                public void contentChange(float degree) {
                    if(degree>-90){
                        rl2.setVisibility(View.VISIBLE);
                    }else {
                        layoutParams.bottomMargin = (int) (llyt_item.getHeight()-Math.cos(degree)*llyt_item.getHeight());
                        ll1.setLayoutParams(layoutParams);
//                        rl2.setVisibility(View.INVISIBLE);
                    }
                }
            });
            llyt_item.startAnimation(animation);
        }
    }
}
