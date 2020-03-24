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
    
	public boolean estFranchissable() {
		return mur;
	}
}
