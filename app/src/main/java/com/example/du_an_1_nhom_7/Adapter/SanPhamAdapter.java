package com.example.du_an_1_nhom_7.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        holder.txt_MaLH.setText("Loại Hàng: "+sanPhamDTO.getMa_loai()+"");
        holder.txt_HSD.setText("HSD: "+sanPhamDTO.getHsd());
        holder.txt_DonGia.setText("Đơn Giá: "+sanPhamDTO.getDon_gia()+"");
        holder.txt_SoLuong.setText("Số Lượng: "+sanPhamDTO.getSo_luong()+"");

    }

    @Override
    public int getItemCount() {
        return list_SP.size();
    }

    public static class ViewholderSanPham extends RecyclerView.ViewHolder{
        TextView txt_maSP, txt_TenSP,txt_MaLH,txt_HSD, txt_DonGia, txt_SoLuong;
        ImageButton btn_Delete;
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

}
