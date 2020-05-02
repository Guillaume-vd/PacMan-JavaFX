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
    int x_max=21;
    int y_max=19;
    int nb_pg;
    private HashMap<Point, ModeleStatique> hm= new HashMap<Point, ModeleStatique>();
    private HashMap<ModeleEntite,Point> map_position = new HashMap<ModeleEntite, Point>();
    private Fantome f1,f2,f3,f4;
    private int[][] map= 
    	   {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 3, 0},
            {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0},
            {0, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 1, 1, 1, 1, 1, 1, 1, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 1, 0, 0, 5, 0, 0, 1, 0, 2, 0, 0, 0, 0},
            {0, 2, 2, 2, 2, 2, 1, 0, 1, 1, 1, 0, 1, 2, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 1, 1, 1, 1, 1, 1, 1, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0},
            {0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 3, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 3, 0},
            {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
            {0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0},
            {0, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 0, 2, 2, 2, 2, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
    
    Point pt, pt_theorique;
    boolean franchissable;
    ModeleStatique ms;
    String s;
    
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
    				cases[n]=new Case(1);
    				hm.put(pts[n],cases[n]);
    			}
    			if(map[i][j]==2) {
    				cases[n]=new Case(2);
    				hm.put(pts[n],cases[n]);
    				nb_pg++;
    			}
    			if(map[i][j]==3) {
    				cases[n]=new Case(3);
    				hm.put(pts[n],cases[n]);
    				nb_pg++;
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
    	map_position.put(f1, f1.getSpawn());
    	map_position.put(f2, f2.getSpawn());
    	map_position.put(f3, f3.getSpawn());
    	map_position.put(f4, f4.getSpawn());
    }


	//si on as manger un phantome
	public String test() {
		pt=map_position.get(p);
    	if(pt.equals(map_position.get(f1))) {
    		if(p.getEtat()) {
    			f1.kill();
    			return "mange";
    		}
    		else {
    			return "contact";
    		}
    	}
    	else if(pt.equals(map_position.get(f2))) {
    		if(p.getEtat()) {
    			f2.kill();
    			return "mange";
    		}
    		else {
    			return "contact";
    		}    	
    		
		}
    	else if(pt.equals(map_position.get(f3))) {
    		if(p.getEtat()) {
    			f3.kill();
    			return "mange";
    		}
    		else {
    			return "contact";
    		}
		}
    	else if(pt.equals(map_position.get(f4))) {
    		if(p.getEtat()) {
    			f4.kill();
    			return "mange";
    		}
    		else {
    			return "contact";
    		}
		}
    	else {
    		return hm.get(map_position.get(p)).getLibelle();
    	}	
    }
    
	//Si déplacement possible
	synchronized boolean possible(ModeleEntite m,Direction d) {
    	pt_theorique=map_position.get(m);
    	Point pt_courant=new Point (map_position.get(m));
    	int x=(int) pt_theorique.getX();
    	int y=(int) pt_theorique.getY();
 
    	switch(d) {
	    	case HAUT:
	    		if(x==0) {
	    			pt_theorique.translate(x_max,y);
	    		}
	    		else {
	    			pt_theorique.translate(-1,0);
	    		}
	    		franchissable=hm.get(pt_theorique).estFranchissable();
	    		map_position.put(m, pt_courant);
	    		return franchissable;
	    		
	    	case BAS:
	    		if(x==x_max) {
	    			pt_theorique.move(0,y);
	    		}
	    		else {
	    			pt_theorique.translate(1,0);
	    		}
	    		franchissable=hm.get(pt_theorique).estFranchissable();
	    		map_position.put(m, pt_courant);
	    		return franchissable;
	    		
	    	case GAUCHE:
	    		if(y==0) {
	    			pt_theorique.move(x,y_max);
	    		}
	    		else {
	    			pt_theorique.translate(0,-1);
	    		}
	    		franchissable=hm.get(pt_theorique).estFranchissable();
	    		map_position.put(m, pt_courant);
	    		return franchissable;
	    		
	    	case DROITE:
	    		if(y==y_max) {
	    			pt_theorique.move(x,0);
	    		}
	    		else {
	    			pt_theorique.translate(0,1);
	    		}
	    		franchissable=hm.get(pt_theorique).estFranchissable();
	    		map_position.put(m, pt_courant);
	    		return franchissable;
	    		
	    	default:
	    		return false;
    	}
    	
    }
    
    synchronized void deplacer(ModeleEntite m, Direction d) {
    	pt=map_position.get(m);
    	switch (d) {
    		case HAUT :
    			if(pt.getX()!=0) {
	    			pt.setLocation(pt.getX()-1, pt.getY());
    			}
    			break;
    		case BAS :
    			if(pt.getX()!=x_max) {
	    			pt.setLocation(pt.getX()+1, pt.getY());
    			}
    			break;
    		case DROITE :
    			if(pt.getY()!=y_max) {
	    			pt.setLocation(pt.getX(), pt.getY()+1);
    			}
    			break;
    		case GAUCHE :
    			if(pt.getY()!=0) {
    				pt.setLocation(pt.getX(), pt.getY()-1);
    			}
    			break;
    		default : break;
    	}
    }

	public void Manger() {
		pt=map_position.get(p);
		ms=hm.get(pt);
		if(ms instanceof Case && ms.getLibelle()!="case") {
			if(ms.getLibelle()=="superpacgomme") {
				p.startSuper();
			}
			((Case) ms).vider();
			map[(int) pt.getX()][(int) pt.getY()]=1;
			nb_pg--;
			//System.out.println(nb_pg);
			if(nb_pg==0) {
				p.gagner();
			}
		}
	}
   
	public HashMap<ModeleEntite, Point> getMap_position() {
		return map_position;
	}
	
	public int[][] getmap(){
		return this.map;
	}
	
	public Pacman getP() {
		return p;
	}

	public Fantome getF1() {
		return f1;
	}

	public Fantome getF2() {
		return f2;
	}

	public Fantome getF3() {
		return f3;
	}

	public Fantome getF4() {
		return f4;
	}

	public void setPosition(Fantome f, int x, int y) {
		pt=map_position.get(f);
		pt.move(x, y);
	}
}
