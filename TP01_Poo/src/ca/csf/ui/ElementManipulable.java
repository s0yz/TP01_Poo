package ca.csf.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ca.csf.formes.DecorateurElementGraphique;
import ca.csf.formes.ElementGraphique;
import ca.csf.formes.UsineForme;

/**
 * Décorateur concret d'{@code ElementGraphiqe}.
 * 
 * Represente un élément sélectionné, optimisé pour être manipulé par une souris.
 */
public class ElementManipulable extends DecorateurElementGraphique {

	/**
	 * Couleur du trait de sélection.
	 */
	private static final Color COULEUR_TRAIT = Color.CYAN;
	
	/**
	 * Largeur du trait de sélection.
	 */
	private static final int Largeur_TRAIT = 2;

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

	private double m_Largeur;

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

	/**
	 * Instancie un ElementManipulable
	 * 
	 * @param p_Element l'élément sélectionné.
	 */
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
		int trait = ElementManipulable.Largeur_TRAIT;
		this.m_Carre = UsineForme.getInstance().getForme("Rectangle");
		this.m_Carre.setCouleur(ElementManipulable.COULEUR_CARRE);
		this.m_Carre.setCouleurTrait(ElementManipulable.COULEUR_CARRE_TRAIT);
		this.m_Carre.setLargeurTrait(trait);
		this.m_Carre.setDimension(ElementManipulable.TAILLE_CARRE, ElementManipulable.TAILLE_CARRE);
		this.set(p_Element, p_X, p_Y);
		float[] tirets = { trait };
		this.m_Trait = new BasicStroke(trait, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, trait, tirets, 0.0f);
	}

	/**
	 * Change l'élément sélectionné. Une valeur null vide la sélection.
	 * 
	 * @param p_Element la nouvelle sélection.
	 */
	public void set(ElementGraphique p_Element) {
		this.set(p_Element, 0, 0);
	}
	
	/**
	 * Change l'élément sélectionné.
	 * 
	 * @param p_Element la nouvelle sélection.
	 */
	@Override
	public void setElement(ElementGraphique p_Element) {
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
			this.setPointSelection(p_X, p_Y);
			this.ajusterCarre();
		}
	}

	/**
	 * Pour modifier le point de sélection.
	 * 
	 * @param p_X
	 * @param p_Y
	 */
	public void setPointSelection(double p_X, double p_Y) {
		this.m_DifferenceX = p_X - this.getElement().getX();
		this.m_DifferenceY = p_Y - this.getElement().getY();
	}

	/**
	 * Pour savoir si un élément est décoré par le décorateur.
	 * 
	 * @return vrai si l'élément décoré est null.
	 */
	public boolean estVide() {
		return this.getElement() == null;
	}

	/**
	 * Pour savoir si le point spécifié se trouve dans le "petit carré" permettant la
	 * redimension.
	 * 
	 * @param p_X Coordonnée en x du point à vérifier.
	 * @param p_Y Coordonnée en y du point à vérifier.
	 * @return Vrai si le point spécifié se situe dans le "petit carré" de redimension.
	 */
	public boolean estDansLeCoin(int p_X, int p_Y) {
		return this.getCarre().contient(p_X, p_Y);
	}

	/**
	 * Ajuste la position et les dimensions de l'élément décoré selon les dimensions
	 * actuelles et la position du décorateur.
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

	/**
	 * Ajuste la position du "petit carré" de sélection.
	 */
	private void ajusterCarre() {
		int decalage = ElementManipulable.TAILLE_CARRE >> 1;
		double x = this.m_X + this.m_Largeur - decalage;
		double y = this.m_Y + this.m_Hauteur - decalage;
		this.getCarre().setPosition(x, y);
	}

	/**
	 * Dessine un rectangle autour de l'élément décoré.
	 * Ne redessinne pas l'élément décoré.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		int x = (int) Math.min(this.getX(), this.getX() + this.m_Largeur);
		int y = (int) Math.min(this.getY(), this.getY() + this.m_Hauteur);
		int largeur = (int) Math.abs(this.m_Largeur);
		int hauteur = (int) Math.abs(this.m_Hauteur);
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
	 * Pour modifier la position de la forme selon le point de sélection.
	 * 
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
	 * Pour obtenir le "petit carre" de redimension.
	 * 
	 * @return le carre.
	 */
	public ElementGraphique getCarre() {
		return m_Carre;
	}
}
