package controller;

import org.thymeleaf.ITemplateEngine;
import service.PostService;
import util.MyFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintStream;
import java.util.List;

public class AddPostController implements IController {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        request.setCharacterEncoding("UTF-8");
        if (request.getMethod().equalsIgnoreCase("post")) {
            String title = request.getParameter("title").trim();
            String name = request.getParameter("name").trim();
            String contact = request.getParameter("contact").trim();
            String des = request.getParameter("des").trim();
            double lng = Double.parseDouble(request.getParameter("lng").trim());
            double lat = Double.parseDouble(request.getParameter("lat").trim());
            String[] urls = request.getParameterValues("images");
            String[] arrHeaders = request.getParameterValues("headers");
            String[] arrContents = request.getParameterValues("contents");
            String[] arrHashtags = request.getParameterValues("hashtags");
            String images = MyFormat.urlsToString(urls);
            String headers = MyFormat.arrToString(arrHeaders);
            String contents = MyFormat.arrToString(arrContents);
            String hashtags = MyFormat.arrToString(arrHashtags);

            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            out.println(title + name + des + headers + contents );
            new PostService().addPost(title, headers, contents, images, hashtags, name, contact, des, lng, lat);
            response.sendRedirect("/");
        }
    }
}
