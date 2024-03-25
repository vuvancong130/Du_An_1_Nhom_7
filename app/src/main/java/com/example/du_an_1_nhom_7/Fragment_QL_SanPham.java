package com.example.du_an_1_nhom_7;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_1_nhom_7.Adapter.SanPhamAdapter;
import com.example.du_an_1_nhom_7.Adapter.Spinner_LoaiHang_Adapter;
import com.example.du_an_1_nhom_7.DAO.LoaiHangDAO;
import com.example.du_an_1_nhom_7.DAO.SanPhamDAO;
import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.DTO.SanPhamDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_QL_SanPham extends Fragment {
    RecyclerView rc_sp;
    FloatingActionButton fab_them_sanpham;
    SanPhamAdapter adapter;
    Dialog dialog;
    SanPhamDAO sanPhamDAO;
    SanPhamDTO sanPhamDTO;
    ArrayList<SanPhamDTO> list_SP;
    EditText tiedt_add_maSP,tiedt_add_tenSP,tiedt_add_HSD,tiedt_add_donGia,tiedt_add_soLuong;
    Spinner spn_add_SP;
    Button btn_huy_addSP,btn_addSP;
    LoaiHangDAO lhdao;
    ArrayList<LoaiHangDTO> list_lh;

    Spinner_LoaiHang_Adapter spnadt;
    int maloaihang;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment__q_l__san_pham, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_sp = view.findViewById(R.id.rc_san_pham);
        fab_them_sanpham = view.findViewById(R.id.fab_san_pham);

       capNhatLV();

        fab_them_sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opendialog();

            }
        });
    }
    public void capNhatLV(){
        sanPhamDAO=new SanPhamDAO(getContext());
        list_SP= (ArrayList<SanPhamDTO>) sanPhamDAO.getAll();
        adapter= new SanPhamAdapter(list_SP,getContext());
        rc_sp.setAdapter(adapter);
    }
    public void opendialog(){
        dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_san_pham);

        tiedt_add_maSP=dialog.findViewById(R.id.tiedt_add_maSP);
        tiedt_add_tenSP=dialog.findViewById(R.id.tiedt_add_tenSP);
        spn_add_SP=dialog.findViewById(R.id.sp_lh_addSP);
        tiedt_add_HSD=dialog.findViewById(R.id.tiedt_add_HSD);
        tiedt_add_donGia=dialog.findViewById(R.id.tiedt_add_DonGia);
        tiedt_add_soLuong=dialog.findViewById(R.id.tiedt_add_SoLuong);
        btn_addSP=dialog.findViewById(R.id.btn_addSP);
        btn_huy_addSP=dialog.findViewById(R.id.btn_huy_addSP);

        tiedt_add_maSP.setEnabled(false);



            //cho danh sách lên spinner
            list_lh=new ArrayList<>();
            lhdao=new LoaiHangDAO(getContext());
            list_lh= (ArrayList<LoaiHangDTO>) lhdao.getAll();
            spnadt=new Spinner_LoaiHang_Adapter(getContext(),list_lh);
            spn_add_SP.setAdapter(spnadt);
            spn_add_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    maloaihang=list_lh.get(position).getMa_loai_hang();
                    Toast.makeText(getContext(), "Chọn "+list_lh.get(position).getTen_loai_hang(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        btn_huy_addSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_addSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SanPhamDTO sp=new SanPhamDTO();
                sp.setTen_SP(tiedt_add_tenSP.getText().toString());
                sp.setMa_loai(maloaihang);
                sp.setHsd(tiedt_add_HSD.getText().toString());


                if(validate()>0){
                    sp.setDon_gia(Integer.parseInt(tiedt_add_donGia.getText().toString()));
                    sp.setSo_luong(Integer.parseInt(tiedt_add_soLuong.getText().toString()));

                           if(sanPhamDAO.insert(sp)>0){
                               Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                           }

                       capNhatLV();
                       dialog.dismiss();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();



    }
    private int validate(){
       int check=1;
       String ten=tiedt_add_tenSP.getText().toString();
       String hsd=tiedt_add_HSD.getText().toString();

       String dongiatext=tiedt_add_donGia.getText().toString();
       String soluongtext=tiedt_add_soLuong.getText().toString();

       if(ten.isEmpty()){
           tiedt_add_tenSP.setError("Vui lòng nhập tên sản phẩm");
           check=-1;
       }else if(hsd.isEmpty()){
           tiedt_add_HSD.setError("Vui lòng nhập hạn sử dụng");
           check=-1;
       }else if(dongiatext.isEmpty()) {
           tiedt_add_donGia.setError("Vui lòng nhập đơn giá");
           check=-1;
       }else if(soluongtext.isEmpty()) {
           tiedt_add_soLuong.setError("Vui lòng nhập số lượng");
           check=-1;
       }else{
           try {
               int dongia= Integer.parseInt(dongiatext);
              int soluong=Integer.parseInt(soluongtext);


           }catch (NumberFormatException e){
               tiedt_add_donGia.setError("Vui lòng nhập số");
               tiedt_add_soLuong.setError("Vui lòng nhập số");
                check=-1;
           }
       }
       return check;
    }
}