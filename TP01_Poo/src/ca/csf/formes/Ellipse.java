package ca.csf.formes;

import java.awt.Point;
import java.awt.Shape;

/**
 * @author Cedric Mariage
 *
 */
public class Ellipse extends Forme {

	/**
	 * @param p_Nom
	 */
	Ellipse(int p_X, int p_Y) {
		this(p_X, p_Y, 0, 0);
	}
	
	/**
	 * 
	 * @param p_X
	 * @param p_Y
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	Ellipse(int p_X, int p_Y, int p_Largeur, int p_Hauteur) {
		super("Ellipse", p_X, p_Y, p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return new java.awt.geom.Ellipse2D.Float(
				this.getX(), this.getY(), this.getLargeur(), this.getHauteur());
	}
}
