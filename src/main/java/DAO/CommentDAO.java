package DAO;

import java.util.Date;
import java.util.List;

import model.Comment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

import java.util.ArrayList;
import java.util.Set;

import static DAO.AbsDAO.getConnection;
import static DAO.AbsDAO.gson;

public class CommentDAO {
    private final Jedis jedis = getConnection();
    public List<Comment> getCommentsByScore(int score) {
        List<Comment> listComments = new ArrayList<>();
        Long scoreL = Long.parseLong("" + score);
        Set<String> commentStr = jedis.zrevrangeByScore("food-comment", scoreL, scoreL);
        for (String json : commentStr){
            listComments.add(gson.fromJson(json, Comment.class));
        }
        return listComments;
    }

    public void addComment(Comment comment) {
        Date date = new Date();
        comment.setDate(date);
        jedis.zadd("food-comment",comment.getScore(), gson.toJson(comment));
    }
}
