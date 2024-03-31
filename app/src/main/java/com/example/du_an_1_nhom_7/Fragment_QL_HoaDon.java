package com.example.du_an_1_nhom_7;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1_nhom_7.Adapter.HoaDonAdapter;
import com.example.du_an_1_nhom_7.Adapter.Spinner_LoaiHang_Adapter;
import com.example.du_an_1_nhom_7.Adapter.Spinner_NhanVien_Adapter;
import com.example.du_an_1_nhom_7.Adapter.Spinner_SanPham_Adapter;
import com.example.du_an_1_nhom_7.Adapter.Spinner_ThanhVien_Adapter;
import com.example.du_an_1_nhom_7.DAO.HoaDonDAO;
import com.example.du_an_1_nhom_7.DAO.LoaiHangDAO;
import com.example.du_an_1_nhom_7.DAO.NhanVienDAO;
import com.example.du_an_1_nhom_7.DAO.SanPhamDAO;
import com.example.du_an_1_nhom_7.DAO.ThanhVienDAO;
import com.example.du_an_1_nhom_7.DTO.HoaDonDTO;
import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.example.du_an_1_nhom_7.DTO.SanPhamDTO;
import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_QL_HoaDon extends Fragment {

    RecyclerView rc_hoa_don;
    HoaDonDTO hoaDonDTO;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    FloatingActionButton fab_hoa_don;
    ArrayList<HoaDonDTO> list_hd;
    EditText tiedt_add_maHD, tiedt_add_SoLuong, tiedt_add_DonGia, tiedt_add_ngayXuat;
    RadioGroup rd_gr2, radioGroup;
    RadioButton rdo_duyet,rdo_cduyet;
    Button btn_addHD, btn_huy_addHD;
    Dialog dialog;
    Spinner sp_lh_addNV, sp_lh_addTV, sp_lh_addSP;
    ArrayList<NhanVienDTO> list_nv;
    NhanVienDAO nhanVienDAO;
    Spinner_NhanVien_Adapter spinnerNhanVienAdapter;
    ArrayList<ThanhVienDTO> list_tv;
    ThanhVienDAO thanhVienDAO;
    Spinner_ThanhVien_Adapter spinnerThanhVienAdapter;
    ArrayList<SanPhamDTO> list_sp;
    SanPhamDAO sanPhamDAO;
    Spinner_SanPham_Adapter spinnerSanPhamAdapter;
    String mNV;
    int mTV;
    int mSP;
    RadioButton rd_nhap, rd_xuat;
    String nX;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment__q_l__hoa_don, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_hoa_don = view.findViewById(R.id.rc_hoa_don);
        fab_hoa_don = view.findViewById(R.id.fab_hoa_don);

        updateLV();
        fab_hoa_don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
    }

    private void opendialog() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_hoa_don);

        tiedt_add_maHD = dialog.findViewById(R.id.tiedt_add_maHD);
        sp_lh_addNV = dialog.findViewById(R.id.sp_lh_addNV);
        sp_lh_addTV = dialog.findViewById(R.id.sp_lh_addTV);
        sp_lh_addSP = dialog.findViewById(R.id.sp_lh_addSP);
        tiedt_add_SoLuong = dialog.findViewById(R.id.tiedt_add_soLuong_hd);
        tiedt_add_DonGia = dialog.findViewById(R.id.tiedt_add_DonGia_hd);
        rd_gr2=dialog.findViewById(R.id.rd_gr2);
        rdo_duyet=dialog.findViewById(R.id.rd_duyet);
        rdo_cduyet=dialog.findViewById(R.id.rd_cduyet);
        rd_nhap = dialog.findViewById(R.id.rd_nhap);
        rd_xuat = dialog.findViewById(R.id.rd_xuat);
        tiedt_add_ngayXuat = dialog.findViewById(R.id.tiedt_add_ngayXuat);
         radioGroup = dialog.findViewById(R.id.rd_gr);
        btn_addHD = dialog.findViewById(R.id.btn_addHD);
        btn_huy_addHD = dialog.findViewById(R.id.btn_huy_addHD);
        tiedt_add_maHD.setEnabled(false);

        list_nv = new ArrayList<>();
        nhanVienDAO = new NhanVienDAO(getContext());
        list_nv = (ArrayList<NhanVienDTO>) nhanVienDAO.getAll();
        spinnerNhanVienAdapter = new Spinner_NhanVien_Adapter(getContext(),list_nv);
        sp_lh_addNV.setAdapter(spinnerNhanVienAdapter);
        sp_lh_addNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mNV = (list_nv.get(position).getMaNV());
                Toast.makeText(getContext(),"Chọn" + list_nv.get(position).getHo_ten(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list_tv = new ArrayList<>();
        thanhVienDAO = new ThanhVienDAO(getContext());
        list_tv = (ArrayList<ThanhVienDTO>) thanhVienDAO.getAll();
        spinnerThanhVienAdapter = new Spinner_ThanhVien_Adapter(getContext(),list_tv);
        sp_lh_addTV.setAdapter(spinnerThanhVienAdapter);
        sp_lh_addTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTV = list_tv.get(position).getMaTV();
                Toast.makeText(getContext(),"Chọn" + list_tv.get(position).getHo_ten(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        list_sp = new ArrayList<>();
        sanPhamDAO = new SanPhamDAO(getContext());
        list_sp = (ArrayList<SanPhamDTO>) sanPhamDAO.getAll();
        spinnerSanPhamAdapter = new Spinner_SanPham_Adapter(getContext(),list_sp);
        sp_lh_addSP.setAdapter(spinnerSanPhamAdapter);
        sp_lh_addSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSP = list_sp.get(position).getMa_SP();
                Toast.makeText(getContext(),"Chọn" + list_sp.get(position).getTen_SP(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_huy_addHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_addHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonDTO hdDTO = new HoaDonDTO();
                hdDTO.setMaNV(mNV);
                hdDTO.setMaTV(mTV);
                hdDTO.setMaSP(mSP);
                hdDTO.setNgayXuat(tiedt_add_ngayXuat.getText().toString());

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(rd_nhap.isChecked()){
                            hdDTO.setNhap_xuat(0);
                        }else if(rd_xuat.isChecked()){
                            hdDTO.setNhap_xuat(1);
                        }
                    }
                });
                rd_gr2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(rdo_duyet.isChecked()){
                            hdDTO.setTrangThai(1);
                        }else if(rdo_cduyet.isChecked()){
                            hdDTO.setTrangThai(0);
                        }
                    }
                });

                if(validate()>0){
                    hdDTO.setSoLuong(Integer.parseInt(tiedt_add_SoLuong.getText().toString()));
                    hdDTO.setDonGia(Integer.parseInt(tiedt_add_DonGia.getText().toString()));


                    if(hoaDonDAO.insert(hdDTO)>0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }

                    updateLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private int validate() {
        int check=1;

        String soluongtext = tiedt_add_SoLuong.getText().toString();
        String dongiatext = tiedt_add_DonGia.getText().toString();
        String ngayxuattext = tiedt_add_ngayXuat.getText().toString();

        if (dongiatext.length() == 0) {
            tiedt_add_SoLuong.setError("Vui lòng nhập số lượng");
            check=-1;
        } else if (soluongtext.length() == 0) {
            tiedt_add_DonGia.setError("Vui lòng nhập đơn giá");
            check=-1;
        }else if (ngayxuattext.length() == 0) {
            tiedt_add_ngayXuat.setError("Vui lòng nhập ngày");
            check=-1;
        } else {
            try {
                int soluong=Integer.parseInt(soluongtext);
                int dongia= Integer.parseInt(dongiatext);

            }catch (NumberFormatException e){
                tiedt_add_SoLuong.setError("Vui lòng nhập số");
                tiedt_add_DonGia.setError("Vui lòng nhập số");

                check=-1;
            }
        }
        return check;
    }

    private void updateLV() {
        hoaDonDAO = new HoaDonDAO(getContext());
        list_hd = (ArrayList<HoaDonDTO>) hoaDonDAO.getAll();
        hoaDonAdapter = new HoaDonAdapter(list_hd,getContext());
        rc_hoa_don.setAdapter(hoaDonAdapter);
    }
}
