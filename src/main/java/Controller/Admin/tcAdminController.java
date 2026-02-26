package Controller.Admin;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.Admin.Admin;
import Modal.Admin.AdminBO;

@WebServlet("/tcAdminController")
public class tcAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public tcAdminController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("dangnhapAdmin.jsp");
            return;
        }

        try {
            AdminBO adminBO = new AdminBO();

            LocalDate now = LocalDate.now();
            int selectedThang = now.getMonthValue();
            int selectedNam = now.getYear();

            String pThang = request.getParameter("thang");
            String pNam = request.getParameter("nam");

            if (pThang != null && pNam != null) {
                selectedThang = Integer.parseInt(pThang);
                selectedNam = Integer.parseInt(pNam);
            }

            int userInMonth = adminBO.tongNDTheoThang(selectedThang, selectedNam);
            int postInMonth = adminBO.tongBVTheoThang(selectedThang, selectedNam);

            int totalUsers = adminBO.tongNguoiDung();
            int totalPosts = adminBO.tongBaiDang();

            request.setAttribute("selectedThang", selectedThang);
            request.setAttribute("selectedNam", selectedNam);
            request.setAttribute("nowYear", now.getYear());

            request.setAttribute("userInMonth", userInMonth);
            request.setAttribute("postInMonth", postInMonth);
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalPosts", totalPosts);

            request.setAttribute("activeMenu", "dashboard");

            RequestDispatcher rd = request.getRequestDispatcher("tcAdmin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dangnhapAdminController");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}