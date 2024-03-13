package com.example.du_an_1_nhom_7.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbhelper extends SQLiteOpenHelper {
    public MyDbhelper(Context context) {
        super(context, "QLKhoHang", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_NhanVien = "CREATE TABLE NhanVien (maNV TEXT  PRIMARY KEY , hoTen TEXT NOT NULL, matKhau TEXT NOT NULL);";
        db.execSQL(tb_NhanVien);
        String insert_NhanVien = "INSERT INTO NhanVien (maNV,hoTen,matKhau) VALUES ('Admin','admin','123'), ('NvA','Nguyen Van A','123'), ('NvB','Nguyen Van B','123');";
        db.execSQL(insert_NhanVien);

        String tb_ThanhVien = "CREATE TABLE ThanhVien (maTV INTEGER PRIMARY KEY AUTOINCREMENT , hoTen TEXT NOT NULL , namSinh TEXT NOT NULL , gioiTinh TEXT  NOT NULL , sodienThoai TEXT NOT NULL);";
        db.execSQL(tb_ThanhVien);
        String insert_ThanhVien = "INSERT INTO ThanhVien (hoTen,namSinh,gioiTinh,sodienThoai) VALUES ('Nguyen Anh B','1994','Nam','0976336726'), ('Tran Minh T','1999','Nam','0264532963'), ('Pham Thuy K','2001','Nữ','0342998678'), ('Kieu Minh A','1987','Nam','0364723456');";
        db.execSQL(insert_ThanhVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS NhanVien");
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            onCreate(db);
        }
    }
}
