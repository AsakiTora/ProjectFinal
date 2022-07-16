package com.app.projectfinal.DataLocal;

import android.content.Context;

import com.google.gson.Gson;

import com.app.projectfinal.Model.SanPham;
import com.app.projectfinal.Model.User;

public class DataLocalManager {
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init (Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setUser(User user) {
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_OBJECT_USER, strJsonUser);
    }

    public static User getUser() {
        String strJsonUser = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonUser, User.class);
        return user;
    }

    public static void setSP(SanPham sanPham) {
        Gson gson = new Gson();
        String strJsonSP = gson.toJson(sanPham);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_OBJECT_USER, strJsonSP);
    }
    public static SanPham getSP() {
        String strJsonSP = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        SanPham sanPham = gson.fromJson(strJsonSP, SanPham.class);
        return sanPham;
    }
}
