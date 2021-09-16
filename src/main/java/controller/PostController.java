package controller;

import model.Comment;
import model.Post;
import org.thymeleaf.ITemplateEngine;
import service.CommentService;
import service.PostService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PostController extends MyController{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
        int id = 0;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id").trim());
            Post post = new PostService().getPost(id);
            if(post == null) {
                templateEngine.process("error", ctx, response.getWriter());
                return;
            }
            ctx.setVariable("post", post);

            List<Comment> listComment = new CommentService().getComments(id);

            ctx.setVariable("listComment", listComment);
        }else{
            System.out.println("id is null");
            templateEngine.process("error", ctx, response.getWriter());
            return;
        }

        templateEngine.process("post", ctx, response.getWriter());
    }
}
