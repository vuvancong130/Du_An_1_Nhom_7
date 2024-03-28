package com.example.du_an_1_nhom_7.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        HoaDonDTO hoaDonDTO = list_HD.get(position);

        if (holder.txt_maHD != null) {
            holder.txt_maHD.setText("Mã HD: " + hoaDonDTO.getMaHD());
        }

        NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
        NhanVienDTO nhanVienDTO = nhanVienDAO.getID(hoaDonDTO.getMaNV()) ;
        holder.txt_maNV.setText("Nhân viên: " + nhanVienDTO.getHo_ten());

        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        ThanhVienDTO thanhVienDTO = thanhVienDAO.getID(String.valueOf(hoaDonDTO.getMaTV()));
        holder.txt_maTV.setText("Tên thành viên: " + thanhVienDTO.getHo_ten());

        SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
        SanPhamDTO sanPhamDTO = sanPhamDAO.getID(String.valueOf(hoaDonDTO.getMaSP()));
        holder.txt_maSP.setText("Tên sản phẩm: " + sanPhamDTO.getTen_SP());

        holder.txt_soLuong.setText("Số lượng : " + hoaDonDTO.getSoLuong() + " ");
        holder.txt_donGia.setText("Đơn giá: " + hoaDonDTO.getDonGia() + " ");
        holder.txt_ngayXuat.setText("Ngày xuất hóa đơn : " + hoaDonDTO.getNgayXuat());

        if (hoaDonDTO.getNhap_xuat() == 0){
            holder.txt_loaiHoaDon.setText("Loại hóa đon : nhập");
        } else if (hoaDonDTO.getNhap_xuat() == 1){
            holder.txt_loaiHoaDon.setText("Loại hóa đơn : xuất");
        }

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


    }

    @Override
    public int getItemCount() {
        return list_HD.size();
    }

    public class ViewholderHoaDon extends RecyclerView.ViewHolder {
        TextView txt_maHD, txt_maNV, txt_maTV, txt_maSP, txt_soLuong, txt_donGia, txt_ngayXuat, txt_loaiHoaDon;
        ImageButton imgbtn_delete;
        HoaDonDAO hoaDonDAO;
        public ViewholderHoaDon(@NonNull View itemView) {
            super(itemView);

            txt_maHD = itemView.findViewById(R.id.txt_maHD);
            txt_maNV = itemView.findViewById(R.id.txt_maNV);
            txt_maTV = itemView.findViewById(R.id.txt_maTV);
            txt_maSP = itemView.findViewById(R.id.txt_maSP);
            txt_soLuong = itemView.findViewById(R.id.txt_soLuong);
            txt_donGia = itemView.findViewById(R.id.txt_donGia);
            txt_ngayXuat = itemView.findViewById(R.id.txt_ngayXuat);
            txt_loaiHoaDon = itemView.findViewById(R.id.txt_loaiHoaDon);
            imgbtn_delete = itemView.findViewById(R.id.imgbtn_delete);
        }
    }
}
