package superMario;

public class SuperMario {

    public static void main(String[] args) {
        Reproductor reproductor = new Reproductor();
        
        reproductor.openFile("src/audios/Mario Bros - Log in Menu.mp3");
        reproductor.play();
        
        MarioLogin MarioLog = new MarioLogin();
        
        MarioLog.setVisible(true);
        MarioLog.setLocationRelativeTo(null);
        MarioLog.setResizable(false);
        
        
    }
}

