package com.example.du_an_1_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_1_nhom_7.DAO.NhanVienDAO;
import com.google.android.material.textfield.TextInputEditText;

public class Activity_Quen_Mat_Khau extends AppCompatActivity {
    TextInputEditText resetpass;
    NhanVienDAO nhanVienDAO;
    Button xacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        resetpass = findViewById(R.id.PassWordResetPass);
        xacnhan = findViewById(R.id.btn_xacnhan);
        nhanVienDAO = new NhanVienDAO(this);

        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = resetpass.getText().toString();
                String id = getIntent().getStringExtra("id_key");
                String matKhau = getIntent().getStringExtra("id_matKhau");

                int result = nhanVienDAO.ResetPassword(id, matKhau, newpass);
                if (result == 1){
                    Toast.makeText(Activity_Quen_Mat_Khau.this, "Đặt lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else  {
                    Toast.makeText(Activity_Quen_Mat_Khau.this, "Đặt lại mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}