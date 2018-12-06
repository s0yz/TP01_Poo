package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * L'interface ElementGraphique fournit des opérations pour manipuler des formes
 * ou autres éléments vivant dans un plan à 2 dimensions.
 * 
 * @author Cedric Mariage
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
	int getX();

	/**
	 * Pour obtenir la coordonne en y.
	 * 
	 * @return la coordonne en y.
	 */
	int getY();

	/**
	 * Pour obtenir la largeur en pixel.
	 * 
	 * @return la largeur en pixel.
	 */
	int getLargeur();

	/**
	 * Pour obtenir la hauteur en pixel.
	 * 
	 * @return la hauteur en pixel.
	 */
	int getHauteur();

	/**
	 * Pour obtenir la couleur de remplissage.
	 * 
	 * @return la couleur de remplissage ou null.
	 */
	Color getCouleur();

	/**
	 * Pour obtenir la largeur du trait.
	 * 
	 * @return l'épaisseur du trait en pixel.
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
	boolean contient(int p_X, int p_Y);

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
	void setPosition(int p_X, int p_Y);
	
	/**
	 * Pour déplacer la forme.
	 * 
	 * @param p_X déplacement en x.
	 * @param p_Y déplacement en y.
	 */
	void deplacer(int p_X, int p_Y);

	/**
	 * Pour modifier la largeur.
	 * 
	 * @param p_Largeur la nouvelle largeur.
	 */
	void setLargeur(int p_Largeur);

	/**
	 * Pour modifier la hauteur.
	 * 
	 * @param p_Largeur la nouvelle hauteur.
	 */
	void setHauteur(int p_Hauteur);

	/**
	 * Pour modifier les dimensions de la formes.
	 * 
	 * @param p_Largeur largeur.
	 * @param p_Hauteur hauteur
	 */
	void setDimension(int p_Largeur, int p_Hauteur);
	
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
}
