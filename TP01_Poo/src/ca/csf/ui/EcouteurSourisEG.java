package ca.csf.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.FormeFactory;
import ca.csf.modele.ModeleElementGraphique;

/**
 * Listener de MouseEvent. Permet de manipuler des {@code ElementGraphique} avec
 * la souris.
 */
class EcouteurSourisEG extends MouseAdapter {

	private ModeleElementGraphique m_Modele;

	private EspaceTravail m_Espace;
	
	/**
	 * 
	 */
	private boolean m_Deplacement = false;
	
	/**
	 * 
	 */
	private boolean m_Redimension = false;

	/*
	 * Propriétés de la forme qui sera ajouter lors de l'évènement mousePressed.
	 */
	private String m_Nom = "";
	private Color m_Couleur = Color.RED;
	private Color m_CouleurTrait = Color.BLACK;
	private int m_LargeurTrait = 2;
	
	public EcouteurSourisEG(EspaceTravail p_Espace) {
		this.m_Espace = p_Espace;
		this.m_Modele = p_Espace.getModele();
	}

	public void setNom(String p_Nom) {
		this.m_Nom = p_Nom;
	}

	public void setLargeurTrait(int p_LargeurTrait) {
		this.m_LargeurTrait = p_LargeurTrait;
	}

	public void setCouleur(Color p_Couleur) {
		this.m_Couleur = p_Couleur;
	}

	public void setCouleurTrait(Color p_CouleurTrait) {
		this.m_CouleurTrait = p_CouleurTrait;
	}

	/**
	 * Sélectionne ou ajoute un {@code ElementGraphique}.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent p_e) {
		if (this.m_Nom == "") {
			ElementManipulable element = this.m_Espace.getSelection();
			if (element != null && element.estDansLeCoin(p_e.getX(), p_e.getY())) {
				this.m_Redimension = true;
			} else {
				this.selectionner(p_e);
				element = this.m_Espace.getSelection();
				if (element != null && element.contient(p_e.getX(), p_e.getY())) {
					this.m_Deplacement = true;
				}
			}
		} else {
			this.ajouter(p_e);
		}
	}

	/**
	 * 
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent p_e) {
		ElementGraphique selection = this.m_Espace.getSelection();
		if (selection != null) {
			if (selection.getLargeur() == 0 && selection.getHauteur() == 0) {
				int deplacement = -1 * (FenetrePrincipale.TAILLE_DEFAUT >> 1);
				selection.setLargeur(FenetrePrincipale.TAILLE_DEFAUT);
				selection.setHauteur(FenetrePrincipale.TAILLE_DEFAUT);
				selection.deplacer(deplacement, deplacement);
			}
		}
		this.m_Deplacement = this.m_Redimension = false;
	}

	/**
	 * 
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mouseDragged(MouseEvent p_e) {
		ElementGraphique selection = this.m_Espace.getSelection();
		if (selection != null) {
			// Redimension
			if (this.m_Redimension) {
				double largeur = p_e.getX() - selection.getX();
				double hauteur = p_e.getY() - selection.getY();
				selection.setDimension(largeur, hauteur);
				// Déplacement
			} else if (this.m_Deplacement) {
				selection.setPosition(p_e.getX(), p_e.getY());
			}
		}
	}

	/**
	 * Sélection la première forme sous le clic de la souris.
	 */
	private void selectionner(MouseEvent p_e) {
		ElementGraphique element = this.m_Modele.get(p_e.getX(), p_e.getY());
		ElementManipulable selection = new ElementManipulable(element, p_e.getX(), p_e.getY());
		this.m_Espace.setSelection(selection);
		this.m_Deplacement = selection != null;
	}

	/**
	 * Ajoute une forme à la position de la souris selon les propriété actuelles.
	 */
	private void ajouter(MouseEvent p_e) {
		FormeFactory factory = FormeFactory.getInstance();
		ElementGraphique forme = factory.getForme(this.m_Nom);
		forme.setPosition(p_e.getX(), p_e.getY());
		forme.setCouleur(this.m_Couleur);
		forme.setLargeurTrait(this.m_LargeurTrait);
		forme.setCouleurTrait(this.m_CouleurTrait);
		this.m_Modele.ajouter(forme);
		forme = this.m_Modele.getSelection();
		this.m_Espace.setSelection(new ElementManipulable(forme));
		this.m_Redimension = true;
	}
}
