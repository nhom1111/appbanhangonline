package com.example.appbanhangonline.Framment;

import android.app.AlertDialog;
import android.app.Dialog;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tai_khoan,container,false);
        Anhxa();
        if (MainActivity.taiKhoan.getIdTaiKhoan()==null){
            Intent intent = new Intent(getActivity(),Login_Activity.class);
            startActivity(intent);
        }else{
            if (MainActivity.taiKhoan.getAnhTaiKhoan()!=null){
                Picasso.with(getContext()).load((String) MainActivity.taiKhoan.getAnhTaiKhoan()).into(circleImageView);
            }else{
                circleImageView.setImageResource(R.drawable.taikhoan);
            }
            txttentaikhoan.setText(MainActivity.taiKhoan.getTenTaiKhoan());
            txtsdt.setText(MainActivity.taiKhoan.getSDT());
            txtgmail.setText(MainActivity.taiKhoan.getGmail());
            lndangxuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
                    editor.remove("tentaikhoan");
                    editor.remove("cbghinho");
                    MainActivity.taiKhoan = new TaiKhoan();
                    MainActivity.sanphamtrongkho.clear();
                    MainActivity.sanphamgiohang.clear();;
                    editor.commit();
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                }
            });
        }

        batsukien();

        return view;
    }

    private void batsukien() {
        lngioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_dialog_gioithieu);
                //anh xa dialog
                    TextView ten=    dialog.findViewById(R.id.textviewtenthongtin);
                    TextView namsinh=    dialog.findViewById(R.id.textviewnamsinhthongtin);
                    TextView gioitinh=    dialog.findViewById(R.id.textviewgioitinhthongtin);
                    TextView sothich=    dialog.findViewById(R.id.textviewsothichthongtin);


                dialog.show();
            }
        });
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
