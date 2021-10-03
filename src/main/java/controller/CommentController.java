package controller;

import model.Comment;
import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.ArrayList;

public class CommentController extends MyController{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
//        List<Comment> listComment = new ArrayList<>();
        ctx.setVariable("listComment", null);
    }
}

