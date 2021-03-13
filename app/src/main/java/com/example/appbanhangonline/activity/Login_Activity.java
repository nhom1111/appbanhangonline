package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhangonline.Framment.Fragment_Tai_Khoan;
import com.example.appbanhangonline.Model.TaiKhoan;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    Button btndangnhap,btndangky;
    CheckBox cbghinho;
    EditText edttendn,edtmatkhau;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dangky");
        if (bundle!=null){
            edtmatkhau.setText(bundle.getString("passowrd"));
            edttendn.setText(bundle.getString("tentaikhoan"));
        }

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan  = edttendn.getText().toString();
                String matkhau      = edtmatkhau.getText().toString();

                if (tentaikhoan.equals("") || matkhau.equals("")) {
                    Toast.makeText(Login_Activity.this, "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Dataserver dataserver = APIServer.getServer();
                    Call<TaiKhoan> callback = dataserver.getTaiKhoan(tentaikhoan);
                    callback.enqueue(new Callback<TaiKhoan>() {
                        @Override
                        public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                            TaiKhoan taikhoan = (TaiKhoan) response.body();
                            if (taikhoan.getPassword().equals(matkhau)) {
                                MainActivity.taiKhoan = taikhoan;
                                if (cbghinho.isChecked()) {
                                    SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
                                    editor.putString("tentaikhoan", taikhoan.getTenTaiKhoan());
                                    editor.putBoolean("cbghinho", true);
                                    editor.commit();
                                } else {
                                    SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
                                    editor.remove("tentaikhoan");
                                    editor.remove("cbghinho");
                                    editor.commit();
                                }

                                Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                intent.putExtra("taikhoan",taikhoan);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Login_Activity.this, "Mật khẩu sai", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TaiKhoan> call, Throwable t) {
                            Toast.makeText(Login_Activity.this, "Tên đăng nhập sai", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this,DangKy_Activity.class);
                startActivity(intent);
            }
        });
    }


    void AnhXa(){
        btndangnhap     =findViewById(R.id.buttondangnhaplogin);
        btndangky       =findViewById(R.id.buttondangkylogin);
        cbghinho        =findViewById(R.id.checkboxghinhologin);
        edtmatkhau      =findViewById(R.id.edittextmatkhaulogin);
        edttendn        =findViewById(R.id.edittexttendangnhaplogin);
        toolbar         =findViewById(R.id.toolbarlogin);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}