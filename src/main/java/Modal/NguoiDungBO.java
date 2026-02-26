package Modal;

import java.util.ArrayList;

public class NguoiDungBO {

	NguoiDungDAO dao = new NguoiDungDAO();

	public NguoiDung dangNhap(String un, String pass) throws Exception {
		return dao.ktdangnhap(un, pass);
	}

	public NguoiDung getNguoiDungByUsername(String username) throws Exception {
		return dao.getNguoiDungByUsername(username);
	}

	public NguoiDung getNguoiDungByMaND(long maND) throws Exception {
		return dao.getNguoiDungByMaND(maND);
	}

	public ArrayList<NguoiDung> searchNguoiDung(String keyword) throws Exception {
		return dao.searchNguoiDung(keyword);
	}

    public boolean kiemTraTonTai(String tenDangNhap) throws Exception {
        return dao.kiemTraTonTai(tenDangNhap);
    }

    public boolean dangKy(NguoiDung nd) throws Exception {
    	return dao.dangKy(nd);
    }
    public boolean emailTonTai(String email) throws Exception {
        return dao.emailTonTai(email);
    }
    
    public ArrayList<NguoiDung> getNguoiDungGoiY(long maND) throws Exception {
        return dao.getNguoiDungGoiY(maND);
    }
    public boolean capNhatThongTin(NguoiDung nd) throws Exception {
        return dao.capNhatThongTin(nd);
    }
    
    public boolean capNhatMatKhau(long maND, String matKhauMoi) throws Exception {
        return dao.capNhatMatKhau(maND, matKhauMoi);
    }

}
