package Modal.Admin;

import java.sql.*;
import java.util.ArrayList;

import HoTro.KetNoi;
import Modal.BaiDang;
import Modal.NguoiDung;

public class AdminDAO {
	KetNoi kn = new KetNoi();

	public Admin login(String taiKhoan, String matKhau) throws Exception {
		kn.ketnoi();
		String sql = "SELECT * FROM DangNhap WHERE TaiKhoan=? AND MatKhau=? AND Quyen='admin'";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, taiKhoan);
		ps.setString(2, matKhau);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Admin ad = new Admin();
			ad.setMaDN(rs.getLong("MaDN"));
			ad.setTaiKhoan(rs.getString("TaiKhoan"));
			ad.setMatKhau(rs.getString("MatKhau"));
			ad.setQuyen(rs.getString("Quyen"));
			rs.close();
			kn.cn.close();
			return ad;
		}
		return null;
	}

	public int tongNDTheoThang(int thang, int nam) throws Exception {
		kn.ketnoi();
		String sql = "SELECT COUNT(*) FROM NguoiDung WHERE MONTH(NgayTao) = ? AND YEAR(NgayTao) = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setInt(1, thang);
		ps.setInt(2, nam);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int dem = rs.getInt(1);
			rs.close();
			return dem;
		}
		return 0;
	}

	public int tongBVTheoThang(int thang, int nam) throws Exception {
		kn.ketnoi();
		String sql = "SELECT COUNT(*) FROM BaiDang WHERE MONTH(NgayDang) = ? AND YEAR(NgayDang) = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setInt(1, thang);
		ps.setInt(2, nam);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int dem = rs.getInt(1);
			rs.close();
			return dem;
		}
		return 0;
	}

	public int tongNguoiDung() throws Exception {
		kn.ketnoi();
		String sql = "SELECT COUNT(*) FROM NguoiDung";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int tong = rs.next() ? rs.getInt(1) : 0;
		rs.close();
		kn.cn.close();
		return tong;
	}

	public int tongBaiDang() throws Exception {
		kn.ketnoi();
		String sql = "SELECT COUNT(*) FROM BaiDang";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int tong = rs.next() ? rs.getInt(1) : 0;
		rs.close();
		kn.cn.close();
		return tong;
	}

	public ArrayList<NguoiDungAdminView> getDanhSachNguoiDungAdmin(String key) throws Exception {
		ArrayList<NguoiDungAdminView> ds = new ArrayList<>();
		kn.ketnoi();

		String sql = "SELECT nd.*, " + "COUNT(DISTINCT bd.MaBaiDang) AS SoBaiViet, " 
				+ "COUNT(DISTINCT td1.NguoiTheoDoi) AS SoNguoiTheoDoi, " 
				+ "COUNT(DISTINCT td2.NguoiDuocTheoDoi) AS SoDangTheoDoi "
				+ "FROM NguoiDung nd LEFT JOIN BaiDang bd ON nd.MaND = bd.MaND "
				+ "LEFT JOIN TheoDoi td1 ON nd.MaND = td1.NguoiDuocTheoDoi "
				+ "LEFT JOIN TheoDoi td2 ON nd.MaND = td2.NguoiTheoDoi WHERE (? IS NULL OR ? = '' "
				+ "OR nd.TenDangNhap LIKE ? OR nd.HoTen LIKE ? OR nd.Email LIKE ?) "
				+ "GROUP BY nd.MaND, nd.TenDangNhap, nd.MatKhau, nd.HoTen, nd.Email, "
				+ "nd.AnhDaiDien, nd.NgayTao, nd.BiKhoa, nd.TieuSu";

		PreparedStatement ps = kn.cn.prepareStatement(sql);

		ps.setString(1, key);
		ps.setString(2, key);
		String s = "%" + key + "%";
		ps.setString(3, s);
		ps.setString(4, s);
		ps.setString(5, s);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			NguoiDung nd = new NguoiDung();
			nd.setMaND(rs.getLong("MaND"));
			nd.setTenDangNhap(rs.getString("TenDangNhap"));
			nd.setHoTen(rs.getString("HoTen"));
			nd.setEmail(rs.getString("Email"));
			nd.setAnhDaiDien(rs.getString("AnhDaiDien"));
			nd.setTieuSu(rs.getString("TieuSu"));

			NguoiDungAdminView view = new NguoiDungAdminView();
			view.setNguoiDung(nd);
			view.setbiKhoa(rs.getBoolean("BiKhoa"));
			view.setSoBaiViet(rs.getInt("SoBaiViet"));
			view.setSoNguoiTheoDoi(rs.getInt("SoNguoiTheoDoi"));
			view.setSoDangTheoDoi(rs.getInt("SoDangTheoDoi"));

			ds.add(view);
		}

		rs.close();
		ps.close();
		kn.cn.close();

		return ds;
	}

	public ArrayList<BaiDang> getDanhSachBaiDang(String key) throws Exception {
		ArrayList<BaiDang> ds = new ArrayList<>();
		kn.ketnoi();

		String sql = "SELECT b.MaBaiDang, b.MaND, b.NoiDung, b.HinhAnh, b.NgayDang, " + "u.TenDangNhap, u.AnhDaiDien "
				+ "FROM BaiDang b " + "JOIN NguoiDung u ON b.MaND = u.MaND "
				+ "WHERE (? IS NULL OR ? = '' OR b.NoiDung LIKE ? OR u.TenDangNhap LIKE ?) "
				+ "ORDER BY b.NgayDang DESC";

		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, key);
		ps.setString(2, key);
		ps.setString(3, "%" + key + "%");
		ps.setString(4, "%" + key + "%");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			BaiDang bd = new BaiDang();
			bd.setMaBaiDang(rs.getLong("MaBaiDang"));
			bd.setMaND(rs.getLong("MaND"));
			bd.setNoiDung(rs.getString("NoiDung"));
			bd.setHinhAnh(rs.getString("HinhAnh"));
			bd.setNgayDang(rs.getString("NgayDang"));
			bd.setTenDangNhap(rs.getString("TenDangNhap"));
			bd.setAnhDaiDien(rs.getString("AnhDaiDien"));
			ds.add(bd);
		}

		rs.close();
		ps.close();
		kn.cn.close();

		return ds;
	}

	public void xoaNguoiDung(long maND) throws Exception {
		kn.ketnoi();
		String sql = "DELETE FROM NguoiDung WHERE MaND = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setLong(1, maND);
		ps.executeUpdate();
		ps.close();
		kn.cn.close();
	}
	
	public void khoaNguoiDung(long maND) throws Exception {
		kn.ketnoi();
		String sql = "UPDATE NguoiDung SET BiKhoa = 1 WHERE MaND = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setLong(1, maND);
		ps.executeUpdate();
		ps.close();
		kn.cn.close();
	}

	public void moKhoaNguoiDung(long maND) throws Exception {
		kn.ketnoi();
		String sql = "UPDATE NguoiDung SET BiKhoa = 0 WHERE MaND = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setLong(1, maND);
		ps.executeUpdate();
		ps.close();
		kn.cn.close();
	}

}