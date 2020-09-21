package com.renewalstudio.vk_client.Models;

public class FriendModel {
    public String name;
    public String surname;
    public String image;
    public boolean isOnline;
    public int id;

    public FriendModel(String name, String surname, String image, int isOnline, int id){
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.isOnline = isOnline == 1;
        this.id = id;
    }
}
