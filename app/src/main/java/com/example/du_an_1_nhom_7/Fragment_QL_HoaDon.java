package com.example.du_an_1_nhom_7;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import android.annotation.SuppressLint;
import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
    TextView txt_tinhtongtien;
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
    SearchView searchView;
    Button btn_nhap,btn_xuat,btn_tatca;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment__q_l__hoa_don, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_hoa_don = view.findViewById(R.id.rc_hoa_don);
        fab_hoa_don = view.findViewById(R.id.fab_hoa_don);

        btn_nhap=view.findViewById(R.id.btn_nhap);
        btn_xuat=view.findViewById(R.id.btn_xuat);

        btn_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(getContext());

                hoaDonAdapter = new HoaDonAdapter(getlist_nhap(),getContext());
                rc_hoa_don.setAdapter(hoaDonAdapter);
            }
        });
        btn_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonDAO = new HoaDonDAO(getContext());

                hoaDonAdapter = new HoaDonAdapter(getlist_xuat(),getContext());
                rc_hoa_don.setAdapter(hoaDonAdapter);
            }
        });

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
        txt_tinhtongtien=dialog.findViewById(R.id.txt_tinhtongtien);
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tiedt_add_DonGia.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    tinhtong();
                }
            }
        });
        tiedt_add_SoLuong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    tinhtong();
                }
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


                if(rd_nhap.isChecked()){
                    hdDTO.setNhap_xuat(0);
                }else if(rd_xuat.isChecked()){
                    hdDTO.setNhap_xuat(1);
                }


                if(rdo_duyet.isChecked()){
                    hdDTO.setTrangThai(1);
                }else if(rdo_cduyet.isChecked()){
                    hdDTO.setTrangThai(0);
                }


                if(validate()>0){
                    hdDTO.setSoLuong(Integer.parseInt(tiedt_add_SoLuong.getText().toString()));
                    hdDTO.setDonGia(Integer.parseInt(tiedt_add_DonGia.getText().toString()));

                    int maSP = list_sp.get(sp_lh_addSP.getSelectedItemPosition()).getMa_SP();
                    if(rdo_duyet.isChecked()) {
                        if (rd_nhap.isChecked()) {
                            if(hoaDonDAO.insert(hdDTO)>0){
                                int soLuongnhap = Integer.parseInt(tiedt_add_SoLuong.getText().toString());
                                SanPhamDTO spdto = sanPhamDAO.getID(String.valueOf(maSP));
                                if (spdto != null) {
                                    int soLuongHienTai = spdto.getSo_luong();
                                    int soLuongMoi = soLuongHienTai + soLuongnhap;
                                    spdto.setSo_luong(soLuongMoi);
                                    sanPhamDAO.update(spdto);
//                                    Toast.makeText(getContext(), "Cập nhật số lượng sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("Thông Báo");
                                    builder.setMessage("Cập nhật số lượng sản phẩm thành công!");
                                    builder.setCancelable(true);

                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }}
                        } else if (rd_xuat.isChecked()) {
                            int soLuongxuat = Integer.parseInt(tiedt_add_SoLuong.getText().toString());
                            SanPhamDTO spdto = sanPhamDAO.getID(String.valueOf(maSP));
                            if (spdto != null) {
                                int soLuongHienTai = spdto.getSo_luong();
                                int soLuongMoi = soLuongHienTai - soLuongxuat;
                                if(soLuongHienTai<soLuongxuat){
                                    Toast.makeText(getContext(), "Số lượng xuất vượt quá số lượng hiện có của sản phẩm!", Toast.LENGTH_SHORT).show();
                                }else {
                                    if(hoaDonDAO.insert(hdDTO)>0){
                                        spdto.setSo_luong(soLuongMoi);
                                        sanPhamDAO.update(spdto);
                                        Toast.makeText(getContext(), "Cập nhật số lượng sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }else if(rdo_cduyet.isChecked()){
                        if(hoaDonDAO.insert(hdDTO)>0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
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
    private void tinhtong(){
        try {
            int soluong=Integer.parseInt(tiedt_add_SoLuong.getText().toString());
            int dongia=Integer.parseInt(tiedt_add_DonGia.getText().toString());
            int tong=soluong*dongia;
            String format=dinhdang(tong);
            txt_tinhtongtien.setText(String.valueOf(format));
        }catch(NumberFormatException e){

            txt_tinhtongtien.setText("");
        }
    }
    public String dinhdang(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##"); // Định dạng chuỗi với dấu chấm
        return decimalFormat.format(number);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nhập mã HĐ hoặc mã NV");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hoaDonAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hoaDonAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }
    private ArrayList<HoaDonDTO> getlist_nhap(){
        ArrayList<HoaDonDTO> list_nhap=new ArrayList<>();
        for(HoaDonDTO hd : list_hd){
            if(hd.getNhap_xuat()==0){
                list_nhap.add(hd);
            }
        }
        return list_nhap;
    }
    private ArrayList<HoaDonDTO> getlist_xuat(){
        ArrayList<HoaDonDTO> list_xuat=new ArrayList<>();
        for(HoaDonDTO hd : list_hd){
            if(hd.getNhap_xuat()==1){
                list_xuat.add(hd);
            }
        }
        return list_xuat;
    }
}
