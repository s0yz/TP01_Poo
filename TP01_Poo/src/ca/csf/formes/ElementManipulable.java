package ca.csf.formes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Décorateur concret d'{@code ElementGraphiqe}.
 * 
 * Optimisé pour être manipuler par une souris. Gère les dimensions négatives.
 */
public class ElementManipulable extends DecorateurElementGraphique {

	/**
	 * Couleur du trait de sélection.
	 */
	private static final Color COULEUR_TRAIT = Color.CYAN;

	/**
	 * Taille du petit carré permettant la redimension.
	 */
	private static final int TAILLE_CARRE = 14;

	/**
	 * Couleur du petit carré permettant la redimension.
	 */
	private static final Color COULEUR_CARRE = new Color(255, 255, 255, 175);

	/**
	 * Couleur du trait du petit carré permettant la redimension.
	 */
	private static final Color COULEUR_CARRE_TRAIT = new Color(0, 0, 0, 100);

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
	 * Trait utilisé mettre en évidence l'élément décorée.
	 */
	private BasicStroke m_Trait;

	/**
	 * Petit carré pour la redimension;
	 */
	private ElementGraphique m_Carre;

	public ElementManipulable(ElementGraphique p_Element) {
		this(p_Element, 0, 0);
	}

	/**
	 * Construit un {@code ElementManipulable}.
	 * 
	 * @param p_Element élément décoré.
	 * @param p_X       coordonnée en x du point de sélection.
	 * @param p_Y       coordonnée en y du point de sélection.
	 */
	public ElementManipulable(ElementGraphique p_Element, double p_X, double p_Y) {
		super(p_Element);
		this.m_Carre = FormeFactory.getInstance().getForme("Rectangle");
		this.getCarre().setCouleur(ElementManipulable.COULEUR_CARRE);
		this.getCarre().setCouleurTrait(ElementManipulable.COULEUR_CARRE_TRAIT);
		this.getCarre().setDimension(ElementManipulable.TAILLE_CARRE, ElementManipulable.TAILLE_CARRE);
		this.set(p_Element, p_X, p_Y);
		float[] tirets = { 1.0f };
		this.m_Trait = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, tirets, 0.0f);
	}

	/**
	 * Change l'élément sélectionné.
	 * 
	 * @param p_Element la nouvelle sélection.
	 */
	public void set(ElementGraphique p_Element) {
		this.set(p_Element, 0, 0);
	}

	/**
	 * Change l'élément décoré pour un autre.
	 * 
	 * @param p_Element le nouvel élément décoré.
	 * @param p_X       coordonnée en x du point de sélection.
	 * @param p_Y       coordonnée en y du point de sélection.
	 */
	public void set(ElementGraphique p_Element, double p_X, double p_Y) {
		super.setElement(p_Element);
		if (p_Element != null) {
			this.m_X = p_Element.getX();
			this.m_Y = p_Element.getY();
			this.m_Largeur = p_Element.getLargeur();
			this.m_Hauteur = p_Element.getHauteur();
			this.m_DifferenceX = p_X - this.getElement().getX();
			this.m_DifferenceY = p_Y - this.getElement().getY();
			this.ajusterCarre();
		}
	}

	/**
	 * Pour savoir si un élément est décoré.
	 * 
	 * @return vrai si l'élément décoré est null.
	 */
	public boolean estVide() {
		return this.getElement() == null;
	}

	/**
	 * Pour savoir si le point spécifié se trouve dans le petit carré permettant la
	 * redimension.
	 * 
	 * @param p_X
	 * @param p_Y
	 * @return
	 */
	public boolean estDansLeCoin(int p_X, int p_Y) {
		return this.getCarre().contient(p_X, p_Y);
	}

	/**
	 * Ajuste la position et les dimensions de l'élément décoré
	 * selon les dimensions actuelles 
	 */
	private void ajusterElement() {
		if (!this.estVide()) {
			if (this.supporteDimensionsNegatives()) {
				super.setPosition(this.getX(), this.getY());
				super.setDimension(this.m_Largeur, this.m_Hauteur);
			} else {
				double x = Math.min(this.getX(), this.getX() + this.m_Largeur);
				double y = Math.min(this.getY(), this.getY() + this.m_Hauteur);
				double largeur = Math.abs(this.m_Largeur);
				double hauteur = Math.abs(this.m_Hauteur);
				super.setPosition(x, y);
				super.setDimension(largeur, hauteur);				
			}
			this.ajusterCarre();
		}
	}

	private void ajusterCarre() {
		int decalage = ElementManipulable.TAILLE_CARRE >> 1;
		double x = this.m_X + this.m_Largeur - decalage;
		double y = this.m_Y + this.m_Hauteur - decalage;
		this.getCarre().setPosition(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		int x = (int) Math.round(super.getX());
		int y = (int) Math.round(super.getY());
		int largeur = (int) Math.round(super.getLargeur());
		int hauteur = (int) Math.round(super.getHauteur());
		p_Graphic.setColor(ElementManipulable.COULEUR_TRAIT);
		p_Graphic.setStroke(m_Trait);
		p_Graphic.drawRect(x, y, largeur, hauteur);
		this.getCarre().dessiner(p_Graphic);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getX() {
		return this.estVide() ? 0.0 : this.m_X;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getY() {
		return this.estVide() ? 0.0 : this.m_Y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getLargeur() {
		return this.estVide() ? 0.0 : this.m_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getHauteur() {
		return this.estVide() ? 0.0 : this.m_Hauteur;
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

	/**
	 * Pour obtenir le carre.
	 * 
	 * @return le carre.
	 */
	public ElementGraphique getCarre() {
		return m_Carre;
	}
}
