package com.niraj.sparkprofile.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.utils.AppConstants;


/**
 * Created by NirajKumar on 10/31/17.
 */

public class AppButtonCFS extends AppCompatButton {


    public AppButtonCFS(Context context) {
        super(context);
    }

    public AppButtonCFS(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public AppButtonCFS(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AppButtonCFS,
                0, 0);
        String textFontFamily;
        try {
            textFontFamily = a.getString(R.styleable.AppButtonCFS_buttonFontFamily);
            if (!AppConstants.trim(textFontFamily, "").isEmpty()) {
                Typeface iconFont = FontManager.getTypeface(context.getApplicationContext(), textFontFamily);
                this.setTypeface(iconFont, getTypeface().getStyle());
                setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize());
            }
        } finally {
            a.recycle();
        }
    }
}
