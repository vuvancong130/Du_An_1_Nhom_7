package com.example.du_an_1_nhom_7.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1_nhom_7.DAO.NhanVienDAO;
import com.example.du_an_1_nhom_7.DAO.ThanhVienDAO;
import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.example.du_an_1_nhom_7.Fragment_QL_NhanVien;
import com.example.du_an_1_nhom_7.Fragment_QL_ThanhVien;
import com.example.du_an_1_nhom_7.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.TViewHolder> {
    private Context context;
    ArrayList<NhanVienDTO> list;
    TextInputEditText tiedt_add_maNV,tiedt_add_tenNV,tiedt_add_sodienthoai;
    Button btn_addTV,btn_huy_addTV;

    public NhanVienAdapter(Context context, ArrayList<NhanVienDTO> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public TViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_nhan_vien, parent, false);
        return new TViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NhanVienDTO nhanVienDTO = list.get(position);
        holder.txt_maNV.setText("Mã nhân viên: " + nhanVienDTO.getMaNV());
        holder.txt_tenNV.setText("Tên nhân viên: " + nhanVienDTO.getHo_ten());
        holder.txt_sodienThoai.setText("Số điện thoại: " + nhanVienDTO.getSdt());

        holder.imgbnt_deleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa?");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(true);

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
                        int result = nhanVienDAO.delete(String.valueOf(nhanVienDTO.getMaNV()));
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                            list.remove(nhanVienDTO);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_nhan_vien, null);
                builder.setView(view);
                Dialog dialog = builder.create();

                tiedt_add_maNV = view.findViewById(R.id.tiedt_add_maNV);
                tiedt_add_tenNV = view.findViewById(R.id.tiedt_add_tenNV);
                tiedt_add_sodienthoai = view.findViewById(R.id.tiedt_add_sodienthoai);
                btn_addTV = view.findViewById(R.id.btn_addTV);
                btn_huy_addTV = view.findViewById(R.id.btn_huy_addTV);

                tiedt_add_maNV.setText(String.valueOf(list.get(position).getMaNV()));
                tiedt_add_tenNV.setText(list.get(position).getHo_ten());
                tiedt_add_sodienthoai.setText(list.get(position).getSdt());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                btn_huy_addTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btn_addTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String maNV = tiedt_add_maNV.getText().toString();
                        String tenTV = tiedt_add_tenNV.getText().toString();
                        String SDT = tiedt_add_sodienthoai.getText().toString();
                        nhanVienDTO.setMaNV(maNV);
                        nhanVienDTO.setHo_ten(tenTV);
                        nhanVienDTO.setSdt(SDT);
                        boolean err = false;
                        if (maNV.isEmpty()) {
                            tiedt_add_maNV.setError("Vui lòng mã nhân viên!");
                            err = true;
                        }
                        if (tenTV.isEmpty()) {
                            tiedt_add_tenNV.setError("Vui lòng nhập tên nhân viên!");
                            err = true;
                        }
                        if (SDT.isEmpty()) {
                            tiedt_add_sodienthoai.setError("Vui lòng nhập số điện thoại thành viên!");
                            err = true;
                        }

                        if(!err){
                            int kq = holder.nhanVienDAO.update(nhanVienDTO);
                            if (kq > 0) {
                                notifyDataSetChanged();
                                dialog.dismiss();
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
        return list.size();
    }

    class TViewHolder extends RecyclerView.ViewHolder {
        TextView txt_maNV, txt_tenNV, txt_sodienThoai;
        ImageButton imgbnt_deleteTV;
        NhanVienDTO nhanVienDTO;
        NhanVienDAO nhanVienDAO;
        NhanVienAdapter nhanVienAdapter;
        Fragment_QL_NhanVien fragmentQlNhanVien;

        public TViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_maNV = itemView.findViewById(R.id.txt_maNV);
            txt_tenNV = itemView.findViewById(R.id.txt_tenNV);
            txt_sodienThoai = itemView.findViewById(R.id.txt_sodienthoai);
            imgbnt_deleteTV = itemView.findViewById(R.id.imgbnt_deleteTV);

            nhanVienDTO = new NhanVienDTO();
            nhanVienDAO = new NhanVienDAO(context);
            nhanVienAdapter = new NhanVienAdapter(context, list);
            fragmentQlNhanVien = new Fragment_QL_NhanVien();
        }
    }
}
