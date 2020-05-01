/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Point;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1920164
 */
public class Pacman extends ModeleEntite implements Runnable{
    private int truescore,score, nb_vie, win_score;
    private Grille g;
    private Direction d, d0;
    private boolean isSuper;
    private Point spawn;
    
    public Pacman(Grille g){
    	this.g=g;
        score=0;
        truescore=0;
        nb_vie=3;
        isSuper=false;
        win_score=20;
        spawn=new Point(7,9);
        d=Direction.NULLE;
        d0=Direction.NULLE;
    }
    
    public Point getSpawn() {
		return spawn;
	}
    
    public void setDirection(Direction d) {
    	d0=this.d;
    	this.d=d;
    	
    }
    
    public void run(){
    	String resultat;
    	 while(true) { 
    		 resultat=g.test();
    		 switch(resultat) {
	 	    	case "pacgomme" :
	 	    		g.Manger(this);
	 	    		score=score+100;
	 	            truescore++;
	 	    		break;
	 	    		
	 	    	case "superpacgomme" :
	 	    		score=score+200;
	 	            truescore++;
	 	            isSuper=true;
	 	    		break;
	 	    		
	 	    	case "mangerfantome" :
	 	    		score=score+400;
	 	    		break;
	 	    		
	 	    	case "contact" :
	 	    		nb_vie--;
	 		        if(nb_vie==0){
	 		        	System.out.println("Défaite");
	 		        }
	 	    		break;
	 	    	
	 	    	default :
	 	    		break;
	     	}
	         if (truescore==win_score){
	             System.out.println("Vicotire");   
	         }
	         
	         if (g.possible(this,d)){
	             g.deplacer(this,d);
	         }
	         else if(g.possible(this,d0)){
	             g.deplacer(this,d0);
	         }
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
    	
    	
        
    }
    
    public void start() {
        new Thread(this).start();
}
    
    public boolean getEtat() {
    	return isSuper;
    }
}
