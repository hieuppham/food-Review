package DAO;

import java.util.Date;
import java.util.List;

import model.Comment;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Set;

import static DAO.AbsDAO.getConnection;
import static DAO.AbsDAO.gson;

public class CommentDAO {
    public List<Comment> getCommentsByScore(int score) {
        List<Comment> listComments = new ArrayList<>();
        Jedis jedis = getConnection();
        Long scoreL = Long.parseLong("" + score);
        Set<String> commentStr = jedis.zrevrangeByScore("food-comment", scoreL, scoreL);
        for (String json : commentStr){
            listComments.add(gson.fromJson(json, Comment.class));
        }
        return listComments;
    }

    public void addComment(Comment comment) {
        Jedis jedis = getConnection();
        Date date = new Date();
        comment.setDate(date);
        jedis.zadd("food-comment",comment.getScore(), gson.toJson(comment));
    }
}
