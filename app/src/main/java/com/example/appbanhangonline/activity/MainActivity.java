package com.example.appbanhangonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appbanhangonline.Framment.Fragment_Gio_Hang;
import com.example.appbanhangonline.Framment.Fragment_Ke_Hang;
import com.example.appbanhangonline.Framment.Fragment_Tai_Khoan;
import com.example.appbanhangonline.Framment.Fragment_Thong_Ke;
import com.example.appbanhangonline.Framment.Fragment_Trang_Chu;
import com.example.appbanhangonline.Model.Banner;
import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.Model.TaiKhoan;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sharedPreferences;

    public static ArrayList<SanPham> sanphamgiohang=new ArrayList<>();
    public static ArrayList<SanPham> sanphams=new ArrayList<>();
    public static ArrayList<SanPham> sanphamtrongkho=new ArrayList<>();
    public static ArrayList<LoaiSP> arrayloaisp=new ArrayList<>();
    public static ArrayList<Banner> banners=new ArrayList<>();
    public static ArrayList<LoaiSP> arrayAllloaiSP=new ArrayList<>();
    public static TaiKhoan taiKhoan=null;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Trang_Chu()).commit();
        navigationView.setSelectedItemId(R.id.navigationtrangchu);
        KhoiTao();
        if (MainActivity.arrayAllloaiSP.size()==0){
            GetData();
        }
        sharedPreferences =getSharedPreferences("dataLogin",MODE_PRIVATE);
        boolean cbghinho = sharedPreferences.getBoolean("cbghinho",false);
        if (cbghinho==false){
            if (taiKhoan ==null) taotaikhoanrong();
        }else{
            String name = sharedPreferences.getString("tentaikhoan","");
            getDataTaikhoan(name);
        }

        Intent intent = getIntent();
        if (intent!=null){
            if (intent.hasExtra("taikhoan")){
                taiKhoan = (TaiKhoan) intent.getSerializableExtra("taikhoan");
                Toast.makeText(this,taiKhoan.getTenTaiKhoan(), Toast.LENGTH_SHORT).show();
                getDataTaikhoan(taiKhoan.getTenTaiKhoan());
            }


            if (intent.hasExtra("activityhoadon")){
                Bundle bundle = getIntent().getBundleExtra("activityhoadon");
                if(bundle!=null){
                    SanPham sp = (SanPham) bundle.getSerializable("sanpham");
                    String soluong = bundle.getString("soluong");
                    String gia      = bundle.getString("gia");
                    sp.setSoLuong(soluong);
                    sp.setGiaSP(gia);
                    if(sp!=null) {
                        sanphamgiohang.add(sp);
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Gio_Hang()).commit();
                    navigationView.setSelectedItemId(R.id.navigationgiohang);
                }


            }
        }


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment= new Fragment_Trang_Chu();
                switch (item.getItemId()){
                    case R.id.navigationtrangchu:
                        selectedFragment= new Fragment_Trang_Chu();
                        break;
                    case R.id.navigationkehang:
                        selectedFragment= new Fragment_Ke_Hang();
                        break;
                    case R.id.navigationgiohang:
                        selectedFragment= new Fragment_Gio_Hang();
                        break;
                    case R.id.navigationthongke:
                        selectedFragment= new Fragment_Thong_Ke();
                        break;
                    case R.id.navigationtaikhoan:
                        selectedFragment= new Fragment_Tai_Khoan();
                        }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
        });

    }




    private void getDataTaikhoan(String name) {
        Dataserver dataserver = APIServer.getServer();
        Call<TaiKhoan> callback = dataserver.getTaiKhoan(name);
        callback.enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                TaiKhoan taikhoan = response.body();
                MainActivity.taiKhoan = taikhoan;
            }
            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {
            }
        });
    }

    private void KhoiTao(){
        sanphams=new ArrayList<>();
        sanphamtrongkho=new ArrayList<>();
        arrayloaisp=new ArrayList<>();
    }
    private void GetData() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<LoaiSP>> callback=dataserver.getAllLoaiSP();
        callback.enqueue(new Callback<List<LoaiSP>>() {
            @Override
            public void onResponse(Call<List<LoaiSP>> call, Response<List<LoaiSP>> response) {
                MainActivity.arrayAllloaiSP = (ArrayList<LoaiSP>) response.body();
            }
            @Override
            public void onFailure(Call<List<LoaiSP>> call, Throwable t) {
            }
        });
    }


    private void taotaikhoanrong(){
        Dataserver dataserver = APIServer.getServer();
        Call<TaiKhoan> callback = dataserver.getTaiKhoan("");
        callback.enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                TaiKhoan taikhoan = response.body();
                MainActivity.taiKhoan = taikhoan;
            }
            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {

            }
        });
    }
}