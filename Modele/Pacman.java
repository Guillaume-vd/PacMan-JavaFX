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
    
    public Pacman(){
        score=0;
        truescore=0;
        nb_vie=3;
        isSuper=false;
        win_score=20;
    }
    
    public void action(){
        if (g.test(this)=="pacgomme"){
            score=score+100;
            truescore++;
        }
        if (g.test(this)=="superpacgomme"){
            score=score+200;
            truescore++;
            isSuper=true;
        }
        
        if (g.test(this)=="mangerfantome"){
            score=score+400;
        }
        if (g.test(this)=="contact") {
        	nb_vie--;
	        if(nb_vie==0){
	        	System.out.println("Défaite");
	      }
        }
        if (truescore==win_score){
            System.out.println("Vicotire");   
        }
        
        if (g.possible(d)){
            g.deplacer(this,d);
        }
        else if(g.possible(d0)){
            g.deplacer(this,d0);
        }
        
    }
    
    public boolean getEtat() {
    	return isSuper;
    }
}
