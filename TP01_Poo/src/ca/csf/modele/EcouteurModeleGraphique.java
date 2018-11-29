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
}
