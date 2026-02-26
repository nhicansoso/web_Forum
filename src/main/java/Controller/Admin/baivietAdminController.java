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

import Modal.BaiDang;
import Modal.BaiDangBO;
import Modal.BinhLuanBO;
import Modal.LuotLikeBO;
import Modal.Admin.Admin;

@WebServlet("/baivietAdminController")
public class baivietAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        LuotLikeBO likeBO = new LuotLikeBO();
        BinhLuanBO blBO = new BinhLuanBO();
        if (admin == null) {
            response.sendRedirect("dangnhapAdmin.jsp");
            return;
        }

        BaiDangBO baiDangBO = new BaiDangBO();
        String action = request.getParameter("action");
        String key = request.getParameter("key");

        try {
        	if ("delete".equals(action)) {
        	    String idStr = request.getParameter("id");
        	    if (idStr != null) {
        	        long maBaiDang = Long.parseLong(idStr);
        	        baiDangBO.xoaBaiDang(maBaiDang);
        	    }
        	    response.sendRedirect("baivietAdminController");
        	    return;
        	}

            ArrayList<BaiDang> dsBaiDang = baiDangBO.layBaiDangTheoTenDangNhap(key);
            if (dsBaiDang != null) {
                for (BaiDang p : dsBaiDang) {
                    p.setSoLike(likeBO.demLike(p.getMaBaiDang()));
                    if (p.getDanhSachCmt() == null) {
                        p.setDanhSachCmt(new ArrayList<>());
                    }else {
                    	p.setDanhSachCmt(blBO.getBinhLuanTheoBaiDang(p.getMaBaiDang()));
                    }
                }
            }
            request.setAttribute("dsBaiDang", dsBaiDang);
            request.setAttribute("key", key);
            request.setAttribute("activeMenu", "posts");
            RequestDispatcher rd = request.getRequestDispatcher("baivietAdmin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dangnhapAdminControlelr");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}