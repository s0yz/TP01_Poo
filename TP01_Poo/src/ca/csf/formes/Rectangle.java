package ca.csf.formes;

import java.awt.Point;
import java.awt.Shape;

/**
 * @author Cedric Mariage
 *
 */
public class Rectangle extends Forme {
	
	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public Rectangle(int p_X, int p_Y, int p_Largeur, int p_Hauteur) {
		super("Rectangle", p_X, p_Y, p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return new java.awt.Rectangle(
				this.getX(), this.getY(), this.getLargeur(), this.getHauteur());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point getPosition() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(Point p_Point) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(Point p_Position) {
	}
}
