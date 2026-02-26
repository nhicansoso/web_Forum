package Modal;

import java.util.ArrayList;

public class BaiDang {

    private long maBaiDang;
    private long maND;
    private String noiDung;
    private String hinhAnh;
    private String ngayDang;
    private String tenDangNhap;
    private String anhDaiDien;

    private ArrayList<BinhLuan> danhSachCmt;
    private String trangThaiTheoDoi;

    private int soLike;
    private boolean daLike;

    public BaiDang() {
        this.danhSachCmt = new ArrayList<>();
    }

    public BaiDang(long maBaiDang, long maND, String noiDung,
                   String hinhAnh, String ngayDang,
                   String tenDangNhap, String anhDaiDien) {
        this.maBaiDang = maBaiDang;
        this.maND = maND;
        this.noiDung = noiDung;
        this.hinhAnh = hinhAnh;
        this.ngayDang = ngayDang;
        this.tenDangNhap = tenDangNhap;
        this.anhDaiDien = anhDaiDien;
        this.danhSachCmt = new ArrayList<>();
    }

    public long getMaBaiDang() {
        return maBaiDang;
    }

    public void setMaBaiDang(long maBaiDang) {
        this.maBaiDang = maBaiDang;
    }

    public long getMaND() {
        return maND;
    }

    public void setMaND(long maND) {
        this.maND = maND;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public ArrayList<BinhLuan> getDanhSachCmt() {
        return danhSachCmt;
    }

    public void setDanhSachCmt(ArrayList<BinhLuan> danhSachCmt) {
        this.danhSachCmt = danhSachCmt;
    }

    public String getTrangThaiTheoDoi() {
        return trangThaiTheoDoi;
    }

    public void setTrangThaiTheoDoi(String trangThaiTheoDoi) {
        this.trangThaiTheoDoi = trangThaiTheoDoi;
    }

    public int getSoLike() {
        return soLike;
    }

    public void setSoLike(int soLike) {
        this.soLike = soLike;
    }

    public boolean getDaLike() {
        return daLike;
    }

    public boolean isDaLike() {
        return daLike;
    }

    public void setDaLike(boolean daLike) {
        this.daLike = daLike;
    }
}
