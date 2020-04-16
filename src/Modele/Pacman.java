/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author p1920164
 */
public class Pacman extends ModeleEntite{
    private int truescore,score, nb_vie, win_score;
    private Grille g;
    private Direction d, d0;
    private boolean isSuper;
    
    public Pacman(Grille g){
    	this.g=g;
        score=0;
        truescore=0;
        nb_vie=3;
        isSuper=false;
        win_score=20;
    }
    
    public void action(){
    	String resultat=g.test(this);
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
        
    }
    
    public boolean getEtat() {
    	return isSuper;
    }
}
