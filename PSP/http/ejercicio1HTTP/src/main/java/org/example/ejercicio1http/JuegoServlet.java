package org.example.ejercicio1http;

import org.example.config.Constants;
import org.example.config.MessageConstants;
import org.example.config.ThymeleafConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.domain.model.Estadisticas;
import org.example.domain.model.Partida;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet("/juego")
public class JuegoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("estadisticas") == null) {
            Estadisticas estadisticas = new Estadisticas();
            estadisticas.setPartidas(new ArrayList<>());
            session.setAttribute("estadisticas", estadisticas);
        }

        String nombre = req.getParameter("nombre");
        String nombreAnterior = (String) session.getAttribute("nombre");

        if (nombre != null) {
            if (nombreAnterior != null && !nombreAnterior.equals(nombre)) {
                inicializarJuego(session);
            }
            session.setAttribute("nombre", nombre);
        } else {
            nombre = nombreAnterior;
        }

        if (session.getAttribute(Constants.NUMERO_SECRETO) == null ||
            req.getParameter(Constants.PARAM_REINICIAR) != null) {
            inicializarJuego(session);
        }

        String mensaje = "";
        boolean ganaste = false;

        Boolean gameOverAttr = (Boolean) session.getAttribute(Constants.GAME_OVER);
        boolean gameOver = gameOverAttr != null && gameOverAttr;

        String numeroParam = req.getParameter(Constants.PARAM_NUMERO);

        if (numeroParam != null && !numeroParam.isEmpty() && !gameOver) {
            try {
                int numero = Integer.parseInt(numeroParam);

                if (numero < Constants.MIN_NUMERO || numero > Constants.MAX_NUMERO) {
                    mensaje = "Por favor, introduce un número entre " + Constants.MIN_NUMERO + " y " + Constants.MAX_NUMERO;
                } else {
                    int numeroSecreto = (Integer) session.getAttribute(Constants.NUMERO_SECRETO);
                    int intentosRestantes = (Integer) session.getAttribute(Constants.INTENTOS_RESTANTES);

                    if (numero == numeroSecreto) {
                        int intentosUsados = Constants.MAX_INTENTOS - intentosRestantes + 1;
                        mensaje = Constants.MSG_GANASTE + numeroSecreto + "! 🎉";
                        ganaste = true;
                        gameOver = true;

                        session.setAttribute(Constants.GAME_OVER, true);
                        session.setAttribute("numeroSecreto", numeroSecreto);
                        session.setAttribute("intentosUsados", intentosUsados);

                        guardarPartida(session, numeroSecreto, intentosUsados, true);

                        resp.sendRedirect("win");
                        return;

                    } else {
                        // FALLO - Restar intento
                        intentosRestantes--;
                        session.setAttribute(Constants.INTENTOS_RESTANTES, intentosRestantes);

                        if (intentosRestantes <= 0) {
                            // GAME OVER
                            mensaje = MessageConstants.MSG_GAME_OVER + numeroSecreto + " 😢";
                            gameOver = true;
                            session.setAttribute(Constants.GAME_OVER, true);
                            guardarPartida(session, numeroSecreto, Constants.MAX_INTENTOS, false);
                        } else {
                            // DAR PISTA
                            if (numero < numeroSecreto) {
                                mensaje = Constants.MSG_NUMERO_MAYOR + " ⬆️";
                            } else {
                                mensaje = Constants.MSG_NUMERO_MENOR + " ⬇️";
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                mensaje = Constants.MSG_NUMERO_INVALIDO;
            }
        }

        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(webExchange);

        ctx.setVariable("nombre", nombre);
        ctx.setVariable(MessageConstants.VAR_MENSAJE, mensaje);
        ctx.setVariable("ganaste", ganaste);
        ctx.setVariable("gameOver", gameOver);
        ctx.setVariable(Constants.VAR_INTENTOS, session.getAttribute(Constants.INTENTOS_RESTANTES));
        ctx.setVariable("minNumero", Constants.MIN_NUMERO);
        ctx.setVariable("maxNumero", Constants.MAX_NUMERO);

        resp.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process(Constants.TEMPLATE_JUEGO, ctx, resp.getWriter());
    }

    private void inicializarJuego(HttpSession session) {
        Random random = new Random();
        int numeroSecreto = random.nextInt(Constants.MAX_NUMERO - Constants.MIN_NUMERO + 1) + Constants.MIN_NUMERO;

        session.setAttribute(Constants.NUMERO_SECRETO, numeroSecreto);
        session.setAttribute(Constants.INTENTOS_RESTANTES, Constants.MAX_INTENTOS);
        session.removeAttribute(Constants.GAME_OVER);
    }

    private void guardarPartida(HttpSession session, int numeroSecreto, int intentosUsados, boolean ganada) {
        Estadisticas estadisticas = (Estadisticas) session.getAttribute("estadisticas");
        String nombre = (String) session.getAttribute("nombre");

        Partida partida = new Partida();
        partida.setJugador(nombre);
        partida.setNumeroSecreto(numeroSecreto);
        partida.setIntentosUsados(intentosUsados);
        partida.setPartidasGandas(ganada ? 1 : 0);
        partida.setPartidasPerdidas(ganada ? 0 : 1);

        estadisticas.getPartidas().add(partida);
        session.setAttribute("estadisticas", estadisticas);
    }
}
