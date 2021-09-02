package controller;

import org.thymeleaf.ITemplateEngine;
import service.PostService;
import util.MyFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddPostController implements IController{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        request.setCharacterEncoding("UTF-8");
        if (request.getMethod().equalsIgnoreCase("post")) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String name = request.getParameter("name");
            String contact = request.getParameter("contact");
            String[] urls = request.getParameterValues("images");
            String images = MyFormat.urlsToIds(urls);
            String hashtags = request.getParameter("hashtags");
            new PostService().addPost(title, content, images, hashtags, name, contact);
            response.sendRedirect("/write");
        }
    }
}
