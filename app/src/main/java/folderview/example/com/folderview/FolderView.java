package folderview.example.com.folderview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by niuxuan on 16-10-14.
 */
public class FolderView extends ViewGroup{

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
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    private void init() {

    }


}
