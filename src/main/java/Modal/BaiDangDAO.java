package Modal;

import java.sql.*;
import java.util.ArrayList;

import HoTro.KetNoi;

public class BaiDangDAO {

    public ArrayList<BaiDang> getBaiDang() throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT b.*, n.TenDangNhap, n.AnhDaiDien " +
                     "FROM BaiDang b INNER JOIN NguoiDung n ON b.MaND = n.MaND " +
                     "ORDER BY b.NgayDang DESC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();

        ArrayList<BaiDang> ds = new ArrayList<>();

        while (rs.next()) {
            BaiDang bd = new BaiDang(
                rs.getLong("MaBaiDang"),
                rs.getLong("MaND"),
                rs.getString("NoiDung"),
                rs.getString("HinhAnh"),
                rs.getString("NgayDang"),
                rs.getString("TenDangNhap"),
                rs.getString("AnhDaiDien")
            );

            ds.add(bd);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    public ArrayList<BaiDang> getBaiDangTheoDoi(long maNDHienTai) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT b.*, n.TenDangNhap, n.AnhDaiDien " +
                     "FROM BaiDang b JOIN NguoiDung n ON b.MaND = n.MaND " +
                     "WHERE b.MaND IN (SELECT NguoiDuocTheoDoi FROM TheoDoi WHERE NguoiTheoDoi = ?) " +
                     "ORDER BY b.NgayDang DESC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maNDHienTai);
        ResultSet rs = cmd.executeQuery();

        ArrayList<BaiDang> ds = new ArrayList<>();

        while (rs.next()) {
            BaiDang bd = new BaiDang(
                rs.getLong("MaBaiDang"),
                rs.getLong("MaND"),
                rs.getString("NoiDung"),
                rs.getString("HinhAnh"),
                rs.getString("NgayDang"),
                rs.getString("TenDangNhap"),
                rs.getString("AnhDaiDien")
            );

            ds.add(bd);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    public void dangBai(long maND, String noiDung, String file) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO BaiDang (MaND, NoiDung, HinhAnh) VALUES (?, ?, ?)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maND);
        cmd.setString(2, noiDung);
        cmd.setString(3, file);
        cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
    }

    public ArrayList<BaiDang> getPostsByUser(long maND) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT b.*, n.TenDangNhap, n.AnhDaiDien FROM BaiDang b " +
                     "JOIN NguoiDung n ON b.MaND = n.MaND " +
                     "WHERE b.MaND = ? ORDER BY b.NgayDang DESC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maND);
        ResultSet rs = cmd.executeQuery();

        ArrayList<BaiDang> ds = new ArrayList<>();
        while (rs.next()) {
            BaiDang bd = new BaiDang(
                rs.getLong("MaBaiDang"),
                rs.getLong("MaND"),
                rs.getString("NoiDung"),
                rs.getString("HinhAnh"),
                rs.getString("NgayDang"),
                rs.getString("TenDangNhap"),
                rs.getString("AnhDaiDien")
            );

            ds.add(bd);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    public ArrayList<BaiDang> layDanhSachBaiDangTheoNguoiDung(String key) throws Exception {

        ArrayList<BaiDang> ds = new ArrayList<>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql =
            "SELECT b.MaBaiDang, b.MaND, b.NoiDung, b.HinhAnh, b.NgayDang, " +
            "       n.TenDangNhap, n.AnhDaiDien " +
            "FROM BaiDang b " +
            "JOIN NguoiDung n ON b.MaND = n.MaND " +
            "WHERE (? IS NULL OR ? = '' OR n.TenDangNhap = ?) " +
            "ORDER BY b.NgayDang DESC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setString(1, key);
        cmd.setString(2, key);
        cmd.setString(3, key != null ? "%" + key + "%" : null);

        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            BaiDang bd = new BaiDang(
                rs.getLong("MaBaiDang"),
                rs.getLong("MaND"),
                rs.getString("NoiDung"),
                rs.getString("HinhAnh"),
                rs.getString("NgayDang"),
                rs.getString("TenDangNhap"),
                rs.getString("AnhDaiDien")
            );
            ds.add(bd);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }


    public boolean xoaBaiDang(long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi(); kn.ketnoi();
        String sql = "DELETE FROM BaiDang WHERE MaBaiDang = ? ";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maBaiDang);

        int n = ps.executeUpdate();
        ps.close(); kn.cn.close();
        return n > 0;
    }
}
