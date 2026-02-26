package Modal;

import java.util.ArrayList;

public class ThongBaoBO {

    ThongBaoDAO dao = new ThongBaoDAO();

    public void themThongBaoFollow(long maNDGui, long maNDNhan) throws Exception {
        dao.themThongBaoFollow(maNDGui, maNDNhan);
    }
    
    public ArrayList<ThongBao> getThongBao(long maNDNhan) throws Exception {
        return dao.getThongBao(maNDNhan);
    }
    public NguoiDung getNguoiGuiByMaTB(long maTB) throws Exception {
        return dao.getNguoiGuiByMaTB(maTB);
    }

    public void danhDauDaDoc(long maTB) throws Exception {
        dao.danhDauDaDoc(maTB);
    }

    public int demThongBaoChuaDoc(long maNDNhan) throws Exception {
        return dao.demThongBaoChuaDoc(maNDNhan);
    }
    
    public void xoaThongBao(long maTB) throws Exception {
        dao.xoaThongBao(maTB);
    }
    
    public ThongBao getThongBaoByMaTB(long maTB) throws Exception {
        return dao.getThongBaoByMaTB(maTB);
    }


}
