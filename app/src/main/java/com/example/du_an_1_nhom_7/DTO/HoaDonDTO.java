package com.example.du_an_1_nhom_7.DTO;

public class HoaDonDTO {

    private int maHD;
    private String maNV;
    private int maTV;
    private int maSP;
    private int soLuong;
    private  int donGia;
    private String ngayXuat;
    private int nhap_xuat;

    private int trangThai;
    public HoaDonDTO() {
    }

    public HoaDonDTO(String maNV, int maTV, int maSP, int soLuong, int donGia, String ngayXuat, int nhap_xuat, int trangThai) {
        this.maNV = maNV;
        this.maTV = maTV;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayXuat = ngayXuat;
        this.nhap_xuat = nhap_xuat;
        this.trangThai = trangThai;
    }

    public HoaDonDTO(int maHD, String maNV, int maTV, int maSP, int soLuong, int donGia, String ngayXuat, int nhap_xuat, int trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maTV = maTV;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ngayXuat = ngayXuat;
        this.nhap_xuat = nhap_xuat;
        this.trangThai = trangThai;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getNhap_xuat() {
        return nhap_xuat;
    }

    public void setNhap_xuat(int nhap_xuat) {
        this.nhap_xuat = nhap_xuat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
