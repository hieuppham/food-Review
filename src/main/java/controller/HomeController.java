package controller;

import model.Post;
import org.thymeleaf.ITemplateEngine;
import service.PostService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HomeController extends MyController {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);

        String text = null;
        String url = "/?";
        //url use for pagination
        if (request.getParameter("text") != null) {
            text = request.getParameter("text").trim();
            url = url + "&text=" + text;
        }
        ctx.setVariable("url", url);
        //page use for pagination
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page").trim());
        }
        ctx.setVariable("page", page);
        //totalPages use for pagination
        int totalPages = 0;
        totalPages = new PostService().getTotalPages(text);
        ctx.setVariable("totalPages", totalPages);
        //list of post
        List<Post> list = new ArrayList<>();
        list = new PostService().searchPosts(text, page);
        ctx.setVariable("list", list);

        templateEngine.process("index", ctx, response.getWriter());
    }
}
