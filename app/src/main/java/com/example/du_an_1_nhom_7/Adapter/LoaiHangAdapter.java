package com.example.du_an_1_nhom_7.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1_nhom_7.DAO.LoaiHangDAO;
import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.Fragment_QL_LoaiHang;
import com.example.du_an_1_nhom_7.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LoaiHangAdapter extends RecyclerView.Adapter<LoaiHangAdapter.LSViewHolder> {
    Context context;
    ArrayList<LoaiHangDTO> list;

    TextInputEditText tiedt_add_maLH, tiedt_add_tenLH, tiedt_add_thue;
    Button btn_addLS, btn_huy_addLH;
    public LoaiHangAdapter(Context context, ArrayList<LoaiHangDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_loai_hang, parent, false);
        return new LSViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LSViewHolder holder, int position) {
        LoaiHangDTO loaiHangDTO = list.get(position);
        holder.txt_maLH.setText("Mã loại hàng: " + loaiHangDTO.getMa_loai_hang());
        holder.txt_tenLH.setText("Tên loại hàng: " + loaiHangDTO.getTen_loai_hang());
        holder.txt_thue.setText("Thuế: " + loaiHangDTO.getThue());

        holder.imgbnt_deleteLH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa?");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(true);

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiHangDAO loaiHangDAO = new LoaiHangDAO(context);
                        int result = loaiHangDAO.delete(String.valueOf(loaiHangDTO.getMa_loai_hang()));
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                            list.remove(loaiHangDTO);
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
                View view = inflater.inflate(R.layout.dialog_loai_hang, null);
                builder.setView(view);
                Dialog dialog = builder.create();


                tiedt_add_maLH = view.findViewById(R.id.tiedt_add_maLH);
                tiedt_add_tenLH = view.findViewById(R.id.tiedt_add_tenLH);
                tiedt_add_thue = view.findViewById(R.id.tiedt_add_thue);
                btn_addLS = view.findViewById(R.id.btn_addLH);
                btn_huy_addLH = view.findViewById(R.id.btn_huy_addLH);

                tiedt_add_maLH.setText(String.valueOf(list.get(position).getMa_loai_hang()));
                tiedt_add_tenLH.setText(list.get(position).getTen_loai_hang());
                tiedt_add_thue.setText(list.get(position).getThue());

                dialog.show();

                btn_huy_addLH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btn_addLS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenLS = tiedt_add_tenLH.getText().toString();
                        String thue = tiedt_add_thue.getText().toString();
                        loaiHangDTO.setTen_loai_hang(tenLS);
                        loaiHangDTO.setThue(thue);
                        if (tenLS.isEmpty()) {
                            tiedt_add_tenLH.setError("Vui lòng nhập tên loại hàng!");
                        } else if (thue.isEmpty()) {
                            tiedt_add_thue.setError("Vui lòng nhập thuế!");
                        } else {
                            int kq = holder.loaiHangDAO.update(loaiHangDTO);
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
    class LSViewHolder extends RecyclerView.ViewHolder {

        TextView txt_maLH, txt_tenLH, txt_thue;
        ImageButton imgbnt_deleteLH;

        LoaiHangDAO loaiHangDAO;
        LoaiHangDTO loaiHangDTO;
        LoaiHangAdapter loaiHangAdapter;
        Fragment_QL_LoaiHang fragQlLoaiHang;


        public LSViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_maLH = itemView.findViewById(R.id.txt_maLH);
            txt_tenLH = itemView.findViewById(R.id.txt_tenLH);
            txt_thue = itemView.findViewById(R.id.txt_thue);
            imgbnt_deleteLH = itemView.findViewById(R.id.imgbnt_deleteLH);


            loaiHangDAO = new LoaiHangDAO(context);
            loaiHangDTO = new LoaiHangDTO();
            loaiHangAdapter = new LoaiHangAdapter(context, list);
            fragQlLoaiHang = new Fragment_QL_LoaiHang();

        }
    }
}
