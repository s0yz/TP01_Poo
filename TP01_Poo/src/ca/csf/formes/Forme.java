package ca.csf.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

/**
 * 
 * 
 * @author Cedric Mariage
 */
public abstract class Forme implements ElementGraphique {
	
	/**
	 * Nom de la forme.
	 */
	private final String m_Nom;
	private Color m_Couleur;
	private Color m_CouleurTrait;
	private int m_LargeurTrait;
	
	/**
	 * @param p_Nom nom de la forme
	 */
	Forme(String p_Nom) {
		this.m_Nom = p_Nom;
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
	public Iterable<Point> getPoints() {
		return null;
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
	 * Pour obtenir la Shape représentant la Forme.
	 * 
	 * @return
	 */
	protected abstract Shape getShape();
}
