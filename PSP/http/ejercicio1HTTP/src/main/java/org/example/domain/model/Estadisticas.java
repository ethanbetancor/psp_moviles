package org.example.domain.model;

import java.util.List;

public class Estadisticas {
    private List<Partida> partidas;

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public void AñadirPartidas(Partida partida) {
        this.partidas.add(partida);
    }
}
