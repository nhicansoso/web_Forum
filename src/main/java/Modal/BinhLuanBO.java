package Modal;

import java.util.ArrayList;

public class BinhLuanBO {
    BinhLuanDAO dao = new BinhLuanDAO();

    public void themBinhLuan(long maND, long maBaiDang, String noiDung) throws Exception {
        dao.themBinhLuan(maND, maBaiDang, noiDung);
    }
    public ArrayList<BinhLuan> getComments(String key) throws Exception {
        return dao.getAllComments(key);
    }

    public void xoaBinhLuan(long maBL) throws Exception {
        dao.xoaBinhLuan(maBL);
    }
    
    public ArrayList<BinhLuan> getBinhLuanTheoBaiDang(long maBaiDang) throws Exception {
        return dao.getBinhLuanTheoBaiDang(maBaiDang);
    }
}
