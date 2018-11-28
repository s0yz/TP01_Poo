package ca.csf.modele;

import ca.csf.formes.ElementGraphique;

/**
 * 
 * 
 * @author
 */
public interface EcouteurModeleGraphique {
	/**
	 * 
	 */
	void reagirModifications();
	
	/**
	 * 
	 */
	void reagirNouvelElement(ElementGraphique p_Element);
}
