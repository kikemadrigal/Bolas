package es.tipolisto.bolas.modelo;

import com.badlogic.gdx.assets.AssetManager;


public class Asset {

    private static AssetManager assetManager;


    public static AssetManager getAssetManager(){
        if(assetManager==null){
            assetManager=new AssetManager();
        }
        return assetManager;
    }
}
