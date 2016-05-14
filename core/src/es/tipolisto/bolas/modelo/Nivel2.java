package es.tipolisto.bolas.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import es.tipolisto.bolas.modelo.Pantallas.PantallaAbstracta;
import es.tipolisto.bolas.modelo.modelo.Partida;


public class Nivel2 extends PantallaAbstracta {
    private Texture texture;
    private Stage stage;
    private Partida partida;
    Viewport viewport;
    public Nivel2(Juego juego,int id) {
        super(juego, id);
    }

    @Override
    public void load() {
        texture=new Texture(Gdx.files.internal("background.png"));
        stage=new Stage();
        viewport=new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage=new Stage(viewport, juego.spriteBatch);
        stage.setDebugAll(true);
        partida=new Partida(7);

        partida.generarPartida();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(partida);


    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }

}
