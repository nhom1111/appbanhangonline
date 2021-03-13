package com.example.appbanhangonline.Server;

import com.example.appbanhangonline.Model.Banner;
import com.example.appbanhangonline.Model.LoaiSP;
import com.example.appbanhangonline.Model.SPCho;
import com.example.appbanhangonline.Model.SanPham;
import com.example.appbanhangonline.Model.TaiKhoan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Dataserver {

    @GET("server/banner.php")
    Call<List<Banner>> GetDataBanner();

    @GET("server/getloaisp.php")
    Call<List<LoaiSP>> GetDataLoaiSP();

    @GET("server/allsanpham.php")
    Call<List<SanPham>> GetDataAllSanPham();

    @FormUrlEncoded
    @POST("server/sanphamtheoloai.php")
    Call<List<SanPham>> getSanPhamTheoLoai (@Field("idloai") String idloai);

    @GET("server/getallloaisp.php")
    Call<List<LoaiSP>> getAllLoaiSP();

    @FormUrlEncoded
    @POST("server/sanphamtheotaikhoan.php")
    Call<List<SanPham>> getSanPhamTheoTK(@Field("idtaikhoan") String idtaikhoan);

    @FormUrlEncoded
    @POST("server/deletesptrongkho.php")
    Call<String> getDeleteSP(@Field("idsp") String idsp);

    @FormUrlEncoded
    @POST("server/gettaikhoan.php")
    Call<TaiKhoan> getTaiKhoan(@Field("Tentaikhoan") String Tentaikhoan);

    @FormUrlEncoded
    @POST("server/setdangky.php")
    Call<String> setTaiKhoan(@Field("tentaikhoan") String tentaikhoan,
                             @Field("gmail") String gmail,
                             @Field("SDT") String SDT,
                             @Field("password") String passowrd);


    @FormUrlEncoded
    @POST("server/insertdonhang.php")
    Call<String> insertdonhang(@Field("diachi") String diachi,
                               @Field("ngay")String ngay,
                               @Field("tenkhachhang") String tenkhachhang,
                               @Field("sdt") String std);

    @FormUrlEncoded
    @POST("server/insetctdonhang.php")
    Call<String> insertctdh (@Field("json") String sp);


    //add anh
    @Multipart
    @POST("uploadanh.php")
    Call<String> uploadphot(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("server/insertsanpham.php")
    Call<String> insertsanpham(@Field("idloaisp") String idloaisp,
                               @Field("idtaikhoan") String idtaikhoan,
                               @Field("tensp") String tensp,
                               @Field("soluong") String soluong,
                               @Field("gia") String gia,
                               @Field("mota") String mota,
                               @Field("linkanh") String linkanh);


    @FormUrlEncoded
    @POST("server/getdoanhthu.php")
    Call<String> getdoanhthu(@Field("idtaikhoan") String idtaikhoan);


    @FormUrlEncoded
    @POST("server/getchitieu.php")
    Call<String> getchitieu(@Field("tentaikhoan") String tentaikhoan);


    @FormUrlEncoded
    @POST("server/getspcho.php")
    Call<List<SPCho>> getspcho(@Field("idtaikhoan")String idtaikhoan);
}
