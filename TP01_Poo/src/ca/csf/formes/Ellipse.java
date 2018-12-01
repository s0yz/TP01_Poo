package ca.csf.formes;

import java.awt.Shape;

/**
 * @author Cedric Mariage
 *
 */
public class Ellipse extends Forme {

	/**
	 * @param p_Nom
	 */
	Ellipse() {
		this(0, 0, 0, 0);
	}
	
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
