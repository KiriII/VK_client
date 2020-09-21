package com.renewalstudio.vk_client.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.renewalstudio.vk_client.Adapters.FriendInfoAdapter;
import com.renewalstudio.vk_client.Presenters.FriendInfoPresenter;
import com.renewalstudio.vk_client.R;
import com.renewalstudio.vk_client.Views.FriendInfoView;

import java.util.ArrayList;

public class FriendInfoActivity extends AppCompatActivity implements FriendInfoView {

    private RecyclerView photoList;
    private GridLayoutManager gridLayoutManager;
    private FriendInfoAdapter friendInfoAdapter;
    private FriendInfoPresenter friendInfoPresenter;
    private ImageView bigPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info);

        gridLayoutManager = new GridLayoutManager(this , 2);

        friendInfoAdapter = new FriendInfoAdapter();

        friendInfoPresenter = new FriendInfoPresenter(getIntent().getIntExtra("id", 168857091) , this);
        //Log.e("ID" , "" + getIntent().getIntExtra("id", 168857091));
        friendInfoPresenter.loadInfo();

        photoList = findViewById(R.id.friendInfoList);
        bigPhoto = findViewById(R.id.bigPhoto);

        bigPhoto.setElevation(2.0f);
        bigPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigPhoto.setVisibility(View.INVISIBLE);
            }
        });

        photoList.setAdapter(friendInfoAdapter);
        photoList.setLayoutManager(gridLayoutManager);
        photoList.setHasFixedSize(true);
    }

    @Override
    public void setupPhotoList(ArrayList<String> photoList){
        friendInfoAdapter.setupPhotos(photoList, FriendInfoActivity.this);
    }

    @Override
    public void setupBigPhoto(Drawable drawable){
        bigPhoto.setImageDrawable(drawable);
        bigPhoto.setVisibility(View.VISIBLE);
    }
}
