package org.example.ejercicio1http;

import java.io.*;

import org.example.config.Constants;
import org.example.config.ThymeleafConstants;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@WebServlet("/login")
public class HelloServlet extends HttpServlet {



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String nombre = request.getParameter("nombre");

        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext ctx = new WebContext(webExchange);
        ctx.setVariable("nombre", nombre);

        response.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process(Constants.TEMPLATE_JUEGO, ctx, response.getWriter());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext ctx = new WebContext(webExchange);

        response.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process("index", ctx, response.getWriter());
    }

}