package Modal.Admin;

import java.util.ArrayList;

import Modal.BaiDang; 

public class AdminBO {
    AdminDAO dao = new AdminDAO();
    //kiểm tra đăng nhập
    public Admin login(String taiKhoan, String matKhau) throws Exception {
        return dao.login(taiKhoan, matKhau);
    }

    //số người dùng trong tháng
    public int tongNDTheoThang(int thang, int nam) throws Exception {
        return dao.tongNDTheoThang(thang, nam);
    }

    //số bài viết trong tháng
    public int tongBVTheoThang(int thang, int nam) throws Exception {
        return dao.tongBVTheoThang(thang, nam);
    }

    //lấy dữ liệu những người muốn tìm theo key
    public ArrayList<NguoiDungAdminView> getDanhSachNguoiDungAdmin(String key) throws Exception {
        return dao.getDanhSachNguoiDungAdmin(key);
    }
  //lấy dữ liệu những bài viết muốn tìm theo key
    public ArrayList<BaiDang> getDanhSachBaiDang(String key) throws Exception {
        return dao.getDanhSachBaiDang(key);
    }
    //tổng người dùng
    public int tongNguoiDung() throws Exception {
        return dao.tongNguoiDung();
    }
    //tổng bài đăng
    public int tongBaiDang() throws Exception {
        return dao.tongBaiDang();
    }
    //xoá người dùng
    public void xoaNguoiDung(long maND) throws Exception {
        dao.xoaNguoiDung(maND);
    }
    
    public void khoaNguoiDung(long maND) throws Exception {
    	dao.khoaNguoiDung(maND);
    }

    public void moKhoaNguoiDung(long maND) throws Exception {
    	dao.moKhoaNguoiDung(maND);
    }

}