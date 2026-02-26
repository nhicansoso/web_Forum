package Controller;

import java.io.IOException;
import java.util.ArrayList;

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
import Modal.NguoiDung;
import Modal.NguoiDungBO;
import Modal.TheoDoiBO;
import Modal.ThongBao;
import Modal.ThongBaoBO;

@WebServlet("/tcController")
public class tcController extends HttpServlet {
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

            BaiDangBO bdBO = new BaiDangBO();
            TheoDoiBO tdBO = new TheoDoiBO();
            LuotLikeBO likeBO = new LuotLikeBO();
            NguoiDungBO ndBO = new NguoiDungBO();
            ThongBaoBO tbBO = new ThongBaoBO();
            BinhLuanBO blBO = new BinhLuanBO();

            ArrayList<ThongBao> dsThongBao = tbBO.getThongBao(ndSession.getMaND());
            session.setAttribute("dsThongBao", dsThongBao);

            int soThongBaoChuaDoc = tbBO.demThongBaoChuaDoc(ndSession.getMaND());
            session.setAttribute("soThongBaoChuaDoc", soThongBaoChuaDoc);
            request.setAttribute("dsThongBao", dsThongBao);
            request.setAttribute("soThongBaoChuaDoc", soThongBaoChuaDoc);

            String tab = request.getParameter("tab");
            ArrayList<BaiDang> ds;

            if ("following".equals(tab)) {
                ds = bdBO.getBaiDangTheoDoi(ndSession.getMaND()); 
            } else {
                ds = bdBO.getBaiDang();
            }


            if (ds != null) {
                for (BaiDang p : ds) {

                    if (p.getMaND() != ndSession.getMaND()) {
                        String trangThai = tdBO.getTrangThaiTheoDoi(
                                ndSession.getMaND(),
                                p.getMaND()
                        );
                        p.setTrangThaiTheoDoi(trangThai);
                    }

                    p.setDaLike(likeBO.daLike(ndSession.getMaND(), p.getMaBaiDang()));
                    p.setSoLike(likeBO.demLike(p.getMaBaiDang()));
                    if (p.getDanhSachCmt() == null) {
                        p.setDanhSachCmt(new ArrayList<>());
                    }else {
                    	p.setDanhSachCmt(blBO.getBinhLuanTheoBaiDang(p.getMaBaiDang()));
                    }
                }
            }

            ArrayList<NguoiDung> dsDangTheoDoi = tdBO.getDanhSachDangTheoDoi(ndSession.getMaND());
            ArrayList<NguoiDung> dsGoiY = ndBO.getNguoiDungGoiY(ndSession.getMaND());

            request.setAttribute("ds", ds);
            request.setAttribute("ndSession", ndSession);
            request.setAttribute("dsDangTheoDoi", dsDangTheoDoi);
            request.setAttribute("dsGoiY", dsGoiY);

            request.getRequestDispatcher("tc.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi tcController: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}