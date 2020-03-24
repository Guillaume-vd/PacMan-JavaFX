/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Point;
import java.util.HashMap;

import Modele.ModeleEntite.Direction;

/**
 *
 * @author p1920164
 */
public class Grille {
    public Pacman p;
    private HashMap<Point, ModeleStatique> hm;
    private HashMap<ModeleEntite,Point> map_position;
    private Point spawn;
    private Fantome f1,f2,f3,f4;
    private int[][] map;
    
    public Grille() {
    	for(int i=0;i<map.length;i++) {
    		for(int j=0;j<map[i].length;i++) {
    			Point pt=new Point(i,j);
    			if(map[i][j]==0) {
    				ModeleStatique mur=new Mur();
    				hm.put(pt,mur);
    			}
    			if(map[i][j]==1) {
    				ModeleStatique couloir=new Case();
    				hm.put(pt,couloir);
    			}
    			if(map[i][j]==2) {
    				ModeleStatique pg=new Pacgomme();
    				hm.put(pt,pg);
    			}
    			if(map[i][j]==3) {
    				ModeleStatique spg=new SuperPacgomme();
    				hm.put(pt,spg);
    			}
    		}
    	}
    	p=new Pacman();
    	f1=new Fantome();
    	f2=new Fantome();
    	f3=new Fantome();
    	f4=new Fantome();
    	
    	map_position.put(p, spawn);
    	map_position.put(f1, spawn);// regarder le point de spawn des fantomes
    	map_position.put(f2, spawn);
    	map_position.put(f3, spawn);
    	map_position.put(f4, spawn);
    }
    
    public String test(Pacman p) {
    	Point position=map_position.get(p);
    	ModeleStatique case_position=hm.get(position);
    	switch(case_position) {
    		case 
    	}
    }
    
    public boolean possible(Direction d) {
    	return false;
    }
    
    public void deplacer(ModeleEntite m, Direction d) {
    	Point pt=map_position.get(m);
    	switch (d) {
    		case HAUT :
    			pt.setLocation(pt.getX(), pt.getY()-1);
    			map_position.put(m,pt);
    		case BAS :
    			pt.setLocation(pt.getX(), pt.getY()+1);
    			map_position.put(m,pt);
    		case DROITE :
    			pt.setLocation(pt.getX()+1, pt.getY());
    			map_position.put(m,pt);
    		case GAUCHE :
    			pt.setLocation(pt.getX()-1, pt.getY());
    			map_position.put(m,pt);
    		default : break;
    	}
    }
    
}
