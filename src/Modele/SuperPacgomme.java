/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author p1920164
 */
public class SuperPacgomme extends ModeleStatique{
    boolean boost = false;
    private boolean estMange;
    int timerboost = 0;
    
    int nbSupPacgommeMange = 0; 
    
    public SuperPacgomme() {
    	estMange=false;
    	super.mur=false;
    	super.libelle="superpacgomme";
    }

    public void estMange() {
    	estMange=true;
    	super.libelle="case";
    }
    
    public boolean getMange() {
    	return estMange;
    	
    }

    public void eatSupPacgome(){
        nbSupPacgommeMange++;
        boost = true;
        startTimer();
    }
    
    public int geteatSupPacgome(){ return nbSupPacgommeMange; }

    public boolean getboost(){ return boost;}
    
    public void startTimer(){
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(8),
        ae -> wakeup()));
    }

    private KeyFrame wakeup() {
        boost = false;
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
