package ca.csf.formes;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * 
 */
public class Ligne extends Forme {

	Ligne() {
		this(0, 0);
	}

	/**
	 * @param p_Nom
	 */
	Ligne(int p_X, int p_Y) {
		this(p_X, p_Y, p_X, p_Y);
	}

	/**
	 * 
	 * @param p_X1
	 * @param p_Y1
	 * @param p_X2
	 * @param p_Y2
	 */
	Ligne(int p_X1, int p_Y1, int p_X2, int p_Y2) {
		super("Ligne", p_X1, p_Y1, p_X2 - p_X1, p_Y2 - p_Y1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return new Line2D.Float(this.getX(), this.getY(), this.getX() + this.getLargeur(),
				this.getY() + this.getHauteur());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(int p_X, int p_Y) {
		int x = Math.min(this.getX(), this.getX() + this.getLargeur());
		int y = Math.min(this.getY(),this.getY() + this.getHauteur());
		int hauteur = Math.abs(this.getLargeur());
		int largeur = Math.abs(this.getHauteur());
		Rectangle rect = new Rectangle(x, y, hauteur, largeur);
		return rect.contient(p_X, p_Y);
	}
}
