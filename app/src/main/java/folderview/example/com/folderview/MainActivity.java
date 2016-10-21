package folderview.example.com.folderview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    private FolderView fv;
        private RelativeLayout view2,rl1,rl2;
        private LinearLayout view1;

        private FolderCardAnimation folderCardAnimation;

        private RelativeLayout.LayoutParams layoutParams;

        private int fvheight;

        private boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fv = (FolderView) findViewById(R.id.fv);

        view1 = (LinearLayout) findViewById(R.id.view1);
        view2 = (RelativeLayout) findViewById(R.id.view2);

        rl1 = (RelativeLayout) findViewById(R.id.rl1);
        rl2 = (RelativeLayout) findViewById(R.id.rl2);

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){

                }else {
                    FolderCardAnimation folderCardAnimation = new FolderCardAnimation(0,-180,view2.getWidth()/2,view2.getHeight(),fv);
                    folderCardAnimation.setDuration(500);
                    folderCardAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    folderCardAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            int left = 0;
                            int top = view2.getHeight();
                            int width = view2.getWidth();
                            int height = view2.getHeight()*2;
                            Log.i("layout",left+":"+top+":"+width+":"+height);
                            view2.setX(0);
                            view2.setY(top);
                            view2.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    view2.startAnimation(folderCardAnimation);
                }
            }
        });

    }
}
