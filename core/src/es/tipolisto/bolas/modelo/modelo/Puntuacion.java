package es.tipolisto.bolas.modelo.modelo;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import es.tipolisto.bolas.modelo.Asset;

public class Puntuacion extends Actor {
    private long puntuacion;
    private TextureRegion[] numeroTextureRegion;


    public Puntuacion() {
        /*this. numeroTextureRegion=new TextureRegion[10];
        this.puntuacion = 0;
        Texture numerosTexture= Asset.getAssetManager().get("puntuacion.png", Texture.class);
        for(int i=0;i<10;i++){
            this. numeroTextureRegion[i]=new TextureRegion(numerosTexture, 64*i, 0,64,64);
        }*/
        this(0);

    }
    public Puntuacion(long puntuacion){
        this.puntuacion=puntuacion;
        this. numeroTextureRegion=new TextureRegion[6];
        Texture numerosTexture= Asset.getAssetManager().get("puntuacion_grande.png", Texture.class);
        for(int i=0;i<6;i++){
            //64 es lo que mide de acho cada numero dentro de la imagen puntuacion_grande
            this. numeroTextureRegion[i]=new TextureRegion(numerosTexture, 64*i, 0,64,64);
        }
    }

    public long getPuntuacion(){
        return this.puntuacion;
    }

    public long incrementarPuntuacion(long incremento){
        this.puntuacion+=incremento;
        return this.puntuacion;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        String puntuacionString=Long.toString(puntuacion);
        int numeroDeZecros=6-puntuacionString.length();
        String puntuacionStringFinal="";
        for(int i=0; i<numeroDeZecros;i++){
           puntuacionStringFinal+="0";
        }
        puntuacionStringFinal+=puntuacionString;
        //recorremos el escorefinalstring para que nos de todos los caracteres
        for(int ch=0; ch<puntuacionStringFinal.length();ch++){
            char number=puntuacionStringFinal.charAt(ch);
          //  System.out.println("numero: "+number);
            //le restamos el caracter 0 para que nos devuelva el código ASCII decimal
            int index=number-'0';
            batch.draw( numeroTextureRegion[index],64*ch, Gdx.graphics.getHeight()-100);
        }
    }





    //Esto es para detectar el ancho de la pantalla y redimensionar la puntuación


    /**Escala a la que tiene que redimensionarse el marcador*/
    private int escala=6;

    @Override
    protected void sizeChanged() {
       int width=(int) getWidth();
        int height=(int) getHeight();
        //MAth.min devuelve el menor de dos valores int
        int min=Math.min(width, height);
        //obtenemos la relacción de escala
        escala=min/9;
    }
}