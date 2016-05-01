package es.tipolisto.bolas.modelo.modelo;


public class Puntuacion {
    private long puntuacion;

    public Puntuacion() {
        this.puntuacion = 0;
    }
    public Puntuacion(long puntuacion){
        this.puntuacion=puntuacion;
    }

    public long getPuntuacion(){
        return this.puntuacion;
    }

    public long incrementarPuntuacion(long incremento){
        this.puntuacion+=incremento;
        return this.puntuacion;
    }
}