package Modal.Admin;

import Modal.NguoiDung;

public class NguoiDungAdminView {

    private NguoiDung nguoiDung;
    private int soBaiViet;
    private int soNguoiTheoDoi;
    private int soDangTheoDoi;
    private boolean biKhoa;

    public NguoiDungAdminView() {
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public int getSoBaiViet() {
        return soBaiViet;
    }

    public void setSoBaiViet(int soBaiViet) {
        this.soBaiViet = soBaiViet;
    }

    public int getSoNguoiTheoDoi() {
        return soNguoiTheoDoi;
    }

    public void setSoNguoiTheoDoi(int soNguoiTheoDoi) {
        this.soNguoiTheoDoi = soNguoiTheoDoi;
    }

    public int getSoDangTheoDoi() {
        return soDangTheoDoi;
    }

    public void setSoDangTheoDoi(int soDangTheoDoi) {
        this.soDangTheoDoi = soDangTheoDoi;
    }
    
    public boolean getbiKhoa() {
        return biKhoa;
    }

    public void setbiKhoa(boolean biKhoa) {
        this.biKhoa = biKhoa;
    }

}
