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
    int x_max, y_max;
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
    	p=new Pacman(this);
    	f1=new Fantome(this,1);
    	f2=new Fantome(this,2);
    	f3=new Fantome(this,3);
    	f4=new Fantome(this,4);
    	
    	map_position.put(p, spawn);
    	map_position.put(f1, spawn);// regarder le point de spawn des fantomes
    	map_position.put(f2, spawn);
    	map_position.put(f3, spawn);
    	map_position.put(f4, spawn);
    }
    
    public String test(Pacman p) {
    	String test;
    	Point position=map_position.get(p);
    	ModeleStatique ms;
    	ms=hm.get(position);
    	if(ms.possedeFantome()) {
    		test="contact";
    	}
    	else {
    		test=ms.getLibelle();
    	}
    	return test;
    	
    }
    
    public boolean possible(ModeleEntite m,Direction d) {
    	boolean possible=false;
    	ModeleStatique ms;
    	Point position=map_position.get(m);
    	int x=(int) position.getX();
    	int y=(int) position.getY();
    	Point pos_futur=position;
    	switch(d) {
    	case HAUT:
    		if(y==0) {
    			pos_futur.translate(x,y_max);
    		}
    		else {
    			pos_futur.translate(0,-1);
    		}
    		break;
    		
    	case BAS:
    		if(y==y_max) {
    			pos_futur.move(x,0);
    		}
    		else {
    			pos_futur.translate(0,1);
    		}
    		break;
    		
    	case GAUCHE:
    		if(y==y_max) {
    			pos_futur.move(x_max,y);
    		}
    		else {
    			pos_futur.translate(-1,0);
    		}
    		break;
    		
    	case DROITE:
    		if(x==x_max) {
    			pos_futur.move(0,y);
    		}
    		else {
    			pos_futur.translate(1,0);
    		}
    		break;
    		
    	default:
    		break;
    	}
    	ms=hm.get(pos_futur);
		possible=ms.estFranchissable();
    	return possible;
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

	public void Manger(Pacman pacman) {
		Point position=map_position.get(pacman);
		ModeleStatique ms=hm.get(position);
		if(ms instanceof Pacgomme) {
			((Pacgomme) ms).estMange();
		}
		else if(ms instanceof SuperPacgomme) {
			((SuperPacgomme) ms).estMange();
		}
		
	}
    
}
