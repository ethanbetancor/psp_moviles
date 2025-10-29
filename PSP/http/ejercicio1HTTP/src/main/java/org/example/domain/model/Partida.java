package org.example.domain.model;

public class Partida {
    private String jugador;
    private int numeroSecreto;
    private int intentosUsados;
    private int partidasJugadas;
    private int PartidasGandas;
    private int partidasPerdidas;

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getNumeroSecreto() {
        return numeroSecreto;
    }

    public void setNumeroSecreto(int numeroSecreto) {
        this.numeroSecreto = numeroSecreto;
    }

    public int getIntentosUsados() {
        return intentosUsados;
    }

    public void setIntentosUsados(int intentosUsados) {
        this.intentosUsados = intentosUsados;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPartidasGandas() {
        return PartidasGandas;
    }

    public void setPartidasGandas(int partidasGandas) {
        PartidasGandas = partidasGandas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }
}
