package ca.csf.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import ca.csf.formes.ElementGraphique;

/**
 * @author Cedric Mariage
 */
public abstract class Forme implements ElementGraphique {
	
	public static final int LARGEUR_TRAIT_DEFAUT = 1;
	
	/**
	 * 
	 */
	private final String m_Nom;
	
	/**
	 * 
	 */
	private int m_X;
	
	/**
	 * 
	 */
	private int m_Y;
	
	/**
	 * 
	 */
	private int m_Largeur;
	
	/**
	 * 
	 */
	private int m_Hauteur;
	
	/**
	 * 
	 */
	private int m_LargeurTrait = LARGEUR_TRAIT_DEFAUT;
	
	/**
	 * 
	 */
	private Color m_Couleur;
	
	/**
	 * 
	 */
	private Color m_CouleurTrait = Color.BLACK;
	
	/**
	 * 
	 * 
	 * @param p_Nom nom de la forme
	 */
	Forme(String p_Nom) {
		this.m_Nom = p_Nom;
	}
	
	/**
	 * 
	 * @param p_Nom
	 * @param p_X
	 * @param p_Y
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	Forme(String p_Nom, int p_X, int p_Y, int p_Largeur, int p_Hauteur) {
		this.m_Nom = p_Nom;
		this.m_X = p_X;
		this.m_Y = p_Y;
		this.m_Largeur = p_Largeur;
		this.m_Hauteur = p_Hauteur;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		if (this.getLargeurTrait() > 0 && this.getCouleurTrait() != null) {
			p_Graphic.setStroke(new BasicStroke(this.getLargeurTrait()));
			p_Graphic.setColor(this.getCouleurTrait());
			p_Graphic.draw(this.getShape());
		}
		if (this.getCouleur() != null) {
			p_Graphic.setColor(this.getCouleur());
			p_Graphic.fill(this.getShape());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getX() {
		return this.m_X;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getY() {
		return this.m_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLargeur() {
		return this.m_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHauteur() {
		return this.m_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getCouleur() {
		return this.m_Couleur;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLargeurTrait() {
		return this.m_LargeurTrait;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getCouleurTrait() {
		return this.m_CouleurTrait;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(int x, int y) {
		return this.getShape().contains(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNom() {
		return this.m_Nom;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {
		this.m_X = p_X;
		this.m_Y = p_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		this.m_Largeur = p_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		this.m_Hauteur = p_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleur(Color p_Couleur) {
		this.m_Couleur = p_Couleur;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeurTrait(int p_PX) {
		this.m_LargeurTrait = p_PX;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleurTrait(Color p_Couleur) {
		this.m_CouleurTrait = p_Couleur;
	}
	
	/**
	 * Pour obtenir la Shape correspondant à la Forme.
	 * 
	 * @return
	 */
	protected abstract Shape getShape();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public final Point getPosition() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public final boolean contient(Point p_Point) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public final void setPosition(Point p_Position) {
	}
}
