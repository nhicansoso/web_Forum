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

/**
 * Servlet implementation class doimatkhauController
 */
@WebServlet("/doimatkhauController")
public class doimatkhauController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doimatkhauController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {

            String tb = (String) session.getAttribute("tbLoi");
            String tbOK = (String) session.getAttribute("tbThanhCong");

            if (tb != null) {
                request.setAttribute("tbLoi", tb);
                session.removeAttribute("tbLoi"); 
            }

            if (tbOK != null) {
                request.setAttribute("tbThanhCong", tbOK);
                session.removeAttribute("tbThanhCong"); 
            }
        }


	        request.getRequestDispatcher("tcnController").forward(request, response);
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("nd") == null) {
            response.sendRedirect("dangnhapController");
            return;
        }

        try {
            request.setCharacterEncoding("utf-8");

            NguoiDung nd = (NguoiDung) session.getAttribute("nd");

            String matKhauCu = request.getParameter("matKhauCu");
            String matKhauMoi = request.getParameter("matKhauMoi");
            String xacNhanMatKhauMoi = request.getParameter("xacNhanMatKhauMoi");

            HoTro md = new HoTro();
            NguoiDungBO ndBO = new NguoiDungBO();

            String matKhauCuMaHoa = md.ecrypt(matKhauCu);

            if (!nd.getMatKhau().equals(matKhauCuMaHoa)) {
                session.setAttribute("tbLoi", "Mật khẩu hiện tại không đúng");

            } else if (!matKhauMoi.equals(xacNhanMatKhauMoi)) {
                session.setAttribute("tbLoi", "Xác nhận mật khẩu không khớp");

            } else {
                String matKhauMoiMaHoa = md.ecrypt(matKhauMoi);
                boolean kq = ndBO.capNhatMatKhau(nd.getMaND(), matKhauMoiMaHoa);

                if (kq) {
                    nd.setMatKhau(matKhauMoiMaHoa);
                    session.setAttribute("nd", nd);
                    session.setAttribute("tbThanhCong", "Đổi mật khẩu thành công");
                } else {
                    session.setAttribute("tbLoi", "Đổi mật khẩu thất bại");
                }
            }

        } catch (Exception e) {
            session.setAttribute("tbLoi", "Lỗi hệ thống!");
        }
        response.sendRedirect("doimatkhauController");
    }


}
