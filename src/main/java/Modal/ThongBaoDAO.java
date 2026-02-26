package Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import HoTro.KetNoi;

public class ThongBaoDAO {

    KetNoi kn = new KetNoi();

    // thêm thông báo khi có người theo dõi
    public void themThongBaoFollow(long maNDGui, long maNDNhan) throws Exception {
        kn.ketnoi();

        String sql = """
            INSERT INTO ThongBao (MaNDNhan, MaNDGui, LoaiThongBao, DaDoc)
            VALUES (?, ?, 'FOLLOW', 0)
        """;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maNDNhan);
        ps.setLong(2, maNDGui);
        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }
    public NguoiDung getNguoiGuiByMaTB(long maTB) throws Exception {
        kn.ketnoi();

        String sql = """
            SELECT nd.MaND, nd.TenDangNhap, nd.HoTen, nd.AnhDaiDien
            FROM ThongBao tb
            JOIN NguoiDung nd ON tb.MaNDGui = nd.MaND
            WHERE tb.MaTB = ?
        """;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maTB);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            NguoiDung nd = new NguoiDung();
            nd.setMaND(rs.getLong("MaND"));
            nd.setTenDangNhap(rs.getString("TenDangNhap"));
            nd.setHoTen(rs.getString("HoTen"));
            nd.setAnhDaiDien(rs.getString("AnhDaiDien"));

            rs.close();
            ps.close();
            kn.cn.close();
            return nd;
        }

        rs.close();
        ps.close();
        kn.cn.close();
        return null;
    }

    // lấy danh sách thông báo của 1 người
    public ArrayList<ThongBao> getThongBao(long maNDNhan) throws Exception {
        kn.ketnoi();
        ArrayList<ThongBao> ds = new ArrayList<>();

        String sql = """
            SELECT tb.MaTB, tb.MaNDGui, tb.DaDoc,
                   nd.TenDangNhap, nd.AnhDaiDien
            FROM ThongBao tb
            JOIN NguoiDung nd ON tb.MaNDGui = nd.MaND
            WHERE tb.MaNDNhan = ?
            ORDER BY tb.MaTB DESC
        """;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maNDNhan);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ThongBao tb = new ThongBao();
            tb.setMaTB(rs.getLong("MaTB"));
            tb.setMaNDGui(rs.getLong("MaNDGui"));
            tb.setLoaiThongBao("FOLLOW");
            tb.setDaDoc(rs.getBoolean("DaDoc"));
            tb.setTenNguoiGui(rs.getString("TenDangNhap"));
            tb.setAnhNguoiGui(rs.getString("AnhDaiDien"));
            ds.add(tb);
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return ds;
    }
    
    public ThongBao getThongBaoByMaTB(long maTB) throws Exception {
        kn.ketnoi();

        String sql = "SELECT MaTB, MaNDGui, MaNDNhan, LoaiThongBao, DaDoc FROM ThongBao WHERE MaTB = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maTB);
        ResultSet rs = ps.executeQuery();

        ThongBao tb = null;
        if (rs.next()) {
            tb = new ThongBao();
            tb.setMaTB(rs.getLong("MaTB"));
            tb.setMaNDGui(rs.getLong("MaNDGui"));
            tb.setMaNDNhan(rs.getLong("MaNDNhan"));
            tb.setLoaiThongBao(rs.getString("LoaiThongBao"));
            tb.setDaDoc(rs.getBoolean("DaDoc"));
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return tb;
    }


    // đánh dấu thông báo đã đọc
    public void danhDauDaDoc(long maTB) throws Exception {
        kn.ketnoi();

        String sql = "UPDATE ThongBao SET DaDoc = 1 WHERE MaTB = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maTB);
        ps.executeUpdate();

        ps.close();
        kn.cn.close();
    }

    // đếm số thông báo chưa đọc (để hiện badge)
    public int demThongBaoChuaDoc(long maNDNhan) throws Exception {
        kn.ketnoi();

        String sql = """
            SELECT COUNT(*) 
            FROM ThongBao 
            WHERE MaNDNhan = ? AND DaDoc = 0
        """;

        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maNDNhan);
        ResultSet rs = ps.executeQuery();

        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        ps.close();
        kn.cn.close();

        return count;
    }
    
    public void xoaThongBao(long maTB) throws Exception {
    	kn.ketnoi();
        String sql = "DELETE FROM ThongBao WHERE maTB = ?";
        PreparedStatement ps = kn.cn.prepareStatement(sql);
        ps.setLong(1, maTB);
        ps.executeUpdate();
        
        ps.close();
        kn.cn.close();
    }

}
