package Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import HoTro.HoTro;
import Modal.NguoiDung;
import Modal.NguoiDungBO;

/**
 * Servlet implementation class dangkyController
 */
@WebServlet("/dangkyController")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 1024 * 1024 * 5,
	    maxRequestSize = 1024 * 1024 * 10
	)
public class dangkyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dangkyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		 String tenDangNhap = request.getParameter("tenDangNhap");
		    if (tenDangNhap == null) {
		        request.getRequestDispatcher("dangky.jsp").forward(request, response);
		        return;
		    }

		    try {
		        String matKhau = request.getParameter("matKhau");
		        String hoTen = request.getParameter("hoTen");
		        String email = request.getParameter("email");
		        String anhDaiDien = "assets/avatar/default.jpg";
		        
		        HoTro md = new HoTro();
				matKhau = md.ecrypt(matKhau);
		        NguoiDungBO bo = new NguoiDungBO();

		        if (bo.kiemTraTonTai(tenDangNhap)) {
		            request.setAttribute("tb", "Tên đăng nhập đã tồn tại");
		            request.getRequestDispatcher("dangky.jsp").forward(request, response);
		            return;
		        }

		        if (bo.emailTonTai(email)) {
		            request.setAttribute("tb", "Email đã được sử dụng");
		            request.getRequestDispatcher("dangky.jsp").forward(request, response);
		            return;
		        }
		        Part filePart = request.getPart("anhDaiDien");
		        if (filePart != null && filePart.getSize() > 0) {
		            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
		            String uploadPath = request.getServletContext().getRealPath("assets/avatar");
		            File dir = new File(uploadPath);
		            if (!dir.exists()) dir.mkdirs();
		            filePart.write(uploadPath + File.separator + fileName);
		            anhDaiDien = "assets/avatar/" + fileName;
		        }
				

		        NguoiDung nd = new NguoiDung();
		        nd.setTenDangNhap(tenDangNhap);
		        nd.setMatKhau(matKhau);
		        nd.setHoTen(hoTen);
		        nd.setEmail(email);
		        nd.setAnhDaiDien(anhDaiDien);

		        boolean kq = bo.dangKy(nd);

		        if (kq) {
		            request.setAttribute("tbThanhCong", "Đăng ký thành công");
		            request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
		        } else {
		            request.setAttribute("tb", "Đăng ký thất bại");
		            request.getRequestDispatcher("dangky.jsp").forward(request, response);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("tb",e.getMessage());
		        request.getRequestDispatcher("dangky.jsp").forward(request, response);
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
