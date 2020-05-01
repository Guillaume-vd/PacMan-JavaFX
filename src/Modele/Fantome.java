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
    private int num_fantome;
    private Grille g;
    private boolean vivant;
    private Point spawn;
    HashMap<Integer,Direction> d_possibles =new HashMap<Integer,Direction>();
    
    public Fantome(Grille g, int n){
    	this.g=g;
    	num_fantome=n;
    	vivant=true;
    	switch(n) {
    		case 1 :
    			spawn=new Point(8,9);
    			break;
    		case 2 :
    			spawn=new Point(9,9);
    			break;
    		case 3 :
    			spawn=new Point(9,8);
    			break;
    		case 4 :
    			spawn=new Point(9,10);
    			break;
    		default : break;
    	}
    }
    
    public Point getSpawn() {
		return spawn;
	}

	public void run(){
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
    
    public void action(){
    	d_possibles.clear();
    	d_inv=getInv(d);
        int chemin=0;
        int i=0;
        
        for(Direction d1 : Direction.values()){
          if(g.possible(this,d1) && d1!=d_inv) {
              chemin++;
              d_possibles.put(chemin,d1);
          }
        }
        if(vivant) {
        	double r=Math.random();
        	if(chemin==1) {
        		d=d_possibles.get(chemin);
        	}
        	if(chemin==2) {
        		if(r<0.45) d=d_possibles.get(1);
        		if(r>0.45 && r<0.90) d=d_possibles.get(2);
        		else d=d_inv;
        	}
        	if(chemin==3) {
        		if(r<0.3) d=d_possibles.get(1);
        		if(r>0.3 && r<0.6) d=d_possibles.get(2);
        		if(r>0.6 && r<0.9) d=d_possibles.get(3);
        		else d=d_inv;
        	}
        	
        }
        else {
        	d=goSpawn();
        }
        g.deplacer(this,d);
    }
    
    
	private Direction getInv(Direction d) {
		if(d==Direction.HAUT) return Direction.BAS;
		if(d==Direction.BAS) return Direction.HAUT;
		if(d==Direction.GAUCHE) return Direction.DROITE;
		else return Direction.GAUCHE;
	}

	private Direction goSpawn() {
		return d;
	}
}
