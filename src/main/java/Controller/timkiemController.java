package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.NguoiDung;
import Modal.NguoiDungBO;
import Modal.ThongBao;
import Modal.ThongBaoBO;

/**
 * Servlet implementation class timkiemController
 */
@WebServlet("/timkiemController")
public class timkiemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public timkiemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("nd") == null) {
	        response.sendRedirect("dangnhapController");
	        return;
	    }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        NguoiDung ndSession = (NguoiDung) session.getAttribute("nd");
        ArrayList<ThongBao> dsThongBao = (ArrayList<ThongBao>) session.getAttribute("dsThongBao");
    	Integer soThongBaoChuaDoc = (Integer) session.getAttribute("soThongBaoChuaDoc");
        String keyword = request.getParameter("search");
        ArrayList<NguoiDung> dsKetQua = new ArrayList<>();

        try {	
            if(keyword != null && !keyword.trim().isEmpty()) {
                NguoiDungBO bo = new NguoiDungBO();
                dsKetQua = bo.searchNguoiDung(keyword);
            }
            request.setAttribute("dsThongBao", dsThongBao);
            request.setAttribute("soThongBaoChuaDoc", soThongBaoChuaDoc);
            request.setAttribute("keyword", keyword);
            request.setAttribute("dsKetQua", dsKetQua);
            request.setAttribute("ndSession", ndSession);
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("tb", "Lỗi khi tìm kiếm: " + e.getMessage());
        }

        request.getRequestDispatcher("timkiem.jsp").forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
