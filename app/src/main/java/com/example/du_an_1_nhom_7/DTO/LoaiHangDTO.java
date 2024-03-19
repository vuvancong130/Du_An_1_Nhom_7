package com.example.du_an_1_nhom_7.DTO;

public class LoaiHangDTO {
    private int ma_loai_hang;
    private String ten_loai_hang;
    private String thue;

    public LoaiHangDTO(int ma_loai_hang, String ten_loai_hang, String thue) {
        this.ma_loai_hang = ma_loai_hang;
        this.ten_loai_hang = ten_loai_hang;
        this.thue = thue;
    }

    public LoaiHangDTO(String ten_loai_hang, String thue) {
        this.ten_loai_hang = ten_loai_hang;
        this.thue = thue;
    }

    public LoaiHangDTO() {
    }

    public int getMa_loai_hang() {
        return ma_loai_hang;
    }

    public void setMa_loai_hang(int ma_loai_hang) {
        this.ma_loai_hang = ma_loai_hang;
    }

    public String getTen_loai_hang() {
        return ten_loai_hang;
    }

    public void setTen_loai_hang(String ten_loai_hang) {
        this.ten_loai_hang = ten_loai_hang;
    }

    public String getThue() {
        return thue;
    }

    public void setThue(String thue) {
        this.thue = thue;
    }
}
