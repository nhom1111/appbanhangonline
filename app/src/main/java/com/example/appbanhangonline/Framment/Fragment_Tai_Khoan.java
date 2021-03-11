package com.example.appbanhangonline.Framment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbanhangonline.Model.TaiKhoan;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.Activity_All_Loai_SP;
import com.example.appbanhangonline.activity.Login_Activity;
import com.example.appbanhangonline.activity.MainActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Tai_Khoan extends Fragment {
    View view;
    CircleImageView circleImageView;
    LinearLayout lngioithieu,lndangxuat,lnthoat;
    TextView txttentaikhoan,txtsdt,txtgmail;
    public static TaiKhoan taiKhoan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tai_khoan,container,false);
        Anhxa();
        if (taiKhoan.getAnhTaiKhoan()!=null){
            Picasso.with(getContext()).load((String) taiKhoan.getAnhTaiKhoan()).into(circleImageView);
        }else{
            circleImageView.setImageResource(R.drawable.taikhoan);
        }
        txttentaikhoan.setText(taiKhoan.getTenTaiKhoan());
        txtsdt.setText(taiKhoan.getSDT());
        txtgmail.setText(taiKhoan.getGmail());
        lndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taiKhoan = new TaiKhoan();
                SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
                editor.remove("tentaikhoan");
                editor.remove("cbghinho");
                editor.commit();
                Intent intent = new Intent(getActivity(), Login_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void Anhxa() {
        circleImageView =view.findViewById(R.id.circleimageviewtk);
        lngioithieu     =view.findViewById(R.id.linearLayout_gioithieu_tk);
        lndangxuat      =view.findViewById(R.id.linearLayout_dangxuat_tk);
        lnthoat         =view.findViewById(R.id.linearLayout_thoat_tk);
        txttentaikhoan  =view.findViewById(R.id.textviewtentaikhoantk);
        txtgmail        =view.findViewById(R.id.textviewgmailtaikhoantk);
        txtsdt          =view.findViewById(R.id.textviewsdttaikhoantk);
    }


}
