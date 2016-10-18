package folderview.example.com.folderview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private RelativeLayout tv,rl1,rl2,rl_con;
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
        rl_con = (RelativeLayout) findViewById(R.id.rl_con);

        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startAnimation(folderCardAnimation,tv,-180);
/*                ll1.setVisibility(View.VISIBLE);
                ExpandAnimation expandAnimation = new ExpandAnimation(ll1,rl_con);

                expandAnimation.setFillAfter(true);
                Log.i("ss","ssss");
                expandAnimation.setDuration(3000);
                ll1.startAnimation(expandAnimation);*/

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

            final int conHeight = rl_con.getHeight();

            animation = new FolderCardAnimation(0, degree, width, height);
            animation.setDuration(3000);
            animation.setFillAfter(true);
            animation.setRepeatCount(0);
            layoutParams = (RelativeLayout.LayoutParams) ll1.getLayoutParams();
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
//                    layoutParams = (RelativeLayout.LayoutParams) ll1.getLayoutParams();
//                    layoutParams.bottomMargin = rl2.getHeight();
//                    ll1.setLayoutParams(layoutParams);
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
//                    layoutParams = (RelativeLayout.LayoutParams) ll1.getLayoutParams();
//                    if(degree>-90){
//                        layoutParams.height = (int) Math.cos(degree);
//                    }else {
//                        layoutParams.height = (int) (llyt_item.getHeight()-Math.cos(degree)*llyt_item.getHeight());
//                    }
//                    ll1.setLayoutParams(layoutParams);

                    if(degree>-90){
                        rl2.setVisibility(View.VISIBLE);
                    }else {
                        rl2.setVisibility(View.INVISIBLE);

                        RelativeLayout.LayoutParams rl_conParams = (RelativeLayout.LayoutParams) rl_con.getLayoutParams();
                        rl_conParams.height = (int) (conHeight-Math.cos(degree*Math.PI/180)*conHeight);
                        Log.i("height",rl_conParams.height+"");
                        rl_con.setLayoutParams(rl_conParams);
                    }
                }
            });
            llyt_item.startAnimation(animation);
        }
    }
}
