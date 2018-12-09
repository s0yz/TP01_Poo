package ca.csf.formes;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public interface Forme2DFactory {
	
	/**
	 * Retourne une instance de la forme demandée.
	 * 
	 * @param p_Nom le nom de la forme désirée.
	 * @return la forme demandée ou null
	 */
	ElementGraphique getForme(String p_Nom);

	/**
	 *  
	 * @param p_Nom
	 * @param p_X
	 * @param p_Y
	 * @return
	 */
	ElementGraphique getForme(String p_Nom, double p_X, double p_Y);

	/**
	 * @param p_Nom
	 * @param p_X
	 * @param p_Y
	 * @param p_Largeur
	 * @param p_Hauteur
	 * @return
	 */
	ElementGraphique getForme(String p_Nom, double p_X, double p_Y, double p_Largeur, double p_Hauteur);
}
