package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.BaiDangBO;

@WebServlet("/xoabaivietController")
public class xoabaivietController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BaiDangBO bo = new BaiDangBO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public xoabaivietController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("nd") == null) {
            response.sendRedirect("dangnhapController");
            return;
        }
        
        try {

            String maBaiDangStr = request.getParameter("maBaiDang");
            if (maBaiDangStr == null) {
                response.getWriter().println("Bài viết không tồn tại!");
                return;
            }

            long maBaiDang = Long.parseLong(maBaiDangStr);
            boolean kq = bo.xoaBaiDang(maBaiDang);

            if (kq) {
                String from = request.getParameter("from");
                if ("tcn".equals(from)) {
                    response.sendRedirect("tcnController");
                } else {
                    response.sendRedirect("tcController");
                }
            } else {
                response.getWriter().println("Không thể xóa bài viết này!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
