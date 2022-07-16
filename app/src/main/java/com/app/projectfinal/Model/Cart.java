package com.app.projectfinal.Model;

public class Cart {
    int idUser, idSP, soLuong;
    String tong, imgSP, tenSP;

    public Cart(int idUser, int idSP, String tenSP, int soLuong, String tong, String imgSP) {
        this.idUser = idUser;
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.tong = tong;
        this.imgSP = imgSP;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTong() {
        return tong;
    }

    public void setTong(String tong) {
        this.tong = tong;
    }

    public String getImgSP() {
        return imgSP;
    }

    public void setImgSP(String imgSP) {
        this.imgSP = imgSP;
    }
}
