package com.example.appbanhangonline.Framment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.Adapter.Adapter_San_Pham_Ke_Hang;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.Model.TaiKhoan;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.example.appbanhangonline.activity.Activity_Add_SP;
import com.example.appbanhangonline.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Ke_Hang extends Fragment {
    View view;
    RecyclerView recyclerView;
    ImageButton ibtnAddsanpham;
    TextView txtspctkhcb;
    public static Adapter_San_Pham_Ke_Hang adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ke_hang,container,false);
        AnhXa();
        KiemTraTaiKhoan();


        EvenClickButton();
        if (MainActivity.taiKhoan.getIdTaiKhoan()==null){
            ibtnAddsanpham.setVisibility(View.INVISIBLE);
            txtspctkhcb.setVisibility(View.INVISIBLE);
        }
        if(MainActivity.sanphamtrongkho.size()==0){
            GetData();
        }else{
            adapter = new Adapter_San_Pham_Ke_Hang(getContext(),MainActivity.sanphamtrongkho);
            LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
            linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayout);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    private void KiemTraTaiKhoan() {
        if (MainActivity.taiKhoan.getIdTaiKhoan()==null){
            ibtnAddsanpham.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Bạn phải đăng nhập để sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
        }else{
            ibtnAddsanpham.setVisibility(View.VISIBLE);
        }
    }

    private void EvenClickButton() {
        ibtnAddsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Add_SP.class);
                startActivity(intent);
            }
        });
    }


    private void GetData() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<SanPham>> callback= dataserver.getSanPhamTheoTK(MainActivity.taiKhoan.getIdTaiKhoan());
        callback.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                MainActivity.sanphamtrongkho = (ArrayList<SanPham>) response.body();
                adapter = new Adapter_San_Pham_Ke_Hang(getContext(),MainActivity.sanphamtrongkho);
                LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
                linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayout);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        recyclerView = view.findViewById(R.id.recyclerviewsanphamtrongkho);
        ibtnAddsanpham = view.findViewById(R.id.imagebuttonaddsanpham);
        txtspctkhcb = view.findViewById(R.id.textviewspctkhcb);
        ibtnAddsanpham.setVisibility(View.VISIBLE);
        txtspctkhcb.setVisibility(View.VISIBLE);
    }

}
