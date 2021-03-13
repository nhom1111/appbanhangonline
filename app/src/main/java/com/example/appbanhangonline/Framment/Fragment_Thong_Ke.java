package com.example.appbanhangonline.Framment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbanhangonline.Adapter.Adapter_SPCho;
import com.example.appbanhangonline.Model.SPCho;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.example.appbanhangonline.activity.MainActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Thong_Ke  extends Fragment {
    View view;
    RelativeLayout layoutdoanhthu,layoutchitieu;
    TextView txtdoanhthu,txtchitieu;
    TextView txtcacdonhangcuaban;
    ListView lvdonhang;
    Adapter_SPCho adapter_spCho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_ke,container,false);
        AnhXa();
        KiemTraTaiKhoan();

        if (!MainActivity.taiKhoan.getIdTaiKhoan().equals("")){
            Dataserver dataserver = APIServer.getServer();
            Call<String> callback = dataserver.getdoanhthu(MainActivity.taiKhoan.getIdTaiKhoan());
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String sdoanhthu = (String) response.body();
                    int doanhthu = Integer.parseInt(sdoanhthu);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    txtdoanhthu.setText(decimalFormat.format(doanhthu)+" VND");
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    }
            });

            Call<String> call = dataserver.getchitieu(MainActivity.taiKhoan.getTenTaiKhoan());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String schitieu = (String) response.body();
                    int chitieu = Integer.parseInt(schitieu);
                    DecimalFormat decimalFormat = new DecimalFormat("-###,###,###");
                    txtchitieu.setText(decimalFormat.format(chitieu)+" VND");
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

            Call<List<SPCho>> choCall = dataserver.getspcho(MainActivity.taiKhoan.getIdTaiKhoan());
            choCall.enqueue(new Callback<List<SPCho>>() {
                @Override
                public void onResponse(Call<List<SPCho>> call, Response<List<SPCho>> response) {
                    ArrayList<SPCho> spchos = (ArrayList<SPCho>) response.body();
                    adapter_spCho = new Adapter_SPCho(R.layout.dong_sp_cho,getContext(),spchos);
                    lvdonhang.setAdapter(adapter_spCho);
                }

                @Override
                public void onFailure(Call<List<SPCho>> call, Throwable t) {

                }
            });

        }


        return view;
    }
    private void KiemTraTaiKhoan() {
        if (MainActivity.taiKhoan.getIdTaiKhoan()==null){
            layoutchitieu.setVisibility(View.INVISIBLE);
            layoutdoanhthu.setVisibility(View.INVISIBLE);
            lvdonhang.setVisibility(View.INVISIBLE);
            txtcacdonhangcuaban.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Bạn phải đăng nhập để sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
        }else{
            layoutchitieu.setVisibility(View.VISIBLE);
            layoutdoanhthu.setVisibility(View.VISIBLE);
            lvdonhang.setVisibility(View.VISIBLE);
            txtcacdonhangcuaban.setVisibility(View.VISIBLE);
        }
    }
    private void AnhXa(){
        layoutchitieu   = view.findViewById(R.id.layoutchitieuthongke);
        layoutdoanhthu  = view.findViewById(R.id.layoutdoanhthuthongke);
        txtchitieu      = view.findViewById(R.id.textviewchitieuthongke);
        txtdoanhthu     = view.findViewById(R.id.textviewdoanhthuthongke);
        lvdonhang       = view.findViewById(R.id.listviewdonhangthongke);
        txtcacdonhangcuaban = view.findViewById(R.id.textviewcacdonhangcuaban);
    }
}
