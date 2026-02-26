package Controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Modal.NguoiDung;
import Modal.NguoiDungBO;

@WebServlet("/capnhattcnController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,   // 1MB
    maxFileSize = 5 * 1024 * 1024,     // 5MB
    maxRequestSize = 10 * 1024 * 1024  // 10MB
)
public class capnhattcnController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("nd") == null) {
            response.sendRedirect("dangnhapController");
            return;
        }

        try {
    		request.setCharacterEncoding("UTF-8");
    		response.setCharacterEncoding("UTF-8");

            NguoiDung ndSession = (NguoiDung) session.getAttribute("nd");
            NguoiDungBO ndBO = new NguoiDungBO();

            String hoTen = request.getParameter("hoTen");
            String email = request.getParameter("email");
            String tieuSu = request.getParameter("tieuSu");
            String tenDangNhap = request.getParameter("tenDangNhap");
            if (tieuSu == null) tieuSu = "";

            if (hoTen == null || hoTen.trim().isEmpty())
                hoTen = ndSession.getHoTen();

            if (email == null || email.trim().isEmpty())
                email = ndSession.getEmail();
            
            if (!tenDangNhap.equals(ndSession.getTenDangNhap()) && ndBO.kiemTraTonTai(tenDangNhap)) {
                session.setAttribute("tbLoi", "Tên đăng nhập đã tồn tại");
                response.sendRedirect("tcnController");
                return;
            }
            
            if (!email.equals(ndSession.getEmail()) && ndBO.emailTonTai(email)) {
                session.setAttribute("tbLoi", "Email đã tồn tại");
                response.sendRedirect("tcnController");
                return;
            }

            Part filePart = request.getPart("anhDaiDien");
            String anhDaiDien = ndSession.getAnhDaiDien();

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = request.getServletContext().getRealPath("assets/avatar");
                File dir = new File(uploadPath);
                if (!dir.exists()) dir.mkdirs();

                filePart.write(uploadPath + File.separator + fileName);
                anhDaiDien = "assets/avatar/" + fileName;
            }

            ndSession.setHoTen(hoTen);
            ndSession.setEmail(email);
            ndSession.setTieuSu(tieuSu);
            ndSession.setTenDangNhap(tenDangNhap);
            ndSession.setAnhDaiDien(anhDaiDien);

            boolean kq = ndBO.capNhatThongTin(ndSession);

            if (kq) {
                session.setAttribute("nd", ndSession);
                session.setAttribute("tbThanhCong", "Cập nhật thông tin thành công");
            } else {
                session.setAttribute("tbLoi", "Cập nhật thất bại");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("tbLoi", e.getMessage());
        }

        response.sendRedirect("capnhattcnController");
    }
}
