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
public class Pacgomme extends ModeleStatique{
    
    int nbPacgommeMange = 0;
    //int nbPacgommeTotal = 200;
    
    public Pacgomme() {
    	super.mur = false;
    }
    
    public void eatPacgome(){
        nbPacgommeMange++;
    }
    
    public int geteatPacgome(){
        return nbPacgommeMange;
    }
}