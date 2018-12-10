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
	Ligne(double p_X, double p_Y) {
		this(p_X, p_Y, p_X, p_Y);
	}

	/**
	 * 
	 * @param p_X1
	 * @param p_Y1
	 * @param p_X2
	 * @param p_Y2
	 */
	Ligne(double p_X1, double p_Y1, double p_X2, double p_Y2) {
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
}
