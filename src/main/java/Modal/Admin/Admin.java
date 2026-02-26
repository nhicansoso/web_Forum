package Modal.Admin;

public class Admin {
    private long maDN;
    private String taiKhoan;
    private String matKhau;
    private String quyen;

    public Admin() {
    }

    public Admin(long maDN, String taiKhoan, String matKhau, String quyen) {
        this.maDN = maDN;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.quyen = quyen;
    }

    public long getMaDN() {
        return maDN;
    }

    public void setMaDN(long maDN) {
        this.maDN = maDN;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
}
