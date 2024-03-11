package com.example.du_an_1_nhom_7.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.example.du_an_1_nhom_7.DbHelper.MyDbhelper;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    SQLiteDatabase db;
    MyDbhelper dbHelper;
    public NhanVienDAO(Context context) {
        dbHelper = new MyDbhelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(NhanVienDTO nhanVienDTO){
        ContentValues values = new ContentValues();
        values.put("maNV", nhanVienDTO.getMaNV());
        values.put("hoTen", nhanVienDTO.getHo_ten());
        values.put("matKhau", nhanVienDTO.getMat_khau());

        return db.insert("NhanVien", null, values);
    }
    public int update(NhanVienDTO nhanVienDTO) {
        ContentValues values = new ContentValues();
        values.put("maNV", nhanVienDTO.getMaNV());
        values.put("hoTen", nhanVienDTO.getHo_ten());
        values.put("matKhau", nhanVienDTO.getMat_khau());

        return db.update("NhanVien", values, "maNV=?", new String[]{nhanVienDTO.getMaNV()});

    }
    public int delete(String id) {
        return db.delete("NhanVien", "maNV=?", new String[]{id});
    }
    private List<NhanVienDTO> getData(String sql, String... selectionArgs) {

        List<NhanVienDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            NhanVienDTO obj = new NhanVienDTO(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2));

            list.add(obj);
        }
        return list;
    }
    // get all list
    public List<NhanVienDTO> getAll() {
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }
    //get data by id
    public NhanVienDTO getID(String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        List<NhanVienDTO> list = getData(sql, id);
        Log.d("zzzz", "getID: " + list.size());
        return list.get(0);
    }
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ? AND matKhau = ?";
        List<NhanVienDTO> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
}
