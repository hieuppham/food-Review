package controller;

import model.Post;
import org.thymeleaf.ITemplateEngine;
import service.PostService;

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
        ctx.setVariable("listComment", null);
        templateEngine.process("post", ctx, response.getWriter());
    }
}
