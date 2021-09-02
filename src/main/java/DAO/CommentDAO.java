package DAO;


import model.Comment;
import util.MyFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAO extends AbsDAO {

    public List<Comment> getComments(int post_id) {
        List<Comment> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Comment where post_id=? order by date DESC ");
            ps.setInt(1, post_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setPost_id(rs.getInt("post_id"));
                comment.setContent(rs.getString("content"));
                comment.setName(rs.getString("name"));
                comment.setContact(rs.getString("contact"));
                comment.setDate(rs.getDate("date"));
                list.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addComment(Comment comment) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into Comment(post_id, name, contact, content, date) values (?,?,?,?,?)");
            ps.setInt(1, comment.getPost_id());
            ps.setString(2, comment.getName());
            ps.setString(3, comment.getContact());
            ps.setString(4, comment.getContent());
            ps.setString(5, MyFormat.formatDate(new Date()));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
