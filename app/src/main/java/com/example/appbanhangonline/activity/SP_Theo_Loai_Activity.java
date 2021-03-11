package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appbanhangonline.Adapter.Adapter_SP_Theo_Loai;
import com.example.appbanhangonline.Adapter.AllSanPham_Adapter;
import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SP_Theo_Loai_Activity extends AppCompatActivity {
    ListView lvsptheoloai;
    Toolbar toolbar;
    LoaiSP loaiSP;
    Adapter_SP_Theo_Loai adapter;
    ArrayList<SanPham> arraylistsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_theo_loai);
        AnhXa();
        loaiSP= (LoaiSP) getIntent().getSerializableExtra("LoaiSP");
        ActionToolBar();

        GetInformation();
    }

    private void GetInformation() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<SanPham>> callback = dataserver.getSanPhamTheoLoai(loaiSP.getIdLoaiSP());
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                    arraylistsanpham = (ArrayList<SanPham>) response.body();
                    adapter = new Adapter_SP_Theo_Loai(SP_Theo_Loai_Activity.this,android.R.layout.simple_list_item_1,arraylistsanpham);
                    lvsptheoloai.setAdapter(adapter);

                    lvsptheoloai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(SP_Theo_Loai_Activity.this,Activity_Chi_Tiet_San_Pham.class);
                            intent.putExtra("sanpham",arraylistsanpham.get(position));
                            startActivity(intent);
                        }
                    });

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(loaiSP.getTenLoaiSP());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void AnhXa(){
        lvsptheoloai = findViewById(R.id.listviewsptheoloai);
        toolbar = (Toolbar) findViewById(R.id.toolbarsptheoloai);
    }
}