package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appbanhangonline.Adapter.AllSanPham_Adapter;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ImageButton imageButton;
    EditText edt;
    RecyclerView rv;
    AllSanPham_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rv = findViewById(R.id.recyclertimkiem);
        edt = findViewById(R.id.edittexttimkiem);
        imageButton = findViewById(R.id.imagebuttonsearch);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tukhoa = edt.getText().toString();
                if (!tukhoa.equals("")){
                    if (isNumeric(tukhoa)){
                      searchTuKhoaTheoGia(tukhoa);
                    } else{
                        searchTuKhoaTen(tukhoa);
                    }
                }else{
                    Toast.makeText(SearchActivity.this, "Nhập từ khóa cần tìm!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchTuKhoaTen(String query){
        Dataserver dataserver = APIServer.getServer();
        Call<List<SanPham>> call = dataserver.search(query);
        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                ArrayList<SanPham> arraylist = (ArrayList<SanPham>) response.body();
                adapter = new AllSanPham_Adapter(SearchActivity.this,arraylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rv.setLayoutManager(linearLayoutManager);
                rv.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }


    private void searchTuKhoaTheoGia(String query){
        Dataserver dataserver = APIServer.getServer();
        Call<List<SanPham>> call = dataserver.searchgia(query);
        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                ArrayList<SanPham> arraylist = (ArrayList<SanPham>) response.body();
                adapter = new AllSanPham_Adapter(SearchActivity.this,arraylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rv.setLayoutManager(linearLayoutManager);
                rv.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}