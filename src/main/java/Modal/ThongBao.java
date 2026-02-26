package Modal;

public class ThongBao {

    private long maTB;
    private long maNDNhan;
    private long maNDGui;
    private String loaiThongBao;
    private boolean daDoc;

    private String tenNguoiGui;
    private String anhNguoiGui;

    public ThongBao() {}

    public long getMaTB() {
        return maTB;
    }

    public void setMaTB(long maTB) {
        this.maTB = maTB;
    }

    public long getMaNDNhan() {
        return maNDNhan;
    }

    public void setMaNDNhan(long maNDNhan) {
        this.maNDNhan = maNDNhan;
    }

    public long getMaNDGui() {
        return maNDGui;
    }

    public void setMaNDGui(long maNDGui) {
        this.maNDGui = maNDGui;
    }

    public String getLoaiThongBao() {
        return loaiThongBao;
    }

    public void setLoaiThongBao(String loaiThongBao) {
        this.loaiThongBao = loaiThongBao;
    }

    public boolean isDaDoc() {
        return daDoc;
    }

    public void setDaDoc(boolean daDoc) {
        this.daDoc = daDoc;
    }

    public String getTenNguoiGui() {
        return tenNguoiGui;
    }

    public void setTenNguoiGui(String tenNguoiGui) {
        this.tenNguoiGui = tenNguoiGui;
    }

    public String getAnhNguoiGui() {
        return anhNguoiGui;
    }

    public void setAnhNguoiGui(String anhNguoiGui) {
        this.anhNguoiGui = anhNguoiGui;
    }
}
