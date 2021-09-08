package DAO;


import model.Post;
import util.MyFormat;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAO extends AbsDAO {
    private final int NUMBER_OF_POST_IN_PAGE = 8;

    public List<Post> getPosts(String text, int page) {
        List<Post> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement ps;

            if (text != null) {
                if (page != 1) {
                    ps = con.prepareStatement("select *  from Post where match (title, content, hashtags) against (? IN NATURAL LANGUAGE MODE) LIMIT ? OFFSET ?");
                    ps.setString(1, "'" + text + "'");
                    ps.setInt(2, NUMBER_OF_POST_IN_PAGE);
                    ps.setInt(3, (page - 1) * NUMBER_OF_POST_IN_PAGE);
                } else {
                    ps = con.prepareStatement("select *  from Post where match (title, content, hashtags) against (? IN NATURAL LANGUAGE MODE) LIMIT ?");
                    ps.setString(1, "'" + text + "'");
                    ps.setInt(2, NUMBER_OF_POST_IN_PAGE);
                }

            } else {
                if (page != 1) {
                    ps = con.prepareStatement("select *  from Post LIMIT ? OFFSET ?");
                    ps.setInt(1, NUMBER_OF_POST_IN_PAGE);
                    ps.setInt(2, (page - 1) * NUMBER_OF_POST_IN_PAGE);
                } else {
                    ps = con.prepareStatement("select *  from Post LIMIT ? ");
                    ps.setInt(1, NUMBER_OF_POST_IN_PAGE);
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.set_id(rs.getInt("_id"));
                post.setTitle(rs.getString("title"));
                post.setHeader(MyFormat.toListString(rs.getString("header")));
                post.setContent(MyFormat.toListString(rs.getString("content")));
                post.setDate(rs.getDate("date"));
                post.setHashtags(MyFormat.toListString(rs.getString("hashtags")));
                post.setImages(MyFormat.toListString(rs.getString("images")));
                post.setName(rs.getString("name"));
                post.setContact(rs.getString("contact"));
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getNumberOfPosts(String text) {
        int numberOfPost = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps;
            ResultSet rs;
            if (text != null) {
                ps = con.prepareStatement("select count(_id) as count from Post where match (title, content, hashtags) against (? IN NATURAL LANGUAGE MODE)");
                ps.setString(1, "'" + text + "'");
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement("select count(_id) as count from Post");
                rs = ps.executeQuery();
            }
            if (rs.next()) numberOfPost = rs.getInt("count");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfPost;
    }

    public Post getPostById(int id) {
        Post post = new Post();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Post where _id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                post.set_id(rs.getInt("_id"));
                post.setTitle(rs.getString("title"));
                post.setHeader(MyFormat.toListString(rs.getString("header")));
                post.setContent(MyFormat.toListString(rs.getString("content")));
                post.setDate(rs.getDate("date"));
                post.setHashtags(MyFormat.toListString(rs.getString("hashtags")));
                post.setImages(MyFormat.toListString(rs.getString("images")));
                post.setName(rs.getString("name"));
                post.setContact(rs.getString("contact"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public static class addPost {
        public addPost(String title, String header, String content, String images, String hashtags, String name, String contact) {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("insert into Post(title, date, header, content, images, hashtags, name, contact) " +
                        "values (?, ?, ?, ?, ?, ?,?,?)");
                ps.setString(1, title);
                ps.setString(2, MyFormat.formatDate(new Date()));
                ps.setString(3, header);
                ps.setString(4, content);
                ps.setString(5, images);
                ps.setString(6, hashtags);
                ps.setString(7, name);
                ps.setString(8, contact);
                ps.execute();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
