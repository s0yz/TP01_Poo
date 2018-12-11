package ca.csf.formes;

/**
 * Fournit des méthodes pour obtenir des {@link ElementGraphique}.
 */
public interface UsineElementGraphique {
	
	/**
	 * Retourne une instance de la forme demandée.
	 * 
	 * @param p_Nom le nom de la forme à obtenir.
	 * @return la forme demandée ou null.
	 */
	ElementGraphique getForme(String p_Nom);

	/**
	 * Retourne une instance de la forme avec la position et les dimensions spécifiées.
	 * 
	 * @param p_Nom le nom de la forme à obtenir.
	 * @param p_X coordonné en x.
	 * @param p_Y coordonné en u.
	 * @param p_Largeur largeur.
	 * @param p_Hauteur hauteur.
	 * @return
	 */
	ElementGraphique getForme(String p_Nom, double p_X, double p_Y, double p_Largeur, double p_Hauteur);
}
