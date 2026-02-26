package TEMP;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Modal.BaiDangDAO;

/**
 * Servlet implementation class dangbaiController_part2
 */
@WebServlet("/dangbaiController_part2")
public class dangbaiController_part2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangbaiController_part2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        Long maND = (Long) session.getAttribute("maND");
        if (maND == null) {
            response.sendRedirect("dangnhapController");
            return;
        }

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("Vui lòng chọn file và thử lại.");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        String noiDung = "";
        String fileName = "";

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {

                if (item.isFormField()) {
                    if (item.getFieldName().equals("noiDung")) {
                        noiDung = item.getString("UTF-8");
                    }
                }
                else {
                    if (item.getName() != null && !item.getName().trim().equals("")) {

                        fileName = System.currentTimeMillis() + "_" + item.getName();

                        String uploadPath = request.getServletContext()
                                .getRealPath("/assets/img");

                        File folder = new File(uploadPath);
                        if (!folder.exists()) folder.mkdirs();

                        File file = new File(uploadPath + File.separator + fileName);
                        item.write(file);
                    }
                }
            }

            BaiDangDAO dao = new BaiDangDAO();
            dao.dangBai(maND, noiDung, fileName);

            response.sendRedirect("tcController");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi đăng bài!");
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
