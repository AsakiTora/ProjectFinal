package com.app.projectfinal.model;

public class SanPham {

    private String tenSP, loaiSP, moTaSP, ngayDang, hinhSP, donVi, tinhTrang, shop, giaSP;
    private int idSP, idLSP, soLuong, idUser;

    public SanPham(int idSP, int idLSP, String loaiSP, String giaSP, String tenSP, String moTaSP, String ngayDang, String hinhSP, String donVi, String tinhTrang, String shop, int soLuong, int idUser) {
        this.idSP = idSP;
        this.idLSP = idLSP;
        this.loaiSP = loaiSP;
        this.giaSP = giaSP;
        this.tenSP = tenSP;
        this.moTaSP = moTaSP;
        this.ngayDang = ngayDang;
        this.hinhSP = hinhSP;
        this.donVi = donVi;
        this.tinhTrang = tinhTrang;
        this.shop = shop;
        this.soLuong = soLuong;
        this.idUser = idUser;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getIdLSP() {
        return idLSP;
    }

    public void setIdLSP(int idLSP) {
        this.idLSP = idLSP;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }
}
