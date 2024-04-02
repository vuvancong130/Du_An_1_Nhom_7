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
        values.put("sdt", nhanVienDTO.getSdt());

        return db.insert("NhanVien", null, values);
    }
    public int update(NhanVienDTO nhanVienDTO) {
        ContentValues values = new ContentValues();
        values.put("maNV", nhanVienDTO.getMaNV());
        values.put("hoTen", nhanVienDTO.getHo_ten());
        values.put("matKhau", nhanVienDTO.getMat_khau());
        values.put("sdt", nhanVienDTO.getSdt());

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
                    c.getString(2),
                    c.getString(3));

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
    public NhanVienDTO getID(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        List<NhanVienDTO> list = getData(sql, maNV);
        Log.d("zzzz", "getID: " + list.size());
        return list.get(0);
    }
    public int checkLogin(String maNV, String matKhau) {
        String sql = "SELECT * FROM NhanVien WHERE maNV = ? AND matKhau = ?";
        List<NhanVienDTO> list = getData(sql, maNV, matKhau);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
    public int ResetPass (String maNV, String newPass) {
        ContentValues values = new ContentValues();
        values.put("matKhau", newPass);
        return db.update("NhanVien", values, "maNV=?", new String[]{maNV});
    }
    public int ResetPassword(String maNV, String sdt, String newPass) {
        // Kiểm tra xem thông tin username và email có trùng khớp không
        String checkPass = ForgotPassword(maNV, sdt);
        if (!checkPass.equals("")) {
            return ResetPass(maNV, newPass);
        } else {
            return 1;
        }
    }

    // forgot
    public String ForgotPassword(String maNV, String sdt) {
        Cursor c = db.rawQuery("Select matKhau From NhanVien where maNV = ? AND sdt = ?", new String[]{maNV, sdt});
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getString(0);
        } else {
            return "";
        }
    }
}
