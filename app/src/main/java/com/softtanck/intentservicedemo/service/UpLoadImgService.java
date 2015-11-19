package com.softtanck.intentservicedemo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.softtanck.intentservicedemo.MainActivity;

/**
 * Created by winterfell on 11/17/2015.
 */
public class UpLoadImgService extends IntentService {


    public UpLoadImgService() {
        super("ceshi");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpLoadImgService(String name) {
        super(name);
    }


    public static void startUploadImg(Context context, String path) {
        Intent intent = new Intent(context, UpLoadImgService.class);
        intent.setAction(MainActivity.UPLOAD_IMG);
        intent.putExtra(MainActivity.EXTRA_IMG_PATH, path);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        if (null != intent) {
            String action = intent.getAction();

            if (action.equals(MainActivity.UPLOAD_IMG)) {
                //UpLoad file
                uploadImg(intent.getStringExtra(MainActivity.EXTRA_IMG_PATH));
            }
        }

    }

    private void uploadImg(String path) {
        try {
            Thread.sleep(2000);
            Intent intent = new Intent(MainActivity.UPLOAD_IMG);
            intent.putExtra(MainActivity.EXTRA_IMG_PATH, path);
            sendBroadcast(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
