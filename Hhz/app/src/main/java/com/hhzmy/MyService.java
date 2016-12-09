package com.hhzmy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    int mStartMode; // indicates how to behave if the service is killed
    IBinder mBinder; // interface for clients that bind
    boolean mAllowRebind; // indicates whether onRebind should be used

    public MyService() {
    }

    @Override
    public void onCreate() {
        // The service is being created
        Log.e("TAG", "onCreate()--进行它的初始化工作");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("TAG", "onStart()--服务启动");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("TAG", "onBind()-- bindService()()方法传过来的Intent对象,如果service是被绑定的，它们它的活动生命周期是在onUnbind()方法返回后结束。");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getToast();
        Log.e("TAG", "onStartCommand()--处理由startService()方法传过来的Intent对象,如果service是被开启的，那么它的活动生命周期和整个生命周期一同结束。");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy()--释放残留的资源");
        // The service is no longer used and is being destroyed
    }

    public void getToast() {
        Toast.makeText(this, "Service吐司!", Toast.LENGTH_SHORT).show();
    }
}
