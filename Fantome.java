/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.HashMap;

/**
 *
 * @author p1920164
 */
public class Fantome extends ModeleEntite{
    public Direction d0;
    public Fantome(){
        
    }
    
    public void action(){
        int chemin=0;
        int i=0;
        double r;
        HashMap<Direction,String> tab =new HashMap<Direction,String>();
        for(Direction d : Direction.values()){
          if(g.possible(d)) {
              chemins++;
              tab.add(d);
          }
        }
        
        g.deplacer(this,d);
    }
}
