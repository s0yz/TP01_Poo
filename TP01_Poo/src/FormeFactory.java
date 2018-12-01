
/**
 * @author Cedric Mariage
 *
 */
public interface FormeFactory {
	
	/**
	 * @return
	 */
	FormeFactory getInstance();
		
	void creerCarre(int p_Taille);
	
	void creerRectangle(int p_Largeur, int p_Hauteur);
	
	void creerRond(int p_Diametre);
	
	void creerEllipse(int p_Largeur, int p_Hauteur);
}
