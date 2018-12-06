package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Cedric Mariage
 *
 */
public class DecorateurElementGraphique implements ElementGraphique {

	ElementGraphique m_Element;
	
	public DecorateurElementGraphique(ElementGraphique p_Element) {
		this.m_Element = p_Element;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		this.m_Element.dessiner(p_Graphic);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getX() {
		return this.m_Element.getX();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getY() {
		return this.m_Element.getY();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLargeur() {
		return this.m_Element.getLargeur();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHauteur() {
		return this.m_Element.getHauteur();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getCouleur() {
		return this.m_Element.getCouleur();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLargeurTrait() {
		return this.m_Element.getLargeurTrait();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getCouleurTrait() {
		return this.m_Element.getCouleurTrait();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contient(int p_X, int p_Y) {
		return this.m_Element.contient(p_X, p_Y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNom() {
		return this.m_Element.getNom();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {
		this.m_Element.setPosition(p_X, p_Y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deplacer(int p_X, int p_Y) {
		this.setPosition(this.getX() + p_X, this.getY() + p_Y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		this.m_Element.setLargeur(p_Largeur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		this.m_Element.setHauteur(p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(int p_Largeur, int p_Hauteur) {
		this.m_Element.setDimension(p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleur(Color p_Couleur) {
		this.m_Element.setCouleur(p_Couleur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeurTrait(int p_PX) {
		this.m_Element.setLargeurTrait(p_PX);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleurTrait(Color p_Couleur) {
		this.m_Element.setCouleurTrait(p_Couleur);
	}
}
