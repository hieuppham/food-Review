package service;
import java.util.List;

import DAO.CommentDAO;
import model.Comment;

public class CommentService {
    private final CommentDAO commentDAO = new CommentDAO();
    public List<Comment> getCommentsByScore(int score){
        return commentDAO.getCommentsByScore(score);
    }

    public void addComment(Comment comment){
        commentDAO.addComment(comment);
    }
}
