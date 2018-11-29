package ca.csf.formes;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Cedric Mariage
 */
public class Rectangle extends Forme {
	
	/**
	 * 
	 */
	private java.awt.Rectangle m_Rectangle;
	
	/**
	 * 
	 */
	Rectangle() {
		this(new Point(0, 0), 0, 0);
	}
	
	public Rectangle(int p_X, int p_Y, int p_Largeur, int p_Hauteur) {
		super("Rectangle");
		this.m_Rectangle = new java.awt.Rectangle(p_X, p_Y, p_Largeur, p_Hauteur);
	}
	
	/**
	 * @param m_Position
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	Rectangle(Point m_Position, int p_Largeur, int p_Hauteur) {
		this(m_Position.x, m_Position.y, p_Largeur, p_Hauteur);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getX() {
		return this.m_Rectangle.x;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getY() {
		return this.m_Rectangle.y;
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
	public int getLargeur() {
		return this.m_Rectangle.width;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHauteur() {
		return this.m_Rectangle.height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.awt.Rectangle getBounds() {
		return this.m_Rectangle.getBounds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(double p_x, double p_y) {
		return this.m_Rectangle.contains(p_x, p_y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Point2D p_p) {
		return this.m_Rectangle.contains(p_p);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean intersects(double p_x, double p_y, double p_w, double p_h) {
		return this.m_Rectangle.intersects(p_x, p_y, p_w, p_h);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean intersects(Rectangle2D p_r) {
		return this.m_Rectangle.intersects(p_r);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(double p_x, double p_y, double p_w, double p_h) {
		return this.m_Rectangle.contains(p_x, p_y, p_w, p_h);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Rectangle2D p_r) {
		return this.m_Rectangle.contains(p_r);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform p_at) {
		return this.m_Rectangle.getPathIterator(p_at);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PathIterator getPathIterator(AffineTransform p_at, double p_flatness) {
		return m_Rectangle.getPathIterator(p_at, p_flatness);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(Point p_Position) {
		this.m_Rectangle.setLocation(p_Position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {
		this.m_Rectangle.x = p_X;
		this.m_Rectangle.y = p_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		this.m_Rectangle.width = p_Largeur;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		this.m_Rectangle.height = p_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(Point p_Point) {
		return this.m_Rectangle.contains(p_Point);
	}
}
