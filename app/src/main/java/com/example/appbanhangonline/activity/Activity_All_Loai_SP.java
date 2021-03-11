package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.appbanhangonline.Adapter.LoaiSP_Adapter;
import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_All_Loai_SP extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    LoaiSP_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_loai_sp);
        AnhXa();
        ActionToolbar();
        if (MainActivity.arrayAllloaiSP.size()==0){
            GetData();
        }else{
            adapter = new LoaiSP_Adapter(Activity_All_Loai_SP.this,MainActivity.arrayAllloaiSP);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Activity_All_Loai_SP.this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    private void GetData() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<LoaiSP>> callback=dataserver.getAllLoaiSP();
        callback.enqueue(new Callback<List<LoaiSP>>() {
            @Override
            public void onResponse(Call<List<LoaiSP>> call, Response<List<LoaiSP>> response) {
                MainActivity.arrayAllloaiSP = (ArrayList<LoaiSP>) response.body();
                adapter = new LoaiSP_Adapter(Activity_All_Loai_SP.this,MainActivity.arrayAllloaiSP);
               GridLayoutManager gridLayoutManager = new GridLayoutManager(Activity_All_Loai_SP.this,2);
               recyclerView.setLayoutManager(gridLayoutManager);
               recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<LoaiSP>> call, Throwable t) {

            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setBackgroundColor(Color.WHITE);
    }

    void AnhXa(){
        toolbar = findViewById(R.id.toolbarallloaisp);
        recyclerView = findViewById(R.id.recyclerviewallloaisp);
    }
}