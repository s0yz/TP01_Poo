package ca.csf.formes;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public interface Forme2DFactory {
	
	/**
	 * Retourne une la forme demandée.
	 * 
	 * @param p_Nom le nom de la forme désirée.
	 * @return La forme demandée ou null
	 */
	ElementGraphique getForme(String p_Nom);
}
