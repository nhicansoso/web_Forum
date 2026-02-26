package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import HoTro.HoTro;
import Modal.NguoiDung;
import Modal.NguoiDungBO;

@WebServlet("/dangnhapController")
public class dangnhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public dangnhapController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		String un = request.getParameter("tenDangNhap");
		String pass = request.getParameter("matKhau");

		if (un == null || pass == null) {
			request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
			return;
		}

		try {
			NguoiDungBO bo = new NguoiDungBO();
			HoTro md = new HoTro();
            pass = md.ecrypt(pass);
			NguoiDung nd = bo.dangNhap(un, pass);

			if (nd != null) {

				HttpSession session = request.getSession();

				session.setAttribute("nd", nd);              
//				session.setAttribute("maND", nd.getMaND()); 
//				session.setAttribute("un", nd.getTenDangNhap());

				response.sendRedirect("tcController");

			} else {
				request.setAttribute("tb", "Sai tên đăng nhập hoặc mật khẩu!");
				request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tb", "Lỗi hệ thống!");
			request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
