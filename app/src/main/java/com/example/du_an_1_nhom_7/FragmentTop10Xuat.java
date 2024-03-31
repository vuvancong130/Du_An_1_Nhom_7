package com.example.du_an_1_nhom_7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.du_an_1_nhom_7.Adapter.Top10AdapterNhap;
import com.example.du_an_1_nhom_7.Adapter.Top10AdapterXuat;
import com.example.du_an_1_nhom_7.DAO.ThongKeDAO;
import com.example.du_an_1_nhom_7.DTO.Top10;

import java.util.ArrayList;


public class FragmentTop10Xuat extends Fragment {

   ListView lv_top_xuat;
   ArrayList<Top10> list_top_xuat;
   Top10AdapterXuat adt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_top10_xuat,container,false);
        lv_top_xuat=v.findViewById(R.id.lv_top_xuat);
        ThongKeDAO tkdao=new ThongKeDAO(getActivity());
        list_top_xuat=(ArrayList<Top10>) tkdao.getTopXuat();
        adt=new Top10AdapterXuat(getActivity(),this,list_top_xuat);
       lv_top_xuat.setAdapter(adt);
        return v;
    }
}