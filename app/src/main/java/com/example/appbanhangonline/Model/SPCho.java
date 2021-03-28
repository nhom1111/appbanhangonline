package com.example.appbanhangonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SPCho {

    @SerializedName("IdCT")
    @Expose
    private String idCT;
    @SerializedName("IdKhachHang")
    @Expose
    private String idKhachHang;
    @SerializedName("TenSP")
    @Expose
    private String tenSP;
    @SerializedName("SoLuong")
    @Expose
    private String soLuong;
    @SerializedName("Gia")
    @Expose
    private String gia;
    @SerializedName("AnhSP")
    @Expose
    private String anhSP;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;

    public String getIdCT() {
        return idCT;
    }

    public void setIdCT(String idCT) {
        this.idCT = idCT;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}