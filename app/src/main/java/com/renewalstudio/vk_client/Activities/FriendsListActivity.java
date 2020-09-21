package com.renewalstudio.vk_client.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.renewalstudio.vk_client.Adapters.FriendListAdapter;
import com.renewalstudio.vk_client.Models.FriendModel;
import com.renewalstudio.vk_client.Presenters.FriendPresenter;
import com.renewalstudio.vk_client.R;
import com.renewalstudio.vk_client.Views.FriendView;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity implements FriendView {

    private FriendListAdapter friendListAdapter;
    private FriendPresenter friendPresenter;
    private CheckBox onlyOnline;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        RecyclerView mFriendsList = findViewById(R.id.friendList);
        onlyOnline = findViewById(R.id.onlyOnline);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        onlyOnline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                friendPresenter.LoadFriends(onlyOnline.isChecked());
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                friendPresenter.LoadFriends(onlyOnline.isChecked());
                swipeRefresh.setRefreshing(false);
            }
        });

        friendListAdapter = new FriendListAdapter();

        friendPresenter = new FriendPresenter(this);
        friendPresenter.LoadFriends(false);

        mFriendsList.setAdapter(friendListAdapter);
        mFriendsList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        mFriendsList.setHasFixedSize(true);
    }

    public void setupFriendsList(ArrayList<FriendModel> friendList){
        friendListAdapter.setupFriends(friendList);
    }
}