package ca.csf.formes;

import java.awt.Point;
import java.awt.Shape;

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
	public Rectangle() {
		this(0, 0, 0, 0);
	}

	/**
	 * 
	 * @param p_X
	 * @param p_Y
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	public Rectangle(int p_X, int p_Y, int p_Largeur, int p_Hauteur) {
		super("Rectangle");
		this.m_Rectangle = new java.awt.Rectangle(p_X, p_Y, p_Largeur, p_Hauteur);
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
	 * 
	 * @deprecated Va être retirer pour alléger l'interface. Voir {@code getX()} et
	 *             {@code getY()}.
	 */
	@Override
	public Point getPosition() {
		return this.m_Rectangle.getLocation();
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
	public boolean contient(Point p_Point) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(int p_X, int p_Y) {
		return this.m_Rectangle.contains(p_X, p_Y);
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
	 * @deprecated Va être retirer pour alléger l'interface. Voir
	 *             {@code setPosition(int p_X, int p_Y)}.
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
	protected Shape getShape() {
		return this.m_Rectangle;
	}
}
