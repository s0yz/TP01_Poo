package ca.csf.formes;

import java.awt.Point;
import java.awt.Shape;

import ca.csf.formes.Forme;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public class Ellipse extends Forme {

	java.awt.geom.Ellipse2D.Float m_Ellipse;
	
	/**
	 * @param p_Nom
	 */
	Ellipse() {
		super("Ellipse");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getX() {
		return Math.round(m_Ellipse.x);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getY() {
		return Math.round(m_Ellipse.y);
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
		return Math.round(m_Ellipse.width);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHauteur() {
		return Math.round(m_Ellipse.height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(int p_X, int p_Y) {
		return this.m_Ellipse.contains(p_X, p_Y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(Point p_Point) {
		return this.m_Ellipse.contains(p_Point);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(Point p_Position) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {
		this.m_Ellipse.x = p_X;
		this.m_Ellipse.x = p_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		this.m_Ellipse.width = p_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		this.m_Ellipse.height = p_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return this.m_Ellipse;
	}
}
