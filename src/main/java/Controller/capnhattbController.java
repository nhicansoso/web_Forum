package Controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Modal.NguoiDung;

import Modal.ThongBaoBO;

@WebServlet("/capnhattbController")
public class capnhattbController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public capnhattbController() {
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
	        String action = request.getParameter("action");
	        String maTBStr = request.getParameter("maTB");

	        if (action == null || maTBStr == null) {
	            response.sendRedirect("tcController");
	            return;
	        }

	        long maTB = Long.parseLong(maTBStr);
	        ThongBaoBO tbBO = new ThongBaoBO();
	        NguoiDung ndSession = (NguoiDung) session.getAttribute("nd");
	        NguoiDung nguoiGui = tbBO.getNguoiGuiByMaTB(maTB);
	        if ("read".equals(action)) {
	            tbBO.danhDauDaDoc(maTB);

	            session.setAttribute("dsThongBao", tbBO.getThongBao(ndSession.getMaND()));
	            session.setAttribute("soThongBaoChuaDoc", tbBO.demThongBaoChuaDoc(ndSession.getMaND()));
	            
	            if (nguoiGui != null) {
	                response.sendRedirect("tcnController?username=" + nguoiGui.getTenDangNhap());
	            } else {
	                response.sendRedirect("tcController");
	            }
	            return;
	        }

	        if ("delete".equals(action)) {
	            tbBO.xoaThongBao(maTB);

	            session.setAttribute("dsThongBao", tbBO.getThongBao(ndSession.getMaND()));
	            session.setAttribute("soThongBaoChuaDoc", tbBO.demThongBaoChuaDoc(ndSession.getMaND()));

	                response.sendRedirect("tcController");
	                return;
	        }

	        response.sendRedirect("tcController");

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("tcController");
	    }
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
