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
import Modele.SimplePacMan;

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author p1923494
 */
public class SimpleVC extends Application {
	
	private HashMap<ModeleEntite, Point> positions;
    public final int SIZE_X = 19;
    public final int SIZE_Y = 21;
    
    @Override
    public void start(Stage primaryStage) { 
        Grille grille = new Grille();
        int[][] gr = grille.getmap(); 
        // initialisation du modèle
        Pacman p =grille.getP();
        Fantome f1=grille.getF1();
        Fantome f2=grille.getF2();
        Fantome f3=grille.getF3();
        Fantome f4=grille.getF4();
        // création de la grille
        GridPane grid = new GridPane();  
        // préparation des images
        Image imPM = new Image("Pacman.png"); 
        Image imVide = new Image("Vide.png");
        Image imPacGome = new Image("bean.png");
        Image imSpPacGome = new Image("pouvoir.png");
        Image imMur = new Image("mur.png");
        Image imPouvoir = new Image("pouvoir.png");
        Image imFantome1 = new Image("fantome_cyan.png");
        Image imFantome2 = new Image("fantome_orange.png");
        Image imFantome3 = new Image("fantome_rose.png");
        Image imFantome4 = new Image("fantome_rouge.png");
        
        // tableau permettant de récupérer les cases graphiques lors du rafraichissement
        ImageView[][] tab = new ImageView[SIZE_Y][SIZE_X]; 

        // initialisation de la grille (sans image)
        for (int i = 0; i < SIZE_Y; i++) { 
            for (int j = 0; j < SIZE_X; j++) {
                ImageView img = new ImageView();
                tab[i][j] = img;
                grid.add(img, j, i);
            }
        }
        for (int i = 0; i < SIZE_Y; i++) { 
            for (int j = 0; j < SIZE_X; j++) {
                //System.out.println("i :"+i+", j: "+j);
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
                else {
                    tab[i][j].setImage(imVide);
                } 
            }
        }
        
       
        
        
        Observer o =  new Observer() {// l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )
            @Override
            
            public void update(Observable o, Object arg) {
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
            	tab[x_pacman][y_pacman].setImage(imPM);
            	tab[x_f1][y_f1].setImage(imFantome1);
            	tab[x_f1][y_f2].setImage(imFantome2);
            	tab[x_f1][y_f3].setImage(imFantome3);
            	tab[x_f1][y_f4].setImage(imFantome4);
                // rafraichissement graphique
                
            }
        };
        

        p.addObserver(o);
        p.start(); // on démarre spm
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 380, 470);
        
        primaryStage.setTitle("PacMan");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on écoute le clavier
            
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
            	KeyCode key=event.getCode();
            	switch(key) {
            		case UP :
            			p.setDirection(Direction.HAUT);
            			break;
            		case DOWN :
            			p.setDirection(Direction.BAS);
            			break;
            		case LEFT :
            			p.setDirection(Direction.GAUCHE);
            			break;
            		case RIGHT :
            			p.setDirection(Direction.DROITE);
            			break;
            		default : break;
            	}
            }
        });
        
        grid.requestFocus();  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
