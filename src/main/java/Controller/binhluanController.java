package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Modal.BinhLuanBO;
import Modal.NguoiDung;

@WebServlet("/binhluanController")
public class binhluanController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public binhluanController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("nd") == null) {
			response.sendRedirect("dangnhapController");
            return;
		}

		NguoiDung nd = (NguoiDung) session.getAttribute("nd");
		String strMaBaiDang = request.getParameter("maBaiDang");
		String noiDung = request.getParameter("noiDung");

		try {
			if (strMaBaiDang != null && !strMaBaiDang.isEmpty() && noiDung != null && !noiDung.trim().isEmpty()) {

				long maBaiDang = Long.parseLong(strMaBaiDang);

				BinhLuanBO blBO = new BinhLuanBO();
				blBO.themBinhLuan(nd.getMaND(), maBaiDang, noiDung.trim());

				response.getWriter().write("{\"success\":true,\"tenNguoiDung\":\"" + nd.getTenDangNhap()
						+ "\",\"noiDung\":\"" + noiDung.trim() + "\"}");
			} else {
				response.getWriter().write("{\"success\":false,\"msg\":\"Thiếu dữ liệu\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("{\"success\":false,\"msg\":\"Lỗi khi thêm bình luận\"}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
