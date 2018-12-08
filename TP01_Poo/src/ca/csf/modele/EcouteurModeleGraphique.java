package ca.csf.modele;

import java.awt.Color;

import ca.csf.formes.ElementGraphique;

/**
 * Interface pour des ecouteurs de {@code ModeleElementGraphique}.
 * 
 * @author Cedric Mariage
 */
public interface EcouteurModeleGraphique {
	
	/**
	 * Invoqué par le {@code ModeleElementGraphique} lors de changements majeurs.
	 */
	void reagirModifications();
	
	/**
	 * Invoqué par le {@code ModeleElementGraphique} écouté lors de changements mineurs.
	 * 
	 * @param p_Element Element ayant été modifié.
	 */
	void reagirModifications(ElementGraphique p_Element);
	
	/**
	 * Invoqué par le {@code ModeleElementGraphique} lors d'un changement de taille.
	 */	
	void reagirNouvelleTaille(double p_Hauteur, double p_Largeur);
	
	/**
	 * Invoqué par le {@code ModeleElementGraphique} lors d'un changement de couleur de fond.
	 */	
	void reagirNouvelleCouleurDeFond(Color p_Couleur);
}
