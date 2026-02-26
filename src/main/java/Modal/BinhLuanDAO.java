package Modal;

import java.sql.*;
import java.util.ArrayList;

import HoTro.KetNoi;

public class BinhLuanDAO {
	
    public void themBinhLuan(long maND, long maBaiDang, String noiDung) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "INSERT INTO BinhLuan (MaND, MaBaiDang, NoiDung) VALUES (?, ?, ?)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maND);
        cmd.setLong(2, maBaiDang);
        cmd.setString(3, noiDung);

        cmd.executeUpdate();
        kn.cn.close();
    }
	
	public ArrayList<BinhLuan> getCommentsByPost(long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT bl.*, n.TenDangNhap " +
                     "FROM BinhLuan bl INNER JOIN NguoiDung n ON bl.MaND = n.MaND " +
                     "WHERE bl.MaBaiDang = ? " +
                     "ORDER BY bl.NgayBL ASC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maBaiDang);
        ResultSet rs = cmd.executeQuery();

        ArrayList<BinhLuan> ds = new ArrayList<>();
        while (rs.next()) {
            ds.add(new BinhLuan(
                rs.getLong("MaBL"),
                rs.getLong("MaND"),
                rs.getLong("MaBaiDang"),
                rs.getString("NoiDung"),
                rs.getString("NgayBL"),
                rs.getString("TenDangNhap")
            ));
        }

        rs.close();
        kn.cn.close();
        return ds;
    }
    
    public ArrayList<BinhLuan> getAllComments(String key) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT bl.*, n.TenDangNhap " +
                     "FROM BinhLuan bl " +
                     "INNER JOIN NguoiDung n ON bl.MaND = n.MaND " +
                     "WHERE (? IS NULL OR ? = '' OR bl.NoiDung LIKE ? OR n.TenDangNhap LIKE ?) " +
                     "ORDER BY bl.NgayBL DESC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setString(1, key);
        cmd.setString(2, key);
        cmd.setString(3, key != null ? "%" + key + "%" : null);
        cmd.setString(4, key != null ? "%" + key + "%" : null);

        ResultSet rs = cmd.executeQuery();
        ArrayList<BinhLuan> list = new ArrayList<>();
        while(rs.next()) {
            list.add(new BinhLuan(
                rs.getLong("MaBL"),
                rs.getLong("MaND"),
                rs.getLong("MaBaiDang"),
                rs.getString("NoiDung"),
                rs.getString("NgayBL"),
                rs.getString("TenDangNhap")
            ));
        }

        rs.close();
        kn.cn.close();
        return list;
    }


    //xóa bình luận
    public void xoaBinhLuan(long maBL) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "DELETE FROM BinhLuan WHERE MaBL=?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maBL);
        cmd.executeUpdate();

        kn.cn.close();
    }
    
	public ArrayList<BinhLuan> getBinhLuanTheoBaiDang(long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT bl.*, n.TenDangNhap " +
                     "FROM BinhLuan bl INNER JOIN NguoiDung n ON bl.MaND = n.MaND " +
                     "WHERE bl.MaBaiDang = ? " +
                     "ORDER BY bl.NgayBL ASC";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, maBaiDang);
        ResultSet rs = cmd.executeQuery();

        ArrayList<BinhLuan> ds = new ArrayList<>();
        while (rs.next()) {
            ds.add(new BinhLuan(
                rs.getLong("MaBL"),
                rs.getLong("MaND"),
                rs.getLong("MaBaiDang"),
                rs.getString("NoiDung"),
                rs.getString("NgayBL"),
                rs.getString("TenDangNhap")
            ));
        }

        rs.close();
        kn.cn.close();
        return ds;
    }
}
