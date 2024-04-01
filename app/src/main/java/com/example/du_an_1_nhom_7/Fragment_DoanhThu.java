package com.example.du_an_1_nhom_7;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.du_an_1_nhom_7.DAO.ThongKeDAO;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Fragment_DoanhThu extends Fragment {

TextView txt_tungay,txt_denngay,txt_doanhthu;
Button btn_xemdt;
SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
int mYear,mMonth,mDay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment__doanh_thu,container,false);
         txt_tungay=v.findViewById(R.id.txt_tungay);
         txt_denngay=v.findViewById(R.id.txt_denngay);
         txt_doanhthu=v.findViewById(R.id.txt_doanhthu);
         btn_xemdt=v.findViewById(R.id.btn_xemdt);

         txt_tungay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Calendar c=Calendar.getInstance();
                 mYear=c.get(Calendar.YEAR);
                 mMonth=c.get(Calendar.MONTH);
                 mDay=c.get(Calendar.DAY_OF_MONTH);
                 DatePickerDialog d=new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                 d.show();

             }
         });

         txt_denngay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Calendar c=Calendar.getInstance();
                 mYear=c.get(Calendar.YEAR);
                 mMonth=c.get(Calendar.MONTH);
                 mDay=c.get(Calendar.DAY_OF_MONTH);
                 DatePickerDialog d=new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
                 d.show();
             }
         });


         btn_xemdt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String tuNgay=txt_tungay.getText().toString();
                 String denNgay=txt_denngay.getText().toString();
                 ThongKeDAO tkdao=new ThongKeDAO(getActivity());
                 txt_doanhthu.setText(String.valueOf(tkdao.getDoanhthu(tuNgay,denNgay)));
             }
         });
         return v;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            txt_tungay.setText(sdf.format(c.getTime()));

        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(mYear,mMonth,mDay);
            txt_denngay.setText(sdf.format(c.getTime()));

        }
    };
    public String dinhdang(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        return decimalFormat.format(number);
    }
}