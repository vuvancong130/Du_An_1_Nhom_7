package com.example.du_an_1_nhom_7.DTO;

public class ThanhVienDTO {
    private int maTV;
    private String ho_ten, nam_sinh, gioi_tinh, so_dien_thoai;

    public ThanhVienDTO() {
    }

    public ThanhVienDTO(int maTV, String ho_ten, String nam_sinh, String gioi_tinh, String so_dien_thoai) {
        this.maTV = maTV;
        this.ho_ten = ho_ten;
        this.nam_sinh = nam_sinh;
        this.gioi_tinh = gioi_tinh;
        this.so_dien_thoai = so_dien_thoai;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getNam_sinh() {
        return nam_sinh;
    }

    public void setNam_sinh(String nam_sinh) {
        this.nam_sinh = nam_sinh;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }
}
