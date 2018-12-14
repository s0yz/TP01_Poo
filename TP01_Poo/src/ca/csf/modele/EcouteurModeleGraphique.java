package ca.csf.modele;

import ca.csf.formes.ElementGraphique;

/**
 * Interface pour des ecouteurs de {@code ModeleElementGraphique}.
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
	void reagirNouvelleTaille();
	
	/**
	 * Invoqué par le {@code ModeleElementGraphique} lors d'un changement de couleur de fond.
	 */	
	void reagirNouvelleCouleurDeFond();
}
