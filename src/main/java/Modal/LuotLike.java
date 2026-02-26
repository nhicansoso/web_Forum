package Modal;

import java.util.Date;

public class LuotLike {
    private long maLike;
    private long maND;
    private long maBaiDang;
    private Date ngayLike;

    public LuotLike() {}

    public LuotLike(long maLike, long maND, long maBaiDang, Date ngayLike) {
        this.maLike = maLike;
        this.maND = maND;
        this.maBaiDang = maBaiDang;
        this.ngayLike = ngayLike;
    }

    public long getMaLike() {
        return maLike;
    }

    public void setMaLike(long maLike) {
        this.maLike = maLike;
    }

    public long getMaND() {
        return maND;
    }

    public void setMaND(long maND) {
        this.maND = maND;
    }

    public long getMaBaiDang() {
        return maBaiDang;
    }

    public void setMaBaiDang(long maBaiDang) {
        this.maBaiDang = maBaiDang;
    }

    public Date getNgayLike() {
        return ngayLike;
    }

    public void setNgayLike(Date ngayLike) {
        this.ngayLike = ngayLike;
    }
}
