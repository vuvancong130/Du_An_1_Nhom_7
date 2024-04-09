package com.example.du_an_1_nhom_7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.example.du_an_1_nhom_7.DbHelper.MyDbhelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context){
        MyDbhelper myDbhelper = new MyDbhelper(context);
        db = myDbhelper.getWritableDatabase();
    }
    public long insert(ThanhVienDTO thanhVienDTO) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVienDTO.getHo_ten());
        values.put("namSinh", thanhVienDTO.getNam_sinh());
        values.put("gioiTinh", thanhVienDTO.getGioi_tinh());
        if (thanhVienDTO.getSo_dien_thoai() != null && !thanhVienDTO.getSo_dien_thoai().isEmpty()) {
            values.put("sodienThoai", thanhVienDTO.getSo_dien_thoai());
        } else {
            return -1;
        }
        return db.insert("ThanhVien", null, values);
    }
    public int update(ThanhVienDTO thanhVienDTO) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVienDTO.getHo_ten());
        values.put("namSinh", thanhVienDTO.getNam_sinh());
        values.put("gioiTinh", thanhVienDTO.getGioi_tinh());
        values.put("sodienThoai", thanhVienDTO.getSo_dien_thoai());

        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(thanhVienDTO.getMaTV())});
    }
    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }
    public List<ThanhVienDTO> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }
    public ThanhVienDTO getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV = ?";
        List<ThanhVienDTO> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @SuppressLint("Range")
    private List<ThanhVienDTO> getData (String sql, String... selectionArgs){
        List<ThanhVienDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        if (c != null && c.moveToFirst()) {
            do {
                ThanhVienDTO obj = new ThanhVienDTO(
                        c.getInt(c.getColumnIndex("maTV")),
                        c.getString(c.getColumnIndex("hoTen")),
                        c.getString(c.getColumnIndex("namSinh")),
                        c.getString(c.getColumnIndex("sodienThoai")),
                        c.getInt(c.getColumnIndex("gioiTinh"))
                        );
                list.add(obj);
            } while (c.moveToNext());
            c.close();
        }
        return list;
    }
    public boolean checkThanhVienIsUsed(Context context,int thanhVienId) {
        MyDbhelper dbhelper = new MyDbhelper(context);
        db = dbhelper.getReadableDatabase();


        String query = "SELECT COUNT(*) FROM HoaDon WHERE maTV = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(thanhVienId)});

        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;
        }

        return false;
    }
}
