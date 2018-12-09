package ca.csf.formes;

import java.awt.Shape;
import java.awt.geom.Line2D;

import com.sun.javafx.scene.paint.GradientUtils.Point;

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
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(double p_X, double p_Y) {
		double x = Math.min(this.getX(), this.getX() + this.getLargeur());
		double y = Math.min(this.getY(),this.getY() + this.getHauteur());
		double hauteur = Math.abs(this.getLargeur());
		double largeur = Math.abs(this.getHauteur());
		Rectangle rect = new Rectangle(x - 2, y - 2, hauteur + 2, largeur + 2);
		return rect.contient(p_X, p_Y);
	}
	
	public void setPoint1() {
		
	}
	
	public void setPoint2() {
		
	}
	
	public Point getPoint1() {
		return null;
	}
	
	public Point getPoint2() {
		return null;
	}
}
