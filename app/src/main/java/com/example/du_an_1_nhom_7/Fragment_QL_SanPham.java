package com.example.du_an_1_nhom_7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.du_an_1_nhom_7.Adapter.SanPhamAdapter;
import com.example.du_an_1_nhom_7.DAO.SanPhamDAO;
import com.example.du_an_1_nhom_7.DTO.SanPhamDTO;

import java.util.ArrayList;

public class Fragment_QL_SanPham extends Fragment {
    RecyclerView rc_sp;
    ImageButton btn_ADD_Sach;
    SanPhamAdapter adapter;
    SanPhamDAO sanPhamDAO;
    SanPhamDTO sanPhamDTO;
    ArrayList<SanPhamDTO> list_SP;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment__q_l__san_pham, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_sp = view.findViewById(R.id.rc_san_pham);
        btn_ADD_Sach = view.findViewById(R.id.fab_san_pham);
        sanPhamDAO=new SanPhamDAO(getContext());
        list_SP= (ArrayList<SanPhamDTO>) sanPhamDAO.getAll();
        adapter= new SanPhamAdapter(list_SP,getContext());
        rc_sp.setAdapter(adapter);

    }
}