package com.lightthefuture.minor_ii;

/**
 * Created by Deepanshu on 5/6/2016.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class IconifiedTextView extends LinearLayout {

    private TextView mText;
    private ImageView mIcon;

    public IconifiedTextView(Context context, IconifiedText aIconifiedText) {
        super(context);

                /* First Icon and the Text to the right (horizontal),
                 * not above and below (vertical) */
        this.setOrientation(HORIZONTAL);

        mIcon = new ImageView(context);
        mIcon.setImageDrawable(aIconifiedText.getIcon());
        // left, top, right, bottom
        mIcon.setPadding(0, 1, 5, 0); // 5px to the right

                /* At first, add the Icon to ourself
                 * (! we are extending LinearLayout) */
        addView(mIcon,  new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        mText = new TextView(context);
        mText.setText(aIconifiedText.getText());
        mText.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        mText.setPadding(0, 12, 5, 0);
                /* Now the text (after the icon) */
        addView(mText, new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    public void setText(String words) {
        mText.setText(words);
    }

    public void setIcon(Drawable bullet) {
        mIcon.setImageDrawable(bullet);
    }
}