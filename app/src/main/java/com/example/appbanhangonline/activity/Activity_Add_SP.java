package com.example.appbanhangonline.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.Adapter.Adapter_Spinner_Addsp;
import com.example.appbanhangonline.Adapter.LoaiSP_Adapter;
import com.example.appbanhangonline.Framment.Fragment_Tai_Khoan;
import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.Server.APIServer;
import com.example.appbanhangonline.Server.Dataserver;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Add_SP extends AppCompatActivity {
    EditText edttensp,edtsoluong, edtgia,edtmota;
    Button   btnthem,btnhuy;
    ImageView imganh;
    TextView txtthaydoi;
    Spinner sploai;
    String idloaisp;
    Adapter_Spinner_Addsp adapter_spinner_addsp;
    final int REQUETS_CODE_CAMERA =123;
    final int REQUEST_CODE_FOLDER =456;
    String realpath="";
    public static LoaiSP loaisp = new LoaiSP();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sp);

        AnhXa();
        Intent intent = getIntent();
        if (intent.hasExtra("sanpham")){
            SanPham sp = (SanPham) intent.getSerializableExtra("sanpham");
            Picasso.with(getApplication()).load(sp.getAnhSP()).into(imganh);
            edttensp.setText(sp.getTenSP());
            edtgia.setText(sp.getGiaSP());
            edtmota.setText(sp.getMoTa());
            edtsoluong.setText(sp.getSoLuong());
            int idloaisp=0;
            for(LoaiSP lsp:MainActivity.arrayAllloaiSP){
                if (Integer.parseInt(lsp.getIdLoaiSP())==Integer.parseInt(sp.getIdLoaiSP())){
                    break;
                }
                idloaisp++;
            }
            sploai.setSelection(idloaisp);
            btnthem.setText("Sửa");
        }
        txtthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Activity_Add_SP.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER);

//                ActivityCompat.requestPermissions(Activity_Add_SP.this,new String[]{Manifest.permission.CAMERA},
//                        REQUETS_CODE_CAMERA);
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //thêm sp vào kệ hàng
                insertsp();


                //sửa sp trong kệ hàng
                updatesp();

            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void updatesp() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUETS_CODE_CAMERA:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUETS_CODE_CAMERA);
                }else{
                    Toast.makeText(this, "Bạn không cho phép mở CAMERA", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }else{
                    Toast.makeText(this, "Bạn không cho phép truy cập", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode==REQUETS_CODE_CAMERA&&resultCode == RESULT_OK &&data!=null){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imganh.setImageBitmap(bitmap);
        }

        if (requestCode==REQUEST_CODE_FOLDER&&resultCode == RESULT_OK &&data!=null){
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imganh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void insertsp(){

        LoaiSP loaisp2 = (LoaiSP) sploai.getSelectedItem();
        String idloaisps = idloaisp;
        String idtaikhoan = MainActivity.taiKhoan.getIdTaiKhoan().toString();
        String tensp = edttensp.getText().toString();
        String soluong = edtsoluong.getText().toString();
        String gia =edtgia.getText().toString();
        String mota=edtmota.getText().toString();
        if (idloaisps.equals("")||tensp.equals("")||soluong.equals("")||gia.equals("")||mota.equals("")) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            File file = new File(realpath);
            String file_path = file.getAbsolutePath();
            String[] mangtenfile = file_path.split("\\.");

            file_path = mangtenfile[0] + System.currentTimeMillis() + "." + mangtenfile[1];
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file", file_path, requestBody);

            Dataserver dataserverText = APIServer.getServer();
            Call<String> callback = dataserverText.uploadphot(body);
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String anh = (String) response.body();
                    String linkanh ="https://doanbanhoaonline.000webhostapp.com/anh/"+anh;
                    Dataserver dataserver = APIServer.getServer();
                    Call<String> callback = dataserver.insertsanpham(idloaisps,idtaikhoan,tensp,soluong,gia,mota,linkanh);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String kq= (String)response.body();
                            Intent intent = new Intent(Activity_Add_SP.this,MainActivity.class);
                            MainActivity.sanphams.clear();
                            MainActivity.sanphamtrongkho.clear();

                            if (kq.equals("fail")){
                                Toast.makeText(Activity_Add_SP.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }else{
                                Toast.makeText(Activity_Add_SP.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Activity_Add_SP.this,t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }



    private void AnhXa() {
        edttensp   =findViewById(R.id.edittextaddsptensp);
        edtsoluong =findViewById(R.id.edittextaddspsoluong);
        edtgia     =findViewById(R.id.edittextaddspgia);
        edtmota    =findViewById(R.id.edittextaddspmota);

        sploai      =findViewById(R.id.spinneraddsanpham);
        btnthem      =findViewById(R.id.buttonaddspThemsp);
        btnhuy       =findViewById(R.id.buttonaddsphuysp);
        imganh    =findViewById(R.id.imageviewaddsanphamanhsp);
//        imganh.setImageResource(R.drawable.anhnen);
        toolbar =findViewById(R.id.toolbaraddsanpham);
        txtthaydoi=findViewById(R.id.textviewthaydoiaddsp);



        adapter_spinner_addsp = new Adapter_Spinner_Addsp(R.layout.dong_spinner_addsp,MainActivity.arrayAllloaiSP,getApplication());
        sploai.setAdapter(adapter_spinner_addsp);

        sploai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoaiSP lsp = MainActivity.arrayAllloaiSP.get(position);
                idloaisp = lsp.getIdLoaiSP();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("Thông tin");
    }


    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] projs = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, projs, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

}