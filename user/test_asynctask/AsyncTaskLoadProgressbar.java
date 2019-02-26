package com.jrr.user.test_asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.jrr.user.myfragment.R;

/**
 * Created by user on 2018/8/2
 *
 * 
 * 异步任务--AsyncTask
 1 必须在UI线程中创建Asynctask 的实列
 2 必须在UI线程中调用Asynctask的execure方法
 3 重写的四个方法是系统自动调用的,不应手动调用
 4 每个AsyncTask只能被执行一次,多次调用将会引发异常

 AsyncTask<params,Progress,Result>

 params 启动任务输入参数类型
 Progress 后台执行任务返回的进度值的类型
 Result 执行任务完成后返回的结果类型

 onPreExecute  执行后台操作前被调用 在主线程中执行
 onPostExecute  一般用于更新UI或其他必须在主线程执行的操作,传递参数bitmap为
     oInBackground方法中的返回值执行onProgressUpdate后系统会自动调用,并将onProgressUpdate值传递过来  在主线程中执行
 doInBackground  后台线程要完成的任务 不能在doInBackground(Params… params)中更新UI 在子线程中
 onProgressUpdate  在doInBackground  中调用publishProgress 更新任务的进度后,就会触发该方法. 在主线程完成
 onCancelled 在主线程中,当异步任务被取消时,该方法将被调用, 要注意的是这个时onPostExecute将不会被执行
 
 */

public class AsyncTaskLoadProgressbar extends Activity{

    private ProgressBar mProgressBar;

    private MyAsyncTask  mMyAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_progressbar_layout);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMyAsyncTask = new MyAsyncTask();
        mMyAsyncTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //cancle 只是将对应的Asynctask 标记为cancel的状态
       if(mMyAsyncTask != null && mMyAsyncTask.getStatus() == AsyncTask.Status.RUNNING ){
           mMyAsyncTask.cancel(true);
       }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i= 0;i < 100; i++){

                try {
                    if(isCancelled()){
                      break;
                    }
                    publishProgress(i);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(isCancelled()){
               return;
            }
            mProgressBar.setProgress(values[0]);
        }
    }
}
