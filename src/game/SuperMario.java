package game;

import game.manager.GamePanel;
import javax.swing.JFrame;

public class SuperMario {

    public static void main(String[] args) {
        
        /* 
        //login
        Reproductor reproductor = new Reproductor();
        
        reproductor.openFile("src/audios/Mario Bros - Log in Menu.mp3");
        reproductor.play();
        
        MarioLogin MarioLog = new MarioLogin();
        
        MarioLog.setVisible(true);
        MarioLog.setLocationRelativeTo(null);
        MarioLog.setResizable(false);
        */
        
        //game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setResizable(false);
        window.setTitle("Papa pera la papaya");
        
        GamePanel game = new GamePanel();
        window.add(game);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        game.startGameThread();
        
    }
}

