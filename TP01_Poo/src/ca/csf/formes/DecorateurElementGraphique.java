package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Décorateur d'{@link ElementGraphique}. Les classes intéressées à décorer un
 * ÉlémentGraphique hériterons de cette classe.
 */
public class DecorateurElementGraphique implements ElementGraphique {

	private ElementGraphique m_Element;

	/**
	 * Construit un décorateur d'{@code ElementGraphiqe}.
	 * 
	 * @param p_Element l'élément décoré.
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	public DecorateurElementGraphique(ElementGraphique p_Element) {
		this.m_Element = p_Element;
	}

	/**
	 * Pour obtenir l'élément décoré.
	 * 
	 * @return l'élément décoré.
	 */
	public ElementGraphique getElement() {
		return this.m_Element;
	}

	/**
	 * Pour changer l'élément décoré.
	 * 
	 * @param p_Element le nouvel élément décoré.
	 */
	protected void setElement(ElementGraphique p_Element) {
		this.m_Element = p_Element;
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		this.m_Element.dessiner(p_Graphic);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public double getX() {
		return this.m_Element.getX();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public double getY() {
		return this.m_Element.getY();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public double getLargeur() {
		return this.m_Element.getLargeur();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public double getHauteur() {
		return this.m_Element.getHauteur();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public Color getCouleur() {
		return this.m_Element.getCouleur();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public int getLargeurTrait() {
		return this.m_Element.getLargeurTrait();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public Color getCouleurTrait() {
		return this.m_Element.getCouleurTrait();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public boolean contient(double p_X, double p_Y) {
		return this.m_Element.contient(p_X, p_Y);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public String getNom() {
		return this.m_Element.getNom();
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setPosition(double p_X, double p_Y) {
		this.m_Element.setPosition(p_X, p_Y);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void deplacer(double p_X, double p_Y) {
		this.setPosition(this.getX() + p_X, this.getY() + p_Y);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setLargeur(double p_Largeur) {
		this.m_Element.setLargeur(p_Largeur);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setHauteur(double p_Hauteur) {
		this.m_Element.setHauteur(p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setDimension(double p_Largeur, double p_Hauteur) {
		this.m_Element.setDimension(p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setCouleur(Color p_Couleur) {
		this.m_Element.setCouleur(p_Couleur);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setLargeurTrait(int p_PX) {
		this.m_Element.setLargeurTrait(p_PX);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public void setCouleurTrait(Color p_Couleur) {
		this.m_Element.setCouleurTrait(p_Couleur);
	}

	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public boolean supporteDimensionsNegatives() {
		return this.m_Element.supporteDimensionsNegatives();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object p_obj) {
		return this.m_Element == null ? p_obj == null : this.m_Element.equals(p_obj);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws NullPointerException si l'élément décoré est null.
	 */
	@Override
	public String toString() {
		return this.m_Element.toString();
	}
}
