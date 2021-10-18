package service;

import DAO.PostDAO;
import model.Feature;
import model.Post;

import java.util.List;

public class PostService {
    private final int NUMBER_OF_POST_IN_PAGE = 12;
    private final PostDAO postDAO = new PostDAO();

    public List<Post> getPosts(int page) {
        return postDAO.getPosts(page);
    }

    public int getTotalPages() {
        int numberOfPost = postDAO.getNumberOfPosts();
        return ((int) Math.ceil(numberOfPost / NUMBER_OF_POST_IN_PAGE)) + 1;
    }

    public Post getPost(int score) {
        return postDAO.getPostByScore(score);
    }

    public void editPost(Post post) {
        postDAO.editPost(post);
    }

    public void deletePost(int score) {
        postDAO.deletePost(score);
    }

    public List<Feature> getAllFeatures() {
        return postDAO.getAllFeatures();
    }
}
