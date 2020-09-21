package com.renewalstudio.vk_client.Presenters;

import android.util.Log;

import com.renewalstudio.vk_client.Activities.FriendInfoActivity;
import com.renewalstudio.vk_client.Views.FriendInfoView;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendInfoPresenter {

    private ArrayList<String> photos;
    private int id;
    private FriendInfoView friendInfoView;

    public FriendInfoPresenter(int id, FriendInfoView friendInfoView){
        photos = new ArrayList<>();
        this.id = id;
        this.friendInfoView = friendInfoView;
    }

    public void testLoadInfo(){
        photos.add("https://sun9-3.userapi.com/CEO02qa0r6n4XYC8wQx6RGsrlyWWR40w38j1qA/uOc_dOUHIx8.jpg");
        photos.add("https://sun9-21.userapi.com/txb1nNVCWwaSWbxNTixeQrdxTLobUiEtXboqvA/57t_jD0hTA4.jpg");
        photos.add("https://sun9-49.userapi.com/54Rdv2xr8C2mzJ1GA-Yfy2wAuaGRl60QZsYfJw/oU_gzzEFxTs.jpg");
        photos.add("https://sun9-3.userapi.com/CEO02qa0r6n4XYC8wQx6RGsrlyWWR40w38j1qA/uOc_dOUHIx8.jpg");
        photos.add("https://sun9-21.userapi.com/txb1nNVCWwaSWbxNTixeQrdxTLobUiEtXboqvA/57t_jD0hTA4.jpg");
        photos.add("https://sun9-49.userapi.com/54Rdv2xr8C2mzJ1GA-Yfy2wAuaGRl60QZsYfJw/oU_gzzEFxTs.jpg");
        friendInfoView.setupPhotoList(photos);
    }

    public void loadInfo(){
        VK.execute(new VkFriendInfoRequest("photos.getAll").addParam("owner_id", id), new VKApiCallback<List<String>>() {
            @Override
            public void success(List<String> strings) {
                photos.addAll(strings);
                friendInfoView.setupPhotoList(photos);
            }

            @Override
            public void fail(@NotNull Exception e) {
                Log.e("ERROR", e.toString());
            }
        });
    }
}

class VkFriendInfoRequest extends VKRequest<List<String>> {

    VkFriendInfoRequest(@NotNull String method) {
        super(method);
    }

    @Override
    public List<String> parse(@NotNull JSONObject r) throws Exception {
        JSONObject ObjectUsers = r.getJSONObject("response");
        JSONArray users = ObjectUsers.getJSONArray("items");
        ArrayList<String> photos = new ArrayList<>();
        for (int i = 0; i < users.length(); i++){
            JSONArray photoSizes = users.getJSONObject(i).getJSONArray("sizes");
            photos.add(photoSizes.getJSONObject(photoSizes.length() - 1).getString("url"));
        }
        return photos;
    }
}
