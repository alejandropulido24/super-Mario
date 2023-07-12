package game.manager;

import game.Graficos.CargarBufferedImage;
import java.awt.image.BufferedImage;

public class NivelManager {
    
    private CargarBufferedImage loader;
    private BufferedImage texturaNivel;
    private ObjetoManager manager;    
    
    public NivelManager(ObjetoManager manager){
        this.manager = manager;
        loader = new CargarBufferedImage();
    }
    
    
    
    public void setNivel(String texturaNivel){
        this.texturaNivel = loader.cargarImagen(texturaNivel);
        
        int ancho = this.texturaNivel.getWidth();
        int alto = this.texturaNivel.getHeight();
        
        for(int i=0; i<alto; i++){
            for(int j=0; j<ancho; j++){
                int pixel = this.texturaNivel.getRGB(i, j);
            }
        }
    }
}
