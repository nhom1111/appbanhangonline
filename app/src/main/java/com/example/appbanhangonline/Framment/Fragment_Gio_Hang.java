package com.example.appbanhangonline.Framment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhangonline.Adapter.Adapter_Gio_Hang;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.example.appbanhangonline.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Gio_Hang extends Fragment {
    private static TextView txttongtien,txtgiohangtrong;
    LinearLayout layouttongtien;
    View view;
    ListView lv;
    Button btnthanhtoan;
    public static Adapter_Gio_Hang adapter_gio_hang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_gio_hang,container,false);
        Anhxa();
        TinhTien();
        KiemTraTaiKhoan();

        if (MainActivity.sanphamgiohang.size()!=0) {
            txtgiohangtrong.setVisibility(View.INVISIBLE);
            adapter_gio_hang = new Adapter_Gio_Hang(getContext(), R.layout.dong_gio_hang, MainActivity.sanphamgiohang);
            lv.setAdapter(adapter_gio_hang);
        }

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.sanphamgiohang.size()==0){
                    Toast.makeText(getContext(),"Giỏ Hàng Trống", Toast.LENGTH_SHORT).show();
                }else{
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.custom_dialog_diachi);
                    EditText edtdiachi =dialog.findViewById(R.id.edittextdiachi);
                    Button btnthem=dialog.findViewById(R.id.buttonthemdiachi);
                    Button btnhuy=dialog.findViewById(R.id.buttonhuydiachi);
                    btnthem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String diachi = edtdiachi.getText().toString();
                            DateFormat decimalFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date today = new Date();
                            String ngay = decimalFormat.format(today);
                            Dataserver dataserver = APIServer.getServer();
                            Call callback = dataserver.insertdonhang(diachi,ngay,MainActivity.taiKhoan.getTenTaiKhoan(),MainActivity.taiKhoan.getSDT());
                            callback.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                   final String kq = (String) response.body();


                                   if (kq.equals("fail")){
                                        Toast.makeText(getContext(), "Thất Bại", Toast.LENGTH_SHORT).show();
                                    }else{
                                       Toast.makeText(getContext(), "hoadon ok", Toast.LENGTH_SHORT).show();

                                       JSONArray jsonArray = new JSONArray();
                                       for (int i=0;i<MainActivity.sanphamgiohang.size();i++){
                                           JSONObject jsonObject = new JSONObject();
                                           try {
                                               jsonObject.put("IdSP",MainActivity.sanphamgiohang.get(i).getIdSP());
                                               jsonObject.put("IdHoaDon",kq);
                                               jsonObject.put("SoLuong",MainActivity.sanphamgiohang.get(i).getSoLuong());
                                               jsonObject.put("Gia",MainActivity.sanphamgiohang.get(i).getGiaSP());
                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                           jsonArray.put(jsonObject);
                                       }

                                       String json = String.valueOf(jsonArray);
                                       Dataserver dataserver1 = APIServer.getServer();
                                       Call callback1 = dataserver1.insertctdh(json);
                                       callback1.enqueue(new Callback<String>() {
                                           @Override
                                           public void onResponse(Call<String> call, Response<String> response) {
                                               String kq=(String)response.body();
                                               if (kq.equals("success")){
                                                   Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                                                   MainActivity.sanphamgiohang.clear();
                                                   adapter_gio_hang.notifyDataSetChanged();
                                                   TinhTien();
                                               }else{
                                                   if (kq.equals("fail")){
                                                       Toast.makeText(getContext(),"Lỗi", Toast.LENGTH_SHORT).show();
                                                   }
                                               }
                                           }

                                           @Override
                                           public void onFailure(Call<String> call, Throwable t) {
                                           }
                                       });                                        //else
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                }
                            });
                            dialog.dismiss();

                        }
                    });
                    btnhuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();


                }



            }
        });
        return view;
    }

    private void KiemTraTaiKhoan() {
        if (MainActivity.taiKhoan.getIdTaiKhoan()==null){
            layouttongtien.setVisibility(View.INVISIBLE);
            txttongtien.setVisibility(View.INVISIBLE);
            txtgiohangtrong.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.INVISIBLE);
            btnthanhtoan.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Bạn phải đăng nhập để sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
        }else{
            layouttongtien.setVisibility(View.VISIBLE);
            txttongtien.setVisibility(View.VISIBLE);
            txtgiohangtrong.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);
            btnthanhtoan.setVisibility(View.VISIBLE);
        }


    }

    void Anhxa(){
        lv = view.findViewById(R.id.listviewgiohang);
        txtgiohangtrong = view.findViewById(R.id.textviewgiohangtrong);
        txtgiohangtrong.setVisibility(View.INVISIBLE);
        txttongtien = view.findViewById(R.id.textviewtongtien);
        btnthanhtoan = view.findViewById(R.id.buttongiohangtt);
        layouttongtien=view.findViewById(R.id.layouttongtiengiohang);
    }
    public static void TinhTien(){
        long tongtien=0;
        for(SanPham sp:MainActivity.sanphamgiohang) {
            int tien = Integer.parseInt(sp.getGiaSP());
            int soluong=Integer.parseInt(sp.getSoLuong());
            tongtien+=tien*soluong;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+" VND");
        if(MainActivity.sanphamgiohang.size()==0){
            txtgiohangtrong.setVisibility(View.VISIBLE);
        }
    }
}
