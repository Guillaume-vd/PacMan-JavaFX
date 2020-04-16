/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author p1920164
 */
public class ModeleStatique extends Grille {
	public boolean mur;
	public boolean fantome;
	public String libelle;
    
	public boolean estFranchissable() {
		return mur;
	}
	
	public void setFantome() {
		fantome=true;
	}
	
	public void removeFantome() {
		fantome=false;
	}
	
	public boolean possedeFantome() {
		return fantome;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
}
