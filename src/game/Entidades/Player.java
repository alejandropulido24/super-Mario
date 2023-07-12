package game.Entidades;

import game.Graficos.Animacion;
import game.Graficos.Textura;
import game.Objeto.Objeto;
import static game.Objeto.Objeto.Bloque;
import static game.Objeto.Objeto.Tubo;
import game.Objeto.Objeto_Juego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.manager.GamePanel;
import game.manager.KeyManager;
import game.manager.ObjetoManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Objeto_Juego {
    public static final float ANCHO = 32;
    public static final float ALTO = 32;  
    int n_Golpes = 2;
    GamePanel game;
    KeyManager controlManager;
   
    private boolean haciaDelante, salta;
    
    
    Textura textura;
    ObjetoManager objManager;
    BufferedImage[] playerGrande, playerPeque, actualSprite;
    Animacion caminaEnGrande, caminaEnPeque, actualAnimacion;
    
    
    public Player(GamePanel game, KeyManager controlManager, ObjetoManager objetoManager, float x, float y,int escala){      
        super(x, y, Objeto.Jugador, ANCHO, ALTO, escala);
        
        this.game = game;
        this.controlManager = controlManager;
        
        haciaDelante = false;
        salta = false;
        
        objManager = objetoManager;
        
        textura = game.getTextura();
        playerGrande = textura.getMarioGrande();
        playerPeque = textura.getMarioPeque();
               
        
        actualSprite = playerPeque;
        caminaEnPeque = new Animacion(2, playerPeque[1], playerPeque[2], playerPeque[3]);
        caminaEnGrande = new Animacion(2, playerGrande[1], playerGrande[2], playerGrande[3]);
        actualAnimacion = caminaEnPeque;
        
    }
    
    @Override
    public void pos() {
        setX(getVelocidadX() + getX());
        setY(getVelocidadY() + getY());
        aplicarGravedad();
        
        actualAnimacion.anima();
        colision();
    }
    
    
    public void update() {        

        if (controlManager.arriba) {
           if(!salta){
               setVelocidadY(-15);
               salta = true;
           }
        }
        if (controlManager.abajo) {
            setVelocidadY(3);
            salta = false;
        }
        if (controlManager.derecha) {
            setVelocidadX(3);
            haciaDelante = true;
        }
        if (controlManager.izquierda) {
            setVelocidadX(-3);
            haciaDelante = false;
        }


        pos();        
        
    }
    
    
    @Override
    public void mostrar(Graphics2D g2) {
        if(salta){
            if(haciaDelante){
                g2.drawImage(actualSprite[5], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
            } else {
                g2.drawImage(actualSprite[5], (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto(), null);
            }
        } else if(getVelocidadX() > 0){
            actualAnimacion.dibujarSprite(g2, (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
            haciaDelante = true;
        } else if(getVelocidadX() < 0){
            actualAnimacion.dibujarSprite(g2, (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto()); 
            
            haciaDelante = false;
        } else{
            g2.drawImage(actualSprite[0], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
            
        }
        aplicarGravedad();
        //mostrarBordes(g2);
    }
    
    public void colision(){
        for(int i = 0; i < objManager.getLista().size(); i++){
            Objeto_Juego temporal = objManager.getLista().get(i);
            
            if(temporal.getId() == Objeto.Bloque || temporal.getId() == Objeto.Tubo){
                if(getBounds().intersects(temporal.getBounds())){
                    setY(temporal.getY() - temporal.getAlto()*2);
                    setVelocidadY(0);
                    setVelocidadX(0);
                    salta = false;
                }
                
                if(getBordeTop().intersects(temporal.getBounds())){
                    setY(temporal.getY() + temporal.getAlto());
                    setVelocidadY(0);                   
                }
                
                if(getBordeDerecha().intersects(temporal.getBounds())){
                    setX(temporal.getX() - temporal.getAncho());                    
                    setVelocidadX(0);
                }
                
                if(getBordeIzquierda().intersects(temporal.getBounds())){
                    setX(temporal.getX() + temporal.getAncho());
                    setVelocidadX(0);
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX() + getAncho()/2 - getAncho()/4),
                            (int) (getY() + getAlto()/2),
                            (int) (getAncho()/2),
                            (int) (getAlto()/2)); 
    }
    
    public Rectangle getBordeTop(){
        return new Rectangle((int) (getX() + (getAncho()/2) - (getAncho()/4)),
                            (int) (getY()),
                            (int) (getAncho()/2),
                            (int) (getAlto()/2));               
    }
    
    public Rectangle getBordeDerecha(){
        return new Rectangle((int) (getX() + getAncho()/2 - 5),
                            (int) (getY() + 5),
                            5,
                            (int) (getAlto()/2) - 10); 
    }
    
    public Rectangle getBordeIzquierda(){
        return new Rectangle((int) (getX() + getAncho()/2),
                            (int) (getY() + 5),
                            5,
                            (int) (getAlto()/2) - 10); 
    }
    
    public void mostrarBordes(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor(Color.red);
        g2.draw(getBounds());
        g2.draw(getBordeDerecha());
        g2.draw(getBordeIzquierda());
        g2.draw(getBordeTop());
    }

}
