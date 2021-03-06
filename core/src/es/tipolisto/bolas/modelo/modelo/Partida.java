package es.tipolisto.bolas.modelo.modelo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Partida extends Group{
    private final int tamanio;
    /**
     * Mi partida tendrá un array de bolas que será el tablero
     */
    private Bola[][] arrayBolas;
    private List<Bola> arrayListTodasLasBolas;
    private Puntuacion puntuacion;


    public Partida(int tamanio){
        this.tamanio=tamanio;
        this.puntuacion=new Puntuacion();
        this.puntuacion.setPosition(50, Gdx.graphics.getHeight());
        //this.puntuacion.setSize(Gdx.graphics.getWidth(),80);
        //this.puntuacion.setBounds(50,250,300,20);
        addActor(puntuacion);
        float tamanioTablero=Gdx.graphics.getWidth()/tamanio;
        arrayBolas=new Bola[tamanio][tamanio];

        arrayListTodasLasBolas=new ArrayList<Bola>();
        for (int y=0; y<tamanio; y++){
            for(int x=0;x<tamanio;x++){
                Bola bola=new Bola(ColorBola2.rojo, this, x, y);
                arrayBolas[x][y]=bola;
                arrayListTodasLasBolas.add(bola);
                addActor(arrayBolas[x][y]);
                //arrayBolas[x][y].setPosition(50*x, 50*y);
                //arrayBolas[x][y].setSize(50,50);
                // No es necesario llamar a los 2 métdos anteriores con este basta
               // arrayBolas[x][y].setBounds(50*x, 50*y, 50,50);
                arrayBolas[x][y].setBounds(tamanioTablero*x, tamanioTablero*y, tamanioTablero,tamanioTablero);
            }
        }
    }






    //Controlamos y hacemos estas acciones cuando las bolas seleccionadas sean 4
    private int cuantasBolasTocadas=0;
    public void bolaSeleccionada(Bola bola){
        if(!bola.isBolaSeleccionada()){
            cuantasBolasTocadas--;
            return;
        }
        if(++cuantasBolasTocadas==4){
            // TODO: Procesar todas las bolas tocadas
            //Primero obtenemos las bolas que tienen su atributo bola.seleccionada a true.
            List<Bola> arrayListBolasSeleccionadas=getBolasSeleccionadas();
           /* boolean validadasBolasDelMismoColor=comprobarSiTodasLasBolasSOnDelMismoColor(arrayListBolasSeleccionadas);
            System.out.println(validadasBolasDelMismoColor ? "Son validas son del mismo color" : "No son validas no son del mismo color");*/


            Set<Integer> filas=new HashSet<Integer>();
            Set<Integer> columnas=new HashSet<Integer>();

            for(Bola bolaParaObtenerFilaColumna: arrayListBolasSeleccionadas){
                filas.add(bolaParaObtenerFilaColumna.getFila());
                columnas.add( bolaParaObtenerFilaColumna.getColumna());
            }
            if(!comprobarSiTodasLasBolasSOnDelMismoColor(arrayListBolasSeleccionadas)){
                //System.out.println("Hay una bola de otro color");

            }else if(filas.size()!=2 || columnas.size()!=2){
               // System.out.println("No has seleccionado de la misma fila o columna.");
            }else{
                puntuacion.incrementarPuntuacion(100);
                //Sacamos la celda que hemos pulsado y hemos acertado
                //la única forma de ver los valores de la celda (las x y la y) qye metimos
                //cuando creamos la bola es esta:
                Integer[] valoresFilas=filas.toArray(new Integer[2]);
                Integer[] valoresColumnaa=columnas.toArray(new Integer[2]);
                //Esto te da las esquinas:
                int minX=Math.min(valoresFilas[0], valoresColumnaa[1]);
                int maxX=Math.min(valoresFilas[0], valoresColumnaa[1]);
                int minY=Math.min(valoresFilas[0], valoresColumnaa[1]);
                int maxY=Math.min(valoresFilas[0], valoresColumnaa[1]);
            }

            for(Bola bolaParaVaciar: arrayListBolasSeleccionadas){
                bolaParaVaciar.setBolaSeleccionada(false);
            }
            cuantasBolasTocadas=0;

        }
    }
    private List<Bola> getBolasSeleccionadas(){
        List<Bola> arrayListBolasSelecciondas=new ArrayList<Bola>();
        /*El código de abajo se prodría hacer así:
        for(Bola bola:arrayListTodasLasBolas){
            if(bola.isBolaSeleccionada()){
                arrayListBolasSelecciondas.add(bola);
            }
        }*/
        for(int y=0;y<tamanio;y++){
            for(int x=0;x<tamanio;x++){
                if(arrayBolas[x][y].isBolaSeleccionada()){
                    arrayListBolasSelecciondas.add(arrayBolas[x][y]);
                }
            }
        }
        return arrayListBolasSelecciondas;
    }

    private boolean comprobarSiTodasLasBolasSOnDelMismoColor(List<Bola> arrayListTodasLasBolas){
        String colorPrimeraBola=arrayListTodasLasBolas.get(0).obtenerColor();
        for(Bola bola: arrayListTodasLasBolas){
            if(bola.obtenerColor()!=colorPrimeraBola){
                return false;
            }
        }
        return true;
    }

    public Bola[][] getBolas(){
        return this.arrayBolas;
    }
    public Puntuacion getPuntuacion(){
        return this.puntuacion;
    }
    public int getTamanio(){
        return this.tamanio;
    }



    public void generarPartida(){
        int ultimoColor=0;
        int contador=0;
        int nuevoColor;
        String[] todosLosColores=ColorBola2.getValues();
        System.out.println("Numero de colores: " + todosLosColores.length);
        for (int y=0; y<arrayBolas.length; y++){
            for(int x=0;x<arrayBolas.length;x++){
                int numeroColor=MathUtils.random(todosLosColores.length-1);

                if(numeroColor==ultimoColor){
                    contador++;
                    if(contador==3){
                         nuevoColor=MathUtils.random(todosLosColores.length-1);
                         contador=0;
                    }
                }else{
                    ultimoColor=numeroColor;
                }
               //String colorBola=todosLosColores[MathUtils.random(todosLosColores.length-1)];
                String colorBola=todosLosColores[MathUtils.random(ultimoColor)];
                arrayBolas[x][y].setColor(colorBola);

            }
        }
    }

    @Override
    public void act(float delta) {

    }

}
