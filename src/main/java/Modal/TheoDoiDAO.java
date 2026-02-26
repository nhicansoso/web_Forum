package Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import HoTro.KetNoi;

public class TheoDoiDAO {
	public String getTrangThaiTheoDoi(long nguoiTheoDoi, long nguoiDuocTheoDoi) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = """
            SELECT TrangThai
            FROM TheoDoi
            WHERE NguoiTheoDoi = ? AND NguoiDuocTheoDoi = ?
        """;

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, nguoiTheoDoi);
        cmd.setLong(2, nguoiDuocTheoDoi);

        ResultSet rs = cmd.executeQuery();
        String trangThai = null;

        if (rs.next()) {
            trangThai = rs.getString("TrangThai");
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return trangThai;
    }

    // Thêm theo dõi (mặc định pending)
    public void themTheoDoi(long nguoiTheoDoi, long nguoiDuocTheoDoi) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = """
            INSERT INTO TheoDoi (NguoiTheoDoi, NguoiDuocTheoDoi, TrangThai, NgayTao)
            VALUES (?, ?, 1, GETDATE())
        """;

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, nguoiTheoDoi);
        cmd.setLong(2, nguoiDuocTheoDoi);

        cmd.executeUpdate();

        cmd.close();
        kn.cn.close();
    }
    
    public void xoaTheoDoi(long nguoiTheoDoi, long nguoiDuocTheoDoi) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = """
            DELETE FROM TheoDoi
            WHERE NguoiTheoDoi = ? AND NguoiDuocTheoDoi = ?
        """;

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, nguoiTheoDoi);
        cmd.setLong(2, nguoiDuocTheoDoi);

        cmd.executeUpdate();

        cmd.close();
        kn.cn.close();
    }
    
    public ArrayList<NguoiDung> getDanhSachDangTheoDoi(long maND) throws Exception {
        ArrayList<NguoiDung> ds = new ArrayList<>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = """
            SELECT nd.MaND, nd.TenDangNhap, nd.AnhDaiDien
            FROM TheoDoi td
            JOIN NguoiDung nd ON td.NguoiDuocTheoDoi = nd.MaND
            WHERE td.NguoiTheoDoi = ? AND td.TrangThai = 1
        """;

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maND);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            NguoiDung nd = new NguoiDung();
            nd.setMaND(rs.getLong("MaND"));
            nd.setTenDangNhap(rs.getString("TenDangNhap"));
            nd.setAnhDaiDien(rs.getString("AnhDaiDien"));
            ds.add(nd);
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return ds;
    }
    
    public int demNguoiTheoDoi(long maND) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM TheoDoi WHERE NguoiDuocTheoDoi = ?";
        int count = 0;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maND);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }

        // 🔴 ĐÓNG TÀI NGUYÊN
        rs.close();
        ps.close();
        kn.cn.close();

        return count;
    }


    public int demDangTheoDoi(long maND) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM TheoDoi WHERE NguoiTheoDoi = ?";
        int count = 0;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maND);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }

        // 🔴 ĐÓNG TÀI NGUYÊN
        rs.close();
        ps.close();
        kn.cn.close();

        return count;
    }


}
