package Modal;

import java.util.ArrayList;

public class TheoDoiBO {

    TheoDoiDAO dao = new TheoDoiDAO();

    public String getTrangThaiTheoDoi(long nguoiTheoDoi, long nguoiDuocTheoDoi) throws Exception {
        return dao.getTrangThaiTheoDoi(nguoiTheoDoi, nguoiDuocTheoDoi);
    }

    public void themTheoDoi(long nguoiTheoDoi, long nguoiDuocTheoDoi) throws Exception {
        dao.themTheoDoi(nguoiTheoDoi, nguoiDuocTheoDoi);
    }
    
    public void xoaTheoDoi(long nguoiTheoDoi, long nguoiDuocTheoDoi) throws Exception {
        dao.xoaTheoDoi(nguoiTheoDoi, nguoiDuocTheoDoi);
    }
    
    public ArrayList<NguoiDung> getDanhSachDangTheoDoi(long maND) throws Exception {
        return dao.getDanhSachDangTheoDoi(maND);
    }
    
    public int demNguoiTheoDoi(long maND) throws Exception {
        return dao.demNguoiTheoDoi(maND);
    }

    public int demDangTheoDoi(long maND) throws Exception {
        return dao.demDangTheoDoi(maND);
    }


}
