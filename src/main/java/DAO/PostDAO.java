package DAO;

import model.Feature;
import model.Geo;
import model.Post;
import model.Properties;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

public class PostDAO extends AbsDAO {
    private final int NUMBER_OF_POST_IN_PAGE = 12;
    private final Jedis jedis = getConnection();

    public void editPost(Post post) {
        Long scoreL = Long.parseLong(String.valueOf(post.getScore()));
        jedis.zremrangeByScore("food-review", scoreL, scoreL);
        String json = gson.toJson(post);
        jedis.zadd("food-review", scoreL, json);
    }

    public void deletePost(int score) {
        Long scoreL = Long.parseLong(String.valueOf(score));
        jedis.zremrangeByScore("food-review", scoreL, scoreL);
    }

    public List<Post> getPosts(int page) {
        List<Post> list = new ArrayList<>();
        long start = Long.parseLong(String.valueOf((page - 1) * NUMBER_OF_POST_IN_PAGE));
        long stop = Long.parseLong(String.valueOf(page * NUMBER_OF_POST_IN_PAGE - 1));
        Set<String> strPost = jedis.zrangeByScore("food-review", start, stop);
        strPost.forEach(str -> {
            Post post = gson.fromJson(str, Post.class);
            list.add(post);
        });
        return list;
    }

    public int getNumberOfPosts() {
        return Integer.parseInt(String.valueOf(jedis.zcount("food-review", 0L, Long.MAX_VALUE)));
    }

    public Post getPostByScore(int score) {
        Long scoreL = Long.parseLong(String.valueOf(score));
        String json = jedis.zrangeByScore("food-review", scoreL, scoreL).iterator().next();
        Post post = gson.fromJson(json, Post.class);
        return post;
    }

    public List<Feature> getAllFeatures() {
        List<Feature> list = new ArrayList<>();
        Set<String> strPosts = jedis.zrangeByScore("food-review", 0L, -1L);
        strPosts.forEach(str -> {
            Post post = gson.fromJson(str, Post.class);
            Feature feature = new Feature();

            String description = post.getTitle();
            feature.setProperties(new Properties(description));

            double[] coordinates = post.getCoordinates();
            Geo geo = new Geo(coordinates);
            feature.setGeometry(geo);

            list.add(feature);
        });
        return list;
    }

}
