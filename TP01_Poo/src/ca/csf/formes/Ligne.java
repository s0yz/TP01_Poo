package ca.csf.formes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public class Ligne extends Forme {

	/**
	 * @param p_Nom
	 */
	Ligne(int p_X, int p_Y) {
		this(p_X, p_Y, p_X, p_Y);
		// TODO Auto-generated constructor stub
	}
	
	Ligne(int p_X1, int p_Y1, int p_X2, int p_Y2) {
		super("Ligne", p_X1, p_Y1, p_X2 - p_X1, p_Y2 - p_Y1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return new Line2D.Float(
				this.getX(),
				this.getY(),
				this.getX() + this.getLargeur(),
				this.getY() + this.getHauteur());
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
