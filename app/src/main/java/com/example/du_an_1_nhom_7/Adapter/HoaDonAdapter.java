package com.example.du_an_1_nhom_7.Adapter;

import android.app.Activity;
import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1_nhom_7.DAO.HoaDonDAO;
import com.example.du_an_1_nhom_7.DAO.NhanVienDAO;
import com.example.du_an_1_nhom_7.DAO.SanPhamDAO;
import com.example.du_an_1_nhom_7.DAO.ThanhVienDAO;
import com.example.du_an_1_nhom_7.DTO.HoaDonDTO;
import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.example.du_an_1_nhom_7.DTO.SanPhamDTO;
import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.example.du_an_1_nhom_7.R;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewholderHoaDon> {

    private ArrayList<HoaDonDTO> list_HD;
    private Context context;
    EditText tiedt_add_maHD, tiedt_add_SoLuong, tiedt_add_DonGia, tiedt_add_ngayXuat;
    RadioGroup rd_gr2, radioGroup;
    RadioButton rdo_duyet,rdo_cduyet;
    Button btn_addHD, btn_huy_addHD;

    TextView txt_tinhtongtien;
    Spinner sp_lh_addNV, sp_lh_addTV, sp_lh_addSP;
    ArrayList<NhanVienDTO> list_nv;

    Spinner_NhanVien_Adapter spinnerNhanVienAdapter;
    ArrayList<ThanhVienDTO> list_tv;

    Spinner_ThanhVien_Adapter spinnerThanhVienAdapter;
    ArrayList<SanPhamDTO> list_sp;

    Spinner_SanPham_Adapter spinnerSanPhamAdapter;
    RadioButton rd_nhap,rd_xuat;
    String mNV;
    int mTV;
    int mSP;
    NhanVienDAO nhanVienDAO;
    SanPhamDAO sanPhamDAO;
    ThanhVienDAO thanhVienDAO;

    public HoaDonAdapter(ArrayList<HoaDonDTO> list_HD, Context context){
        this.list_HD = list_HD;
        this.context = context;
    }


    @NonNull
    @Override
    public HoaDonAdapter.ViewholderHoaDon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_hoa_don,parent,false);
        ViewholderHoaDon viewholderHoaDon = new ViewholderHoaDon(v);

        return viewholderHoaDon;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ViewholderHoaDon holder, int position) {

        final HoaDonDTO hoaDonDTO = list_HD.get(position);

        if (holder.txt_maHD != null) {
            holder.txt_maHD.setText("Mã HD: " + hoaDonDTO.getMaHD());
        }

        nhanVienDAO = new NhanVienDAO(context);
        NhanVienDTO nhanVienDTO = nhanVienDAO.getID(hoaDonDTO.getMaNV()) ;
        holder.txt_maNV.setText("Nhân viên: " + nhanVienDTO.getHo_ten());

        thanhVienDAO = new ThanhVienDAO(context);
        ThanhVienDTO thanhVienDTO = thanhVienDAO.getID(String.valueOf(hoaDonDTO.getMaTV()));
        holder.txt_maTV.setText("Tên thành viên: " + thanhVienDTO.getHo_ten());

        sanPhamDAO = new SanPhamDAO(context);
        SanPhamDTO sanPhamDTO = sanPhamDAO.getID(String.valueOf(hoaDonDTO.getMaSP()));
        holder.txt_maSP.setText("Tên sản phẩm: " + sanPhamDTO.getTen_SP());

        holder.txt_soLuong.setText("Số lượng : " + hoaDonDTO.getSoLuong() + " ");
        holder.txt_donGia.setText("Đơn giá: " + hoaDonDTO.getDonGia() + " ");
        holder.txt_ngayXuat.setText("Ngày : " + hoaDonDTO.getNgayXuat());

        if (hoaDonDTO.getNhap_xuat() == 0){
            holder.txt_loaiHoaDon.setText("Loại hóa đơn : nhập");
        } else if (hoaDonDTO.getNhap_xuat() == 1){
            holder.txt_loaiHoaDon.setText("Loại hóa đơn : xuất");
        }
        //

        if(hoaDonDTO.getTrangThai()==0){
            holder.txt_trangthai.setText("Đang xử lý");
            holder.txt_trangthai.setTextColor(Color.parseColor("#FF0000"));
        }else if(hoaDonDTO.getTrangThai()==1){
            holder.txt_trangthai.setText("Đã hoàn thành");
            holder.txt_trangthai.setTextColor(Color.parseColor("#00FF00"));

        }
        int tong= holder.tinhtong(hoaDonDTO.getSoLuong(),hoaDonDTO.getDonGia());
        String forramat=dinhdang(tong);
        holder.txt_tongtien.setText("Tổng tiền: "+forramat);

        holder.imgbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn muốn xóa hóa đơn?");
                builder.setCancelable(true);

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HoaDonDAO hoaDonDAO = new HoaDonDAO(context);

                        if (hoaDonDAO.delete(String.valueOf(hoaDonDTO.getMaHD())) > 0){
                            Toast.makeText(context,"Xoá thành công", Toast.LENGTH_SHORT).show();
                            list_HD.remove(hoaDonDTO);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context,"Xóa thât bại, không có dữ liệu để xóa", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inf=((Activity)context).getLayoutInflater();
                View view=inf.inflate(R.layout.dialog_hoa_don,null);
                builder.setView(view);
                Dialog dialog=builder.create();

                tiedt_add_maHD = view.findViewById(R.id.tiedt_add_maHD);
                Spinner sp_lh_addNV = view.findViewById(R.id.sp_lh_addNV);

                txt_tinhtongtien=view.findViewById(R.id.txt_tinhtongtien);
                Spinner sp_lh_addTV = view.findViewById(R.id.sp_lh_addTV);
                Spinner  sp_lh_addSP = view.findViewById(R.id.sp_lh_addSP);
                tiedt_add_SoLuong = view.findViewById(R.id.tiedt_add_soLuong_hd);
                tiedt_add_DonGia = view.findViewById(R.id.tiedt_add_DonGia_hd);
                rd_gr2=view.findViewById(R.id.rd_gr2);
                rdo_duyet=view.findViewById(R.id.rd_duyet);
                rdo_cduyet=view.findViewById(R.id.rd_cduyet);
                rd_nhap = view.findViewById(R.id.rd_nhap);
                rd_xuat = view.findViewById(R.id.rd_xuat);
                tiedt_add_ngayXuat = view.findViewById(R.id.tiedt_add_ngayXuat);
                radioGroup = view.findViewById(R.id.rd_gr);
                btn_addHD = view.findViewById(R.id.btn_addHD);
                btn_huy_addHD = view.findViewById(R.id.btn_huy_addHD);


                tiedt_add_maHD.setEnabled(false);



                tiedt_add_maHD.setText(String.valueOf(list_HD.get(position).getMaHD()));
                tiedt_add_DonGia.setText(String.valueOf(list_HD.get(position).getDonGia()));
                tiedt_add_ngayXuat.setText(list_HD.get(position).getNgayXuat());
                tiedt_add_SoLuong.setText(String.valueOf(list_HD.get(position).getSoLuong()));


                if(list_HD.get(position).getNhap_xuat()==0){
                    rd_nhap.setChecked(true);
                }else if(list_HD.get(position).getNhap_xuat()==1){
                    rd_xuat.setChecked(true);
                }

                if(list_HD.get(position).getTrangThai()==0){
                    rdo_cduyet.setChecked(true);
                }else if(list_HD.get(position).getTrangThai()==1){
                    rdo_duyet.setChecked(true);
                }





                NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
                ArrayList<NhanVienDTO> list_nv = (ArrayList<NhanVienDTO>) nhanVienDAO.getAll();
                Spinner_NhanVien_Adapter spinnerNhanVienAdapter = new Spinner_NhanVien_Adapter(context,list_nv);
                sp_lh_addNV.setAdapter(spinnerNhanVienAdapter);

                int positionNV=0;
                for(int i=0;i<list_nv.size();i++){
                    if(hoaDonDTO.getMaNV()==(list_nv.get(i).getMaNV())){
                        positionNV=i;

                    }
                    sp_lh_addNV.setSelection(positionNV);
                }


                sp_lh_addNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mNV = list_nv.get(position).getMaNV();
                        Toast.makeText(context,"Chọn " + list_nv.get(position).getHo_ten(),Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
                ArrayList<ThanhVienDTO> list_tv = (ArrayList<ThanhVienDTO>) thanhVienDAO.getAll();
                Spinner_ThanhVien_Adapter spinnerThanhVienAdapter = new Spinner_ThanhVien_Adapter(context,list_tv);
                sp_lh_addTV.setAdapter(spinnerThanhVienAdapter);

                int positionTV=0;
                for(int i=0;i<list_tv.size();i++){
                    if(hoaDonDTO.getMaTV()==(list_tv.get(i).getMaTV())){
                        positionTV=i;

                    }
                    sp_lh_addTV.setSelection(positionTV);
                }


                sp_lh_addTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mTV = list_tv.get(position).getMaTV();
                        Toast.makeText(context,"Chọn " + list_tv.get(position).getHo_ten(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
                ArrayList<SanPhamDTO> list_sp = (ArrayList<SanPhamDTO>) sanPhamDAO.getAll();
                Spinner_SanPham_Adapter spinnerSanPhamAdapter = new Spinner_SanPham_Adapter(context,list_sp);
                sp_lh_addSP.setAdapter(spinnerSanPhamAdapter);

                int positionSP=0;
                for(int i=0;i<list_sp.size();i++){
                    if(hoaDonDTO.getMaSP()==(list_sp.get(i).getMa_SP())){
                        positionSP=i;

                    }
                    sp_lh_addSP.setSelection(positionSP);

                }

                sp_lh_addSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mSP = list_sp.get(position).getMa_SP();
                        Toast.makeText(context,"Chọn " + list_sp.get(position).getTen_SP(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

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
                        HoaDonDAO hddao=new HoaDonDAO(context);
                        String ngayxuat=tiedt_add_ngayXuat.getText().toString();

                        hoaDonDTO.setMaNV(mNV);
                        hoaDonDTO.setMaSP(mSP);
                        hoaDonDTO.setMaTV(mTV);
                        hoaDonDTO.setNgayXuat(ngayxuat);


                        if(rd_nhap.isChecked()){
                            hoaDonDTO.setNhap_xuat(0);
                        }else if(rd_xuat.isChecked()){
                            hoaDonDTO.setNhap_xuat(1);
                        }


                        if(rdo_duyet.isChecked()){
                            hoaDonDTO.setTrangThai(1);
                        }else if(rdo_cduyet.isChecked()){
                            hoaDonDTO.setTrangThai(0);
                        }


                        if(validate()>0){
                            int soluong=Integer.parseInt(tiedt_add_SoLuong.getText().toString());
                            int dongia=Integer.parseInt(tiedt_add_DonGia.getText().toString());
                            hoaDonDTO.setDonGia(dongia);
                            hoaDonDTO.setSoLuong(soluong);
                            if(hddao.update(hoaDonDTO)>0){
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });



                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list_HD.size();
    }

    public class ViewholderHoaDon extends RecyclerView.ViewHolder {
        TextView txt_maHD, txt_maNV, txt_maTV, txt_maSP, txt_soLuong, txt_donGia, txt_ngayXuat, txt_loaiHoaDon,txt_trangthai,  txt_tongtien;
        ImageButton imgbtn_delete;
        HoaDonDAO hoaDonDAO;
        public ViewholderHoaDon(@NonNull View itemView) {
            super(itemView);

            txt_tongtien =itemView.findViewById(R.id.txt_tongTien);
            txt_maHD = itemView.findViewById(R.id.txt_maHD);
            txt_maNV = itemView.findViewById(R.id.txt_maNV);
            txt_maTV = itemView.findViewById(R.id.txt_maTV);
            txt_maSP = itemView.findViewById(R.id.txt_maSP);
            txt_soLuong = itemView.findViewById(R.id.txt_soLuong);
            txt_donGia = itemView.findViewById(R.id.txt_donGia);
            txt_ngayXuat = itemView.findViewById(R.id.txt_ngayXuat);
            txt_trangthai=itemView.findViewById(R.id.txt_trangthai);
            txt_loaiHoaDon = itemView.findViewById(R.id.txt_loaiHoaDon);
            imgbtn_delete = itemView.findViewById(R.id.imgbtn_delete);

        }
        private int tinhtong(int soLuong, int donGia) {
            return soLuong * donGia;
        }

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
}
