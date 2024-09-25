package com.example.du_an_1_nhom_7.DTO;

public class SanPhamDTO {
    public int ma_SP;
    public String ten_SP;
    public int ma_loai;
    public String hsd;
    public int don_gia,so_luong;
    public String img;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String ten_SP, int ma_loai, String hsd, int don_gia, int so_luong, String img) {
        this.ten_SP = ten_SP;
        this.ma_loai = ma_loai;
        this.hsd = hsd;
        this.don_gia = don_gia;
        this.so_luong = so_luong;
        this.img = img;
    }

    public SanPhamDTO(int ma_SP, String ten_SP, int ma_loai, String hsd, int don_gia, int so_luong, String img) {
        this.ma_SP = ma_SP;
        this.ten_SP = ten_SP;
        this.ma_loai = ma_loai;
        this.hsd = hsd;
        this.don_gia = don_gia;
        this.so_luong = so_luong;
        this.img = img;
    }

    public int getMa_SP() {
        return ma_SP;
    }

    public void setMa_SP(int ma_SP) {
        this.ma_SP = ma_SP;
    }

    public String getTen_SP() {
        return ten_SP;
    }

    public void setTen_SP(String ten_SP) {
        this.ten_SP = ten_SP;
    }

    public int getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(int ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getHsd() {
        return hsd;
    }

    public void setHsd(String hsd) {
        this.hsd = hsd;
    }

    public int getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
