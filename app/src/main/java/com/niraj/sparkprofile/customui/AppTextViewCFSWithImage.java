package com.niraj.sparkprofile.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.utils.AppConstants;

/**
 * Created by NirajKumar on 19/09/18.
 */

public class AppTextViewCFSWithImage extends RelativeLayout {

    private static final String TAG = "AETCRightImage";

    private AppCompatImageView imgIcon;
    private AppTextViewCFS tvLbl;
    private View view;
    private String textFontFamily;

    public AppTextViewCFSWithImage(Context context) {
        super(context);
        inflate(context, R.layout.layout_textview_left_icon, this);
    }

    public AppTextViewCFSWithImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUI(context, attrs);
    }

    public AppTextViewCFSWithImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUI(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * Set UI with custom view
     *
     * @param context
     * @param attrs
     * @return
     */
    private View setUI(Context context, AttributeSet attrs) {
        setBackground(context.getDrawable(R.drawable.edittext_bg));

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AppTextViewCFSWithImage,
                0, 0);

        if (a.getBoolean(R.styleable.AppTextViewCFSWithImage_isLeft, true)) {
            view = inflate(context, R.layout.layout_textview_left_icon, this);
        } else {
            view = inflate(context, R.layout.layout_textview_right_icon, this);
        }
        tvLbl = (AppTextViewCFS) view.findViewById(R.id.tv_lbl);
        imgIcon = (AppCompatImageView) view.findViewById(R.id.img_lbl_icon);

        try {
            textFontFamily = a.getString(R.styleable.AppTextViewCFSWithImage_textImgButtonFontFamily);
            if (textFontFamily != null) {
                Typeface iconFont = FontManager.getTypeface(context.getApplicationContext(), textFontFamily);
                if (tvLbl.getTypeface() != null) {
                    tvLbl.setTypeface(iconFont, tvLbl.getTypeface().getStyle());
                } else {
                    tvLbl.setTypeface(iconFont);
                }
                tvLbl.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvLbl.getTextSize());
            }
            imgIcon.setImageDrawable(getResources().getDrawable(a.getResourceId(R.styleable.AppTextViewCFSWithImage_textButtonImage, R.drawable.ic_no_image)));
            tvLbl.setText(a.getString(R.styleable.AppTextViewCFSWithImage_android_text));
            invalidate();
        } catch (Exception e) {
            AppConstants.logE(TAG, e.getMessage());
        } finally {
            a.recycle();
        }

        return view;
    }

    public AppCompatImageView getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(AppCompatImageView imgIcon) {
        this.imgIcon = imgIcon;
    }

    public String getText() {
        if (tvLbl != null) {
            return AppConstants.trim(tvLbl.getText() + "", "");
        }
        return "";
    }

    public void setText(String text) {
        if (tvLbl != null) {
            tvLbl.setText(text);
        }
    }
}