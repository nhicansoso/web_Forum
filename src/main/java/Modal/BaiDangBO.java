package Modal;

import java.util.ArrayList;

public class BaiDangBO {
    BaiDangDAO dao = new BaiDangDAO();

    public ArrayList<BaiDang> getBaiDang() throws Exception {
        return dao.getBaiDang();
    }

    public ArrayList<BaiDang> getBaiDangTheoDoi(long maND) throws Exception {
        return dao.getBaiDangTheoDoi(maND);
    }

    public void dangBai(long maND, String noiDung, String fileName) throws Exception {
        dao.dangBai(maND, noiDung, fileName);
    }
    
    public ArrayList<BaiDang> getPostsByUser(long maND) throws Exception {
        return dao.getPostsByUser(maND);
    }
    
    public ArrayList<BaiDang> layBaiDangTheoTenDangNhap(String key) throws Exception {
        return dao.layDanhSachBaiDangTheoNguoiDung(key);
    }
    
    public boolean xoaBaiDang(long maBaiDang) throws Exception {
        return dao.xoaBaiDang(maBaiDang);
    }
}
