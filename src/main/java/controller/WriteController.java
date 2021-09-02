package controller;

import model.Post;
import org.thymeleaf.ITemplateEngine;
import service.PostService;
import util.MyFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WriteController extends MyController {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);
        templateEngine.process("write", ctx, response.getWriter());
    }
}
