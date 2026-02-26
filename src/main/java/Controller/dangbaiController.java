package Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import Modal.BaiDangDAO;
import Modal.NguoiDung;

@WebServlet("/dangbaiController")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  
	    maxFileSize = 5 * 1024 * 1024,    
	    maxRequestSize = 10 * 1024 * 1024  
)
public class dangbaiController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("nd") == null) {
            response.sendRedirect("dangnhapController");
            return;
        }
    	
        NguoiDung ndSession = (NguoiDung) session.getAttribute("nd");

        try {
            String noiDung = request.getParameter("noiDung");

            Part filePart = request.getPart("hinhAnh");
            String fileName = null;
            String file = null;
            if ((noiDung == null || noiDung.isEmpty()) &&
                    (filePart == null || filePart.getSize() == 0)) {

                    response.sendRedirect("tcController");
                    return;
            }
            
            if (filePart != null && filePart.getSize() > 0) {

                fileName = System.currentTimeMillis() + "_" 
                        + filePart.getSubmittedFileName();

                String uploadPath = request.getServletContext()
                        .getRealPath("assets/img");
                
                File folder = new File(uploadPath);
                if (!folder.exists()) folder.mkdirs();
                file = "assets/img/" + fileName;
                filePart.write(uploadPath + File.separator + fileName);
            }
            
            BaiDangDAO dao = new BaiDangDAO();
            dao.dangBai(ndSession.getMaND(), noiDung, file);

            response.sendRedirect("tcController");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi đăng bài!");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
}
