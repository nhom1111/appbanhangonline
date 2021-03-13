package com.example.appbanhangonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SPCho {

@SerializedName("TenKH")
@Expose
private String tenKH;
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

public String getTenKH() {
return tenKH;
}

public void setTenKH(String tenKH) {
this.tenKH = tenKH;
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

}