package com.example.du_an_1_nhom_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1_nhom_7.DAO.NhanVienDAO;
import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.google.android.material.textfield.TextInputEditText;

public class Acticity_Dang_Nhap extends AppCompatActivity {

    Button btn_dangnhap, btn_huydangnhap;
    CheckBox chk_luumk;
    TextInputEditText tiedt_ten_dang_nhap, tiedt_mat_khau;
    TextView tvforgotpassword;
    NhanVienDAO nhanVienDAO;
    NhanVienDTO nhanVienDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_huydangnhap = findViewById(R.id.btn_huydangnhap);
        chk_luumk = findViewById(R.id.chk_luumk);
        tiedt_ten_dang_nhap = findViewById(R.id.tiedt_ten_dang_nhap);
        tiedt_mat_khau = findViewById(R.id.tiedt_mat_khau);
        tvforgotpassword = findViewById(R.id.tv_ForgotPassword);
        nhanVienDTO = new NhanVienDTO();
        nhanVienDAO = new NhanVienDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        tiedt_ten_dang_nhap.setText(pref.getString("USERNAME", ""));
        tiedt_mat_khau.setText(pref.getString("PASSWORD", ""));

        chk_luumk.setChecked(pref.getBoolean("REMEMBER", false));

        btn_huydangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiedt_ten_dang_nhap.setText(null);
                tiedt_mat_khau.setText(null);
            }
        });

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Acticity_DangNhap.this, Activity_Home.class));
                checkLogin();
            }
        });

        tvforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogForgot();
            }
        });
    }

    public void checkLogin() {
        String hoTen = tiedt_ten_dang_nhap.getText().toString();
        String matKhau = tiedt_mat_khau.getText().toString();

        boolean error = false;

        if (hoTen.isEmpty()) {
            tiedt_ten_dang_nhap.setError("Vui lòng nhập tên đăng nhập!");
            error = true;
        }
        if (matKhau.isEmpty()) {
            tiedt_mat_khau.setError("Vui lòng nhập mật khẩu!");
            error = true;

        }


        if (!error) {

            try {
                if (nhanVienDAO.checkLogin(hoTen, matKhau) > 0) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(hoTen, matKhau, chk_luumk.isChecked());

                    Intent intent = new Intent(this, Activity_Home.class);
                    intent.putExtra("hoTen", hoTen);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }


    }

    public void rememberUser(String hoTen, String matKhau, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", hoTen);
            editor.putString("PASSWORD", matKhau);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();

    }

    private void ShowDialogForgot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_xac_nhan, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        TextInputEditText id = view.findViewById(R.id.FgId);
        TextInputEditText sdt = view.findViewById(R.id.FgSdt);
        Button btn_send = view.findViewById(R.id.btn_add);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idd = id.getText().toString();
                String sdtt = sdt.getText().toString();
                String matkhau = nhanVienDAO.ForgotPassword(idd, sdtt);

                if (matkhau.equals("")){
                    Toast.makeText(Acticity_Dang_Nhap.this,"Tài Khoản Không Tồn Tại :((", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Acticity_Dang_Nhap.this, Activity_Quen_Mat_Khau.class);
                    intent.putExtra("id_key", idd);
                    intent.putExtra("id_matKhau", sdtt);
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
    }

}