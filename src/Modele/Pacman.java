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
    private int score, nb_vie;
    private Direction d, d0;
    
    public Pacman(){
        score=0;
        nb_vie=3;
    }
    
    public void action(){
        if (g.possible(d)){
            g.deplacer(this,d);
        }
        else if(g.possible(d0)){
            g.deplacer(this,d0);
        }
        if (g.test(this)=="pacgomme"){
            score=score+100;
        }
        if (g.test(this)=="superpacgomme"){
            score=score+200;
        }
        if (g.perdu(this)){
            nb_vie--;
            if(nb_vie==0){
                System.out.println("Défaite");
            }
        }
        if (score==win_score){
            System.out.println("Vicotire");   
        }
        
    }
}
