/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.net.URL;
import java.awt.Point;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


/**
 *
 * @author p1920164
 */
public class Pacman extends ModeleEntite implements Runnable{
    private int score, nb_vie, t;

    private Grille g;
    private Direction d, d0;
    private boolean isSuper;
    private Point spawn;
    
    //AudioClip pg = new AudioClip(Paths.get("Audio/PelletEat1.wav").toUri().toString());
    //private AudioClip pg = new AudioClip(getClass().getResource("PelletEat1.wav").toString());
    
    public Pacman(Grille g){
    	this.g=g;
        score=0;
        nb_vie=3;
        isSuper=false;
        spawn=new Point(7,9);
        d=Direction.NULLE;
        d0=Direction.NULLE;
        t=200;
    }
    
    public Point getSpawn() {
		return spawn;
	}
    
    public void setDirection(Direction d) {
    	this.d = d;   	
    }
    
    public Direction getDirection() {
    	return this.d;
    } 
    
    public void run(){
    	String resultat;
    	 while(true) { 
    		 resultat=g.test();
    		 switch(resultat) {
	 	    	case "pacgomme" :
	 	    		g.Manger();
	 	    		score=score+100;
	 	    		//pg.play();
	 	    		break;
	 	    		
	 	    	case "superpacgomme" :
	 	    		g.Manger();
	 	    		score=score+200;
	 	            isSuper=true;
	 	    		break;
	 	    		
	 	    	case "manger" :
	 	    		score=score+400;
	 	    		break;
	 	    		
	 	    	case "contact" :
	 	    		nb_vie--;
	 		        if(nb_vie==0){
	 		        	System.out.println("DEFAITE");
	 		        }
	 	    		break;
	 	    	
	 	    	default :
	 	    		break;
	     	}
	         
	         if (g.possible(this,d)){
	             g.deplacer(this,d);
	             d0=d;
	             d=Direction.NULLE;
	         }
	         else if(g.possible(this,d0)){
	             g.deplacer(this,d0);
	         }
 			 setChanged();
 	        
 			 // notification de l'observer
 	         notifyObservers(); 
            
 			 try {  
 			    Thread.sleep(t); // pause 
 			 }catch (InterruptedException ex) {
               	//Logger.getLogger(SimplePacMan.class.getName()).log(Level.SEVERE, null, ex);
 	         }   
         }

    }
    
    public String getScore(){
    	return String.valueOf(score);  	 
    }
    
    public int getVies(){
    	return nb_vie;  	 
    }
    
    public boolean getEtat() {
    	return isSuper;
    }

	public void startSuper() {
		stopSuper();
		t=150;
		isSuper=true;
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(5000),
		        ae -> stopSuper()));
		timeline.play();
	}

	private void stopSuper() {
		t=200;
		isSuper=false;		
	}

	public void gagner() {
		System.out.println("GG");
		
	}
}
