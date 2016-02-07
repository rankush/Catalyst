package com.catalyst.travller.app.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jitendra.karma on 06/02/2016.
 */
public class RainsTextView extends TextView {

    public RainsTextView(Context context) {
        this(context, null);
    }

    public RainsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RainsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/raisins.ttf"));
    }

}
