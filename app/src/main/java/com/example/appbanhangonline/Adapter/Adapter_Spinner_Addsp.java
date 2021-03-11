package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.Activity_Add_SP;
import com.example.appbanhangonline.activity.Activity_All_Loai_SP;

import java.util.ArrayList;

public class Adapter_Spinner_Addsp extends BaseAdapter {
    int Layout;
    ArrayList<LoaiSP> arrayListLoaisp;
    Context context;

    public Adapter_Spinner_Addsp(int layout, ArrayList<LoaiSP> arrayListLoaisp, Context context) {
        Layout = layout;
        this.arrayListLoaisp = arrayListLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(Layout,null);

        TextView txttenloaisp = convertView.findViewById(R.id.textviewtenloaispdongaddsp);
        txttenloaisp.setText(arrayListLoaisp.get(position).getTenLoaiSP().toString());

        Activity_Add_SP.loaisp = arrayListLoaisp.get(position);

        return convertView;
    }
}
