package com.example.du_an_1_nhom_7.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbhelper extends SQLiteOpenHelper {
    public MyDbhelper(Context context) {
        super(context, "QLKhoHang", null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_NhanVien = "CREATE TABLE NhanVien (maNV TEXT  PRIMARY KEY , hoTen TEXT NOT NULL, matKhau TEXT NOT NULL, sdt TEXT NOT NULL);";
        db.execSQL(tb_NhanVien);
        String insert_NhanVien = "INSERT INTO NhanVien (maNV,hoTen,matKhau, sdt) VALUES ('Admin','admin','123', '0932167298'), ('NvA','Nguyen Van A','123','0246993156'), ('NvB','Nguyen Van B','123','0432656793');";
        db.execSQL(insert_NhanVien);

        String tb_ThanhVien = "CREATE TABLE ThanhVien (maTV INTEGER PRIMARY KEY AUTOINCREMENT , hoTen TEXT NOT NULL , namSinh TEXT NOT NULL , gioiTinh TEXT  NOT NULL , sodienThoai TEXT NOT NULL);";
        db.execSQL(tb_ThanhVien);
        String insert_ThanhVien = "INSERT INTO ThanhVien (hoTen,namSinh,gioiTinh,sodienThoai) VALUES ('c','1994','Nam','0976336726'), ('Tran Minh T','1999','Nam','0264532963'), ('Pham Thuy K','2001','Nữ','0342998678'), ('Kieu Minh A','1987','Nam','0364723456');";
        db.execSQL(insert_ThanhVien);

        String tb_LoaiHang = "CREATE TABLE LoaiHang (maLH INTEGER PRIMARY KEY AUTOINCREMENT , tenLH TEXT NOT NULL , thue TEXT NOT NULL);";
        db.execSQL(tb_LoaiHang);
        String insert_LoaiHang = "INSERT INTO LoaiHang (tenLH,thue) VALUES ('Thưc Phẩm Tươi Sống','0,3%'),('Thực Phẩm Chế Biến Sẵn','0,4%'),('Thực Phẩm Có Đường','0,2%'),('Thực Phẩm Từ Bột & Gạo','0,1%');";
        db.execSQL(insert_LoaiHang);

        String tb_SanPham = "CREATE TABLE SanPham (maSP INTEGER PRIMARY KEY AUTOINCREMENT , tenSP TEXT NOT NULL , maLH INTEGER REFERENCES LoaiHang (maLH), HSD TEXT NOT NULL, donGia INTEGER NOT NULL, soLuong INTEGER NOT NULL);";
        db.execSQL(tb_SanPham);
        String insert_SanPham = "INSERT INTO SanPham (tenSP,maLH,HSD,donGia,soLuong) VALUES ('kẹo milo',2,'12-12-2026',12000,20),('kẹo milo',2,'12-12-2026',12000,20),('kẹo milo',2,'12-12-2026',12000,20),('kẹo milo',2,'12-12-2026',12000,20);";
        db.execSQL(insert_SanPham);


        String tb_HoaDon = "CREATE TABLE HoaDon (maHD INTEGER PRIMARY KEY AUTOINCREMENT, maNV TEXT REFERENCES NhanVien(maNV), maTV INTEGER REFERENCES ThanhVien(maTV), maSP INTEGER REFERENCES SanPham(maSP), soLuong INTEGER NOT NULL, donGia INTEGER NOT NULL, ngayXuat TEXT NOT NULL, nhap_xuat INTEGER NOT NULL);";
        db.execSQL(tb_HoaDon);
        String insert_HoaDon = "INSERT INTO HoaDon ( maNV, maTV, maSP, soLuong, donGia, ngayXuat, nhap_xuat) VALUES ('NvA',1,1,1000,12000,'1-4-2024',1),('NvB',2,2,1000,12000,'1-4-2024',1),('NvA',1,2,1000,12000,'1-4-2024',0);";
        db.execSQL(insert_HoaDon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS NhanVien");
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS LoaiHang");
            onCreate(db);
        }
    }
}
