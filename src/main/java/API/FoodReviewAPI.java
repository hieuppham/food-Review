package API;

import model.Comment;
import model.GeoJSON;
import model.Post;
import org.json.simple.JSONObject;
import service.CommentService;
import service.PostService;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/foodreview")
public class FoodReviewAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<Post> getAllPosts(){
        List<Post> list = new ArrayList<>();
        int numOfPages = new PostService().getTotalPages();
        for(int i = 0 ; i < numOfPages; ++i){
            List<Post> newList = new PostService().getPosts(i+1);
            list.addAll(newList);
        }
        return list;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post")
    public JSONObject getPosts(@QueryParam("page") int page) {
        JSONObject result = new JSONObject();
        PostService ps = new PostService();
        result.put("numberOfPage", ps.getTotalPages());
        result.put("posts", ps.getPosts(page));
        result.put("page", page);
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post/{score}")
    public Post getPostByScore(@PathParam("score") int score) {
        return new PostService().getPost(score);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/comment/{post_score}")
    public JSONObject getComments(@PathParam("post_score") int post_score) {
        JSONObject result = new JSONObject();
        result.put("size", new CommentService().getCommentsByScore(post_score).size());
        result.put("data", new CommentService().getCommentsByScore(post_score));
        return result;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/comment/{post_score}")
    public String addComment(Comment comment) {
        new CommentService().addComment(comment);
        return "success";
    }
//    location api
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/map")
    public GeoJSON getMap(@Context HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new GeoJSON("FeatureCollection", new PostService().getAllFeatures());
    }
}
