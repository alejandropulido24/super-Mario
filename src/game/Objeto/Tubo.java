package game.Objeto;

import game.Graficos.Textura;
import game.manager.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tubo extends Objeto_Juego{
    
    private Textura textura;
    private int index;
    private BufferedImage[] sprite;
    
    public boolean puedeEntrar;
    
    public Tubo(float X, float Y, float ancho, float alto, int index, int escala, boolean puedeEntrar, GamePanel gamePanel) {
        super(X, Y, Objeto.Tubo, ancho, alto, escala);
        
        textura = gamePanel.getTextura();
        this.index = index;
        sprite = textura.getTubo1();
        
        this.puedeEntrar = puedeEntrar;
    }

    @Override
    public void pos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mostrar(Graphics2D g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
    
}