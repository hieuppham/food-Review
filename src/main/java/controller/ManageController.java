package controller;

import model.Post;
import org.thymeleaf.ITemplateEngine;
import service.PostService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
public class ManageController extends MyController {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
        request.setCharacterEncoding("UTF-8");
        String uid = request.getParameter("uid");
        if (uid == null || !uid.equals("sQ1UEjTmyKW0MvYsmOmFIipX3Y52")) {
            response.sendRedirect("/");
        } else {
            int score = request.getParameter("score") != null ? Integer.parseInt(request.getParameter("score").trim()) : -1;
            PostService postService = new PostService();
            if (request.getMethod().equalsIgnoreCase("get") && score != -1) {
                Post post = postService.getPost(score);
                if(post == null){
                    post = new Post(score, null, null, new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new double[]{0,0});
                }
                ctx.setVariable("post", post);
            } else if (request.getMethod().equalsIgnoreCase("post") && score != -1 && request.getParameter("action") != null) {
                String action = request.getParameter("action").trim();
                if (action.equals("edit")) {
                    Post post = new Post();

                    String key = request.getParameter("key").trim();
                    String title = request.getParameter("title").trim();
                    Date date = post.stringToDate(request.getParameter("date").trim());
                    double lng = Double.parseDouble(request.getParameter("lng").trim());
                    double lat = Double.parseDouble(request.getParameter("lat").trim());
                    List<String> images = Arrays.asList(request.getParameterValues("images"));
                    List<String> tags = Arrays.asList(request.getParameterValues("tags"));
                    List<String> texts = Arrays.asList(request.getParameterValues("texts"));

                    post.setScore(score);
                    post.setImages(images);
                    post.setTitle(title);
                    post.setDate(date);
                    post.setTags(tags);
                    post.setKey(key);
                    post.setTexts(texts);
                    post.setCoordinates(new double[]{lng, lat});

                    postService.editPost(post);

                    ctx.setVariable("post", post);
                    templateEngine.process("manage", ctx, response.getWriter());
                } else if (action.equals("delete")) {
                    postService.deletePost(score);
                }
            }
            templateEngine.process("manage", ctx, response.getWriter());
        }
    }
}
