package com.example.du_an_1_nhom_7.DTO;

public class NhanVienDTO {
    private String maNV,ho_ten,mat_khau;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String maNV, String ho_ten, String mat_khau) {
        this.maNV = maNV;
        this.ho_ten = ho_ten;
        this.mat_khau = mat_khau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }
}
