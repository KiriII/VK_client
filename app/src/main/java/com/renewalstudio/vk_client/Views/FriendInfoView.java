package com.renewalstudio.vk_client.Views;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public interface FriendInfoView {

    void setupPhotoList(ArrayList<String> photos);

    void setupBigPhoto(Drawable drawable);

}
