package com.example.du_an_1_nhom_7;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.du_an_1_nhom_7.Adapter.ThanhVienAdapter;
import com.example.du_an_1_nhom_7.DAO.ThanhVienDAO;
import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_QL_ThanhVien extends Fragment {
    RecyclerView rc_thanh_vien;
    FloatingActionButton fab_thanh_vien;
    ArrayList<ThanhVienDTO> list;
    Dialog dialog;
    EditText tiedt_add_maTV, tiedt_add_tenTV, tiedt_add_sodienthoai, tiedt_add_namSinh, tiedt_add_gioiTinh;
    Button btn_addTV, btn_huy_addTV;
    RadioGroup rdo_gr;
    RadioButton rdo_nam,rdo_nu;
    static ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVienDTO thanhVienDTO;
    LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__q_l__thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_thanh_vien = view.findViewById(R.id.rc_thanh_vien);
        fab_thanh_vien = view.findViewById(R.id.fab_thanh_vien);
        thanhVienDAO =  new ThanhVienDAO(getActivity());
        capNhatLv();
        fab_thanh_vien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
    }

    public void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanh_vien);
        tiedt_add_maTV = dialog.findViewById(R.id.tiedt_add_maTV);
        tiedt_add_tenTV = dialog.findViewById(R.id.tiedt_add_tenTV);
        tiedt_add_sodienthoai = dialog.findViewById(R.id.tiedt_add_sodienthoai);
        rdo_gr=dialog.findViewById(R.id.rdo_gr_tv);
        rdo_nam=dialog.findViewById(R.id.rdo_nam);
        rdo_nu=dialog.findViewById(R.id.rdo_nu);
        tiedt_add_namSinh = dialog.findViewById(R.id.tiedt_add_namSinh);
        btn_addTV = dialog.findViewById(R.id.btn_addTV);
        btn_huy_addTV = dialog.findViewById(R.id.btn_huy_addTV);


        btn_huy_addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVienDTO = new ThanhVienDTO();
                thanhVienDTO.setHo_ten(tiedt_add_tenTV.getText().toString());
                thanhVienDTO.setSo_dien_thoai(tiedt_add_sodienthoai.getText().toString());
                rdo_gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(rdo_nam.isChecked()){
                            thanhVienDTO.setGioi_tinh(1);
                        }else if(rdo_nu.isChecked()){
                            thanhVienDTO.setGioi_tinh(0);
                        }
                    }
                });

                thanhVienDTO.setNam_sinh(tiedt_add_namSinh.getText().toString());

                if (validate() > 0){
                    if (type == 0) {
                        if (thanhVienDAO.insert(thanhVienDTO) > 0) {
                            Toast.makeText(context, "Thêm thành công.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại.", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        thanhVienDTO.setMaTV(Integer.parseInt(tiedt_add_maTV.getText().toString()));
                        if (thanhVienDAO.update(thanhVienDTO) > 0) {
                            Toast.makeText(context, "Sửa thành công.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private int validate() {
        int check = 1;

        String ten = tiedt_add_tenTV.getText().toString();
        String namSinh = tiedt_add_namSinh.getText().toString();

        String sodienThoai = tiedt_add_sodienthoai.getText().toString();

        if (ten.isEmpty()) {
            tiedt_add_tenTV.setError("Vui lòng nhập tên thành viên!");
            check = -1;
        } else if (namSinh.isEmpty()) {
            tiedt_add_namSinh.setError("Vui lòng nhập năm sinh!");
            check = -1;
        }  else if (sodienThoai.isEmpty()) {
            tiedt_add_sodienthoai.setError("Vui lòng nhập số điện thoại!");
            check = -1;
        } else {
            try {
                // Kiểm tra năm sinh có đúng định dạng số không
                Integer.parseInt(namSinh);
            } catch (NumberFormatException e) {
                tiedt_add_namSinh.setError("Sai định dạng, phải nhập số!");
                check = -1;
            }
        }

        return check;


    }

    public void capNhatLv(){
        list = (ArrayList<ThanhVienDTO>) thanhVienDAO.getAll();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rc_thanh_vien.setLayoutManager(linearLayoutManager);
        thanhVienAdapter = new ThanhVienAdapter(getActivity(), list);
        rc_thanh_vien.setAdapter(thanhVienAdapter);
    }
}