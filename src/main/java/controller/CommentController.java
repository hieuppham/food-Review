package controller;

import model.Comment;
import org.thymeleaf.ITemplateEngine;
import service.CommentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentController extends MyController{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        request.setCharacterEncoding("UTF-8");
        int score = Integer.parseInt(request.getParameter("score"));
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String content = request.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setScore(score);
        comment.setName(name);
        comment.setContact(contact);
        comment.setContent(content);

        new CommentService().addComment(comment);
        response.sendRedirect("/post?score="+score);
    }
}

