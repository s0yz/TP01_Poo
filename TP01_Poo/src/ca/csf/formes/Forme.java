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
	private int m_LargeurTrait = Forme.LARGEUR_TRAIT_DEFAUT;
	
	/**
	 * Couleur.
	 */
	private Color m_Couleur;
	
	/**
	 * Couleur du trait.
	 */
	private Color m_CouleurTrait = Color.BLACK;
		
	/**
	 * Construit une forme.
	 * 
	 * @param p_Nom nom de la forme.
	 * @param p_X coordonnée en x.
	 * @param p_Y coordonnée en y.
	 * @param p_Largeur largeur.
	 * @param p_Hauteur hauteur.
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
	 * @throws IllegalArgumentException si p_Largeur < 0.
	 */
	@Override
	public void setLargeur(double p_Largeur) {
		if (p_Largeur < 0 && !supporteDimensionsNegatives()) {
			throw new IllegalArgumentException("Largeur invalide : " + p_Largeur);
		}
		this.m_Largeur = p_Largeur;
	}

	/**
	 * {@inheritDoc}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.m_Couleur == null) ? 0 : this.m_Couleur.hashCode());
		result = prime * result + ((this.m_CouleurTrait == null) ? 0 : this.m_CouleurTrait.hashCode());
		long temp;
		temp = Double.doubleToLongBits(this.m_Hauteur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.m_Largeur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + this.m_LargeurTrait;
		result = prime * result + ((this.m_Nom == null) ? 0 : this.m_Nom.hashCode());
		temp = Double.doubleToLongBits(this.m_X);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.m_Y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Code généré par eclipse.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forme other = (Forme) obj;
		if (this.m_Couleur == null) {
			if (other.m_Couleur != null)
				return false;
		} else if (!this.m_Couleur.equals(other.m_Couleur))
			return false;
		if (this.m_CouleurTrait == null) {
			if (other.m_CouleurTrait != null)
				return false;
		} else if (!this.m_CouleurTrait.equals(other.m_CouleurTrait))
			return false;
		if (Double.doubleToLongBits(this.m_Hauteur) != Double.doubleToLongBits(other.m_Hauteur))
			return false;
		if (Double.doubleToLongBits(this.m_Largeur) != Double.doubleToLongBits(other.m_Largeur))
			return false;
		if (this.m_LargeurTrait != other.m_LargeurTrait)
			return false;
		if (this.m_Nom == null) {
			if (other.m_Nom != null)
				return false;
		} else if (!this.m_Nom.equals(other.m_Nom))
			return false;
		if (Double.doubleToLongBits(this.m_X) != Double.doubleToLongBits(other.m_X))
			return false;
		if (Double.doubleToLongBits(this.m_Y) != Double.doubleToLongBits(other.m_Y))
			return false;
		return true;
	}
}
