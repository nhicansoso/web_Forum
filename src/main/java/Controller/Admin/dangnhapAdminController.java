package Controller.Admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.Admin.Admin;
import Modal.Admin.AdminBO;

/**
 * Servlet implementation class dangnhapAdminController
 */
@WebServlet("/dangnhapAdminController")
public class dangnhapAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangnhapAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("admin") != null) {
            response.sendRedirect("tcAdminController");
            return;
        }

        RequestDispatcher rd = request.getRequestDispatcher("dangnhapAdmin.jsp");
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taiKhoan = request.getParameter("taiKhoan");
        String matKhau = request.getParameter("matKhau");

        AdminBO adminBO = new AdminBO();
        try {
            Admin admin = adminBO.login(taiKhoan, matKhau);
            if(admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);

                response.sendRedirect("tcAdminController");
            } else {
                request.setAttribute("error", "Tài khoản hoặc mật khẩu sai!");
                RequestDispatcher rd = request.getRequestDispatcher("dangnhapAdmin.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại!");
            RequestDispatcher rd = request.getRequestDispatcher("dangnhapAdmin.jsp");
            rd.forward(request, response);
        }
    }

}
