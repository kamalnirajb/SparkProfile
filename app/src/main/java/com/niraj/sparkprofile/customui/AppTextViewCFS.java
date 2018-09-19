package com.niraj.sparkprofile.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.utils.AppConstants;

/**
 * Created by NirajKumar on 19/09/18.
 */

public class AppTextViewCFS extends AppCompatTextView {

    private final static String TAG = "AppTextViewCFS";

    public AppTextViewCFS(Context context) {
        super(context);
    }

    public AppTextViewCFS(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public AppTextViewCFS(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
               R.styleable.AppTextViewCFS,
                0, 0);
        String textFontFamily;

        try {
            textFontFamily = a.getString(R.styleable.AppTextViewCFS_textFontFamily);
            if (textFontFamily != null) {
                Typeface iconFont = FontManager.getTypeface(context.getApplicationContext(), textFontFamily);
                if (getTypeface() != null) {
                    setTypeface(iconFont, getTypeface().getStyle());
                }else {
                    setTypeface(iconFont);
                }
                setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize());
            }
        } catch (Exception e){
            AppConstants.logE(TAG, e.getMessage());
        } finally {
            a.recycle();
        }
    }

}
