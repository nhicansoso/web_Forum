package Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import HoTro.KetNoi;

public class LuotLikeDAO {

    public boolean daLike(long maND, long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT 1 FROM LuotLike WHERE MaND = ? AND MaBaiDang = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maND);
        ps.setLong(2, maBaiDang);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public void themLike(long maND, long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "INSERT INTO LuotLike(MaND, MaBaiDang) VALUES (?, ?)";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maND);
        ps.setLong(2, maBaiDang);
        ps.executeUpdate();
    }

    public void xoaLike(long maND, long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "DELETE FROM LuotLike WHERE MaND = ? AND MaBaiDang = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maND);
        ps.setLong(2, maBaiDang);
        ps.executeUpdate();
    }

    public int demLike(long maBaiDang) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM LuotLike WHERE MaBaiDang = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maBaiDang);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) return rs.getInt(1);
        return 0;
    }
}
