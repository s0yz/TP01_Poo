package ca.csf.formes;

import java.awt.Color;
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
public abstract class Formes implements ElementGraphique {

	private Shape m_Shape;
	
	Formes(Shape p_Shape) {
		this.m_Shape = new Rectangle(0, 0, 200, 200);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Point getPosition() {
		// TODO
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLargeur() {
		// TODO
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHauteur() {
		// TODO
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCouleur() {
		// TODO
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(Point p_Position) {
		// TODO
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {
		// TODO
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		// TODO
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		// TODO
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleur(Color p_Couleur) {
		// TODO
	}

}
