package com.renewalstudio.vk_client.Presenters;

import android.util.Log;

import com.renewalstudio.vk_client.Activities.FriendsListActivity;
import com.renewalstudio.vk_client.Models.FriendModel;
import com.renewalstudio.vk_client.Views.FriendView;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendPresenter {

    private ArrayList<FriendModel> friendList;

    private FriendView friendView;

    public FriendPresenter(FriendView friendView){
        this.friendView = friendView;
        friendList = new ArrayList<>();
    }

    public void testLoadFriends(boolean onlyOnline) {
        if (!onlyOnline) friendList.add(new FriendModel("Денис", "Сухачёв",
                "https://sun9-3.userapi.com/CEO02qa0r6n4XYC8wQx6RGsrlyWWR40w38j1qA/uOc_dOUHIx8.jpg", 0, 228));
        friendList.add(new FriendModel("Валерий", "Жмышенко",
                "https://sun9-21.userapi.com/txb1nNVCWwaSWbxNTixeQrdxTLobUiEtXboqvA/57t_jD0hTA4.jpg", 1, 1488));
        if (!onlyOnline) friendList.add(new FriendModel("Сашка", "СХС",
                "https://sun9-49.userapi.com/54Rdv2xr8C2mzJ1GA-Yfy2wAuaGRl60QZsYfJw/oU_gzzEFxTs.jpg", 0, 322));
        friendView.setupFriendsList(friendList);
    }

    public void LoadFriends(final boolean onlyOnline) {
        VK.execute(new VKFriendsRequest("friends.get"), new VKApiCallback<List<FriendModel>>() {
            @Override
            public void success(List<FriendModel> friendModels) {
                friendList.clear();
                if (onlyOnline) {
                    for (FriendModel friendModel: friendModels) {
                        if(friendModel.isOnline) friendList.add(friendModel);
                    }
                }
                else friendList.addAll(friendModels);
                friendView.setupFriendsList(friendList);
            }

            @Override
            public void fail(@NotNull Exception e) {
                Log.e("ERROR", e.toString());
            }
        });
    }
}

class VKFriendsRequest extends VKRequest<List<FriendModel>> {

    VKFriendsRequest(@NotNull String method) {
        super(method);
        addParam("fields", "photo_100");
    }

    @Override
    public ArrayList<FriendModel> parse(@NotNull JSONObject r) throws Exception {
        JSONObject ObjectUsers = r.getJSONObject("response");
        JSONArray users = ObjectUsers.getJSONArray("items");
        ArrayList<FriendModel> result = new ArrayList<>();
        for (int i = 0; i < users.length(); i++) {
            result.add(new FriendModel(users.getJSONObject(i).getString("first_name"),
                    users.getJSONObject(i).getString("last_name"),
                    users.getJSONObject(i).getString("photo_100"),
                    users.getJSONObject(i).getInt("online"),
                    users.getJSONObject(i).getInt("id")));
        }
        return result;
    }
}
