package Modal;

public class NguoiDung {
	private long maND;
	private String tenDangNhap;
	private String matKhau;
	private String hoTen;
	private String email;
	private String anhDaiDien;
	private String tieuSu; 

	public NguoiDung(long maND, String tenDangNhap, String matKhau, String hoTen, String email, String anhDaiDien,
			String tieuSu) {
		this.maND = maND;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.email = email;
		this.anhDaiDien = anhDaiDien;
		this.tieuSu = tieuSu;
	}

	public NguoiDung() {
	}

	public long getMaND() {
		return maND;
	}

	public void setMaND(long maND) {
		this.maND = maND;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public String getTieuSu() {
		return tieuSu;
	}

	public void setTieuSu(String tieuSu) {
		this.tieuSu = tieuSu;
	}
}
