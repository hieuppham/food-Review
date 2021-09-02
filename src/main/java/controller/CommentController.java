package controller;

import org.thymeleaf.ITemplateEngine;
import service.CommentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentController implements IController{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        request.setCharacterEncoding("UTF-8");
        if(request.getMethod().equalsIgnoreCase("post") && request.getParameter("action") != null && request.getParameter("action").equals("addComment")){
            int post_id = Integer.parseInt(request.getParameter("post_id").trim());
            String content = request.getParameter("content");
            String name = request.getParameter("name");

            String contact = request.getParameter("contact");
            new CommentService().addComment(post_id, content, name, contact);
            response.sendRedirect("/post?id=" + post_id);
        }
    }
}
