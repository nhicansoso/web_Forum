package Controller.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.BinhLuan;
import Modal.BinhLuanBO;
import Modal.Admin.Admin;

/**
 * Servlet implementation class binhluanAdminController
 */
@WebServlet("/binhluanAdminController")
public class binhluanAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public binhluanAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("dangnhapAdmin.jsp");
            return;
        }

        String action = request.getParameter("action");
        String key = request.getParameter("key");

        BinhLuanBO blBO = new BinhLuanBO();

        try {
            if ("delete".equals(action)) {
                String idStr = request.getParameter("id");
                if (idStr != null) {
                    long maBL = Long.parseLong(idStr);
                    blBO.xoaBinhLuan(maBL);
                }
            }
            ArrayList<BinhLuan> dsBinhLuan = blBO.getComments(key); 

            request.setAttribute("dsBinhLuan", dsBinhLuan);
            request.setAttribute("key", key);
            request.setAttribute("activeMenu", "comments");
            RequestDispatcher rd = request.getRequestDispatcher("binhluanAdmin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
