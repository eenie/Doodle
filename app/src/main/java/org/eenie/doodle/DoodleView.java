package org.eenie.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Eenie on 2016/11/8 at 14:22
 * Email: 472279981@qq.com
 * Des:涂鸦板
 */

public class DoodleView extends View {


    ArrayList<Doodle> mDoodles = new ArrayList<>();

    Doodle mCurrentDoodle;


    Paint mBorderPaint;
    Paint mDragPointPaint;

    private float mDownX;
    private float mDownY;
    private Matrix mDownMatrix;
    private Matrix mMoveMatrix;



    public DoodleView(Context context) {
        super(context);
    }


    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(Color.WHITE);
        mBorderPaint.setAlpha(160);
        mBorderPaint.setStrokeWidth(5f);


        mDragPointPaint = new Paint();
        mDragPointPaint.setAntiAlias(true);
        mDragPointPaint.setColor(Color.WHITE);
        mDragPointPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mDownMatrix = new Matrix();
        mMoveMatrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Doodle doodle : mDoodles) {
            doodle.draw(canvas);
        }

        if (mCurrentDoodle != null) {
            float[] bitmapPoints = getDoodleRange(mCurrentDoodle);
            float x1 = bitmapPoints[0];
            float y1 = bitmapPoints[1];
            float x2 = bitmapPoints[2];
            float y2 = bitmapPoints[3];
            float x3 = bitmapPoints[4];
            float y3 = bitmapPoints[5];
            float x4 = bitmapPoints[6];
            float y4 = bitmapPoints[7];

//            画边框线
            canvas.drawLine(x1, y1, x2, y2, mBorderPaint);
            canvas.drawLine(x1, y1, x3, y3, mBorderPaint);
            canvas.drawLine(x2, y2, x4, y4, mBorderPaint);
            canvas.drawLine(x4, y4, x3, y3, mBorderPaint);
//            画控制点
            canvas.drawCircle(x1, y1, 20, mDragPointPaint);
            canvas.drawCircle(x2, y2, 20, mDragPointPaint);
            canvas.drawCircle(x3, y3, 20, mDragPointPaint);
            canvas.drawCircle(x4, y4, 20, mDragPointPaint);

        }
    }


    /**
     * 获取贴纸的四个角坐标
     * @param doodle 贴纸
     * @return 4个坐标点
     */
    public float[] getDoodleRange(Doodle doodle) {
        if (doodle == null)
            return new float[8];
        return doodle.getMappedBoundPoints();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = MotionEventCompat.getActionMasked(event);


        switch (action) {
            case MotionEvent.ACTION_DOWN:

                LogUtil.e("按下");

                mDownX = event.getX();
                mDownY = event.getY();

                if (mCurrentDoodle != null) {
                    mDownMatrix.set(mCurrentDoodle.getMatrix());
                }

                break;


            case MotionEvent.ACTION_MOVE:
                if (mCurrentDoodle != null) {
                    mMoveMatrix.set(mDownMatrix);
                    mMoveMatrix.postTranslate(event.getX() - mDownX, event.getY() - mDownY);
//                            mHandlingSticker.getMatrix().reset();
                    mCurrentDoodle.getMatrix().set(mMoveMatrix);
                }
                invalidate();
                break;
        }
        return true;
    }


    public void addDoodle(Drawable drawable) {
        if (drawable != null) {
            mDoodles.add(new DoodleDrawable(drawable));
        } else {
            LogUtil.e("Drawable = null");
        }

        invalidate();
    }

    public void addDoodle(Bitmap bitmap) {
        if (bitmap != null) {

            Doodle doodle = new DoodleDrawable(new BitmapDrawable(getResources(), bitmap));

            mCurrentDoodle = doodle;
            mDoodles.add(doodle);
        } else {
            LogUtil.e("bitmap = null");
        }
        invalidate();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);






    }
}
