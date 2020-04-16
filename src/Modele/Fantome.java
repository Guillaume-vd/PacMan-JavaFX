/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author p1920164
 */
public class Fantome extends ModeleEntite{
    private Direction d_inv,d;
    private int num_fantome;
    private Grille g;
    private boolean vivant;
    public Fantome(Grille g, int n){
    	this.g=g;
    	num_fantome=n;
    	vivant=true;
    }
    
    public void action(){
    	d_inv=getInv(d);
        int chemin=0;
        int i=0;
        HashMap<Integer,Direction> tab =new HashMap<Integer,Direction>();
        for(Direction d1 : Direction.values()){
          if(g.possible(this,d1) && d1!=d_inv) {
              chemin++;
              tab.put(chemin,d1);
          }
        }
        if(vivant) {
        	double r=Math.random();
        	if(chemin==1) {
        		d=tab.get(chemin);
        	}
        	if(chemin==3) {
        		if(r<0.45) d=tab.get(1);
        		if(r>0.45 && r<0.90) d=tab.get(2);
        		else d=d_inv;
        	}
        	if(chemin==4) {
        		if(r<0.3) d=tab.get(1);
        		if(r>0.3 && r<0.6) d=tab.get(2);
        		if(r>0.6 && r<0.9) d=tab.get(3);
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
