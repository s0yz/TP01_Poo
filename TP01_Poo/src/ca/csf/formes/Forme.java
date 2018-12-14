package ca.csf.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import ca.csf.formes.ElementGraphique;

/**
 * Classe abstraite définissant des méthodes permettant de manipuler des formes
 * 2D.
 * 
 * @see ElementGraphique
 */
public abstract class Forme implements ElementGraphique {

	/**
	 * Nom de la forme.
	 */
	private final String m_Nom;

	/**
	 * Coordonnée en x du point suppérieure gauche.
	 */
	private double m_X;

	/**
	 * Coordonnée en y du point suppérieure gauche.
	 */
	private double m_Y;

	/**
	 * Largeur.
	 */
	private double m_Largeur;

	/**
	 * Hauteur.
	 */
	private double m_Hauteur;

	/**
	 * Largeur du trait.
	 */
	private int m_LargeurTrait;

	/**
	 * Couleur.
	 */
	private Color m_Couleur;

	/**
	 * Couleur du trait.
	 */
	private Color m_CouleurTrait;

	/**
	 * Construit une forme.
	 * 
	 * @param p_Nom     nom de la forme.
	 * @param p_X       coordonnée en x.
	 * @param p_Y       coordonnée en y.
	 * @param p_Largeur largeur.
	 * @param p_Hauteur hauteur.
	 * @throws IllegalArgumentException si p_Nom est null.
	 */
	protected Forme(String p_Nom, double p_X, double p_Y, double p_Largeur, double p_Hauteur) {
		if (p_Nom == null) {
			throw new IllegalArgumentException("p_Nom est null.");
		}
		this.m_Nom = p_Nom;
		this.setPosition(p_X, p_Y);
		this.setDimension(p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		Shape forme = this.getShape();
		if (this.getCouleur() != null) {
			p_Graphic.setColor(this.getCouleur());
			p_Graphic.fill(forme);
		}
		if (this.getLargeurTrait() > 0 && this.getCouleurTrait() != null) {
			p_Graphic.setStroke(new BasicStroke(this.getLargeurTrait()));
			p_Graphic.setColor(this.getCouleurTrait());
			p_Graphic.draw(forme);
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
		return this.getLargeur() != 0 && this.getHauteur() != 0 && this.getShape().contains(p_X, p_Y);
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
	 * 
	 * @throws IllegalArgumentException si p_Largeur < 0.
	 */
	@Override
	public void setLargeur(double p_Largeur) {
		if (p_Largeur < 0 && !this.supporteDimensionsNegatives()) {
			throw new IllegalArgumentException("Largeur invalide : " + p_Largeur);
		}
		this.m_Largeur = p_Largeur;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException si p_Hauteur < 0.
	 */
	@Override
	public void setHauteur(double p_Hauteur) {
		if (p_Hauteur < 0 && !supporteDimensionsNegatives()) {
			throw new IllegalArgumentException("Hauteur invalide : " + p_Hauteur);
		}
		this.m_Hauteur = p_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(double p_Largeur, double p_Hauteur) {
		this.setLargeur(p_Largeur);
		this.setHauteur(p_Hauteur);
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
	public void setLargeurTrait(int p_Epaisseur) {
		if (p_Epaisseur < 0) {
			throw new IllegalArgumentException("Largeur de trait invalide : " + p_Epaisseur);
		}
		this.m_LargeurTrait = p_Epaisseur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleurTrait(Color p_Couleur) {
		this.m_CouleurTrait = p_Couleur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supporteDimensionsNegatives() {
		return false;
	}

	/**
	 * Pour obtenir la Shape correspondant à la Forme.
	 * 
	 * @return
	 */
	protected abstract Shape getShape();

	/**
	 * Code généré par eclipse.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ElementGraphique))
			return false;
		ElementGraphique other = (ElementGraphique) obj;
		if (this.m_Couleur == null) {
			if (other.getCouleur() != null)
				return false;
		} else if (!this.m_Couleur.equals(other.getCouleur()))
			return false;
		if (this.m_CouleurTrait == null) {
			if (other.getCouleurTrait() != null)
				return false;
		} else if (!this.m_CouleurTrait.equals(other.getCouleurTrait()))
			return false;
		if (this.m_Hauteur != other.getHauteur())
			return false;
		if (this.m_Largeur != other.getLargeur())
			return false;
		if (this.m_LargeurTrait != other.getLargeurTrait())
			return false;
		if (!this.m_Nom.equals(other.getNom()))
			return false;
		if (this.m_X != other.getX())
			return false;
		if (this.m_Y != other.getY())
			return false;
		return true;
	}
}
