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

import Modal.Admin.Admin;
import Modal.Admin.AdminBO;
import Modal.Admin.NguoiDungAdminView;

@WebServlet("/nguoidungAdminController")
public class nguoidungAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin"); 
        if (admin == null) {
            response.sendRedirect("dangnhapAdmin.jsp"); 
            return;
        }

        AdminBO adminBO = new AdminBO();
        String action = request.getParameter("action");
        String key = request.getParameter("key"); 

        try {
        	if ("delete".equals(action)) {
        		long maND = Long.parseLong(request.getParameter("id"));
        		adminBO.xoaNguoiDung(maND);
        	}
        	else if ("lock".equals(action)) {
        		long maND = Long.parseLong(request.getParameter("id"));
        		adminBO.khoaNguoiDung(maND);
        	}
        	else if ("unlock".equals(action)) {
        		long maND = Long.parseLong(request.getParameter("id"));
        		adminBO.moKhoaNguoiDung(maND);
        	}


            ArrayList<NguoiDungAdminView> dsNguoiDung = adminBO.getDanhSachNguoiDungAdmin(key);

            request.setAttribute("dsNguoiDung", dsNguoiDung);
            request.setAttribute("key", key); 
            request.setAttribute("activeMenu", "users");
            RequestDispatcher rd = request.getRequestDispatcher("nguoidungAdmin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
    }

