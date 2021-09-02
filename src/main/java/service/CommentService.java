package service;


import DAO.CommentDAO;
import model.Comment;

import java.util.List;

public class CommentService {
    public List<Comment> getComments(int post_id) {
        return new CommentDAO().getComments(post_id);
    }

    public void addComment(int post_id, String content, String name, String contact) {
        Comment comment = new Comment();
        comment.setPost_id(post_id);
        comment.setContent(content);
        comment.setName(name);
        comment.setContact(contact);
        new CommentDAO().addComment(comment);
    }
}
