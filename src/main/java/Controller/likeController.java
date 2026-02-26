package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modal.LuotLikeBO;
import Modal.NguoiDung;

@WebServlet("/likeController")
public class likeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);
            if (session == null) return;

            NguoiDung nd = (NguoiDung) session.getAttribute("nd");
            if (nd == null) return;

            Long maND = nd.getMaND();
            long maBaiDang =
                    Long.parseLong(request.getParameter("maBaiDang"));

            LuotLikeBO bo = new LuotLikeBO();

            bo.likeOrUnlike(maND, maBaiDang);

            boolean liked = bo.daLike(maND, maBaiDang);
            int soLike = bo.demLike(maBaiDang);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().print(
                "{\"liked\":" + liked + ",\"soLike\":" + soLike + "}"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
