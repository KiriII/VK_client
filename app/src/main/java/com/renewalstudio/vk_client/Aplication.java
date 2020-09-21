package com.renewalstudio.vk_client;

import android.app.Application;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKTokenExpiredHandler;

public class Aplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VK.addTokenExpiredHandler(new VKTokenExpiredHandler() {
            @Override
            public void onTokenExpired() {

            }
        });
    }
}
