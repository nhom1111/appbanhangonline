package com.example.appbanhangonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPham  implements Serializable {

    @SerializedName("IdSP")
    @Expose
    private String idSP;
    @SerializedName("SDT")
    @Expose
    private String sDT;
    @SerializedName("IdLoaiSP")
    @Expose
    private String idLoaiSP;
    @SerializedName("IdTaiKhoan")
    @Expose
    private String idTaiKhoan;
    @SerializedName("TenTaiKhoan")
    @Expose
    private String tenTaiKhoan;
    @SerializedName("TenSP")
    @Expose
    private String tenSP;
    @SerializedName("SoLuong")
    @Expose
    private String soLuong;
    @SerializedName("AnhSP")
    @Expose
    private String anhSP;
    @SerializedName("GiaSP")
    @Expose
    private String giaSP;
    @SerializedName("MoTa")
    @Expose
    private String moTa;

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(String idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
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

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}