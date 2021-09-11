package API;

import model.Comment;
import model.GeoJSON;
import model.Post;
import org.json.simple.JSONObject;
import service.CommentService;
import service.PostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/foodreview")
public class FoodReviewAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post")
    public JSONObject getPosts(@QueryParam("text") String text, @QueryParam("page") int page) {
        JSONObject result = new JSONObject();
        page = (page != 0) ? page : 1;
        result.put("data", new PostService().searchPosts(text, page));
        result.put("page", page);
        result.put("numberOfPage", new PostService().getTotalPages(text));
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post/{id}")
    public Post getPostById(@PathParam("id") int id) {
        return new PostService().getPost(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/comment/{post_id}")
    public JSONObject getComments(@PathParam("post_id") int post_id) {
        JSONObject result = new JSONObject();
        result.put("size", new CommentService().getComments(post_id).size());
        result.put("data", new CommentService().getComments(post_id));
        return result;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/comment/{post_id}")
    public String addComment(@PathParam("post_id") int post_id,  Comment comment) {
        new CommentService().addComment(post_id, comment.getContent(), comment.getName(), comment.getContact());
        return "success";
    }
//    location api
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/map")
    public GeoJSON getMap(){

        return new GeoJSON("FeatureCollection", new PostService().getAllFeatures());
    }
}
