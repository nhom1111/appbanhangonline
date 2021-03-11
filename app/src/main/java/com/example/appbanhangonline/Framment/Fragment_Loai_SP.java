package com.example.appbanhangonline.Framment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.Adapter.LoaiSP_Adapter;
import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.example.appbanhangonline.activity.Activity_All_Loai_SP;
import com.example.appbanhangonline.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Loai_SP extends Fragment {
    View view;
    TextView txtxemthem;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loai_sp, container, false);
        //anh xa
        txtxemthem = view.findViewById(R.id.textviewxemthemloaisp);
        recyclerView = view.findViewById(R.id.recyclerViewLoaiSP);
        if (MainActivity.arrayloaisp.size()==0){
            GetData();
        }
        else{
            LoaiSP_Adapter adapter = new LoaiSP_Adapter(getActivity(),MainActivity.arrayloaisp);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        }
        txtxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_All_Loai_SP.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        Dataserver dataserver = APIServer.getServer();
        Call<List<LoaiSP>> callback = dataserver.GetDataLoaiSP();
        callback.enqueue(new Callback<List<LoaiSP>>() {
            @Override
            public void onResponse(Call<List<LoaiSP>> call, Response<List<LoaiSP>> response) {
                MainActivity.arrayloaisp = (ArrayList<LoaiSP>) response.body();
                LoaiSP_Adapter adapter = new LoaiSP_Adapter(getActivity(),MainActivity.arrayloaisp);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<LoaiSP>> call, Throwable t) {
            }
        });

    }
}
