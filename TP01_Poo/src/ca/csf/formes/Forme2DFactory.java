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
	 * @return La forme demandée ou null
	 */
	ElementGraphique getForme(String p_Nom);

	/**
	 *  
	 * @param p_Nom
	 * @param p_X
	 * @param p_Y
	 * @return
	 */
	ElementGraphique getForme(String p_Nom, int p_X, int p_Y);
}
