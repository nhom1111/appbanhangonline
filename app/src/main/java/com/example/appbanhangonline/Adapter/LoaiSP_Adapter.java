package com.example.appbanhangonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.SP_Theo_Loai_Activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSP_Adapter  extends RecyclerView.Adapter<LoaiSP_Adapter.ViewHolder> {
    Context context;
    ArrayList<LoaiSP> arrayLoaiSP;

    public LoaiSP_Adapter(Context context, ArrayList<LoaiSP> arrayLoaiSP) {
        this.context = context;
        this.arrayLoaiSP = arrayLoaiSP;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_loai_sp,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          LoaiSP loaisp=  arrayLoaiSP.get(position);
        Picasso.with(context).load(loaisp.getAnhLoaiSP()).into(holder.imgAnhLoaiSP);
        holder.txtTenLoaiSP.setText(loaisp.getTenLoaiSP());
    }

    @Override
    public int getItemCount() {
        return arrayLoaiSP.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAnhLoaiSP;
        TextView txtTenLoaiSP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhLoaiSP = itemView.findViewById(R.id.imageloaisp);
            txtTenLoaiSP = itemView.findViewById(R.id.textviewloaisp);

            imgAnhLoaiSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SP_Theo_Loai_Activity.class);
                    intent.putExtra("LoaiSP",arrayLoaiSP.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
