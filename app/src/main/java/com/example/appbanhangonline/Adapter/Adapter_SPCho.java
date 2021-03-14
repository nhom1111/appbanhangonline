package com.example.appbanhangonline.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.Framment.Fragment_Thong_Ke;
import com.example.appbanhangonline.Model.SPCho;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    @SuppressLint("RestrictedApi")
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
            viewHolder.ibtnmenu=convertView.findViewById(R.id.imagebuttonmenuspcho);
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

        viewHolder.ibtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu(viewHolder,spcho,position);
            }
        });
        return convertView;
    }

    private void showmenu(ViewHolder viewholder,SPCho spCho,int position){
        PopupMenu popupMenu = new PopupMenu(context,viewholder.ibtnmenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_spcho,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_sp_cho_chapnhan:
                        Dataserver dataserver = APIServer.getServer();
                        Call<String> call = dataserver.updatecnTTspcho(spCho.getIdCT());
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String kq = response.body();
                                if (kq.equals("successed")){
                                    Toast.makeText(context,"Bạn đã chấp nhận đơn hàng này!", Toast.LENGTH_SHORT).show();
                                }else{
                                    if (kq.equals("failed")){
                                        Toast.makeText(context, "Lỗi hệ thống, vui lòng thử lại sau ít phút!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                Fragment_Thong_Ke.spchos.remove(position);
                                Fragment_Thong_Ke.adapter_spCho.notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });
                        break;
                    case R.id.menu_sp_cho_huy:
                        Dataserver dataserver1 = APIServer.getServer();
                        Call<String> call1 = dataserver1.updatehuyttspcho(spCho.getIdCT());
                        call1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String kq = response.body();
                                if (kq.equals("successed")){
                                    Toast.makeText(context,"Bạn đã hủy đơn hàng này!", Toast.LENGTH_SHORT).show();
                                }else{
                                    if (kq.equals("failed")){
                                        Toast.makeText(context, "Lỗi hệ thống, vui lòng thử lại sau ít phút!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                Fragment_Thong_Ke.spchos.remove(position);
                                Fragment_Thong_Ke.adapter_spCho.notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });

                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


    class ViewHolder{
        TextView txttensp,txtsoluong,txtgia,txtnguoimua;
        ImageView imganhsp;
        ImageButton ibtnmenu;
    }


}
