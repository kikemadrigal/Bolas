package es.tipolisto.bolas.modelo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import java.util.HashMap;
import java.util.Map;


import es.tipolisto.bolas.modelo.Pantallas.PantallaAbstracta;


public class Juego extends Game {
    public SpriteBatch spriteBatch;
    private Map<Integer, PantallaAbstracta> hasMapPantalla;

    @Override
    public void create() {
        this.spriteBatch=new SpriteBatch();
        this.hasMapPantalla=new HashMap<Integer, PantallaAbstracta>();

        /**La clase Asset la crceé aparte para facilitar elacceso a los assets*/
        AssetManager assetManager=Asset.getAssetManager();
        assetManager.load("bolas/red.png", Texture.class);
        assetManager.load("bolas/green.png", Texture.class);
        assetManager.load("bolas/blue.png", Texture.class);
        assetManager.load("bolas/yellow.png", Texture.class);
        assetManager.load("bolas/yellow.png", Texture.class);
        assetManager.load("bolas/selecionada2.png", Texture.class);
        assetManager.finishLoading();

        /**Este es el método que hay creado abajo del todo para añadir pantallas*/
        this.addPantalla(new Nivel2(this, 1));

        /**Este bucle es solo par aque lea losmétdos load de los screens*/
        for (Map.Entry<Integer, PantallaAbstracta> pantallas: hasMapPantalla.entrySet()){
            pantallas.getValue().load();
        }
        setPantalla(1);
    }
    public void setPantalla(int id){
        this.setScreen(hasMapPantalla.get(id));
    }
    public PantallaAbstracta getPantalla(int id){
        return hasMapPantalla.get(id);
    }
    public void addPantalla(PantallaAbstracta pantallaAbstracta){
        hasMapPantalla.put(pantallaAbstracta.getId(), pantallaAbstracta);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
