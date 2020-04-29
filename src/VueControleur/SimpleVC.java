/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VueControleur;
    
import Modele.Grille;
import Modele.SimplePacMan;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author frederic.armetta
 */
public class SimpleVC extends Application {
    
    public final int SIZE_X = 19;
    public final int SIZE_Y = 21;
    
    @Override
    public void start(Stage primaryStage) {
        // initialisation du modÃ¨le
        SimplePacMan spm = new SimplePacMan(SIZE_X, SIZE_Y); 
        Grille grille = new Grille();
        int[][] gr = grille.getmap(); 
        // création de la grille
        GridPane grid = new GridPane();  
        
        // prÃ©paration des images
        Image imPM = new Image("Pacman.png"); 
        Image imVide = new Image("Vide.png");
        Image imPG = new Image("bean.png");
        Image imMur = new Image("mur.png");
        Image imSupPG = new Image("pouvoir.png");
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
        
        
        Observer o =  new Observer() {// l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )
            @Override
            public void update(Observable o, Object arg) {
                // rafraichissement graphique
                for (int i = 0; i < SIZE_X; i++) { 
                    for (int j = 0; j < SIZE_Y; j++) {
                        if (gr[i][j] == 0) { 
                            tab[j][i].setImage(imMur); 
                        }
                        else if (gr[i][j] == 1) { 
                            tab[j][i].setImage(imVide); 
                        }
                        else if(gr[i][j] == 2){
                            tab[j][i].setImage(imPG);                            
                        }
                        else if(gr[i][j] == 3){
                            tab[j][i].setImage(imSupPG);                            
                        }
                        else {
                            tab[j][i].setImage(imVide);
                        } 
                    }
                }
            }
        };
        

        spm.addObserver(o);
        spm.start(); // on dÃ©marre spm
        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on Ã©coute le clavier
            
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                // si on clique sur shift, on remet spm en haut Ã  gauche
                 if (event.isShiftDown()) {
                    spm.initXY(); 
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
