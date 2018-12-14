package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * L'interface ElementGraphique fournit des opérations pour manipuler des formes
 * ou autres éléments vivant dans un plan à 2 dimensions.
 */
public interface ElementGraphique {

	/**
	 * Pour dessiner l'élément.
	 * 
	 * @param p_Graphic
	 */
	void dessiner(Graphics2D p_Graphic);

	/**
	 * Pour obtenir la coordonne en x
	 * 
	 * @return la coordonne en x.
	 */
	double getX();

	/**
	 * Pour obtenir la coordonne en y.
	 * 
	 * @return la coordonne en y.
	 */
	double getY();

	/**
	 * Pour obtenir la largeur.
	 * 
	 * @return la largeur.
	 */
	double getLargeur();

	/**
	 * Pour obtenir la hauteur.
	 * 
	 * @return la hauteur.
	 */
	double getHauteur();

	/**
	 * Pour obtenir la couleur de remplissage.
	 * 
	 * @return la couleur de remplissage ou null.
	 */
	Color getCouleur();

	/**
	 * Pour obtenir la largeur du trait.
	 * 
	 * @return l'épaisseur du trait.
	 */
	int getLargeurTrait();

	/**
	 * Pour obtenir la couleur du trait ou null.
	 * 
	 * @return la couleur du trait ou null.
	 */
	Color getCouleurTrait();

	/**
	 * Pour vérifier si l'élément contient le point.
	 * 
	 * @param p_X la coordonnée du point en x.
	 * @param p_Y la coordonnée du point en y.
	 * @return vrai si l'élément contient le point.
	 */
	boolean contient(double p_X, double p_Y);

	/**
	 * @return le nom de l'element ou de la forme représentée.
	 */
	String getNom();

	/**
	 * Pour modifier la position.
	 * 
	 * @param p_X la nouvelle coordonnée en x.
	 * @param p_Y la nouvelle coordonnée en y.
	 */
	void setPosition(double p_X, double p_Y);
	
	/**
	 * Pour déplacer la forme.
	 * 
	 * @param p_X déplacement en x.
	 * @param p_Y déplacement en y.
	 */
	void deplacer(double p_X, double p_Y);

	/**
	 * Pour modifier la largeur.
	 * 
	 * @param p_Largeur la nouvelle largeur.
	 */
	void setLargeur(double p_Largeur);

	/**
	 * Pour modifier la hauteur.
	 * 
	 * @param p_Largeur la nouvelle hauteur.
	 */
	void setHauteur(double p_Hauteur);

	/**
	 * Pour modifier les dimensions de la formes.
	 * 
	 * @param p_Largeur largeur.
	 * @param p_Hauteur hauteur
	 */
	void setDimension(double p_Largeur, double p_Hauteur);
	
	/**
	 * Pour modifier la couleur.
	 * 
	 * @param p_Couleur la nouvelle couleur.
	 */
	void setCouleur(Color p_Couleur);

	/**
	 * Pour modifier la largeur du trait.
	 * 
	 * @param p_Couleur la nouvelle largeur du trait.
	 */
	void setLargeurTrait(int p_PX);

	/**
	 * Pour modifier la largeur du trait.
	 * 
	 * @param p_Couleur la nouvelle largeur du trait.
	 */
	void setCouleurTrait(Color p_Couleur);
	
	/**
	 * Spécifie si l'élément supporte des dimensions négatives.
	 * @return
	 */
	boolean supporteDimensionsNegatives();
}
