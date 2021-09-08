package service;

import DAO.PostDAO;
import model.Post;
import util.MyFormat;

import java.util.List;

public class PostService {
    private final int NUMBER_OF_POST_IN_PAGE = 8;

    public List<Post> searchPosts(String text, int page) {
        return new PostDAO().getPosts(text, page);
    }

    public int getTotalPages(String text) {
        int numberOfPost = new PostDAO().getNumberOfPosts(text);

        return (int) Math.ceil(numberOfPost / NUMBER_OF_POST_IN_PAGE);
    }

    public Post getPost(int id) {
        return new PostDAO().getPostById(id);
    }

    public void addPost(String title, String headers,String contents, String images, String hashtags, String name, String contact) {
        new PostDAO.addPost(title, headers,contents, images, hashtags, name, contact);
    }
}
