package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public abstract class Formes implements ElementGraphique, Shape {

	private Shape m_Shape;
	
	Formes(Shape p_Shape) {
		this.m_Shape = new Rectangle(0, 0, 200, 200);
	}
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle getBounds() {
		return this.m_Shape.getBounds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle2D getBounds2D() {
		return this.m_Shape.getBounds2D();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(double p_X, double p_Y) {
		return this.m_Shape.contains(p_X, p_Y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Point2D p_P) {
		return this.m_Shape.contains(p_P);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean intersects(double p_X, double p_Y, double p_W, double p_H) {
		return this.m_Shape.intersects(p_X, p_Y, p_W, p_H);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean intersects(Rectangle2D p_r) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(double p_x, double p_y, double p_w, double p_h) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Rectangle2D p_r) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform p_at) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform p_at, double p_flatness) {
		return null;
	}

//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Point getPosition() {
//		// TODO
//		return null;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public int getLargeur() {
//		// TODO
//		return 0;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public int getHauteur() {
//		// TODO
//		return 0;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public int getCouleur() {
//		// TODO
//		return 0;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void setPosition(Point p_Position) {
//		// TODO
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void setLargeur(int p_Largeur) {
//		// TODO
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void setHauteur(int p_Hauteur) {
//		// TODO
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void setCouleur(Color p_Couleur) {
//		// TODO
//	}

}
