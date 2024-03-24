package com.example.du_an_1_nhom_7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1_nhom_7.DTO.HoaDonDTO;
import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.DbHelper.MyDbhelper;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    MyDbhelper mydb;
    SQLiteDatabase db;
    public HoaDonDAO(Context context){
        mydb=new MyDbhelper(context);
        db=mydb.getWritableDatabase();
    }

    public long insert(HoaDonDTO hd){
        ContentValues values=new ContentValues();
        values.put("maSP",hd.getMaSP());
        values.put("maNV",hd.getMaNV());
        values.put("maTV",hd.getMaTV());
        values.put("ngay",hd.getNgay());
        values.put("soLuong",hd.getSoLuong());
        values.put("trangThai",hd.getTrangThai());
        values.put("nhapXuat",hd.getNhapXuat());
        return db.insert("HoaDon",null,values);
    }
    public int update(HoaDonDTO hd){
        ContentValues values=new ContentValues();
        values.put("maSP",hd.getMaSP());
        values.put("maNV",hd.getMaNV());
        values.put("maTV",hd.getMaTV());
        values.put("ngay",hd.getNgay());
        values.put("soLuong",hd.getSoLuong());
        values.put("trangThai",hd.getTrangThai());
        values.put("nhapXuat",hd.getNhapXuat());
        String[] dk=new String[]{String.valueOf(hd.getMaHD())};
        return db.update("HoaDon",values,"maHD=?",dk);
    }
    public int delete(String id){
        return db.delete("HoaDon","maHD=?",new String[]{id});
    }
    public ArrayList<HoaDonDTO> getAll(){
        String sql="SELECT * FROM HOADON";
        return getData(sql);
    }
    //maSP,maNV,maTV,ngay,soLuong,trangThai,nhapXuat
    @SuppressLint("Range")
    private ArrayList<HoaDonDTO> getData(String sql,String...selectionArgs){
        ArrayList<HoaDonDTO> list_hd=new ArrayList<>();
        Cursor c=db.rawQuery(sql, selectionArgs);
        while(c.moveToNext()){
            HoaDonDTO obj=new HoaDonDTO(
                    c.getInt(c.getColumnIndex("maHD")),
                    c.getString(c.getColumnIndex("maNV")),
                    c.getInt(c.getColumnIndex("maTV")),
                    c.getInt(c.getColumnIndex("maSP")),
                    c.getString(c.getColumnIndex("ngay")),
                    c.getInt(c.getColumnIndex("soLuong")),
                    c.getInt(c.getColumnIndex("trangThai")),
                    c.getInt(c.getColumnIndex("nhapXuat"))
            );
            list_hd.add(obj);
        }
        return list_hd;
    }
    public HoaDonDTO getID(String id){
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        List<HoaDonDTO> list = getData(sql, id);
        return list.get(0);
    }
}
