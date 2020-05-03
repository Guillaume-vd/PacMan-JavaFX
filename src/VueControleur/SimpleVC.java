/*
 * To change this license header, choose License Headers in Project Properties.
    private String direction = "";

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

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private String sc = "score :";
    private String nbVie = "nombre de vie :";
    
    @Override
    public void start(Stage primaryStage) {  
        Grille grille = new Grille();
        int[][] gr = grille.getmap();

        HBox hboxvies = new HBox();
        
        // initialisation du modèle
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
        
        // création de la grille
        GridPane grid = new GridPane(); 
        
        // préparation des images
        Image imPM = new Image("Pacman.png");
        Image imPMHaut = new Image("pacmanHaut.png");
        Image imPMBas = new Image("pacmanBas.png");
        Image imPMDroite = new Image("pacmanDroite.png");
        Image imPMGauche = new Image("pacmanGauche.png");

        Image imVide = new Image("Vide.png");
        Image imPacGome = new Image("bean.png");
        Image imSpPacGome = new Image("pouvoir.png");
        Image imMur = new Image("mur.png");
        Image imFantome1 = new Image("fantome_cyan.png");
        Image imFantome2 = new Image("fantome_orange.png");
        Image imFantome3 = new Image("fantome_rose.png");
        Image imFantome4 = new Image("fantome_rouge.png");
        Image imFantomePeur = new Image("FantomePeur.png");
    	Image imMurBase = new Image("murbase.png");

        ImageView imvVie1 = new ImageView();
        ImageView imvVie2 = new ImageView();
        ImageView imvVie3 = new ImageView();



        // tableau permettant de récupérer les cases graphiques lors du rafraichissement
        ImageView[][] tab = new ImageView[SIZE_X][SIZE_Y]; 
        
        Text score = new Text();
        Text scoreT = new Text(sc);

        // initialisation de la grille (sans image)
        for (int i = 0; i < SIZE_X; i++) { 
            for (int j = 0; j < SIZE_Y; j++) {
                ImageView img = new ImageView();
                tab[i][j] = img;
                grid.add(img, j, i);
            }        
        }

        Observer o =  new Observer() {// l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )

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
            	
            	//Affichage fantome
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
            	score.setText(p.getScore());

                //Affichage Vie
                switch(p.getVies()) {
                    case 1:
                        imvVie3.setImage(imVide);
                        imvVie2.setImage(imVide);
                        imvVie1.setImage(imPM);
                        break;
                    case 2:
                        imvVie3.setImage(imVide);
                        imvVie2.setImage(imPM);
                        imvVie1.setImage(imPM);
                        break;
                    case 3:
                        imvVie3.setImage(imPM);
                        imvVie2.setImage(imPM);
                        imvVie1.setImage(imPM);
                        break;
                    default:
                        tab[x_pacman][y_pacman].setImage(imPMDroite);
                }

            }
        };
        

        p.addObserver(o);

        t_p.start();
        t_f1.start();
        t_f2.start();
        t_f3.start();
        t_f4.start();
        StackPane root = new StackPane();

        //Position par rapport au centre de l'image
        score.setTranslateX(-130);
        score.setTranslateY(200);
        root.getChildren().add(score);

        scoreT.setTranslateX(-163);
        scoreT.setTranslateY(200);
        root.getChildren().add(scoreT);

        //Gestion de la vie
        imvVie1.setTranslateX(150);
        imvVie1.setTranslateY(200);
        imvVie2.setTranslateX(130);
        imvVie2.setTranslateY(200);
        imvVie3.setTranslateX(110);
        imvVie3.setTranslateY(200);
        root.getChildren().add(imvVie1);
        root.getChildren().add(imvVie2);
        root.getChildren().add(imvVie3);

        //Ajout de la grille
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
            			direction = "HAUT";
            			break;
            		case DOWN :
            			p.setDirection(Direction.BAS);
                        direction = "BAS";
            			break;
            		case LEFT :
            			p.setDirection(Direction.GAUCHE);
                        direction = "GAUCHE";
            			break;
            		case RIGHT :
            			p.setDirection(Direction.DROITE);
                        direction = "DROITE";
                        break;
            		default : break;
            	}

            }
        });

        grid.requestFocus();
    }

}