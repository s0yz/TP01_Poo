package ca.csf.modele;

import java.awt.Color;

import ca.csf.formes.ElementGraphique;

/**
 * Interface utilisée par un {@code EspaceTravail} pour en réaliser l'affichage
 * et définissant des méthodes pour manipuler une collection
 * d'{@code ElementGraphiqe}
 * 
 * L'espace de travail peut être configuré pour afficher les données de tout
 * modèle implémentant {@code ModeleElementGraphique}.
 */
public interface ModeleElementGraphique extends Iterable<ElementGraphique> {

	/**
	 * Ajoute un élément à la suite.
	 * 
	 * @param p_Element l'élément à ajouter.
	 */
	void ajouter(ElementGraphique p_Element);

	/**
	 * Ajoute les éléments à la suite.
	 * 
	 * @param p_Elements
	 */
	void ajouter(Iterable<ElementGraphique> p_Elements);

	/**
	 * Insère un élément à l'indice spécifié.
	 * 
	 * @param p_Indice  l'indice dminsertion.
	 * @param p_Element l'élément à insérer.
	 */
	void inserer(int p_Indice, ElementGraphique p_Element);

	/**
	 * Ecrase les formes du modèle par celles contenues dans l'iterable.
	 * 
	 * @param p_Elements
	 */
	void remplir(Iterable<ElementGraphique> p_Elements);

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
	 * Pour obtenir l'élément à l'indice spécifié.
	 * 
	 * @param p_Indice l'indice de l'élément désiré.
	 * @return l'élément à l'indice spécifié.
	 */
	public ElementGraphique get(int p_Indice);

	/**
	 * Retourne le dernier élément contenant le point spécifié.
	 * 
	 * @param p_x coodonnée en x devant être contenue par l'élément à selectionner.
	 * @param p_y coodonnée en y devant être contenue par l'élément à selectionner.
	 *            *
	 * @return l'élément selectionné.
	 */
	public ElementGraphique get(double p_x, double p_y);

	/**
	 * Pour obtenir le dernier élément.
	 * 
	 * @return le dernier élément.
	 */
	public ElementGraphique getDernier();

	/**
	 * Pour obtenir l'indice de l'élément spécifié.
	 * 
	 * @param p_Element l'élément dont l'indice sera retourné.
	 * @return l'indice de l'élément ou -1 s'il n'est pas trouvé.
	 */
	int getIndiceDe(ElementGraphique p_Element);

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
	 * Pour obtenir la largeur de l'arrière-plan.
	 * 
	 * @return la largeur de l'arrière-plan.
	 */
	double getLargeur();

	/**
	 * Pour obtenir la hauteur de l'arrière-plan.
	 * 
	 * @return la hauteur de l'arrière-plan.
	 */
	double getHauteur();

	/**
	 * Pour obtenir la couleur de l'arrière-plan.
	 * 
	 * @return la couleur de l'arrière-plan.
	 */
	Color getArrierePlan();

	/**
	 * Pour modifier les dimension de l'arrière plan.
	 * 
	 * @param p_Largeur la nouvelle largeur.
	 * @param p_Hauteur la nouvelle hauteur.
	 */
	void setDimension(double p_Largeur, double p_Hauteur);

	/**
	 * Pour modifier la couleur de l'arrière plan.
	 * 
	 * @param p_Color la nouvelle couleur.
	 */
	void setArrierePlan(Color p_Couleur);

	/**
	 * Pour obtenir le nombre d'éléments.
	 * 
	 * @return le nombre d'éléments.
	 */
	int getCompte();

	int getCompteEcouteur();
}
