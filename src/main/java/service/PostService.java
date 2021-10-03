package service;

import DAO.PostDAO;
import model.Feature;
import model.Post;

import java.util.List;

public class PostService {
    private final int NUMBER_OF_POST_IN_PAGE = 8;

    public List<Post> getPosts(int page) {
        return new PostDAO().getPosts(page);
    }

    public int getTotalPages() {
        int numberOfPost = new PostDAO().getNumberOfPosts();
        return ((int) Math.ceil(numberOfPost / NUMBER_OF_POST_IN_PAGE))+1;
    }

    public Post getPost(int score) {
        return new PostDAO().getPostByScore(score);
    }
//
//    public void addPost(String title, String headers,String contents, String images, String hashtags, String name, String contact, String des, double lng, double lat) {
//        new PostDAO.addPost(title, headers,contents, images, hashtags, name, contact, des, lng, lat);
//    }
//
    public List<Feature> getAllFeatures(){
        return new PostDAO().getAllFeatures();
    }
}
