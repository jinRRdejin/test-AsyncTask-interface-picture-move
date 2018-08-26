package com.jrr.user.myfragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by user on 2018/8/15.
 */

public class SecondActivity extends Activity {

    ImageView img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        img1 = (ImageView) findViewById(R.id.img1);
        //img2 = (ImageView) findViewById(R.id.img2);

        ImageViewOnMultiTouchListener ivmtl = new ImageViewOnMultiTouchListener();
        img1.setOnTouchListener(ivmtl);
    }
}
