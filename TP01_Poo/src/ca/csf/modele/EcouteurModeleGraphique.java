package ca.csf.modele;

import ca.csf.formes.ElementGraphique;

/**
 * Interface pour des ecouteurs de {@code ModeleElementGraphique}.
 * 
 * @author Cedric Mariage
 */
public interface EcouteurModeleGraphique {
	
	/**
	 * Invoqué par le modèle écoutée lors de changements majeurs.
	 */
	void reagirModifications();
	
	/**
	 * Invoqué par le modèle écoutée lors de changements mineurs.
	 * 
	 * @param p_Element Element ayant été modifié.
	 */
	void reagirModifications(ElementGraphique p_Element);
	
	/**
	 * Invoqué par le modèle lors d'un changement de taille.
	 */
	
	void reagirNouvelleTaille(Integer p_Hauteur, Integer p_Largeur);
	
	/**
	 * Invoqué par le modèe lors d'un changement de couleur de fond.
	 */
	
	void reagirNouvelleCouleurDeFond(Color p_Couleur);
}
