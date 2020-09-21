package com.renewalstudio.vk_client.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renewalstudio.vk_client.Activities.FriendInfoActivity;
import com.renewalstudio.vk_client.Models.FriendModel;
import com.renewalstudio.vk_client.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<FriendModel> mFriendList = new ArrayList<>();

    public void setupFriends(ArrayList<FriendModel> friendList){
        mFriendList.clear();
        mFriendList.addAll(friendList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_friends_list, parent, false);
        return new FriendsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FriendsViewHolder){
            ((FriendsViewHolder) holder).bind(mFriendList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

}

class FriendsViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView friendImage = itemView.findViewById(R.id.friendPhoto);
    private TextView friendName = itemView.findViewById(R.id.friendName);
    private ImageView online = itemView.findViewById(R.id.online);

    @SuppressLint("SetTextI18n")
    void bind(final FriendModel friendModel){
        Picasso.get()
                .load(friendModel.image)
                .placeholder(R.drawable.placeholder)
                .into(friendImage);
        //Log.e("FriendListAdapter", friendModel.image);
        friendName.setText(friendModel.name + " " + friendModel.surname);
        if (friendModel.isOnline) online.setVisibility(View.VISIBLE);
        else online.setVisibility(View.INVISIBLE);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), FriendInfoActivity.class);
                intent.putExtra("id", friendModel.id);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    FriendsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

}