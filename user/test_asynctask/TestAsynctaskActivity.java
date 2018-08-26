package com.jrr.user.test_asynctask;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.jrr.user.myfragment.R;

/**
 * Created by user on 2018/8/25.
 */

public class TestAsynctaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_activitylayout);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.INTERNET}, 1);
    }



    public void doClick(View view){

        switch (view.getId()){
            case R.id.bt_image:
                Intent intent = new Intent(TestAsynctaskActivity.this,AsyncTaskLoadImage.class);
                startActivity(intent);
                break;
            case R.id.bt_progress:

                Intent intent2= new Intent(TestAsynctaskActivity.this,AsyncTaskLoadProgressbar.class);
                startActivity(intent2);
                break;
        }


    }
}
