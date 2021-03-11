package com.example.appbanhangonline.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaiKhoan {

@SerializedName("IdTaiKhoan")
@Expose
private String idTaiKhoan;
@SerializedName("TenTaiKhoan")
@Expose
private String tenTaiKhoan;
@SerializedName("SDT")
@Expose
private String sDT;
@SerializedName("Gmail")
@Expose
private String gmail;
@SerializedName("Password")
@Expose
private String password;
@SerializedName("AnhTaiKhoan")
@Expose
private Object anhTaiKhoan;

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

public String getSDT() {
return sDT;
}

public void setSDT(String sDT) {
this.sDT = sDT;
}

public String getGmail() {
return gmail;
}

public void setGmail(String gmail) {
this.gmail = gmail;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public Object getAnhTaiKhoan() {
return anhTaiKhoan;
}

public void setAnhTaiKhoan(Object anhTaiKhoan) {
this.anhTaiKhoan = anhTaiKhoan;
}

}