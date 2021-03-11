package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.appbanhangonline.Framment.Fragment_Gio_Hang;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Gio_Hang extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<SanPham> arraysp;

    public Adapter_Gio_Hang(Context context, int layout, ArrayList<SanPham> arraysp) {
        this.context = context;
        this.layout = layout;
        this.arraysp = arraysp;
    }

    @Override
    public int getCount() {
        return arraysp.size();
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
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,null);


            viewHolder = new ViewHolder();
            viewHolder.txtgia = convertView.findViewById(R.id.textviewgiagiohang);
            viewHolder.txtsoluong = convertView.findViewById(R.id.textviewsoluonggiohang);
            viewHolder.txttensp = convertView.findViewById(R.id.textviewtenspgiohang);
            viewHolder.txtnguoiban = convertView.findViewById(R.id.textviewnguoibangiohang);
            viewHolder.ibtndelete = convertView.findViewById(R.id.imagebuttondeletegiohang);
            viewHolder.imganhsp = convertView.findViewById(R.id.imageviewgiohang);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPham sp= arraysp.get(position);
        viewHolder.txttensp.setText(sp.getTenSP());
        Picasso.with(context).load(sp.getAnhSP()).into(viewHolder.imganhsp);
        viewHolder.txtgia.setText(sp.getGiaSP());
        viewHolder.txtnguoiban.setText(sp.getTenTaiKhoan());
        viewHolder.txtsoluong.setText(sp.getSoLuong());

        viewHolder.ibtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraysp.remove(position);
                Fragment_Gio_Hang.adapter_gio_hang.notifyDataSetChanged();
                Fragment_Gio_Hang.TinhTien();
            }
        });
        return convertView;
    }


    class ViewHolder{
        TextView txttensp,txtsoluong,txtgia,txtnguoiban;
        ImageView imganhsp;
        ImageButton ibtndelete;
    }
}
