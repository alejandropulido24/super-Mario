package game.manager;

import game.Entidades.Player;
import game.Graficos.Camara;
import game.Graficos.Textura;
import game.Objeto.Bloque;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    public final int tamanioBaseCasilla = 16;
    public final int escala = 2;
    
    public final int tamanioCasilla = tamanioBaseCasilla * escala;
    
    public final int maxCasillasHorizontales = 28;
    public final int maxCasillasVerticales = 24;
    public final int FPS = 60;
    
    public final int anchoMaximo = tamanioCasilla * maxCasillasHorizontales; //896
    public final int altoMaximo = tamanioCasilla * maxCasillasVerticales;   //768
    
    public final int anchoCamara = anchoMaximo;
    public final int altoCamara = altoMaximo;
    public final int movimientoCamara = tamanioBaseCasilla*3;
    
    private Player mario;
    
    Camara cam;
    Textura textura;
    Thread gameThread;
    ObjetoManager objetoManager;
    KeyManager controlManager = new KeyManager();
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(anchoMaximo, altoMaximo));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        
        this.addKeyListener(controlManager);
        this.setFocusable(true);
        
        textura = new Textura();
        objetoManager = new ObjetoManager();
        cam = new Camara(0, movimientoCamara, this);
        
        
        mario = new Player(this, controlManager, objetoManager, 50, 50, 2);
        
        //Temporal
        for(int i =0; i<30; i++){
            objetoManager.agregarObj(new Bloque(i*32, 32*10, 32, 32,0, 1, this));
        }
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);        
        gameThread.start();
    }
    
    @Override
    public void run(){
        double drawIntervalo = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime)/drawIntervalo;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void enlazarObjeto(){
        objetoManager.enlazar();
    }
    
    public void update(){
        mario.update();
        cam.pos(mario);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.translate(cam.getX(), cam.getY());
        mario.mostrar(g2);
        objetoManager.mostrar(g2);
        g2.translate(-cam.getX(), -cam.getY());
        
        
        g2.dispose();
    }
    
    public Textura getTextura(){
        return textura;
    }
}
