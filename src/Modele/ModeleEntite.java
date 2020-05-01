/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Point;
import java.util.Observable;

/**
 *
 * @author p1920164
 */
public class ModeleEntite extends Observable implements Runnable{
    public void run(){
    	
    }
    
    public enum Direction{
        HAUT,
        BAS,
        GAUCHE,
        DROITE,
        NULLE
    }
    
}
