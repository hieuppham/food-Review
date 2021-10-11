package controller;

import model.Comment;
import model.Post;
import org.thymeleaf.ITemplateEngine;
import service.CommentService;
import service.PostService;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostController extends MyController {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);

        String scoreStr = request.getParameter("score");
        int score = Integer.parseInt(scoreStr);
        Post post = new PostService().getPost(score);
        ctx.setVariable("post", post);
        List<Comment> commentList = new CommentService().getCommentsByScore(score);
        ctx.setVariable("commentList", commentList);
        templateEngine.process("post", ctx, response.getWriter());
    }
}
