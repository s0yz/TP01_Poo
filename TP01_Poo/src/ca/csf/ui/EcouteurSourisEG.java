package ca.csf.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * Listener de MouseEvent. Permet de manipuler les {@code ElementGraphique} d'un
 * {@code ModeleElementGraphique} avec la souris par le biais d'un
 * {@code EspaceTravail}.
 */
class EcouteurSourisEG extends MouseAdapter {

	private static final int TAILLE_DEFAUT = 50;

	/**
	 * Modèle contenant les éléments manipulés.
	 */
	private ModeleElementGraphique m_Modele;

	/**
	 * Vue affichant les éléments à manipulés.
	 */
	private EspaceTravail m_Espace;

	/**
	 * Indique si un déplacement est en cours.
	 */
	private boolean m_Deplacement = false;

	/**
	 * Indique si une redimension est en cours.
	 */
	private boolean m_Redimension = false;

	/**
	 * Fonction générant l'élément ajouter lors d'un mousePressed.
	 */
	private Supplier<ElementGraphique> m_Fournisseur;

	/**
	 * Construit un {@code EcouteurSourisEG}.
	 * 
	 * @param p_Espace l'espace de travail affichant les formes.
	 * @Precondition p_Espace n'est pas null.
	 */
	public EcouteurSourisEG(EspaceTravail p_Espace) {
		assert (p_Espace != null);
		this.m_Espace = p_Espace;
		this.m_Modele = p_Espace.getModele();
	}

	/**
	 * Pour modifier la {@code Function} générant l'{@code ElementGraphique} lors
	 * d'un mousePressed. Si p_fournisseur a une valeur null, l'évènement
	 * mousePressed sélectionnera plutôt le premier élément situé sous le clic de la
	 * souris.
	 * 
	 * @param p_fournisseur Le nouveau fournisseur ou null.
	 * @throws IllegalArgumentException si p_fournisseur.get() == null.
	 */
	public void setFournisseur(Supplier<ElementGraphique> p_fournisseur) {
		if (p_fournisseur != null && p_fournisseur.get() == null) {
			throw new IllegalArgumentException("p_fournisseur retourne null");
		}
		this.m_Fournisseur = p_fournisseur;
	}

	/**
	 * Sélectionne ou ajoute un {@code ElementGraphique}.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mousePressed(MouseEvent p_e) {
		if (this.m_Fournisseur == null) {
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
	 * Met fin au déplacement ou à la redimension. En case d'ajout d'lélément sans redimensionne le nouvel élément
	 * si nécessaire et s'il y a lieu.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(MouseEvent p_e) {
		ElementGraphique selection = this.m_Espace.getSelection();
		if (selection != null) {
			if (selection.getLargeur() == 0 && selection.getHauteur() == 0) {
				int deplacement = -1 * (EcouteurSourisEG.TAILLE_DEFAUT >> 1);
				selection.setLargeur(EcouteurSourisEG.TAILLE_DEFAUT);
				selection.setHauteur(EcouteurSourisEG.TAILLE_DEFAUT);
				selection.deplacer(deplacement, deplacement);
			}
		}
		this.m_Deplacement = this.m_Redimension = false;
	}

	/**
	 * Déplace ou redimensionne un {@code ElementGraphique}.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void mouseDragged(MouseEvent p_e) {
		ElementGraphique selection = this.m_Espace.getSelection();
		if (selection != null) {
			if (this.m_Redimension) {
				double largeur = p_e.getX() - selection.getX();
				double hauteur = p_e.getY() - selection.getY();
				selection.setDimension(largeur, hauteur);
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
		ElementGraphique forme = this.m_Fournisseur.get();
		forme.setPosition(p_e.getX(), p_e.getY());
		this.m_Modele.ajouter(forme);
		forme = this.m_Modele.getSelection();
		this.m_Espace.setSelection(new ElementManipulable(forme));
		this.m_Redimension = true;
	}
}
