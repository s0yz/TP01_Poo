package ca.csf.modele;

import ca.csf.formes.ElementGraphique;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public interface ModeleElementGraphique extends Iterable<ElementGraphique> {
	
	/**
	 * Pour ajouter un élément.
	 * 
	 * @param p_Element l'élément à ajouter.
	 */
	void ajouter(ElementGraphique p_Element);

	/**
	 * Pour retirer un élément.
	 * 
	 * @param p_Element l'élément à retirer.
	 */
	void retirer(ElementGraphique p_Element);
	
	/**
	 * Pour ajouter un écouteur.
	 * 
	 * @param p_Ecouteur l'écouteur à ajouter.
	 */
	void ajouterEcouteur(EcouteurModeleGraphique p_Ecouteur);

	/**
	 * Pour retirer un écouteur.
	 * 
	 * @param p_Ecouteur l'écouteur à retirer.
	 */
	void removeEcouteur(EcouteurModeleGraphique p_Ecouteur);
}
