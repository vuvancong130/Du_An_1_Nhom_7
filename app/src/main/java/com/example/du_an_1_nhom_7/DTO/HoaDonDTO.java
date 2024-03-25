package com.example.du_an_1_nhom_7.DTO;

public class HoaDonDTO {

    private int maHD;
    private String maNV;
    private int maTV;
    private int maSP;
    private String ngay;
    private int soLuong;
    private int trangThai;
    private int nhapXuat;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String maNV, int maTV, int maSP, String ngay, int soLuong, int trangThai, int nhapXuat) {
        this.maNV = maNV;
        this.maTV = maTV;
        this.maSP = maSP;
        this.ngay = ngay;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.nhapXuat = nhapXuat;
    }

    public HoaDonDTO(int maHD, String maNV, int maTV, int maSP, String ngay, int soLuong, int trangThai, int nhapXuat) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maTV = maTV;
        this.maSP = maSP;
        this.ngay = ngay;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.nhapXuat = nhapXuat;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getNhapXuat() {
        return nhapXuat;
    }

    public void setNhapXuat(int nhapXuat) {
        this.nhapXuat = nhapXuat;
    }
}
