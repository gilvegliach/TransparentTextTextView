/*
 * Copyright (C) 2016 Gil Vegliach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        if (mBackground == bg) {
            return;
        }

        mBackground = bg;

        // Will always draw drawable using view bounds. This might be a
        // problem if the drawable should force the view to be bigger, e.g.
        // the view sets its dimensions to wrap_content and the drawable
        // is larger than the text.
        int w = getWidth();
        int h = getHeight();
        if (mBackground != null && w != 0 && h != 0) {
            mBackground.setBounds(0, 0, w, h);
        }
        requestLayout();
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
        mMaskBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ALPHA_8);
        mMaskCanvas = new Canvas(mMaskBitmap);
        if (mBackground != null) {
            mBackground.setBounds(0, 0, w, h);
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        // Nothing to draw
        if (mBackground == null) return;

        drawMask();
        drawBackground();
        canvas.drawBitmap(mBackgroundBitmap, 0.f, 0.f, null);
    }

    // draw() calls onDraw() leading to stack overflow
    @SuppressLint("WrongCall")
    private void drawMask() {
        clear(mMaskCanvas);
        super.onDraw(mMaskCanvas);
    }

    private void drawBackground() {
        clear(mBackgroundCanvas);
        mBackground.draw(mBackgroundCanvas);
        mBackgroundCanvas.drawBitmap(mMaskBitmap, 0.f, 0.f, mPaint);
    }

    private static void clear(Canvas canvas) {
        canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
    }
}
