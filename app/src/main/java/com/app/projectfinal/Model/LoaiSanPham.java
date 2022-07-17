package com.app.projectfinal.model;

public class LoaiSanPham {
    private String ten_loai, hinh_loai;
    private int idLSP;

    public LoaiSanPham(int idLSP, String ten_loai, String hinh_loai){
        this.idLSP = idLSP;
        this.ten_loai = ten_loai;
        this.hinh_loai = hinh_loai;

    }

    public LoaiSanPham(String ten_loai, int idLSP) {
        this.ten_loai = ten_loai;
        this.idLSP = idLSP;
    }

    public int getIdLSP() {
        return idLSP;
    }

    public void setIdLSP(int idLSP) {
        this.idLSP = idLSP;
    }

    public String getTen_loai() {
        return ten_loai;
    }

    public void setTen_loai(String ten_loai) {
        this.ten_loai = ten_loai;
    }

    public String getHinh_loai() {
        return hinh_loai;
    }

    public void setHinh_loai(String hinh_loai) {
        this.hinh_loai = hinh_loai;
    }
}
