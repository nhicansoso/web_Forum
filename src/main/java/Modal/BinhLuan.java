package Modal;

public class BinhLuan {
	private long maBL;
	private long maND;
	private long maBaiDang;
	private String noiDung;
	private String ngayBL;
	private String tenNguoiDung; 

	public BinhLuan() {
	}

	public BinhLuan(long maBL, long maND, long maBaiDang, String noiDung, String ngayBL, String tenNguoiDung) {
		this.maBL = maBL;
		this.maND = maND;
		this.maBaiDang = maBaiDang;
		this.noiDung = noiDung;
		this.ngayBL = ngayBL;
		this.tenNguoiDung = tenNguoiDung;
	}

	public long getMaBL() {
		return maBL;
	}

	public void setMaBL(long maBL) {
		this.maBL = maBL;
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

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNgayBL() {
		return ngayBL;
	}

	public void setNgayBL(String ngayBL) {
		this.ngayBL = ngayBL;
	}

	public String getTenNguoiDung() {
		return tenNguoiDung;
	}

	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}
}
