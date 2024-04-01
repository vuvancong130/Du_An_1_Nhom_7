package com.example.du_an_1_nhom_7.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.du_an_1_nhom_7.DTO.ThanhVienDTO;
import com.example.du_an_1_nhom_7.R;

import java.util.ArrayList;

public class Spinner_ThanhVien_Adapter extends BaseAdapter {
    Context context;
    ArrayList<ThanhVienDTO> listTV;

    public Spinner_ThanhVien_Adapter(Context context, ArrayList<ThanhVienDTO> listTV){
        this.context = context;
        this.listTV = listTV;
    }
    @Override
    public int getCount() {
        return listTV.size();
    }

    @Override
    public Object getItem(int position) {
        return listTV.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.spinner_thanh_vien, null);
        TextView txt_maTV = convertView.findViewById(R.id.txt_maTV);
        TextView txt_tenTV = convertView.findViewById(R.id.txt_tenTV);

        txt_maTV.setText(listTV.get(position).getMaTV() + " ");
        txt_tenTV.setText(listTV.get(position).getHo_ten());
        return convertView;
    }
}
