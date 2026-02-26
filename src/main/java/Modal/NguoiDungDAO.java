package Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import HoTro.KetNoi;

public class NguoiDungDAO {


	public NguoiDung ktdangnhap(String un, String pass) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap=? AND MatKhau=? AND BiKhoa = 0";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, un);
		cmd.setString(2, pass);

		ResultSet rs = cmd.executeQuery();

		NguoiDung nd = null;
		if (rs.next()) {
			long mand = rs.getLong("MaND");
			String hoten = rs.getString("HoTen");
			String email = rs.getString("Email");
			String anh = rs.getString("AnhDaiDien");

			nd = new NguoiDung(mand, un, pass, hoten, email, anh, null);
		}

		rs.close();
		kn.cn.close();
		return nd;
	}

	public NguoiDung getNguoiDungByUsername(String username) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM NguoiDung WHERE TenDangNhap = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, username);

		ResultSet rs = cmd.executeQuery();

		NguoiDung nd = null;

		if (rs.next()) {
			nd = new NguoiDung(rs.getLong("MaND"), rs.getString("TenDangNhap"), rs.getString("MatKhau"),
					rs.getString("HoTen"), rs.getString("Email"), rs.getString("AnhDaiDien"), rs.getString("TieuSu"));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return nd;
	}

	public NguoiDung getNguoiDungByMaND(long maND) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM NguoiDung WHERE MaND = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, maND);

		ResultSet rs = cmd.executeQuery();

		NguoiDung nd = null;
		if (rs.next()) {
			nd = new NguoiDung(rs.getLong("MaND"), rs.getString("TenDangNhap"), rs.getString("MatKhau"),
					rs.getString("HoTen"), rs.getString("Email"), rs.getString("AnhDaiDien"), rs.getString("TieuSu"));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return nd;
	}

	public boolean kiemTraTonTai(String tenDangNhap) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT 1 FROM NguoiDung WHERE TenDangNhap = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, tenDangNhap);

		ResultSet rs = ps.executeQuery();
		boolean tonTai = rs.next();

		rs.close();
		ps.close();
		kn.cn.close();

		return tonTai;
	}

	public boolean dangKy(NguoiDung nd) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = """
				    INSERT INTO NguoiDung
				    (TenDangNhap, MatKhau, HoTen, Email, AnhDaiDien,BiKhoa)
				    VALUES (?, ?, ?, ?, ?,0)
				""";

		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, nd.getTenDangNhap());
		ps.setString(2, nd.getMatKhau());
		ps.setString(3, nd.getHoTen());
		ps.setString(4, nd.getEmail());
		ps.setString(5, nd.getAnhDaiDien());

		int kq = ps.executeUpdate();

		ps.close();
		kn.cn.close();

		return kq > 0;
	}

	public boolean emailTonTai(String email) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT 1 FROM NguoiDung WHERE Email = ?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();
		boolean tonTai = rs.next();

		rs.close();
		ps.close();
		kn.cn.close();

		return tonTai;
	}


	public ArrayList<NguoiDung> searchNguoiDung(String keyword) throws Exception {
	    ArrayList<NguoiDung> ds = new ArrayList<>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT MaND, TenDangNhap, HoTen, Email, AnhDaiDien, TieuSu "
	               + "FROM NguoiDung "
	               + "WHERE TenDangNhap LIKE ? or HoTen LIKE ?";

	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, "%" + keyword + "%"); 
	    cmd.setString(2, "%" + keyword + "%"); 

	    ResultSet rs = cmd.executeQuery();

	    while(rs.next()) {
	        NguoiDung nd = new NguoiDung(
	            rs.getLong("MaND"),
	            rs.getString("TenDangNhap"),
	            null,
	            rs.getString("HoTen"),
	            rs.getString("Email"),
	            rs.getString("AnhDaiDien"),
	            null
	        );
	        ds.add(nd);
	    }

	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    return ds;
	}


	public ArrayList<NguoiDung> getNguoiDungGoiY(long maND) throws Exception {
		ArrayList<NguoiDung> ds = new ArrayList<>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = """
				    SELECT TOP 5 MaND, TenDangNhap, AnhDaiDien
				    FROM NguoiDung
				    WHERE MaND != ?
				    AND MaND NOT IN (
				        SELECT NguoiDuocTheoDoi
				        FROM TheoDoi
				        WHERE NguoiTheoDoi = ?
				    )
				""";

		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setLong(1, maND);
		ps.setLong(2, maND);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			NguoiDung nd = new NguoiDung();
			nd.setMaND(rs.getLong("MaND"));
			nd.setTenDangNhap(rs.getString("TenDangNhap"));
			nd.setAnhDaiDien(rs.getString("AnhDaiDien"));
			ds.add(nd);
		}

		rs.close();
		ps.close();
		kn.cn.close();

		return ds;
	}

	public boolean capNhatThongTin(NguoiDung nd) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "UPDATE NguoiDung SET TenDangNhap=?, HoTen=?, Email=?, AnhDaiDien=?, TieuSu=? WHERE MaND=?";
		PreparedStatement ps = kn.cn.prepareStatement(sql);
		ps.setString(1, nd.getTenDangNhap());
		ps.setString(2, nd.getHoTen());
		ps.setString(3, nd.getEmail());
		ps.setString(4, nd.getAnhDaiDien());
		ps.setString(5, nd.getTieuSu());
		ps.setLong(6, nd.getMaND());

		int kq = ps.executeUpdate();
		ps.close();
		kn.cn.close();
		return kq > 0;
	}
	
    public boolean capNhatMatKhau(long maND, String matKhauMoi) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "UPDATE NguoiDung SET MatKhau=? WHERE MaND=?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setString(1, matKhauMoi);
        ps.setLong(2, maND);

        int row = ps.executeUpdate();

        ps.close();
        kn.cn.close();

        return row > 0;
    }
}
