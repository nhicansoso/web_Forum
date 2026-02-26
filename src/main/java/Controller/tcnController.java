package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Modal.*;

@WebServlet("/tcnController")
public class tcnController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("nd") == null) {
            response.sendRedirect("dangnhapController");
            return;
        }

        try {
            NguoiDung ndSession = (NguoiDung) session.getAttribute("nd");

            String usernameParam = request.getParameter("username");

            NguoiDungBO ndBO = new NguoiDungBO();
            BaiDangBO bdBO = new BaiDangBO();
            TheoDoiBO tdBO = new TheoDoiBO();
            LuotLikeBO likeBO = new LuotLikeBO();
            ThongBaoBO tbBO = new ThongBaoBO();

            NguoiDung profileUser;
            if (usernameParam == null || usernameParam.trim().isEmpty()
                    || usernameParam.equals(ndSession.getTenDangNhap())) {

                profileUser = ndSession; 
            } else {
                profileUser = ndBO.getNguoiDungByUsername(usernameParam);
            }

            if (profileUser == null) {
                response.sendRedirect("tcController");
                return;
            }

        	ArrayList<ThongBao> dsThongBao = (ArrayList<ThongBao>) session.getAttribute("dsThongBao");
        	Integer soThongBaoChuaDoc = (Integer) session.getAttribute("soThongBaoChuaDoc");
            request.setAttribute("dsThongBao",
            		dsThongBao);
            request.setAttribute("soThongBaoChuaDoc",
            		soThongBaoChuaDoc);

            boolean isOwner =
                    profileUser.getMaND() == ndSession.getMaND();

            String trangThaiTheoDoi = null;
            if (!isOwner) {
                trangThaiTheoDoi = tdBO.getTrangThaiTheoDoi(
                        ndSession.getMaND(),
                        profileUser.getMaND()
                );
            }

            ArrayList<BaiDang> ds =
                    bdBO.getPostsByUser(profileUser.getMaND());

            for (BaiDang p : ds) {
                p.setDaLike(
                        likeBO.daLike(
                                ndSession.getMaND(),
                                p.getMaBaiDang()
                        )
                );
                p.setSoLike(
                        likeBO.demLike(p.getMaBaiDang())
                );
            }

            request.setAttribute("soNguoiTheoDoi",
                    tdBO.demNguoiTheoDoi(profileUser.getMaND()));
            request.setAttribute("soDangTheoDoi",
                    tdBO.demDangTheoDoi(profileUser.getMaND()));

            request.setAttribute("ndSession", ndSession);
            request.setAttribute("profileUser", profileUser);
            request.setAttribute("ds", ds);
            request.setAttribute("isOwner", isOwner);
            request.setAttribute("trangThaiTheoDoi", trangThaiTheoDoi);

            request.getRequestDispatcher("tcn.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi tcnController");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}