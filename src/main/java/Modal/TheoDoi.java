package Modal;

import java.util.Date;

public class TheoDoi {
    private long maTheoDoi;
    private long nguoiTheoDoi;     
    private long nguoiDuocTheoDoi; 
    private String trangThai;      
    private Date ngayTao;

    public TheoDoi() {
    }

    public TheoDoi(long maTheoDoi, long nguoiTheoDoi, long nguoiDuocTheoDoi,
                   String trangThai, Date ngayTao) {
        this.maTheoDoi = maTheoDoi;
        this.nguoiTheoDoi = nguoiTheoDoi;
        this.nguoiDuocTheoDoi = nguoiDuocTheoDoi;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public long getMaTheoDoi() {
        return maTheoDoi;
    }

    public void setMaTheoDoi(long maTheoDoi) {
        this.maTheoDoi = maTheoDoi;
    }

    public long getNguoiTheoDoi() {
        return nguoiTheoDoi;
    }

    public void setNguoiTheoDoi(long nguoiTheoDoi) {
        this.nguoiTheoDoi = nguoiTheoDoi;
    }

    public long getNguoiDuocTheoDoi() {
        return nguoiDuocTheoDoi;
    }

    public void setNguoiDuocTheoDoi(long nguoiDuocTheoDoi) {
        this.nguoiDuocTheoDoi = nguoiDuocTheoDoi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
