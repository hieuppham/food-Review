package controller;

import org.thymeleaf.ITemplateEngine;
import service.CommentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiController extends MyController{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
        templateEngine.process("dev", ctx, response.getWriter());
    }
}
