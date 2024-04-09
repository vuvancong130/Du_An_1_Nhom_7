package com.example.du_an_1_nhom_7.Adapter;

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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1_nhom_7.DAO.LoaiHangDAO;
import com.example.du_an_1_nhom_7.DTO.LoaiHangDTO;
import com.example.du_an_1_nhom_7.DTO.NhanVienDTO;
import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.example.du_an_1_nhom_7.Fragment_QL_LoaiHang;
import com.example.du_an_1_nhom_7.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class LoaiHangAdapter extends RecyclerView.Adapter<LoaiHangAdapter.LSViewHolder> implements Filterable {
    Context context;
    ArrayList<LoaiHangDTO> list;
    ArrayList<LoaiHangDTO> list_search;

    TextInputEditText tiedt_add_maLH, tiedt_add_tenLH, tiedt_add_thue;
    Button btn_addLS, btn_huy_addLH;
    LoaiHangDAO lhdao;
    public LoaiHangAdapter(Context context, ArrayList<LoaiHangDTO> list) {
        this.context = context;
        this.list_search=list;
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
        holder.txt_thue.setText( loaiHangDTO.getMoTa());


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

                        if(loaiHangDAO.checkLoaiHangIsUsed(context,loaiHangDTO.getMa_loai_hang())==true){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Thông Báo");
                            builder.setMessage("Loại hàng đang được chọn, không thể xóa");
                            builder.setCancelable(true);

                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }else{
                        int result = loaiHangDAO.delete(String.valueOf(loaiHangDTO.getMa_loai_hang()));
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                            list.remove(loaiHangDTO);
                            notifyDataSetChanged();
                            dialog.dismiss();

                        }
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
                tiedt_add_thue.setText(list.get(position).getMoTa());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                        String mota = tiedt_add_thue.getText().toString();
                        loaiHangDTO.setTen_loai_hang(tenLS);
                        loaiHangDTO.setMoTa(mota);
                        if (tenLS.isEmpty()) {
                            tiedt_add_tenLH.setError("Vui lòng nhập tên loại hàng!");
                        } else if (mota.isEmpty()) {
                            tiedt_add_thue.setError("Vui lòng nhập mô tả!");
                        } else {
                            int kq = holder.loaiHangDAO.update(loaiHangDTO);
                            if (kq > 0) {
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
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    list = list_search;
                } else {
                    List<LoaiHangDTO> list_lh = new ArrayList<LoaiHangDTO>() {
                    };
                    for (LoaiHangDTO loaiHangDTO : list) {
                        if (loaiHangDTO.getTen_loai_hang().toLowerCase().contains(strSearch.toLowerCase())) {
                            list_lh.add(loaiHangDTO);
                        }
                        try {
                            int maSPSearch = Integer.parseInt(strSearch);
                            if (loaiHangDTO.getMa_loai_hang() == maSPSearch) {
                                list_lh.add(loaiHangDTO);
                            }
                        } catch (NumberFormatException e) {

                        }
                    }
                    list = (ArrayList<LoaiHangDTO>) list_lh;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<LoaiHangDTO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
