package ca.csf.formes;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Représente une ligne 2D.
 */
public class Ligne extends Forme {

	/**
	 * Instancie une ligne. Les valeurs par défaut en font un point à (0, 0).
	 */
	public Ligne() {
		this(0, 0, 0 ,0);
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
	 * Approximation généreuse.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(double p_X, double p_Y) {
		double distAB = Math.hypot(this.getX1() - this.getX2(), this.getY1() - this.getY2());
		double distAC = Math.hypot(this.getX1() - p_X, this.getY1() - p_Y);
		double distBC = Math.hypot(this.getX2() - p_X, this.getY2() - p_Y);
		distAB -= distAC + distBC;
		return Math.abs(distAB) <= this.getLargeurTrait() >> 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supporteDimensionsNegatives() {
		return true;
	}

	public double getX1() {
		return this.getX();
	}

	public double getX2() {
		return this.getX() + this.getLargeur();
	}

	public double getY1() {
		return this.getY();
	}

	public double getY2() {
		return this.getY() + this.getHauteur();
	}
	
	public void setPoint1(double p_X1, double p_Y1) {
		this.setPosition(p_X1, p_Y1);
	}
	
	public void setPoint2(double p_X2, double p_Y2) {
		this.setLargeur(this.getX() - p_X2);
		this.setHauteur(this.getY() - p_Y2);
	}
}
