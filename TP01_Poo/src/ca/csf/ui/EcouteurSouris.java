package ca.csf.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.ElementManipulable;
import ca.csf.formes.FormeFactory;
import ca.csf.modele.ModeleElementGraphique;

/**
 * Listener de MouseEvent permettant de manipuler les {@code ElementGraphique}.
 */
class ManipulateurElement extends MouseAdapter {

	/*
	 * Propriétés de la forme qui sera ajouter lors de l'évènement mousePressed.
	 */
	private String m_Nom = "";
	private int m_LargeurTrait = 2;
	private Color m_Couleur = Color.RED;
	private Color m_CouleurTrait = Color.BLACK;
	
	private ModeleElementGraphique m_Modele;
	private EspaceTravail m_Espace;
	
	public ManipulateurElement(EspaceTravail p_Espace) {
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
		if (this.m_Nom  == "") {
			ElementManipulable element = this.m_Espace.getSelection();
			if(element == null || !element.estDansLeCoin(p_e.getX(), p_e.getY())) {
				this.selectionner(p_e);
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
		ElementManipulable selection = this.m_Espace.getSelection();
		if (selection != null) {
			if (selection.getLargeur() == 0 && selection.getHauteur() == 0) {
				int deplacement = -1 * (FenetrePrincipale.TAILLE_DEFAUT >> 1);
				selection.setLargeur(FenetrePrincipale.TAILLE_DEFAUT);
				selection.setHauteur(FenetrePrincipale.TAILLE_DEFAUT);
				selection.deplacer(deplacement, deplacement);
			}
		}
	}

	/**
	 * 
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mouseDragged(MouseEvent p_e) {
		ElementManipulable selection = this.m_Espace.getSelection();
		if (selection != null) {
			// Redimension
			if (this.m_Nom != "" || selection.estDansLeCoin(p_e.getX(), p_e.getY())) {
				double largeur = p_e.getX() - selection.getX();
				double hauteur = p_e.getY() - selection.getY();
				selection.setDimension(largeur, hauteur);
			// Déplacement
			} else if (selection.contient(p_e.getX(), p_e.getY())) {
				selection.setPosition(p_e.getX(), p_e.getY());					
			}
		}
	}

	/**
	 * 
	 */
	private void selectionner(MouseEvent p_e) {
		ElementGraphique element = this.m_Modele.get(p_e.getX(), p_e.getY());
		ElementManipulable selection = new ElementManipulable(element, p_e.getX(), p_e.getY());
		this.m_Espace.setSelection(selection);
		if (selection != null) {
			//FenetrePrincipale.this.spin_LargeurTrait.setValue(selection.getLargeurTrait());
		}
	}

	/**
	 * 
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
	}
}
