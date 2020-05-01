package Modele;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1923494
 */

public class SimplePacMan extends Observable implements Runnable {
  
int x, y;
Random r = new Random();

	public SimplePacMan() {
	    x = 0; y = 0;   
	}
    
    
    

	@Override
	public void run() {
        // spm descent dans la grille à chaque pas de temps
        while(true) { 
           
			//System.out.println(x + " - " + y);
			setChanged();
	        
			// notification de l'observer
	        notifyObservers(); 
           
			try {  
				Thread.sleep(300); // pause 
			}catch (InterruptedException ex) {
              	Logger.getLogger(SimplePacMan.class.getName()).log(Level.SEVERE, null, ex);
	        }   
        }
	}
    

	public int getX() { 
		return x; 
	}
	    
	
	public int getY() {      
		return y;
	}

	public void initXY() { 
		x = 0;        
		y = 0;   
	}
	
	public void start() {
	        new Thread(this).start();
	}


}
