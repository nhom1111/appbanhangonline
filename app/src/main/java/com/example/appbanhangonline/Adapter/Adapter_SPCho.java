package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.Model.SPCho;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_SPCho extends BaseAdapter {
    int Layout;
    Context context;
    ArrayList<SPCho> arraySPCho;

    public Adapter_SPCho(int layout, Context context, ArrayList<SPCho> arraySPCho) {
        Layout = layout;
        this.context = context;
        this.arraySPCho = arraySPCho;
    }

    @Override
    public int getCount() {
        return arraySPCho.size();
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
        ViewHolder viewHolder;
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(Layout,null);

            viewHolder = new ViewHolder();
            viewHolder.txtgia = convertView.findViewById(R.id.textviewgiaspcho);
            viewHolder.txttensp = convertView.findViewById(R.id.textviewtenspcho);
            viewHolder.txtnguoimua=convertView.findViewById(R.id.textviewtennguoimuaspcho);
            viewHolder.txtsoluong=convertView.findViewById(R.id.textviewsoluongspcho);
            viewHolder.imganhsp=convertView.findViewById(R.id.imageviewspcho);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SPCho spcho = arraySPCho.get(position);
        viewHolder.txttensp.setText(spcho.getTenSP());
        viewHolder.txtnguoimua.setText(spcho.getTenKH());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int gia = Integer.parseInt(spcho.getGia());
        viewHolder.txtgia.setText(decimalFormat.format(gia)+" VND");
        viewHolder.txtsoluong.setText(spcho.getSoLuong());
        Picasso.with(context).load(spcho.getAnhSP()).into(viewHolder.imganhsp);

        return convertView;
    }


    class ViewHolder{
        TextView txttensp,txtsoluong,txtgia,txtnguoimua;
        ImageView imganhsp;
    }
}
