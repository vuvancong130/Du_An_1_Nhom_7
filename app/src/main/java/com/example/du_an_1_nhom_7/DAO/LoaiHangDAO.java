package com.example.du_an_1_nhom_7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.DbHelper.MyDbhelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiHangDAO {
    private SQLiteDatabase db;
    public LoaiHangDAO(Context context){
        MyDbhelper dbhelper = new MyDbhelper(context);
        db = dbhelper.getWritableDatabase();
    }
    public long insert(LoaiHangDTO loaiHangDTO){
        ContentValues values = new ContentValues();
        values.put("tenLH",loaiHangDTO.getTen_loai_hang());
        values.put("thue",loaiHangDTO.getThue());

        return db.insert("LoaiHang", null, values);
    }
    public int update(LoaiHangDTO loaiHangDTO){
        ContentValues values = new ContentValues();
        values.put("tenLH",loaiHangDTO.getTen_loai_hang());
        values.put("thue",loaiHangDTO.getThue());

        return db.update("LoaiHang", values, "maLH=?", new String[]{String.valueOf(loaiHangDTO.getMa_loai_hang())});
    }
    public int delete(String id) {return db.delete("LoaiHang", "maLH=?", new String[]{id});}
    public List<LoaiHangDTO> getAll(){
        String sql = "SELECT * FROM LoaiHang";
        return getData(sql);
    }
    public LoaiHangDTO getID(String id){
        String sql = "SELECT * FROM LoaiHang WHERE maLH = ?";
        List<LoaiHangDTO> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<LoaiHangDTO> getData(String sql, String...selectionArgs){
        List<LoaiHangDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            LoaiHangDTO obj = new LoaiHangDTO(c.getInt(c.getColumnIndex("maLH")),
                    c.getString(c.getColumnIndex("tenLH")),
                    c.getString(c.getColumnIndex("thue")));
            list.add(obj);
        }
        return list;
    }
}
