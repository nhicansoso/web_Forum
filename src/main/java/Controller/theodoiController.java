package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.NguoiDung;
import Modal.TheoDoiBO;
import Modal.ThongBaoBO;

/**
 * Servlet implementation class theodoiController
 */
@WebServlet("/theodoiController")
public class theodoiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public theodoiController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("nd") == null) {
			response.sendRedirect("dangnhapController");
			return;
		}

		try {
			NguoiDung nd = (NguoiDung) session.getAttribute("nd");

			long nguoiTheoDoi = nd.getMaND();
			long nguoiDuocTheoDoi = Long.parseLong(request.getParameter("nguoiBiTheoDoi"));

			TheoDoiBO tdBO = new TheoDoiBO();
			ThongBaoBO tbBO = new ThongBaoBO();

			String trangThai = tdBO.getTrangThaiTheoDoi(nguoiTheoDoi, nguoiDuocTheoDoi);

			if (trangThai == null || trangThai.equals("0")) {
				tdBO.themTheoDoi(nguoiTheoDoi, nguoiDuocTheoDoi);
				tbBO.themThongBaoFollow(nguoiTheoDoi, nguoiDuocTheoDoi);
			} else {
				tdBO.xoaTheoDoi(nguoiTheoDoi, nguoiDuocTheoDoi);
			}
			response.sendRedirect("tcController");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("tcController");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
