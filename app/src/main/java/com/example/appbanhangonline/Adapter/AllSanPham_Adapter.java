package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.Activity_Chi_Tiet_San_Pham;
import com.example.appbanhangonline.activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AllSanPham_Adapter extends RecyclerView.Adapter<AllSanPham_Adapter.Holder>{
    Context context;
    ArrayList<SanPham> arrayList;

    public AllSanPham_Adapter(Context context, ArrayList<SanPham> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_san_pham,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        SanPham sp= (SanPham) arrayList.get(position);
        Picasso.with(context).load(sp.getAnhSP()).into(holder.imageviewsanpham);
        holder.txtten.setText(sp.getTenSP());
        holder.txtnguoiban.setText(sp.getTenTaiKhoan());
        holder.txtsoluong.setText(sp.getSoLuong()+"");
        holder.txtgia.setText(sp.getGiaSP()+" VND");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView txtten,txtgia,txtsoluong,txtnguoiban;
        ImageView imageviewsanpham;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageviewsanpham    = itemView.findViewById(R.id.imageviewallsanpham);
            txtten              = itemView.findViewById(R.id.textviewtenspallsanpham);
            txtgia              = itemView.findViewById(R.id.textviewgiaallsanpham);
            txtsoluong          = itemView.findViewById(R.id.textviewsoluongallsanpham);
            txtnguoiban         = itemView.findViewById(R.id.textviewnguoibanallsanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Activity_Chi_Tiet_San_Pham.class);
                    intent.putExtra("sanpham", MainActivity.sanphams.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}