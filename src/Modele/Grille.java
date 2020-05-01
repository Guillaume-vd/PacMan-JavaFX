/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Modele.ModeleEntite.Direction;

/**
 *
 * @author p1920124
 */
public class Grille {
    public Pacman p;
    int x_max, y_max;
    private HashMap<Point, ModeleStatique> hm= new HashMap<Point, ModeleStatique>();
    private HashMap<ModeleEntite,Point> map_position = new HashMap<ModeleEntite, Point>();
    private Fantome f1,f2,f3,f4;
    private int[][] map= 
    	   {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 2, 0, 0, 2, 0, 0, 2, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 2, 2, 0, 2, 2, 2, 0, 2, 2, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 2, 2, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0},
            {0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
};
    

	public Grille() {
    	ModeleStatique[] cases = new ModeleStatique[500];
    	Point[] pts=new Point[500];
    	int n = 0;
    	
    	for(int i=0;i<map.length;i++) {
    		for(int j=0;j<map[i].length;j++) {
    			pts[n]=new Point(i,j);
    			if(map[i][j]==0) {
    				cases[n]=new Mur();
    				hm.put(pts[n],cases[n]);
    			}
    			if(map[i][j]==1) {
    				cases[n]=new Case();
    				hm.put(pts[n],cases[n]);
    			}
    			if(map[i][j]==2) {
    				cases[n]=new Pacgomme();
    				hm.put(pts[n],cases[n]);
    			}
    			if(map[i][j]==3) {
    				cases[n]=new SuperPacgomme();
    				hm.put(pts[n],cases[n]);
    			}
    			n++;
    		}
    	}
    	p=new Pacman(this);
    	f1=new Fantome(this,1);
    	f2=new Fantome(this,2);
    	f3=new Fantome(this,3);
    	f4=new Fantome(this,4);
    	
    	map_position.put(p, p.getSpawn());
    	map_position.put(f1, f1.getSpawn());// regarder le point de spawn des fantomes
    	map_position.put(f2, f2.getSpawn());
    	map_position.put(f3, f3.getSpawn());
    	map_position.put(f4, f4.getSpawn());
    }


	public String test() {
    	if(map_position.get(p)==map_position.get(f1) ||map_position.get(p)==map_position.get(f2) 
    			|| map_position.get(p)==map_position.get(f3) || map_position.get(p)==map_position.get(f4)) {
    		return "contact";
    	}
    	else {
    		return hm.get(map_position.get(p)).getLibelle();
    	}
    	
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
	    		return false;
    	}
    	return hm.get(pos_futur).estFranchissable();
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

	public HashMap<ModeleEntite, Point> getMap_position() {
		return map_position;
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
   
	public int[][] getmap(){
		return this.map;
	}
	
	public Pacman getP() {
		return p;
	}

	public void setP(Pacman p) {
		this.p = p;
	}

	public Fantome getF1() {
		return f1;
	}

	public void setF1(Fantome f1) {
		this.f1 = f1;
	}

	public Fantome getF2() {
		return f2;
	}

	public void setF2(Fantome f2) {
		this.f2 = f2;
	}

	public Fantome getF3() {
		return f3;
	}

	public void setF3(Fantome f3) {
		this.f3 = f3;
	}

	public Fantome getF4() {
		return f4;
	}

	public void setF4(Fantome f4) {
		this.f4 = f4;
	}
}
