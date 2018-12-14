package ca.csf.formes;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Représente une ligne 2D.
 * 
 * @see ElementGraphique
 */
public class Ligne extends Forme {

	/**
	 * Instancie une ligne. Les valeurs par défaut en font un point à (0, 0).
	 */
	public Ligne() {
		this(0, 0, 0, 0);
	}

	/**
	 * Instancie une ligne selon les points spécifié.
	 * 
	 * @param p_X1 coordonnée en x du premier point.
	 * @param p_Y1 coordonnée en y du premier point.
	 * @param p_X2 coordonnée en x du second point.
	 * @param p_Y2 coordonnée en y du second point.
	 */
	public Ligne(double p_X1, double p_Y1, double p_X2, double p_Y2) {
		super("Ligne", p_X1, p_Y1, p_X2 - p_X1, p_Y2 - p_Y1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return new Line2D.Double(this.getX(), this.getY(), this.getX() + this.getLargeur(),
				this.getY() + this.getHauteur());
	}

	/**
	 * Approximation généreuse...
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(double p_X, double p_Y) {
		if (this.getLargeur() == 0 && this.getHauteur() == 0) {
			return false;
		}
		double distAB = Math.hypot(this.getX() - this.getX2(), this.getY() - this.getY2());
		double distAC = Math.hypot(this.getX() - p_X, this.getY() - p_Y);
		double distBC = Math.hypot(this.getX2() - p_X, this.getY2() - p_Y);
		distAB -= distAC + distBC;
		return Math.abs(distAB) <= this.getLargeurTrait() * 0.05;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supporteDimensionsNegatives() {
		return true;
	}

	/**
	 * Pour obtenir la coordonnée en x du second point.
	 * 
	 * @return la coordonnée en x du second point.
	 */
	public double getX2() {
		return this.getX() + this.getLargeur();
	}

	/**
	 * Pour obtenir la coordonnée en y du second point.
	 * 
	 * @return la coordonnée en y du second point.
	 */
	public double getY2() {
		return this.getY() + this.getHauteur();
	}

	/**
	 * Pour modifier les cooordonnées du premier point.
	 * 
	 * @param p_X1 coordonnée en x.
	 * @param p_Y1 coordonnée en y.
	 */
	public void setPoint1(double p_X1, double p_Y1) {
		this.setLargeur(this.getX2() - p_X1);
		this.setHauteur(this.getY2() - p_Y1);
		this.setPosition(p_X1, p_Y1);
		
	}

	/**
	 * Pour modifier les cooordonnées du second point.
	 * 
	 * @param p_X1 coordonnée en x.
	 * @param p_Y1 coordonnée en y.
	 */
	public void setPoint2(double p_X2, double p_Y2) {
		this.setLargeur(p_X2 - this.getX());
		this.setHauteur(p_Y2 - this.getY());
	}
}
