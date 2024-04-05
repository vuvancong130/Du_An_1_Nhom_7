package com.example.du_an_1_nhom_7.DTO;

public class LoaiHangDTO {
    private int ma_loai_hang;
    private String ten_loai_hang;
    private String moTa;

    public LoaiHangDTO(int ma_loai_hang, String ten_loai_hang, String moTa) {
        this.ma_loai_hang = ma_loai_hang;
        this.ten_loai_hang = ten_loai_hang;
        this.moTa = moTa;
    }

    public LoaiHangDTO() {
    }

    public LoaiHangDTO(String ten_loai_hang, String moTa) {
        this.ten_loai_hang = ten_loai_hang;
        this.moTa = moTa;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}

