package com.jrr.user.test_asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jrr.user.myfragment.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by user on 2018/8/25.
 */

public class AsyncTaskLoadImage extends Activity{

    private ImageView mImageView;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_image_layout);

        mImageView = (ImageView) findViewById(R.id.image);
        pg = (ProgressBar) findViewById(R.id.pg);

        new MyAsycTask().execute("http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg");
    }


    class MyAsycTask extends AsyncTask<String,Void,Bitmap>{


        //1 .做一些最开始测初始化工作,工作主线程
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg.setVisibility(View.VISIBLE);
        }

        //2 .做最后的显示工作,工作主线程
        @Override
        protected void onPostExecute(Bitmap bitmap) {

            pg.setVisibility(View.INVISIBLE);
            mImageView.setImageBitmap(bitmap);
        }


        //做异步的处理工作,在子线程中可以做仍任意的耗时操作
        @Override
        protected Bitmap doInBackground(String... strings) {

            String url = strings[0];
            Bitmap bitmap = null;
            URLConnection  urlConnection;
            InputStream is;

            try {
                urlConnection = new URL(url).openConnection();
                is= urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bitmap = BitmapFactory.decodeStream(bis);

                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
