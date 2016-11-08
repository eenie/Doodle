package org.eenie.doodle;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by Eenie on 2016/11/8 at 15:31
 * Email: 472279981@qq.com
 * Des:
 */

public class DoodleDrawable extends Doodle {
    private Drawable mDrawable;
    private Rect mDrawableBound;

    public DoodleDrawable(Drawable drawable) {
        mDrawable = drawable;
        mMatrix = new Matrix();
        mDrawableBound = new Rect(0, 0, getWidth(), getHeight());
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.concat(mMatrix);
        mDrawable.setBounds(mDrawableBound);
        mDrawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public int getWidth() {
        return mDrawable.getIntrinsicWidth();
    }

    @Override
    public int getHeight() {
        return mDrawable.getIntrinsicHeight();
    }

    @Override
    public void release() {
        super.release();
        if (mDrawable != null) {
            mDrawable = null;
        }
    }
}
