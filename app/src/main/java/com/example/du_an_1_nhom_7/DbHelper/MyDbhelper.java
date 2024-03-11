package com.example.du_an_1_nhom_7.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbhelper extends SQLiteOpenHelper {
    public MyDbhelper(Context context) {
        super(context, "QLKhoHang", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_NhanVien = "CREATE TABLE NhanVien (maNV TEXT  PRIMARY KEY , hoTen TEXT NOT NULL, matKhau TEXT NOT NULL);";
        db.execSQL(tb_NhanVien);
        String insert_NhanVien = "INSERT INTO NhanVien (maNV,hoTen,matKhau) VALUES ('Admin','admin','123'), ('NvA','Nguyen Van A','123'), ('NvB','Nguyen Van B','123');";
        db.execSQL(insert_NhanVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS NhanVien");
            onCreate(db);
        }
    }
}
