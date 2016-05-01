package es.tipolisto.bolas.modelo.modelo;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;




import es.tipolisto.bolas.modelo.Asset;

/**
 * Bola data structure
 * @author KikeMadrigal
 */
public class Bola extends Actor {
    /**La bola puede estar seleecionada o no. */
    private boolean bolaSeleccionada;
    /**La bola tendrá un color. */
    private String colorBola2;
    private Texture texture;
    private int fila, columna;
    //La partida la ponemos para un control de selecciones
    private Partida partida;
    public Bola(String colorBola2, final Partida partida, int fila, int columna){
        this.partida=partida;
        this.colorBola2=colorBola2;
        this.fila=fila;
        this.columna=columna;
        this.bolaSeleccionada=false;
        texture=new Texture(Gdx.files.internal("bolas/red.png"));
        addListener(new  BolaInputListener(this, partida));

    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }


    public boolean isBolaSeleccionada() {
        return bolaSeleccionada;
    }

    public void setBolaSeleccionada(boolean bolaSeleccionada) {
        this.bolaSeleccionada = bolaSeleccionada;
    }

    public String obtenerColor() {
        return this.colorBola2;
    }

    public void setColor(String colorBola) {
        this.colorBola2 = colorBola;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        texture= Asset.getAssetManager().get("bolas/" + obtenerColor());
        batch.draw(texture, getX(), getY(),getWidth(),getHeight());
        if(isBolaSeleccionada()){
            texture= Asset.getAssetManager().get("bolas/selecionada2.png");
            batch.draw(texture, getX(), getY(),getWidth(),getHeight());
        }
    }



    private class BolaInputListener extends InputListener{
        private Partida partida;
        private Bola bola;
        public BolaInputListener(Bola bola, Partida partida){
            this.bola=bola;
            this.partida=partida;
        }
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
           // System.out.println("pinchado en "+Gdx.input.getX()+", "+Gdx.input.getY()+"bola: "+obtenerColor());
            if(bolaSeleccionada){
                bolaSeleccionada=false;
            }else{
                bolaSeleccionada=true;
                partida.bolaSeleccionada(bola);
            }
            // bolaSeleccionada=!bolaSeleccionada;
            return true;
        }
    }

}