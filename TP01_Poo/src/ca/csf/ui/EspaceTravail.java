package ca.csf.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * Classe représentant l'espace de dessin.
 * 
 * Une interface était prévue pour cette classe. FenetrePrincipale aurait alors
 * utilisée cette interface comme donnée membre. Nous lui aurions ensuite donné
 * cette classe dans le main en l'initialisant ou nous lui aurions ajouter une
 * factory method.
 * 
 */
public class EspaceTravail extends JPanel implements EcouteurModeleGraphique {

	private static final long serialVersionUID = -7570189304007187337L;

	/*
	 * Actions
	 */
	private static final String vkGauche = "vkGauche";
	private static final String vkDroite = "vkDroite";
	private static final String vkHaut = "vkHaut";
	private static final String vkBas = "vkBas";
	private static final String vkDelete = "vkDelete";

	/**
	 * Modèle
	 */
	private ModeleElementGraphique m_Modele;

	/**
	 * Element sélectionné.
	 */
	private ElementManipulable m_Selection = new ElementManipulable(null, 0, 0);

	/**
	 * Construit un EspaceTravail
	 * 
	 * @param p_Modele
	 */
	public EspaceTravail(ModeleElementGraphique p_Modele) {
		this.setModeleGraphique(p_Modele);
		this.parametrerActionsTouche();
		this.setOpaque(true);
	}

	/**
	 * Pour obtenir le modèle.
	 * 
	 * @return le modèle.
	 */
	public ModeleElementGraphique getModele() {
		return this.m_Modele;
	}

	/**
	 * Pour obtenir la selection.
	 * 
	 * @return l'élément sélectionner selection.
	 */
	public ElementGraphique getSelection() {
		assert (!(this.m_Selection.getElement() instanceof ElementManipulable)) : "L'élément est encore décoré";
		return this.m_Selection.getElement();
	}

	/**
	 * Pour changer le modèle.
	 * 
	 * @param p_Modele le nouveau modèle.
	 */
	public void setModeleGraphique(ModeleElementGraphique p_Modele) {
		int largeur;
		int hauteur;
		if (p_Modele == null) {
			throw new IllegalArgumentException("p_modeleGraphique est null");
		}
		if (this.m_Modele != null) {
			this.m_Modele.retirerEcouteur(this);
		}
		this.m_Modele = p_Modele;
		this.m_Modele.ajouterEcouteur(this);
		largeur = (int) Math.round(this.m_Modele.getLargeur());
		hauteur = (int) Math.round(this.m_Modele.getHauteur());
		this.setBackground(this.m_Modele.getArrierePlan());
		this.setPreferredSize(new Dimension(largeur, hauteur));
		this.repaint();
	}

	/**
	 * Pour modifier la selection.
	 * 
	 * @param p_selection l'élément à selectionner.
	 * @throws IllegalArgumentException si p_selection est null.
	 */
	public void setSelection(ElementGraphique p_Element) {
		if (p_Element == null) {
			throw new IllegalArgumentException("p_selection est null");
		}
		assert (!(p_Element instanceof ElementManipulable)) : "p_Element est déjà décoré";
		this.m_Selection = new ElementManipulable(p_Element);
		this.repaint();
	}

	/**
	 * Pour modifier la selection. Utilisée par l'{@code EcouteurSourisEG}.
	 * 
	 * @param p_Element l'élément à selectionner
	 */
	private void setSelection(ElementManipulable p_Element) {
		assert (p_Element != null) : "p_Element est null";
		this.m_Selection = p_Element;
		this.repaint();
	}

	/**
	 * Pour déplacer la sélection.
	 * 
	 * @param p_X déplacement en x.
	 * @param p_Y déplacement en y.
	 */
	private void deplacerSelection(int p_X, int p_Y) {
		if (!this.m_Selection.estVide()) {
			this.m_Selection.deplacer(p_X, p_Y);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics p_Graphics) {
		Graphics2D graphics2d = (Graphics2D) p_Graphics;
		super.paintComponent(p_Graphics);
		this.m_Modele.forEach(e -> e.dessiner(graphics2d));
		if (!this.m_Selection.estVide()) {
			this.m_Selection.dessiner(graphics2d);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications() {
		if (this.m_Modele.getCompte() == 0 || this.m_Modele.getIndiceDe(this.m_Selection) == -1) {
			this.m_Selection.set(null);
		}
		this.reagirNouvelleTaille();
		this.reagirNouvelleCouleurDeFond();
		this.repaint();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications(ElementGraphique p_Element) {
		this.redessinerElement(p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleTaille() {
		int largeur = (int) Math.floor(this.m_Modele.getLargeur());
		int hauteur = (int) Math.floor(this.m_Modele.getHauteur());
		this.setPreferredSize(new Dimension(largeur, hauteur));
		this.updateUI();
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleCouleurDeFond() {
		this.setBackground(this.m_Modele.getArrierePlan());
	};

	/**
	 * Redessine la zone contenant l'élément spécifié en tenant compte de la largeur
	 * du trait et des dimensions négatives.
	 * 
	 * @param p_Element
	 */
	private void redessinerElement(ElementGraphique p_Element) {
		if (p_Element != null) {
			int x = (int) Math.round(Math.min(p_Element.getX(), p_Element.getX() + p_Element.getLargeur()));
			int y = (int) Math.round(Math.min(p_Element.getY(), p_Element.getY() + p_Element.getHauteur()));
			int largeur = (int) Math.round(Math.abs(p_Element.getLargeur()));
			int hauteur = (int) Math.round(Math.abs(p_Element.getHauteur()));
			x -= p_Element.getLargeurTrait();
			y -= p_Element.getLargeurTrait();
			largeur += 2 * p_Element.getLargeurTrait();
			hauteur += 2 * p_Element.getLargeurTrait();
			this.repaint(x, y, largeur, hauteur);
		}
	}

	/**
	 * Associe des touches du clavier à des actions, soit les flèches pour déplacer
	 * les formes et Delete pur les supprimer.
	 */
	private void parametrerActionsTouche() {
		ActionMap actionMap = this.getActionMap();
		InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkGauche);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkDroite);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkHaut);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkBas);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), vkDelete);
		actionMap.put(vkGauche, new ActionTouche(e -> {
			this.deplacerSelection(-10, 0);
		}));
		actionMap.put(vkDroite, new ActionTouche(e -> {
			this.deplacerSelection(10, 0);
		}));
		actionMap.put(vkHaut, new ActionTouche(e -> {
			this.deplacerSelection(0, -10);
		}));
		actionMap.put(vkBas, new ActionTouche(e -> {
			this.deplacerSelection(0, 10);
		}));
		actionMap.put(vkDelete, new ActionTouche(e -> {
			if (!this.m_Selection.estVide()) {
				this.m_Modele.retirer(this.m_Selection.getElement());
				this.m_Selection.set(null, 0, 0);
				this.redessinerElement(this.m_Selection.getCarre());
			}
		}));
	}

	/**
	 * Listener de MouseEvent. Permet de manipuler les {@code ElementGraphique} d'un
	 * {@code ModeleElementGraphique} avec la souris par le biais de
	 * l'{@code EspaceTravail}.
	 */
	public class EcouteurSourisEG extends MouseAdapter {

		private static final int TAILLE_DEFAUT = 50;

		/**
		 * Modèle contenant les éléments manipulés.
		 */
		private ModeleElementGraphique m_Modele;

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
		public EcouteurSourisEG() {
			this.m_Modele = EspaceTravail.this.m_Modele;
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
				ElementManipulable element = EspaceTravail.this.m_Selection;
				if (element != null && element.estDansLeCoin(p_e.getX(), p_e.getY())) {
					this.m_Redimension = true;
				} else {
					this.selectionner(p_e);
					element = EspaceTravail.this.m_Selection;
					if (!element.estVide() && element.contient(p_e.getX(), p_e.getY())) {
						this.m_Deplacement = true;
					}
				}
			} else {
				this.ajouter(p_e);
			}
		}

		/**
		 * Met fin au déplacement ou à la redimension. En case d'ajout d'lélément sans
		 * redimensionne le nouvel élément si nécessaire et s'il y a lieu.
		 * 
		 * {@inheritDoc}
		 */
		@Override
		public void mouseReleased(MouseEvent p_e) {
			ElementGraphique selection = EspaceTravail.this.m_Selection;
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
			ElementGraphique selection = EspaceTravail.this.m_Selection;
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
			EspaceTravail.this.setSelection(selection);
			this.m_Deplacement = selection != null;
		}

		/**
		 * Ajoute une forme à la position de la souris selon les propriété actuelles.
		 */
		private void ajouter(MouseEvent p_e) {
			ElementGraphique forme = this.m_Fournisseur.get();
			forme.setPosition(p_e.getX(), p_e.getY());
			this.m_Modele.ajouter(forme);
			forme = this.m_Modele.getDernier();
			EspaceTravail.this.setSelection(new ElementManipulable(forme));
			this.m_Redimension = true;
		}
	}
}
