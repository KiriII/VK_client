package com.renewalstudio.vk_client.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renewalstudio.vk_client.Activities.FriendInfoActivity;
import com.renewalstudio.vk_client.R;
import com.renewalstudio.vk_client.Views.FriendInfoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FriendInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<String> photosList = new ArrayList<>();
    private FriendInfoView view;

    public void setupPhotos(ArrayList<String> photoList, FriendInfoView view) {
        this.view = view;
        photosList.clear();
        photosList.addAll(photoList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.fragment_friend_info, parent, false);
        return new FriendInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FriendInfoViewHolder){
            ((FriendInfoViewHolder) holder).bind(photosList.get(position) , view);
        }
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }
}

class FriendInfoViewHolder extends RecyclerView.ViewHolder{

    private ImageView photoView = itemView.findViewById(R.id.friendPhoto);

    FriendInfoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void bind(String url, final FriendInfoView view){
        Picasso.get()
                .load(url)
                .into(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setupBigPhoto(photoView.getDrawable());
            }
        });
    }
}
