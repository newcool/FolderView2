package folderview.example.com.folderview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by niuxuan on 16-10-14.
 */
public class FolderCardAnimation extends Animation{

    private final float mFromDegrees;

    private final float mToDegrees;

    private final float mCenterX;

    private final float mCenterY;

    private Camera mCamera;
    //用于确定内容是否开始变化
    private boolean isContentChange = false;
    private OnContentChangeListener listener;
    public FolderCardAnimation(float fromDegrees, float toDegrees, float centerX, float centerY) {

        mFromDegrees = fromDegrees;

        mToDegrees = toDegrees;

        mCenterX = centerX;

        mCenterY = centerY;
    }
    ////用于确定内容是否开始变化  在动画开始之前调用
    public void setCanContentChange(){
        this.isContentChange = false;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        final float fromDegrees = mFromDegrees;

//        Log.i("interpolatedTime", String.valueOf(interpolatedTime));
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        Log.i("degrees", String.valueOf(degrees));

        final float centerX = mCenterX;

        final float centerY = mCenterY;

        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();

                if (listener != null) {
                    listener.contentChange(degrees);
                }
  /*
            if (degrees>0) {
                degrees = 270 + degrees - 90;
            }else if (degrees<0){
                degrees = -270+(degrees+90);
            }
        }*/

        camera.rotateX(degrees);

        camera.getMatrix(matrix);

        camera.restore();

        matrix.preTranslate(-centerX, -centerY);

        matrix.postTranslate(centerX, centerY);

    }

    public void setOnContentChangeListener(OnContentChangeListener listener) {
        this.listener = listener;
    }

    public interface OnContentChangeListener{
        void contentChange(float degree);
    }
}
