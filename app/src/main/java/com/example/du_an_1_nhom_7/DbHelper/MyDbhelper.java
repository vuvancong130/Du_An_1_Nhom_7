package com.example.du_an_1_nhom_7.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbhelper extends SQLiteOpenHelper {
    public MyDbhelper(Context context) {
        super(context, "QLKhoHang", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_NhanVien = "CREATE TABLE NhanVien (maNV TEXT  PRIMARY KEY , hoTen TEXT NOT NULL, matKhau TEXT NOT NULL, sdt TEXT NOT NULL);";
        db.execSQL(tb_NhanVien);
        String insert_NhanVien = "INSERT INTO NhanVien (maNV,hoTen,matKhau, sdt) VALUES ('Admin','admin','123', '0932167298'), ('NvA','Nguyen Van A','123','0246993156'), ('NvB','Nguyen Van B','123','0432656793');";
        db.execSQL(insert_NhanVien);

        String tb_ThanhVien = "CREATE TABLE ThanhVien (maTV INTEGER PRIMARY KEY AUTOINCREMENT , hoTen TEXT NOT NULL , namSinh TEXT NOT NULL , gioiTinh integer  NOT NULL , sodienThoai TEXT NOT NULL);";
        db.execSQL(tb_ThanhVien);
        String insert_ThanhVien = "INSERT INTO ThanhVien (hoTen,namSinh,gioiTinh,sodienThoai) VALUES ('Cao Văn C','1994',1,'0976336726'), ('Tran Minh T','1999',1,'0264532963'), ('Pham Thuy K','2001',0,'0342998678'), ('Kieu Minh A','1987',1,'0364723456');";
        db.execSQL(insert_ThanhVien);

        String tb_LoaiHang = "CREATE TABLE LoaiHang (maLH INTEGER PRIMARY KEY AUTOINCREMENT , tenLH TEXT NOT NULL , moTa TEXT NOT NULL);";
        db.execSQL(tb_LoaiHang);
        String insert_LoaiHang = "INSERT INTO LoaiHang (tenLH,moTa) VALUES ('Thưc Phẩm Tươi Sống','Thực phẩm tươi sống là những loại thực phẩm được thu hoạch, nuôi trồng hoặc chế biến mà không qua bất kỳ quá trình nhiệt độ cao nào. Nhờ vào việc không trải qua các phương pháp chế biến nhiệt độ cao, thực phẩm này giữ nguyên được hàm lượng dưỡng chất và độ tươi ngon của nguyên liệu gốc'),"+
                            "('Thực Phẩm Chế Biến Sẵn','Thực phẩm chế biến sẵn là những sản phẩm đã trải qua quá trình chế biến từ nguyên liệu tự nhiên thành sản phẩm cuối cùng có thể sử dụng ngay mà không cần chế biến thêm'), "+
                              "('Thực Phẩm Có Đường','Thực phẩm có đường là nhóm thực phẩm chứa một lượng đường tự nhiên hoặc đường thêm vào để tăng hương vị và độ ngọt'), "+
                                "('Thực Phẩm Từ Bột & Gạo','Thực phẩm từ bột gạo là các sản phẩm được chế biến từ nguyên liệu chính là gạo, một loại ngũ cốc quen thuộc và phổ biến trên toàn thế giới')";
        db.execSQL(insert_LoaiHang);

        String tb_SanPham = "CREATE TABLE SanPham (maSP INTEGER PRIMARY KEY AUTOINCREMENT , tenSP TEXT NOT NULL , maLH INTEGER REFERENCES LoaiHang (maLH), HSD TEXT NOT NULL, donGia INTEGER NOT NULL, soLuong INTEGER NOT NULL,img text not null);";
        db.execSQL(tb_SanPham);
        String insert_SanPham = "INSERT INTO SanPham (tenSP,maLH,HSD,donGia,soLuong,img) VALUES ('kẹo milo',2,'12-12-2026',12000,20,'https://product.hstatic.net/1000304337/product/f58655ca80125e3a87e039da5b0a0553_b6bf731b8506400784de6c6b66495751_1024x1024.jpg'),('Bánh gạo ',2,'12-12-2026',12000,20,'https://cdn.tgdd.vn/Products/Images/3361/83120/banh-gao-nhat-vi-shouyu-mat-ong-ichi-goi-100g-600x600.jpg'),('Lương khô',2,'12-12-2026',12000,20,'https://duchieu.com.vn/profiles/duchieucomvn/uploads/attach/1505450703_tuiluongkho.jpg'),('Mứt tết',2,'12-12-2026',12000,20,'https://cdn.tgdd.vn/2021/01/CookProduct/tong-hop-20-cach-lam-cac-loai-mut-keo-thom-ngon-khong-the-thieu-trong-dip-tet-1-1200x676.jpg');";
        db.execSQL(insert_SanPham);


        String tb_HoaDon = "CREATE TABLE HoaDon (maHD INTEGER PRIMARY KEY AUTOINCREMENT, maNV TEXT REFERENCES NhanVien(maNV), maTV INTEGER REFERENCES ThanhVien(maTV), maSP INTEGER REFERENCES SanPham(maSP), soLuong INTEGER NOT NULL, donGia INTEGER NOT NULL, ngayXuat TEXT NOT NULL, nhap_xuat INTEGER NOT NULL,trangThai integer not null);";
        db.execSQL(tb_HoaDon);
        String insert_HoaDon = "INSERT INTO HoaDon ( maNV, maTV, maSP, soLuong, donGia, ngayXuat, nhap_xuat,trangThai) VALUES ('NvA',1,1,1000,12000,'01-04-2024',1,0),('NvB',2,2,1000,12000,'01-04-2024',1,1),('NvA',1,2,1000,12000,'01-04-2024',1,1);";
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
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS HoaDon");
            onCreate(db);
        }
    }
}
