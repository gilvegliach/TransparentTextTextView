package it.gilvegliach.android.transparenttexttextview;

import android.annotation.SuppressLint;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;

import android.widget.TextView;

public class TransparentTextTextView extends TextView {
    private Bitmap mMaskBitmap;
    private Canvas mMaskCanvas;
    private Paint mPaint;
    private Drawable mBackground;
    private Bitmap mBackgroundBitmap;
    private Canvas mBackgroundCanvas;
    private boolean mSetBoundsOnSizeAvailable;

    public TransparentTextTextView(final Context context) {
        super(context);
        init();
    }

    public TransparentTextTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        super.setTextColor(Color.BLACK);
        super.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    @Deprecated
    public void setBackgroundDrawable(final Drawable bg) {
        mBackground = bg;

        int w = getWidth();
        int h = getHeight();

        // Layout has not run
        if (w == 0 || h == 0) {
            mSetBoundsOnSizeAvailable = true;
            return;
        }

        mBackground.setBounds(0, 0, w, h);
        invalidate();
    }

    @Override
    public void setBackgroundColor(final int color) {
        setBackgroundDrawable(new ColorDrawable(color));
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBackgroundBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mBackgroundCanvas = new Canvas(mBackgroundBitmap);
        mMaskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mMaskCanvas = new Canvas(mMaskBitmap);

        if (mSetBoundsOnSizeAvailable) {
            mBackground.setBounds(0, 0, w, h);
            mSetBoundsOnSizeAvailable = false;
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        drawMask();
        drawBackground();
        canvas.drawBitmap(mBackgroundBitmap, 0.f, 0.f, null);
    }

    private void drawBackground() {
        mBackground.draw(mBackgroundCanvas);
        mBackgroundCanvas.drawBitmap(mMaskBitmap, 0.f, 0.f, mPaint);
    }

    // draw() calls onDraw() leading to stack overflow
    @SuppressLint("WrongCall")
    private void drawMask() {
        mMaskCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        super.onDraw(mMaskCanvas);
    }
}
