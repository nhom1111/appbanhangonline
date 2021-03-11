package com.example.appbanhangonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoaiSP implements Serializable {

@SerializedName("IdLoaiSP")
@Expose
private String idLoaiSP;
@SerializedName("TenLoaiSP")
@Expose
private String tenLoaiSP;
@SerializedName("AnhLoaiSP")
@Expose
private String anhLoaiSP;

public String getIdLoaiSP() {
return idLoaiSP;
}

public void setIdLoaiSP(String idLoaiSP) {
this.idLoaiSP = idLoaiSP;
}

public String getTenLoaiSP() {
return tenLoaiSP;
}

public void setTenLoaiSP(String tenLoaiSP) {
this.tenLoaiSP = tenLoaiSP;
}

public String getAnhLoaiSP() {
return anhLoaiSP;
}

public void setAnhLoaiSP(String anhLoaiSP) {
this.anhLoaiSP = anhLoaiSP;
}

}