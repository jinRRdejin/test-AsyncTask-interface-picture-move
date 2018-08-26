package com.jrr.user.test_interface;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jrr.user.myfragment.R;

/**
 * Created by user on 2018/8/25.
 */

public class TestInterfaceActivity extends Activity implements CallBack{

    private Button mBtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_layout);
        mBtTest = (Button) findViewById(R.id.bt_test);


        Callbackutil.setmCallback(this);
        mBtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callbackutil.doCallbackMetod();
            }
        });
    }

    @Override
    public void doSomting(String string) {
        Toast.makeText(this, string,Toast.LENGTH_LONG) .show();
    }
}
