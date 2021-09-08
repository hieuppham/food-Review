package API;

import model.Post;
import service.PostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api")
public class FoodReviewAPI {
    @GET
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPosts(@QueryParam("text") String text, @QueryParam("page") int page) {
        return new PostService().searchPosts(text, page);
    }

    @GET
    @Path("/post/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPost(@PathParam("id") int id) {
        return new PostService().getPost(id);
    }

    @POST
    @Path("/post/addPost")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPost(String title, String headers, String contents, String images, String hashtags, String name, String contact) {
        new PostService().addPost(
                title,
                headers,
                contents,
                images,
                hashtags,
                name,
                contact
        );
        return "Add post successfully";
    }
}
