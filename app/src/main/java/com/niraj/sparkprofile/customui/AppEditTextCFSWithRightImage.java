package com.niraj.sparkprofile.customui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.utils.AppConstants;

/**
 * Created by NirajKumar on 19/09/18.
 */

public class AppEditTextCFSWithRightImage extends RelativeLayout {

    private static final String TAG = "AETCRightImage";

    private AppCompatEditText editTextCFS;
    private AppTextViewCFS tvRightImage;
    private View view;
    private String textFontFamily;
    private String textRightImage;
    private int inputType;
    private int maxLength;

    private OnRightButtonClickListener onRightButtonClickListener;
    private OnEditClickListener onEditClickListener;

    public AppEditTextCFSWithRightImage(Context context) {
        super(context);
        inflate(context, R.layout.layout_edittext_right_image, this);
    }

    public AppEditTextCFSWithRightImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUI(context, attrs);
    }

    public AppEditTextCFSWithRightImage(Context context, AttributeSet attrs, int defStyleAttr) {
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
                R.styleable.AppEditTextCFSWithButton,
                0, 0);

        view = inflate(context, R.layout.layout_edittext_right_image, this);
        editTextCFS = (AppCompatEditText) view.findViewById(R.id.et_custom_right_image);
        tvRightImage = (AppTextViewCFS) view.findViewById(R.id.tv_right_image);

        editTextCFS.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus && getContext() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        try {
            textFontFamily = a.getString(R.styleable.AppEditTextCFSWithButton_textButtonFontFamily);
            textRightImage = a.getString(R.styleable.AppEditTextCFSWithButton_textButtonRightImage);
            inputType = a.getInteger(R.styleable.AppEditTextCFSWithButton_android_inputType, EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);

            if (textFontFamily != null) {
                Typeface iconFont = FontManager.getTypeface(context.getApplicationContext(), textFontFamily);
                if (editTextCFS.getTypeface() != null) {
                    editTextCFS.setTypeface(iconFont, editTextCFS.getTypeface().getStyle());
                } else {
                    editTextCFS.setTypeface(iconFont);
                }
                editTextCFS.setTextSize(TypedValue.COMPLEX_UNIT_PX, editTextCFS.getTextSize());
            }
            editTextCFS.setInputType(inputType);

            maxLength = a.getInteger(R.styleable.AppEditTextCFSWithButton_android_maxLength, -1);
            AppConstants.logI(TAG, "Max Length for edittext:" + maxLength);
            if (maxLength != -1) {
                editTextCFS.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
            }

            tvRightImage.setText(textRightImage);
            invalidate();
        } catch (Exception e) {
            AppConstants.logE(TAG, e.getMessage());
        } finally {
            a.recycle();
        }

        return view;
    }

    public CharSequence getTvRightImage() {
        if (tvRightImage != null) {
            return tvRightImage.getText();
        }
        return "";
    }

    /**
     * Set right image
     *
     * @param rightImage
     */
    public void setTvRightImage(String rightImage) {
        if (tvRightImage != null) {
            tvRightImage.setText(rightImage);
        }
    }

    public String getText() {
        if (editTextCFS != null) {
            return AppConstants.trim(editTextCFS.getText() + "", "");
        }
        return "";
    }

    public void setText(String text) {
        if (editTextCFS != null) {
            editTextCFS.setText(text);
        }
    }

    /**
     * set edittext editable
     *
     * @param isEditable
     */
    public void setEditable(boolean isEditable) {
        if (!isEditable) {
            editTextCFS.setFocusable(false);
            editTextCFS.setClickable(false);

            editTextCFS.setOnTouchListener((view, motionEvent) -> {
                AppEditTextCFSWithRightImage.this.performClick();
                return false;
            });
        }
    }

    /**
     * Set right image
     *
     * @param inputType
     */
    public void setInputType(int inputType) {
        if (editTextCFS != null) {
            editTextCFS.setInputType(inputType);
        }
    }

    public void setErrorMessage(String errorMessage) {
        if (editTextCFS != null) {
            editTextCFS.setError(errorMessage);
        }
    }

    public void setEditClickListener(final OnEditClickListener onEditClickListener) {
        this.onEditClickListener = onEditClickListener;
        if (onEditClickListener != null && editTextCFS != null) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEditClickListener.onEditClick(AppEditTextCFSWithRightImage.this, getId());
                }
            });
        }
    }

    /**
     * On Right button click listener
     *
     * @param onRightButtonClickListener
     */
    public void setOnRightButtonClickListener(final OnRightButtonClickListener onRightButtonClickListener) {
        this.onRightButtonClickListener = onRightButtonClickListener;
        if (tvRightImage != null) {
            tvRightImage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence rightImage = tvRightImage.getText();
                    if (rightImage != null && getContext().getString(R.string.fa_eye).equals(rightImage)) {
                        tvRightImage.setText(getContext().getString(R.string.fa_eye_striked));
                        editTextCFS.setInputType(InputType.TYPE_CLASS_TEXT);
                    } else if (getContext().getString(R.string.fa_eye_striked).equals(rightImage)) {
                        editTextCFS.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        tvRightImage.setText(getContext().getString(R.string.fa_eye));
                    }
                    if (onRightButtonClickListener != null) {
                        onRightButtonClickListener.onRightButtonClick(AppEditTextCFSWithRightImage.this, getId());
                    }
                }
            });
        }
    }

    public void setTextChangeListener(TextWatcher textChangeListener) {
        editTextCFS.addTextChangedListener(textChangeListener);
    }

    public interface OnRightButtonClickListener {
        public void onRightButtonClick(View view, int id);
    }

    public interface OnEditClickListener {
        public void onEditClick(View view, int id);
    }
}
