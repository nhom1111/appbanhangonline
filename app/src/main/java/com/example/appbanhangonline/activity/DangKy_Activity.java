package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy_Activity extends AppCompatActivity {
    EditText edttentaikhoan,edtSDT,edtgmail,edtpassword,edtnhaplaipass;
    Button btnDangky;
    Toolbar toolbardk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();


        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan  = edttentaikhoan.getText().toString();
                String gmail        = edtgmail.getText().toString();
                String SDT          = edtSDT.getText().toString();
                String password     = edtpassword.getText().toString();
                String nhaplaipass  = edtnhaplaipass.getText().toString();
                if (nhaplaipass.equals(password)) {
                    if (tentaikhoan.equals("") || gmail.equals("") || SDT.length() != 10 || password.length() < 6) {
                        Toast.makeText(DangKy_Activity.this, "Bạn nhập sai, mời nhập lại", Toast.LENGTH_SHORT).show();
                    } else {
                        Dataserver dataserver = APIServer.getServer();
                        Call<String> callback = dataserver.setTaiKhoan(tentaikhoan, gmail, SDT, password);
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if (ketqua.equals("success")) {
                                    Toast.makeText(DangKy_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DangKy_Activity.this, Login_Activity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("tentaikhoan", tentaikhoan);
                                    bundle.putString("password", password);
                                    intent.putExtra("dangky", bundle);
                                    startActivity(intent);
                                } else if (ketqua.equals("tentaikhoantrung"))
                                    Toast.makeText(DangKy_Activity.this, "Trùng tên tài khoản!", Toast.LENGTH_SHORT).show();
                                else if (ketqua.equals("gmailtrung"))
                                    Toast.makeText(DangKy_Activity.this, "Gmail đã đăng ký!", Toast.LENGTH_SHORT).show();
                                else if (ketqua.equals("SDTtrung"))
                                    Toast.makeText(DangKy_Activity.this, "Số điện thoại đã đăng ký!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(DangKy_Activity.this, "Đăng ký lỗi,mời bạn đăng ký lại sau vài giây", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else{
                    Toast.makeText(DangKy_Activity.this,"Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void AnhXa() {
        edttentaikhoan          =findViewById(R.id.edittexttentaikhoandk);
        edtgmail                =findViewById(R.id.edittextGmaildk);
        edtSDT                  =findViewById(R.id.edittextSDTdk);
        edtpassword             =findViewById(R.id.edittextpassowrddk);
        btnDangky               =findViewById(R.id.buttondangkydk);
        toolbardk               =findViewById(R.id.toolbardk);
        edtnhaplaipass          =findViewById(R.id.edittextnhaplaipassowrddk);

        setSupportActionBar(toolbardk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}