package ca.csf.formes;

import java.awt.Shape;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public class Rectangle extends Forme {
	
	public Rectangle() {
		this(0, 0);
	}
	
	/**
	 * 
	 * @param p_X
	 * @param p_Y
	 */
	public Rectangle(double p_X, double p_Y) {
		this(p_X, p_Y, 0, 0);
	}
	
	/**
	 * 
	 * @param p_X
	 * @param p_Y
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	public Rectangle(double p_X, double p_Y, double p_Largeur, double p_Hauteur) {
		super("Rectangle", p_X, p_Y, p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		double x = Math.min(this.getX(), this.getX() + this.getLargeur());
		double y = Math.min(this.getY(), this.getY() + this.getHauteur());
		double largeur = Math.abs(this.getLargeur());
		double hauteur = Math.abs(this.getHauteur());
		return new java.awt.Rectangle.Double(x, y, largeur, hauteur);
	}
}
