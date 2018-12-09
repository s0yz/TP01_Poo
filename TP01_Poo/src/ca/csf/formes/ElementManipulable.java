package ca.csf.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 */
public class ElementManipulable extends DecorateurElementGraphique {

	/**
	 * Coordonnée en x.
	 */
	private double m_X;
	
	/**
	 * Coordonnée en y.
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
	 * Difference en x par rapport à la position de la souris et celle de la forme
	 * sélectionnée.
	 */
	private double m_DifferenceX;

	/**
	 * Difference en y par rapport à la position de la souris et celle de la forme
	 * sélectionnée.
	 */
	private double m_DifferenceY;
	
	/**
	 * Trait utilisé pour dessiner la sélection.
	 */
	private BasicStroke m_Trait;
	
	/**
	 * Construit un {@code ElementManipulable}.
	 * 
	 * @param p_Element élément décoré.
	 * @param p_X coordonnée en x du point de sélection.
	 * @param p_Y coordonnée en y du point de sélection.
	 */
	public ElementManipulable(ElementGraphique p_Element, double p_X, double p_Y) {
		super(p_Element);
		this.set(p_Element, p_X, p_Y);
		float[] tirets = { 1.0f };
		this.m_Trait = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, tirets, 0.0f);
	}
	
	/**
	 * Change l'élément décoré pour un autre.
	 * 
	 * @param p_Element le nouvel élément décoré.
	 * @param p_X coordonnée en x du point de sélection.
	 * @param p_Y coordonnée en y du point de sélection.
	 */
	public void set(ElementGraphique p_Element, double p_X, double p_Y) {
		this.setElement(p_Element);
		if (p_Element != null) {
			this.m_X = p_Element.getX();
			this.m_Y = p_Element.getY();
			this.m_Largeur = p_Element.getLargeur();
			this.m_Hauteur = p_Element.getHauteur();
			this.m_DifferenceX = p_X - this.getElement().getX();
			this.m_DifferenceY = p_Y - this.getElement().getY();
		}
	}
	
	private void ajusterElement() {
		if (this.getElement() != null && this.getNom() != "Ligne") {
			double x = Math.min(this.m_X, this.m_X + this.m_Largeur);
			double y = Math.min(this.m_Y, this.m_Y + this.m_Hauteur);
			double largeur = Math.abs(this.m_Largeur);
			double hauteur = Math.abs(this.m_Hauteur);
			super.setPosition(x, y);
			super.setDimension(largeur, hauteur);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		p_Graphic.setColor(Color.CYAN);
		p_Graphic.setStroke(m_Trait);
		int x = (int) Math.round(super.getX());
		int y = (int) Math.round(super.getY());
		int largeur = (int) Math.round(super.getLargeur());
		int hauteur = (int) Math.round(super.getHauteur());
		p_Graphic.drawRect(x, y, largeur, hauteur);
	}
	
	public boolean estVide() {
		return this.getElement() != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
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
	public void setPosition(double p_X, double p_Y) {
		this.m_X = p_X - this.m_DifferenceX;
		this.m_Y = p_Y - this.m_DifferenceY;
		this.ajusterElement();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deplacer(double p_X, double p_Y) {
		this.m_X += p_X;
		this.m_Y += p_Y;
		this.ajusterElement();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(double p_Largeur) {
		this.m_Largeur = p_Largeur;
		this.ajusterElement();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(double p_Hauteur) {
		this.m_Hauteur = p_Hauteur;
		this.ajusterElement();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(double p_Largeur, double p_Hauteur) {
		this.m_Largeur = p_Largeur;
		this.m_Hauteur = p_Hauteur;
		this.ajusterElement();
	}
}
