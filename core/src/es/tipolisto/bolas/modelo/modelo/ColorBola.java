package es.tipolisto.bolas.modelo.modelo;

/**
 * Created by Kike on 30/04/2016.
 */
public enum ColorBola {
    /**En java los enumeradores pueden tener métodos, fijate!! */
    AZUL("blue.png"), VERDE("green.png"), ROJO("red.png"), AMARILLO("yellow");


    private String path;
    ColorBola(String path){
        this.path=path;
    }
    public String getPath(){
        return path;
    }
}
