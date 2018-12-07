package ca.csf.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import ca.csf.formes.ElementGraphique;

/**
 * Classe abstraite définissant des méthodes permettant de manipuler des formes 2D.
 * 
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
	private double m_X;
	
	/**
	 * 
	 */
	private double m_Y;
	
	/**
	 * 
	 */
	private double m_Largeur;
	
	/**
	 * 
	 */
	private double m_Hauteur;
	
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
	 * @param p_Nom
	 * @param p_X
	 * @param p_Y
	 * @param p_Largeur
	 * @param p_Hauteur
	 */
	protected Forme(String p_Nom, double p_X, double p_Y, double p_Largeur, double p_Hauteur) {
		this.m_Nom = p_Nom;
		this.setPosition(p_X, p_Y);
		this.setDimension(p_Largeur, p_Hauteur);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		if (this.getCouleur() != null) {
			p_Graphic.setColor(this.getCouleur());
			p_Graphic.fill(this.getShape());
		}
		if (this.getLargeurTrait() > 0 && this.getCouleurTrait() != null) {
			p_Graphic.setStroke(new BasicStroke(this.getLargeurTrait()));
			p_Graphic.setColor(this.getCouleurTrait());
			p_Graphic.draw(this.getShape());
		}		
	}

	@Override
	public double getX() {
		return this.m_X;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getY() {
		return this.m_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getLargeur() {
		return this.m_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getHauteur() {
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
	public boolean contient(double p_X, double p_Y) {
		return this.getShape().contains(p_X, p_Y);
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
	public void setPosition(double p_X, double p_Y) {
		this.m_X = p_X;
		this.m_Y = p_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(double p_Largeur) {
		this.setDimension(p_Largeur, this.m_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(double p_Hauteur) {
		this.setDimension(this.m_Largeur, p_Hauteur);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(double p_Largeur, double p_Hauteur) {
		this.m_Largeur = p_Largeur;
		this.m_Hauteur = p_Hauteur;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deplacer(double p_X, double p_Y) {
		this.setPosition(this.getX() + p_X, this.getY() + p_Y);
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
}
