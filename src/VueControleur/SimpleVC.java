/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueControleur;
    
import Modele.Fantome;
import Modele.Grille;
import Modele.ModeleEntite;
import Modele.ModeleEntite.Direction;
import Modele.Pacman;

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import com.sun.prism.paint.Color;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author p1923494
 */
public class SimpleVC extends Application {
	
	private HashMap<ModeleEntite, Point> positions;
    public final int SIZE_X = 21;
    public final int SIZE_Y = 19;
    
    private String direction = "";
	private Label lbl = new Label();
	private String str;


   
    
    @Override
    public void start(Stage primaryStage) {  
        Grille grille = new Grille();
        int[][] gr = grille.getmap(); 
        
        // initialisation du mod�le
        Pacman p =grille.getP();
        Thread t_p=new Thread(p);
        Fantome f1=grille.getF1();
        Thread t_f1=new Thread(f1);
        Fantome f2=grille.getF2();
        Thread t_f2=new Thread(f2);
        Fantome f3=grille.getF3();
        Thread t_f3=new Thread(f3);
        Fantome f4=grille.getF4();
        Thread t_f4=new Thread(f4);
        
        // cr�ation de la grille
        GridPane grid = new GridPane(); 
        
        // pr�paration des images
        Image imPM = new Image("Pacman.png");
        Image imPMHaut = new Image("pacmanHaut.png");
        Image imPMBas = new Image("pacmanBas.png");
        Image imPMDroite = new Image("pacmanDroite.png");
        Image imPMGauche = new Image("pacmanGauche.png");

        Image imVide = new Image("Vide.png");
        Image imPacGome = new Image("bean.png");
        Image imSpPacGome = new Image("pouvoir.png");
        Image imMur = new Image("mur.png");
        Image imPouvoir = new Image("pouvoir.png");
        Image imFantome1 = new Image("fantome_cyan.png");
        Image imFantome2 = new Image("fantome_orange.png");
        Image imFantome3 = new Image("fantome_rose.png");
        Image imFantome4 = new Image("fantome_rouge.png");
        Image imFantomePeur = new Image("FantomePeur.png");
    	Image imMurBase = new Image("murbase.png");

        
        // tableau permettant de r�cup�rer les cases graphiques lors du rafraichissement
        ImageView[][] tab = new ImageView[SIZE_X][SIZE_Y]; 
                
        // initialisation de la grille (sans image)
        for (int i = 0; i < SIZE_X; i++) { 
            for (int j = 0; j < SIZE_Y; j++) {
                ImageView img = new ImageView();
                tab[i][j] = img;
                grid.add(img, j, i);
            }        
        }
        
       
        
        
        Observer o =  new Observer() {// l'observer observe l'obervable (update est ex�cut� d�s notifyObservers() est appel� c�t� mod�le )

			@Override
            
            // rafraichissement graphique
            public void update(Observable o, Object arg) {

            	for (int i = 0; i < SIZE_X; i++) { 
                    for (int j = 0; j < SIZE_Y; j++) {
                        if (gr[i][j] == 0) { 
                            tab[i][j].setImage(imMur); 
                        }
                        else if (gr[i][j] == 1) { 
                            tab[i][j].setImage(imVide); 
                        }
                        else if(gr[i][j] == 2){
                            tab[i][j].setImage(imPacGome);                            
                        }
                        else if(gr[i][j] == 3){
                            tab[i][j].setImage(imSpPacGome);                            
                        }
                        /*else if(gr[i][j] == 5){
                            tab[i][j].setImage(imMurBase);                            
                        }*/
                        else {
                            tab[i][j].setImage(imVide);
                        } 
                    }
            	}
            	int x_pacman,y_pacman,x_f1,x_f2,x_f3,x_f4,y_f1,y_f2,y_f3,y_f4;
            	positions=grille.getMap_position();
            	x_pacman = (int) positions.get(p).getX();
            	y_pacman=(int) positions.get(p).getY();
            	x_f1=(int) positions.get(f1).getX();
            	x_f2=(int) positions.get(f2).getX();
            	x_f3=(int) positions.get(f3).getX();
            	x_f4=(int) positions.get(f4).getX();
            	y_f1=(int) positions.get(f1).getY();
            	y_f2=(int) positions.get(f2).getY();
            	y_f3=(int) positions.get(f3).getY();
            	y_f4=(int) positions.get(f4).getY();

            	//Affichage PacMan
            	switch(direction) {
            		case "HAUT":  
            			tab[x_pacman][y_pacman].setImage(imPMHaut);
            			break;
            		case "BAS":  
            			tab[x_pacman][y_pacman].setImage(imPMBas);
            			break;
            		case "DROITE":  
            			tab[x_pacman][y_pacman].setImage(imPMDroite);
            			break;
            		case "GAUCHE":  
            			tab[x_pacman][y_pacman].setImage(imPMGauche);
            			break;
            		default:
            			tab[x_pacman][y_pacman].setImage(imPMDroite);
            	}
            	
            	//Affichage fanbtome
            	if(p.getEtat()) {
            		tab[x_f1][y_f1].setImage(imFantomePeur);
	            	tab[x_f2][y_f2].setImage(imFantomePeur);
	            	tab[x_f3][y_f3].setImage(imFantomePeur);
	            	tab[x_f4][y_f4].setImage(imFantomePeur);
            	} else {
            		tab[x_f1][y_f1].setImage(imFantome1);
	            	tab[x_f2][y_f2].setImage(imFantome2);
	            	tab[x_f3][y_f3].setImage(imFantome3);
	            	tab[x_f4][y_f4].setImage(imFantome4);
            	}
            	
            	lbl.setText("score: "+" "+p.getScore());
            	lbl.setLayoutX(760);
            	lbl.setLayoutY(370);
            	
            	//System.out.println(lbl);
			}
	
        };
        

        p.addObserver(o);

        t_p.start();
        t_f1.start();
        t_f2.start();
        t_f3.start();
        t_f4.start();
        BorderPane root = new BorderPane();
        
        root.getChildren().add(grid);
    	lbl.setPrefHeight(60);
    	lbl.setAlignment(Pos.BASELINE_LEFT);
    	root.setBottom(lbl);
    	
        Scene scene = new Scene(root, 380, 470);
        
        primaryStage.setTitle("PacMan");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on �coute le clavier
            
			@Override
            public void handle(javafx.scene.input.KeyEvent event) {
            	KeyCode key=event.getCode();
            	switch(key) {
            		case UP :
            			direction = "HAUT";
            			p.setDirection(Direction.HAUT);
            			break;
            		case DOWN :
            			direction = "BAS";
            			p.setDirection(Direction.BAS);
            			break;
            		case LEFT :
            			direction = "GAUCHE";
            			p.setDirection(Direction.GAUCHE);
            			break;
            		case RIGHT :
            			direction = "DROITE";
            			p.setDirection(Direction.DROITE);
            			break;
            		default : break;
            	}
            }
        });
        
        grid.requestFocus();  
    }


  
}
