package com.example.appbanhangonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner {

@SerializedName("IdQuangCao")
@Expose
private String idQuangCao;
@SerializedName("IdLoai")
@Expose
private String idLoai;
@SerializedName("AnhQC")
@Expose
private String anhQC;
@SerializedName("MoTa")
@Expose
private String moTa;

public String getIdQuangCao() {
return idQuangCao;
}

public void setIdQuangCao(String idQuangCao) {
this.idQuangCao = idQuangCao;
}

public String getIdLoai() {
return idLoai;
}

public void setIdLoai(String idLoai) {
this.idLoai = idLoai;
}

public String getAnhQC() {
return anhQC;
}

public void setAnhQC(String anhQC) {
this.anhQC = anhQC;
}

public String getMoTa() {
return moTa;
}

public void setMoTa(String moTa) {
this.moTa = moTa;
}

}