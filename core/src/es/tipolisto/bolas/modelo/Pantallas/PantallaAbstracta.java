package es.tipolisto.bolas.modelo.Pantallas;

import com.badlogic.gdx.Screen;

import es.tipolisto.bolas.modelo.Juego;

/**
 * Creo una pantalla abstracta a la que se le pasará
 * la pantalla desde la clase que extiende de Game
 * Además todas las pantallas tendrán un identificador.
 */
public abstract class PantallaAbstracta implements Screen {
    protected Juego juego;
    protected int id;
    public PantallaAbstracta(Juego juego, int id) {
        this.juego=juego;
        this.id=id;
    }
    

    public void load(){

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public int getId(){
        return this.id;
    }
}
