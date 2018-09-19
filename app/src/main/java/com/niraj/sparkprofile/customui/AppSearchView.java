package com.niraj.sparkprofile.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.utils.AppConstants;


/**
 * Created by NirajKumar on 11/29/17.
 */

public class AppSearchView extends SearchView {

    private final static String TAG = "AppSearchView";

    public AppSearchView(Context context) {
        super(context);
    }

    public AppSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public AppSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AppSearchView,
                0, 0);
        String textFontFamily;

        try {
            SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(android.support.v7.appcompat.R.id.search_src_text);
            textFontFamily = a.getString(R.styleable.AppTextViewCFS_textFontFamily);
            if (textFontFamily != null) {
                Typeface iconFont = FontManager.getTypeface(context.getApplicationContext(), textFontFamily);
                if (searchAutoComplete != null && searchAutoComplete.getTypeface() != null) {
                    searchAutoComplete.setTypeface(iconFont, searchAutoComplete.getTypeface().getStyle());
                } else {
                    searchAutoComplete.setTypeface(iconFont);
                }
                searchAutoComplete.setTextSize(TypedValue.COMPLEX_UNIT_PX, searchAutoComplete.getTextSize());
            }
        } catch (Exception e) {
            AppConstants.logE(TAG, e.getMessage());
        } finally {
            a.recycle();
        }
    }
}
