package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.Framment.Fragment_Tai_Khoan;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Activity_Chi_Tiet_San_Pham extends AppCompatActivity {
    Toolbar toolbarct;
    ImageView imageView;
    TextView txtten,txtgia,txtmota,txttennguoiban,txtstdnguoiban;
    Button btndatmua,btncong,btntru;
    EditText edtsoluongmua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chi__tiet__san__pham);
        AnhXa();
        ActionToolBar();
        GetInformation();


    }
    private void GetInformation() {
        SanPham sp = (SanPham) getIntent().getSerializableExtra("sanpham");
        String TenChiTiet="";
        int GiaChiTiet=0;
        String HinhAnhChiTiet="";
        String MotaChiTiet="";
        String IdSP ="";
        TenChiTiet =sp.getTenSP();
        GiaChiTiet=Integer.parseInt(sp.getGiaSP());
        HinhAnhChiTiet=sp.getAnhSP();
        MotaChiTiet=sp.getMoTa();
        IdSP =sp.getIdSP();
        txtten.setText(TenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Gia: "+decimalFormat.format(GiaChiTiet)+" VND");
        txtmota.setText(MotaChiTiet);
        Picasso.with(getApplicationContext()).load(HinhAnhChiTiet)
                .into(imageView);
        txttennguoiban.setText(sp.getTenTaiKhoan());
        txtstdnguoiban.setText(sp.getSDT());
        EvenButton(sp);
    }
    private  void ActionToolBar(){
        setSupportActionBar(toolbarct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void EvenButton(final SanPham sp){


        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringsoluong = edtsoluongmua.getText().toString();
                int soluong = Integer.parseInt(stringsoluong);
                soluong++;
                edtsoluongmua.setText(soluong+"");
                int gia = soluong*Integer.parseInt(sp.getGiaSP());
                txtgia.setText("Gia: "+decimalFormat.format(gia)+" VND");;
                if(btntru.getVisibility()==View.INVISIBLE){
                    btntru.setVisibility(View.VISIBLE);
                }
                if (soluong==Integer.parseInt(sp.getSoLuong())){
                    btncong.setVisibility(View.INVISIBLE);
                }
            }
        });

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringsoluong = edtsoluongmua.getText().toString();
                int soluong = Integer.parseInt(stringsoluong);
                soluong--;
                edtsoluongmua.setText(soluong+"");
                int gia = soluong*Integer.parseInt(sp.getGiaSP());
                txtgia.setText("Gia: "+decimalFormat.format(gia)+" VND");;
                if(btncong.getVisibility()==View.INVISIBLE){
                    btncong.setVisibility(View.VISIBLE);
                }
                if (soluong==1){
                    btntru.setVisibility(View.INVISIBLE);
                }
            }
        });


        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.taiKhoan.getIdTaiKhoan()==null){
                    Toast.makeText(Activity_Chi_Tiet_San_Pham.this, "Bạn phải đăng nhập thì mới có thể sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Activity_Chi_Tiet_San_Pham.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sanpham", sp);
                    String soluong = edtsoluongmua.getText().toString();
                    int giaint = Integer.parseInt(soluong)*Integer.parseInt(sp.getGiaSP());
                    String gia = String.valueOf(giaint);

                    bundle.putString("soluong", soluong);
                    bundle.putString("gia",gia);
                    intent.putExtra("activityhoadon", bundle);
                    startActivity(intent);
                }
            }
        });
    }

    void AnhXa(){
        toolbarct   = findViewById(R.id.toolbarchitietsanpham);
        imageView   = findViewById(R.id.imageviewchitietsanpham);
        txtten      = findViewById(R.id.textviewtenspctsp);
        txtgia      = findViewById(R.id.textviewgiaspctsp);
        txtmota     = findViewById(R.id.textviewmotactsp);
        btndatmua   = findViewById(R.id.buttondatmuactsp);
        txttennguoiban=findViewById(R.id.textviewnguoibanctsp);
        txtstdnguoiban=findViewById(R.id.textviewsdtctsp);
        edtsoluongmua   = findViewById(R.id.edittextsoluongctsp);
        btncong         =findViewById(R.id.buttoncongchitietdonhang);
        btntru          =findViewById(R.id.buttontruchitietdonhang);
        btntru.setVisibility(View.INVISIBLE);
    }
}