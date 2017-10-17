package com.chikeandroid.retrofittutorial.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ArifmeticService extends Service {
    private Handler handler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        handler.postDelayed(runnable, 4000L);
        return START_STICKY;
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            Toast.makeText(ArifmeticService.this, "Message diplayed", Toast.LENGTH_LONG).show();
            handler.postDelayed(runnable, 4000L);
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
