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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author p1920164
 */
public class Pacman extends ModeleEntite implements Runnable{
    private int score, nb_vie, t;

    private Grille g;
    private Direction d, d0;
    private boolean isSuper;
    private Point spawn;
    
    public Pacman(Grille g){
    	this.g=g;
        score=0;
        nb_vie=3;
        isSuper=false;
        spawn=new Point(7,9);
        d=Direction.NULLE;
        d0=Direction.NULLE;
        t=200;
    }
    
    public Point getSpawn() {
		return spawn;
	}
    
    public void setDirection(Direction d) {
    	this.d=d;   	
    }
    
    public void run(){
    	String resultat;
    	 while(true) { 
    		 resultat=g.test();
    		 switch(resultat) {
	 	    	case "pacgomme" :
	 	    		g.Manger();
	 	    		score=score+100;
	 	    		break;
	 	    		
	 	    	case "superpacgomme" :
	 	    		g.Manger();
	 	    		score=score+200;
	 	            isSuper=true;
	 	    		break;
	 	    		
	 	    	case "manger" :
	 	    		score=score+400;
	 	    		break;
	 	    		
	 	    	case "contact" :
	 	    		nb_vie--;
	 		        if(nb_vie==0){
	 		        	System.out.println("DEFAITE");
	 		        }
	 	    		break;
	 	    	
	 	    	default :
	 	    		break;
	     	}
	         
	         if (g.possible(this,d)){
	             g.deplacer(this,d);
	             d0=d;
	             d=Direction.NULLE;
	         }
	         else if(g.possible(this,d0)){
	             g.deplacer(this,d0);
	         }
 			 setChanged();
 	        
 			 // notification de l'observer
 	         notifyObservers(); 
            
 			 try {  
 			    Thread.sleep(t); // pause 
 			 }catch (InterruptedException ex) {
               	Logger.getLogger(SimplePacMan.class.getName()).log(Level.SEVERE, null, ex);
 	         }   
         }

    }
    
    public int getScore(){
    	return score;  	 
    }
    
    public int getVies(){
    	return nb_vie;  	 
    }
    
    public boolean getEtat() {
    	return isSuper;
    }

	public void startSuper() {
		stopSuper();
		t=150;
		isSuper=true;
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(5000),
		        ae -> stopSuper()));
		timeline.play();
		
	}

	private void stopSuper() {
		t=200;
		isSuper=false;		
	}

	public void gagner() {
		System.out.println("GG");
		
	}
}
