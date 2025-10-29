package org.example.ejercicio1http;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.config.ThymeleafConstants;
import org.example.domain.model.Estadisticas;
import org.example.domain.model.Partida;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/estadisticas")
public class EstadisticasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        // Obtener estadísticas de la sesión
        Estadisticas estadisticas = (Estadisticas) session.getAttribute("estadisticas");
        String nombre = (String) session.getAttribute("nombre");

        // Calcular resumen de estadísticas
        int partidasGanadas = 0;
        int partidasPerdidas = 0;

        if (estadisticas != null && estadisticas.getPartidas() != null) {
            for (Partida partida : estadisticas.getPartidas()) {
                partidasGanadas += partida.getPartidasGandas();
                partidasPerdidas += partida.getPartidasPerdidas();
            }
        }

        // Renderizar template
        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(webExchange);

        ctx.setVariable("nombre", nombre);
        ctx.setVariable("estadisticas", estadisticas);
        ctx.setVariable("partidasGanadas", partidasGanadas);
        ctx.setVariable("partidasPerdidas", partidasPerdidas);

        resp.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process("estadisticas.html", ctx, resp.getWriter());
    }
}
