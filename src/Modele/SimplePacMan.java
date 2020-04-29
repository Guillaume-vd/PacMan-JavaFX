/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fred
 */
public class SimplePacMan extends Observable implements Runnable {

    int x, y, sizeX, sizeY;
    
    Random r = new Random();
    
    public int map[][] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
				            {0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0},
				            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
				            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0},
				            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
				            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
				            {0, 0, 0, 0, 1, 0, 6, 6, 6, 2, 6, 6, 6, 0, 1, 0, 0, 0, 0},
				            {0, 0, 0, 0, 1, 0, 6, 0, 0, 6, 0, 0, 6, 0, 1, 0, 0, 0, 0},
				            {0, 1, 1, 1, 1, 6, 6, 0, 2, 2, 2, 0, 6, 6, 1, 1, 1, 1, 0},
				            {0, 0, 0, 0, 1, 0, 6, 0, 0, 0, 0, 0, 6, 0, 1, 0, 0, 0, 0},
				            {0, 0, 0, 0, 1, 0, 6, 6, 6, 6, 6, 6, 6, 0, 1, 0, 0, 0, 0},
				            {0, 0, 0, 0, 1, 0, 6, 0, 0, 0, 0, 0, 6, 0, 1, 0, 0, 0, 0},
				            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
				            {0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0},
				            {0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
				            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
				            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
				            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
				            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
				            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
    
    
    public SimplePacMan(int _sizeX, int _sizeY) {
        x = 0; y = 0;
        
        sizeX = _sizeX;
        sizeY = _sizeY;
    }
    
    
    @Override
    public void run() {
        // spm descent dans la grille à chaque pas de temps
        while(true) { 
           int deltaX = r.nextInt(2);
           
           if (x + deltaX > 0 && x + deltaX < sizeX) {
               x += deltaX;
           }
           
           int deltaY = r.nextInt(2);
           if (y + deltaY > 0 && y + deltaY < sizeX) {
               y += deltaY;
           } 
           
           //System.out.println(x + " - " + y);
           
           setChanged();
           // notification de l'observer
           notifyObservers(); 
           
            try {
                Thread.sleep(300); // pause
            } 
            catch (InterruptedException ex) {
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
    
    public void start() {
        new Thread(this).start();
    }
    
    public void initXY() {
        x = 0;
        y = 0;
    }
}
