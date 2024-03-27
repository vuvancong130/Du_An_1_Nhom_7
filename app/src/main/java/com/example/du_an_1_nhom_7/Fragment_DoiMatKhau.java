package com.example.du_an_1_nhom_7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_1_nhom_7.DAO.NhanVienDAO;
import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.google.android.material.textfield.TextInputEditText;


public class Fragment_DoiMatKhau extends Fragment {

    Button btn_luu_mkmoi, btn_huy_luu_mkmoi;
    TextInputEditText tiedt_mkcu, tiedt_mkmoi, tiedt_nhaplaimkmoi;
    NhanVienDAO nhanVienDAO;



    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__doi_mat_khau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_luu_mkmoi = view.findViewById(R.id.btn_luu_mkmoi);
        btn_huy_luu_mkmoi = view.findViewById(R.id.btn_huy_luu_mkmoi);
        tiedt_mkcu = view.findViewById(R.id.tiedt_mkcu);
        tiedt_mkmoi = view.findViewById(R.id.tiedt_mkmoi);
        tiedt_nhaplaimkmoi = view.findViewById(R.id.tiedt_nhaplaimkmoi);
        nhanVienDAO = new NhanVienDAO(getContext());

        btn_huy_luu_mkmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiedt_mkcu.setText(null);
                tiedt_mkmoi.setText(null);
                tiedt_nhaplaimkmoi.setText(null);
            }
        });

        btn_luu_mkmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if (validate() > 0) {
                    NhanVienDTO nhanVienDTO = nhanVienDAO.getID(user);
                    nhanVienDTO.setMat_khau(tiedt_mkmoi.getText().toString());
                    if (nhanVienDAO.update(nhanVienDTO) > 0) {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                        tiedt_mkcu.setText(null);
                        tiedt_mkmoi.setText(null);
                        tiedt_nhaplaimkmoi.setText(null);

                    } else {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thất bại.", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public int validate() {
        int check = 1;
        if (tiedt_mkcu.getText().length() == 0 || tiedt_mkmoi.length() == 0 || tiedt_nhaplaimkmoi.length() == 0) {
            if (tiedt_mkcu.getText().length() == 0) {
                tiedt_mkcu.setError("Vui lòng nhập mật khẩu cũ!");
                check = -1;
            }
            if (tiedt_mkmoi.length() == 0) {
                tiedt_mkmoi.setError("Vui lòng nhập mật khẩu mới!");
                check = -1;
            }
            if (tiedt_nhaplaimkmoi.length() == 0) {
                tiedt_nhaplaimkmoi.setError("Vui lòng nhập mật khẩu!");
                check = -1;
            }
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String mkcu = pref.getString("PASSWORD", "");
            String mkmoi = tiedt_mkmoi.getText().toString();
            String mkmoi2 = tiedt_nhaplaimkmoi.getText().toString();
            if (!mkcu.equals(tiedt_mkcu.getText().toString())) {
                tiedt_mkcu.setError("Sai mật khẩu!");
                check = -1;
            }
            if (!mkmoi.equals(mkmoi2)) {
                tiedt_nhaplaimkmoi.setError("Mật khẩu không trùng khớp!");
                check = -1;
            }
        }
        return check;
    }
}