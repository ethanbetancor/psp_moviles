package org.example.config;

public class GameConstants {
    // Configuración del juego
    public static final int MAX_INTENTOS = 5;
    public static final int MIN_NUMERO = 1;
    public static final int MAX_NUMERO = 10;

    // Claves de sesión
    public static final String NUMERO_SECRETO = "numeroSecreto";
    public static final String INTENTOS_RESTANTES = "intentosRestantes";
    public static final String GAME_OVER = "gameOver";

    // Parámetros HTTP
    public static final String PARAM_NUMERO = "numero";
    public static final String PARAM_REINICIAR = "reiniciar";

    // Variables de contexto Thymeleaf
    public static final String VAR_INTENTOS = "intentos";
}
