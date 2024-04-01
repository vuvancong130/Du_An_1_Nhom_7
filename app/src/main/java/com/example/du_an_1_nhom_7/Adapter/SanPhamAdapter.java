package com.example.du_an_1_nhom_7.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1_nhom_7.DAO.LoaiHangDAO;
import com.example.du_an_1_nhom_7.DAO.SanPhamDAO;
import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.DTO.SanPhamDTO;
import com.example.du_an_1_nhom_7.Fragment_QL_LoaiHang;
import com.example.du_an_1_nhom_7.Fragment_QL_SanPham;
import com.example.du_an_1_nhom_7.R;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewholderSanPham> {
    private ArrayList<SanPhamDTO> list_SP;
    private Context context;
    EditText tiedt_add_maSP,tiedt_add_tenSP,tiedt_add_HSD,tiedt_add_donGia,tiedt_add_soLuong;
    Spinner spn_add_SP;
    Button btn_huy_addSP,btn_addSP;
    int maloaihang;

    public SanPhamAdapter(ArrayList<SanPhamDTO> list_SP, Context context) {
        this.list_SP = list_SP;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewholderSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View v=inflater.inflate(R.layout.item_san_pham,parent,false);
        ViewholderSanPham holder= new ViewholderSanPham(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewholderSanPham holder, int position) {
        SanPhamDTO sanPhamDTO=list_SP.get(position);
        holder.txt_maSP.setText("Mã SP: "+sanPhamDTO.getMa_SP()+"");
        holder.txt_TenSP.setText("Tên SP: "+sanPhamDTO.getTen_SP());

        LoaiHangDAO lhdao=new LoaiHangDAO(context);
        LoaiHangDTO lh=lhdao.getID(String.valueOf(sanPhamDTO.ma_loai));
        holder.txt_MaLH.setText("Loại Hàng: "+lh.getTen_loai_hang());
        holder.txt_HSD.setText("HSD: "+sanPhamDTO.getHsd());
        holder.txt_DonGia.setText("Đơn Giá: "+sanPhamDTO.getDon_gia()+"");
        holder.txt_SoLuong.setText("Số Lượng: "+sanPhamDTO.getSo_luong()+"");

        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Xóa?");
                builder.setMessage("Bạn có muốn xóa?");
                builder.setCancelable(true);

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SanPhamDAO spdao=new SanPhamDAO(context);

                        if(spdao.delete(String.valueOf(sanPhamDTO.getMa_SP()))>0){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

                            list_SP.remove(sanPhamDTO);
                            notifyDataSetChanged();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(context, "Xóa thất bại hoặc không có dữ liệu để xóa.", Toast.LENGTH_SHORT).show();
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
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inf=((Activity)context).getLayoutInflater();
                View view=inf.inflate(R.layout.dialog_san_pham,null);
                builder.setView(view);
                AlertDialog dialog=builder.create();

                tiedt_add_maSP=view.findViewById(R.id.tiedt_add_maSP);
                tiedt_add_tenSP=view.findViewById(R.id.tiedt_add_tenSP);
                spn_add_SP=view.findViewById(R.id.sp_lh_addSP);
                tiedt_add_HSD=view.findViewById(R.id.tiedt_add_HSD);
                tiedt_add_donGia=view.findViewById(R.id.tiedt_add_DonGia);
                tiedt_add_soLuong=view.findViewById(R.id.tiedt_add_SoLuong);
                btn_addSP=view.findViewById(R.id.btn_addSP);
                btn_huy_addSP=view.findViewById(R.id.btn_huy_addSP);

                tiedt_add_maSP.setText(String.valueOf(list_SP.get(position).getMa_SP()));
                tiedt_add_tenSP.setText(list_SP.get(position).getTen_SP());
                tiedt_add_HSD.setText(list_SP.get(position).getHsd());
                tiedt_add_donGia.setText(String.valueOf(list_SP.get(position).getDon_gia()));
                tiedt_add_soLuong.setText(String.valueOf(list_SP.get(position).getSo_luong()));



                int position=0;
                LoaiHangDAO lhdao=new LoaiHangDAO(context);
                ArrayList<LoaiHangDTO> list_lh= (ArrayList<LoaiHangDTO>) lhdao.getAll();
                Spinner_LoaiHang_Adapter spnadt=new Spinner_LoaiHang_Adapter(context,list_lh);
                spn_add_SP.setAdapter(spnadt);

                for(int i=0;i<list_lh.size();i++){
                    if(sanPhamDTO.ma_loai==(list_lh.get(i).getMa_loai_hang())){
                        position=i;
                    }
                    Log.i("demo","posSanPham"+position);
                    spn_add_SP.setSelection(position);
                }
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                spn_add_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maloaihang=list_lh.get(position).getMa_loai_hang();
                        Toast.makeText(context, "Chọn "+list_lh.get(position).getTen_loai_hang(), Toast.LENGTH_SHORT).show();
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

                        SanPhamDAO spdao=new SanPhamDAO(context);

                        String tensp=tiedt_add_tenSP.getText().toString();
                        String hsd=tiedt_add_HSD.getText().toString();

                        sanPhamDTO.setTen_SP(tensp);
                        sanPhamDTO.setHsd(hsd);
                        sanPhamDTO.setMa_loai(maloaihang);

                        if(validate()>0){
                            int dongia=Integer.parseInt(tiedt_add_donGia.getText().toString());
                            int soluong=Integer.parseInt(tiedt_add_soLuong.getText().toString());
                            sanPhamDTO.setDon_gia(dongia);
                            sanPhamDTO.setSo_luong(soluong);
                            int kq=spdao.update(sanPhamDTO);
                            if(kq>0){
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }else{
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
        return list_SP.size();
    }

    public static class ViewholderSanPham extends RecyclerView.ViewHolder{
        TextView txt_maSP, txt_TenSP,txt_MaLH,txt_HSD, txt_DonGia, txt_SoLuong;
        ImageButton btn_Delete;
        SanPhamDAO spdao;
        public ViewholderSanPham(@NonNull View itemView) {
            super(itemView);
            txt_maSP=itemView.findViewById(R.id.txt_maSP);
            txt_TenSP=itemView.findViewById(R.id.txt_tenSP);
            txt_MaLH=itemView.findViewById(R.id.txt_maLH);
            txt_HSD=itemView.findViewById(R.id.txt_HSD);
            txt_DonGia=itemView.findViewById(R.id.txt_DonGia);
            txt_SoLuong=itemView.findViewById(R.id.txt_SoLuong);
            btn_Delete=itemView.findViewById(R.id.imgbnt_deleteSP);


        }
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
