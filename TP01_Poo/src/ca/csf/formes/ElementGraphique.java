package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
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
	 * Pour obtenir la position de l'élément..
	 * 
	 * @return la position (coins supérieur gauche)
	 * @deprecated Va être retirer pour alléger l'interface. Voir {@code getX()} et
	 *             {@code getY()}.
	 */
	Point getPosition();

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
	 * Pour vérifier si l'élément contient le point.
	 * 
	 * @param p_Point le point à vérifier.
	 * @return vrai si l'élément contient le point.
	 * @deprecated Va être retirer pour alléger l'interface. Voir
	 *             {@code contient(int p_X, int p_Y)}.
	 */
	boolean contient(Point p_Point);

	/**
	 * @return un iterable contenant les points representant l'element ou null.
	 */
	Iterable<Point> getPoints();

	/**
	 * @return le nom de l'element ou de la forme représentée.
	 */
	String getNom();

	/**
	 * Pour modifier la position.
	 * 
	 * @param p_Position la nouvelle position.
	 * @deprecated Va être retirer pour alléger l'interface. Voir
	 *             {@code setPosition(int p_X, int p_Y)}.
	 */
	void setPosition(Point p_Position);

	/**
	 * Pour modifier la position.
	 * 
	 * @param p_X la nouvelle coordonnée en x.
	 * @param p_Y la nouvelle coordonnée en y.
	 */
	void setPosition(int p_X, int p_Y);

	/**
	 * Pour modifier la largeur.
	 * 
	 * @param p_Largeur la nouvelle largeur en pixel.
	 */
	void setLargeur(int p_Largeur);

	/**
	 * Pour modifier la hauteur.
	 * 
	 * @param p_Largeur la nouvelle hauteur en pixel.
	 */
	void setHauteur(int p_Hauteur);

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
	public void setLargeurTrait(int p_PX);

	/**
	 * Pour modifier la largeur du trait.
	 * 
	 * @param p_Couleur la nouvelle largeur du trait.
	 */
	public void setCouleurTrait(Color p_Couleur);
}
