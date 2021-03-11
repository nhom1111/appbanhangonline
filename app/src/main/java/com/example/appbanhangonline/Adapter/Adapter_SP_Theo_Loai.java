package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_SP_Theo_Loai extends ArrayAdapter {
    public Adapter_SP_Theo_Loai(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txtten,txtgia,txtsoluong,txtnguoiban;
        ImageView imageviewsanpham;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewholder =null;
        if(viewholder==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_sp_theo_loai,parent,false);
            viewholder = new ViewHolder();
            viewholder.txtgia = convertView.findViewById(R.id.textviewgiasptheoloai);
            viewholder.txtten = convertView.findViewById(R.id.textviewtensptheoloai);
            viewholder.txtsoluong = convertView.findViewById(R.id.textviewsoluongsptheoloai);
            viewholder.txtnguoiban = convertView.findViewById(R.id.textviewnguoibansptheoloai);
            viewholder.imageviewsanpham = convertView.findViewById(R.id.imageviewsptheoloai);
            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder) convertView.getTag();
        }
        SanPham sp = (SanPham) getItem(position);
        Picasso.with(getContext()).load(sp.getAnhSP()).into(viewholder.imageviewsanpham);
        viewholder.txtten.setText(sp.getTenSP());
        viewholder.txtnguoiban.setText(sp.getTenTaiKhoan());
        viewholder.txtsoluong.setText(sp.getSoLuong()+"");
        viewholder.txtgia.setText(sp.getGiaSP()+" VND");
        return convertView;
    }

}
