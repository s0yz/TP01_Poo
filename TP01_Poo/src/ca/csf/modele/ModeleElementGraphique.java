package ca.csf.modele;

import java.awt.Color;

import ca.csf.formes.ElementGraphique;

/**
 * Interface définissant les méthodes que l'{@code EspaceTravail} vas utiliser
 * pour réaliser un affichage.
 * 
 * L'espace de travail peur-être configuré pour afficher les données de tout modèle
 * implémentant {@code ModeleElementGraphique}.
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
	 * 
	 * @param p_Indice
	 * @return
	 */
	public ElementGraphique get(int p_Indice);
	
	/**
	 * Retourne le dernier élément contenant le point spécifié.
	 * 
	 * @param p_x coodonnée en x devant être contenue par l'élément à selectionner.
	 * @param p_y coodonnée en y devant être contenue par l'élément à selectionner.
	 * @return l'élément selectionné.
	 */
	public ElementGraphique get(double p_x, double p_y);

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
	void retirerEcouteur(EcouteurModeleGraphique p_Ecouteur);

	/**
	 * Pour obtenir l'élément selectionné.
	 * 
	 * @return le selection.
	 */
	public ElementGraphique getSelection();

	/**
	 * 
	 * @return
	 */
	double getLargeur();

	/**
	 * 
	 * @return
	 */
	double getHauteur();

	/**
	 * 
	 * @return
	 */
	Color getArrierePlan();

	/**
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	void setDimension(double p_Largeur, double p_Hauteur);

	/**
	 * 
	 * @param p_Color
	 */
	void setArrierePlan(Color p_Couleur);
}
