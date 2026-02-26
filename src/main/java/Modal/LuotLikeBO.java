package Modal;

public class LuotLikeBO {

    LuotLikeDAO dao = new LuotLikeDAO();

    public void likeOrUnlike(long maND, long maBaiDang) throws Exception {
        if (dao.daLike(maND, maBaiDang)) {
            dao.xoaLike(maND, maBaiDang);
        } else {
            dao.themLike(maND, maBaiDang);
        }
    }

    public boolean daLike(long maND, long maBaiDang) throws Exception {
        return dao.daLike(maND, maBaiDang);
    }

    public int demLike(long maBaiDang) throws Exception {
        return dao.demLike(maBaiDang);
    }
}
