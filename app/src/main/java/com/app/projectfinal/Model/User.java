package com.app.projectfinal.Model;

public class User {
    int id_user, id_group;
    String account, avatar, name, email, address, password, birth, intro, phone, shop, avt_shop;

    public User(int id_user, int id_group, String phone, String account, String avatar, String name, String email, String address, String birth, String intro, String shop, String avt_shop) {
        this.id_user = id_user;
        this.id_group = id_group;
        this.phone = phone;
        this.account = account;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birth = birth;
        this.intro = intro;
        this.shop = shop;
        this.avt_shop = avt_shop;
    }

    public User() {
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getAvt_shop() {
        return avt_shop;
    }

    public void setAvt_shop(String avt_shop) {
        this.avt_shop = avt_shop;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
