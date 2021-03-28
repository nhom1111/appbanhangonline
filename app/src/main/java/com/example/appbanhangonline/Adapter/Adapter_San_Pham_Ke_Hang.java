package com.example.appbanhangonline.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.Framment.Fragment_Ke_Hang;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.example.appbanhangonline.activity.Activity_Add_SP;
import com.example.appbanhangonline.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_San_Pham_Ke_Hang extends RecyclerView.Adapter<Adapter_San_Pham_Ke_Hang.ViewHolder> {
    Context context;
    ArrayList <SanPham> arrayListsanphamm;

    public Adapter_San_Pham_Ke_Hang(Context context, ArrayList<SanPham> arrayListsanphamm) {
        this.context = context;
        this.arrayListsanphamm = arrayListsanphamm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_san_pham_trong_ke_hang,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sp = arrayListsanphamm.get(position);


        Picasso.with(context).load(sp.getAnhSP()).into(holder.imgAnh);
        holder.txtTen.setText(sp.getTenSP());
        holder.txtSoluong.setText(sp.getSoLuong());
        holder.txtGia.setText(sp.getGiaSP());

        holder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }


            private void ShowMenu() {
                PopupMenu popupMenu = new PopupMenu(context,holder.ibtnEdit);
                popupMenu.getMenuInflater().inflate(R.menu.men_sanpham,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.menu_edit_sanpham:
                                Intent intent = new Intent(context, Activity_Add_SP.class);
                                intent.putExtra("sanpham",sp);
                                context.startActivity(intent);
                                break;
                            case R.id.menu_delete_sanpham:
                                DialogShow();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }


            private void DialogShow(){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Delete");
                alertDialog.setMessage("Bạn có muốn xóa sản phẩm này khỏi kệ hàng không?");
                alertDialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dataserver dataserver = APIServer.getServer();
                        Call<String> callback = dataserver.getDeleteSP(sp.getIdSP());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String kq = response.body();
                                if(kq.equals("success")){
                                    MainActivity.sanphamtrongkho.remove(position);
                                    Fragment_Ke_Hang.adapter.notifyDataSetChanged();
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                });
                alertDialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        if (Integer.parseInt(sp.getSoLuong())==0){
            holder.txtSoluong.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return arrayListsanphamm.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen,txtSoluong,txtGia;
        ImageView imgAnh;
        ImageButton ibtnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen          = itemView.findViewById(R.id.textviewtensptrongkho);
            txtSoluong      = itemView.findViewById(R.id.textviewsoluongsptrongkho);
            txtGia         = itemView.findViewById(R.id.textviewgiasptrongkho);
            imgAnh          = itemView.findViewById(R.id.imageviewsptrongkho);
            ibtnEdit          = itemView.findViewById(R.id.imagebuttoneditsptrongkho);

        }
    }

}
