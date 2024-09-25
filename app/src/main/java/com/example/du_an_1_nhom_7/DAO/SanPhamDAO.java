package com.example.du_an_1_nhom_7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1_nhom_7.DTO.SanPhamDTO;
import com.example.du_an_1_nhom_7.DbHelper.MyDbhelper;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    SQLiteDatabase db;
    MyDbhelper dbHelper;
    public SanPhamDAO(Context context) {
        dbHelper = new MyDbhelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPhamDTO sanPhamDTO) {
        ContentValues values = new ContentValues();
        values.put("tenSP", sanPhamDTO.getTen_SP());
        values.put("maLH", sanPhamDTO.getMa_loai());
        values.put("HSD", sanPhamDTO.getHsd());
        values.put("donGia", sanPhamDTO.getDon_gia());
        values.put("soLuong", sanPhamDTO.getSo_luong());
        values.put("img",sanPhamDTO.getImg());
        return db.insert("SanPham", null, values);

    }

    public int update(SanPhamDTO sanPhamDTO) {
        ContentValues values = new ContentValues();
        values.put("tenSP", sanPhamDTO.getTen_SP());
        values.put("maLH", sanPhamDTO.getMa_loai());
        values.put("HSD", sanPhamDTO.getHsd());
        values.put("donGia", sanPhamDTO.getDon_gia());
        values.put("soLuong", sanPhamDTO.getSo_luong());
        values.put("img",sanPhamDTO.getImg());
        return db.update("SanPham", values, "maSP=?", new String[]{String.valueOf(sanPhamDTO.getMa_SP())});

    }

    public int delete(String id) {
        return db.delete("SanPham", "maSP=?", new String[]{id});

    }


    // get all list
    public List<SanPhamDTO> getAll() {
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

    //get data by id
    public SanPhamDTO getID(String id) {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        List<SanPhamDTO> list = getData(sql, id);
        return list.get(0);
    }


    //get data nhieu tham so
    @SuppressLint("Range")
    private List<SanPhamDTO> getData(String sql, String... selectionArgs) {

        List<SanPhamDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            SanPhamDTO obj = new SanPhamDTO(c.getInt(c.getColumnIndex("maSP")),
                    c.getString(c.getColumnIndex("tenSP")),
                    c.getInt(c.getColumnIndex("maLH")),
                    c.getString(c.getColumnIndex("HSD")),
                    c.getInt(c.getColumnIndex("donGia")),
                    c.getInt(c.getColumnIndex("soLuong")),
                    c.getString(c.getColumnIndex("img")));
            list.add(obj);
        }

        return list;
    }
    public boolean checkSanPhamIsUsed(Context context,int sanPhamId) {
        MyDbhelper dbhelper = new MyDbhelper(context);
        db = dbhelper.getReadableDatabase();

        // Truy vấn kiểm tra xem loại hàng có tồn tại trong bất kỳ sản phẩm nào không
        String query = "SELECT COUNT(*) FROM HoaDon WHERE maSP = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(sanPhamId)});

        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;
        }

        return false;
    }

}
