package service;
import java.util.List;

import DAO.CommentDAO;
import model.Comment;

public class CommentService {
    public List<Comment> getCommentsByScore(int score){
        return new CommentDAO().getCommentsByScore(score);
    }

    public void addComment(Comment comment){
        new CommentDAO().addComment(comment);
    }
}
