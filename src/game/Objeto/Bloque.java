package game.Objeto;

import game.Graficos.Textura;
import game.manager.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bloque extends Objeto_Juego{
    
    private Textura textura;

    private int index;
    private BufferedImage[] sprite;
    
    public Bloque(float X, float Y, float ancho, float alto, int index, int escala, GamePanel game) {
        super(X, Y, Objeto.Bloque, ancho, alto, escala);
        this.index = index;
        textura = game.getTextura();
        sprite = textura.getCasilla1();
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
