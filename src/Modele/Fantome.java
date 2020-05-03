/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1920164
 */
public class Fantome extends ModeleEntite implements Runnable{
    private Direction d_inv,d;
    private int x,y;
    private int t;
    private int num_fantome;
    private Grille g;
    private boolean vivant;
    private Point spawn=new Point();
    HashMap<Integer,Direction> d_possibles =new HashMap<Integer,Direction>();
    
    public Fantome(Grille g, int n){
    	this.g=g;
    	num_fantome=n;
    	vivant=true;
    	t=200;
    	switch(n) {
    		case 1 :
    			x=8;
    			y=9;
    			spawn.setLocation(x, y);
    			break;
    		case 2 :
    			x=9;
    			y=8;
    			spawn.setLocation(x,y);
    			break;
    		case 3 :
    			x=9;
    			y=9;
    			spawn.setLocation(x,y);
    			break;
    		case 4 :
    			x=9;
    			y=10;
    			spawn.setLocation(x,y);
    			break;
    		default : break;
    	}
    }
    
    public Point getSpawn() {
		return spawn;
	}

	public void run(){
        while(true) { 
        	d_possibles.clear();
        	d_inv=getInv(d);
        	d=Direction.NULLE;
            int chemin=0;
            int i=0;
            
            for(Direction d1 : Direction.values()){
              if(g.possible(this,d1) && d1!=d_inv) {
                  chemin++;
                  d_possibles.put(chemin,d1);
              }
            }
            double r=Math.random();
            if(chemin==1) {
            	d=d_possibles.get(chemin);
            }
            if(chemin==2) {
           		if(r<0.45) d=d_possibles.get(1);
           		else if(r>0.45 && r<0.90) d=d_possibles.get(2);
           		else d=d_inv;
           	}
           	if(chemin==3) {
           		if(r<0.3) d=d_possibles.get(1);
           		else if(r>0.3 && r<0.6) d=d_possibles.get(2);
           		else if(r>0.6 && r<0.9) d=d_possibles.get(3);        		
           		else d=d_inv;
            }
            g.deplacer(this,d);
			setChanged();
	        
			// notification de l'observer
	        notifyObservers(); 
           
			try {  
				Thread.sleep(t); // pause 
			}catch (InterruptedException ex) {
              	//Logger.getLogger(SimplePacMan.class.getName()).log(Level.SEVERE, null, ex);
	        }
			t=300;
        }
    	
    }    
    
	private Direction getInv(Direction d) {
		if(d==Direction.HAUT) return Direction.BAS;
		else if(d==Direction.BAS) return Direction.HAUT;
		else if(d==Direction.GAUCHE) return Direction.DROITE;
		else if(d==Direction.DROITE) return Direction.GAUCHE;
		else return Direction.NULLE;
	}


	public void kill() {
		g.setPosition(this,x,y);
		t=3000;
	}
	
}
