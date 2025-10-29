package org.example.ejercicio1http;

import org.example.config.Constants;
import org.example.config.ThymeleafConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/win")
public class winServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Obtener datos de la sesión
        String nombre = (String) session.getAttribute("nombre");
        Integer numeroSecreto = (Integer) session.getAttribute("numeroSecreto");
        Integer intentosUsados = (Integer) session.getAttribute("intentosUsados");
        Integer intentosRestantes = (Integer) session.getAttribute(Constants.INTENTOS_RESTANTES);

        // Renderizar template
        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(webExchange);

        ctx.setVariable("nombre", nombre);
        ctx.setVariable("numeroSecreto", numeroSecreto);
        ctx.setVariable("intentosUsados", intentosUsados);
        ctx.setVariable("intentos", intentosRestantes);

        resp.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process("win", ctx, resp.getWriter());
    }
}
