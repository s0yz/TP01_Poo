package ca.csf.modele;

import java.awt.Color;

import ca.csf.formes.ElementGraphique;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public interface ModeleElementGraphique extends Iterable<ElementGraphique> {
	
	/**
	 * Ajoute un élément.
	 * 
	 * @param p_Element l'élément à ajouter.
	 */
	void ajouter(ElementGraphique p_Element);
	
	/**
	 * Ajoute les formes.
	 * 
	 * @param p_Elements
	 */
	void ajouter(Iterable<ElementGraphique> p_Elements);

	/**
	 * Retire un élément.
	 * 
	 * @param p_Element l'élément à retirer.
	 */
	void retirer(ElementGraphique p_Element);
	
	/**
	 * Retire toute les formes.
	 */
	void vider();
	
	/**
	 * Ecrase les formes du modèle par celles contenues dans l'iterable.
	 * 
	 * @param p_Elements
	 */
	void remplir(Iterable<ElementGraphique> p_Elements);

	/**
	 * Ajoute un écouteur.
	 * 
	 * @param p_Ecouteur l'écouteur à ajouter.
	 */
	void ajouterEcouteur(EcouteurModeleGraphique p_Ecouteur);

	/**
	 * Retire un écouteur.
	 * 
	 * @param p_Ecouteur l'écouteur à retirer.
	 */
	void removeEcouteur(EcouteurModeleGraphique p_Ecouteur);
	
	/**
	 * 
	 * @param p_Largeur
	 */
	void setLargeur(int p_Largeur);
	
	/**
	 * 
	 * @param p_Hauteur
	 */
	void setHauteur(int p_Hauteur);
	
	/**
	 * 
	 * @param p_Color
	 */
	void setCouleurArrierePlan(Color p_Color);
	
	/**
	 * 
	 * @return
	 */
	int getLargeur();
	
	/**
	 * 
	 * @return
	 */
	int getHauteur();
	
	/**
	 * 
	 * @return
	 */
	Color getCouleurArrierePlan();
}
