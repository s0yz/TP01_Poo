package ca.csf.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * Contrôleur de fichier.
 */
public class GestionnaireFichier implements GestionnaireIO, EcouteurModeleGraphique {

	private File m_Fichier;
	private JFrame m_Fenetre;
	private ModeleElementGraphique m_Modele;
	private FormatFichier m_Format;
	private boolean m_Modifications = false;

	/**
	 * 
	 * @param p_Proprio parent du GestionnaireFichier
	 * @param p_Modele 
	 */
	public GestionnaireFichier(JFrame p_Proprio, ModeleElementGraphique p_Modele) {
		this.m_Modele = p_Modele;
		this.m_Fenetre = p_Proprio;
		this.m_Modele.ajouterEcouteur(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enregistrer(FormatFichier p_Format) {
		if (this.m_Fichier == null) {
			this.enregistrerSous(p_Format);
		} else {
			try {
				p_Format.enregistrer(this.m_Modele, this.m_Fichier);
			} catch (Exception e) {
				e.printStackTrace();
				this.afficherErreur("Erreur d'écriture.");
			}
			this.m_Modifications = false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enregistrerSous(FormatFichier p_Format) {
		SelecteurFichierExtensionUnique chooser = new SelecteurFichierExtensionUnique(".xml", "XML");
		if (chooser.showSaveDialog(this.m_Fenetre) == JFileChooser.APPROVE_OPTION) {
			try {
				File fichier = chooser.getSelectedFile();
				p_Format.enregistrer(this.m_Modele, fichier);
				this.m_Fichier = fichier;
				this.m_Modifications = false;
			} catch (Exception e) {
				e.printStackTrace();
				this.afficherErreur("Erreur d'écriture.");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ouvrir(FormatFichier p_Format) {
		if (this.verifierSauvegarde()) {
			SelecteurFichierExtensionUnique fc = new SelecteurFichierExtensionUnique(".xml", "XML");
			if (fc.showOpenDialog(this.m_Fenetre) == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				if (!fc.accept(file)) {
					this.afficherErreur("Type de fichier non-supporté.");
				}
				else {
					try {
						p_Format.ouvrir(this.m_Modele, file);
						this.m_Fichier = file;
						this.m_Modifications = false;
						this.m_Format = p_Format;
					} catch (Exception e) {
						e.printStackTrace();
						this.afficherErreur("Erreur de lecture.");
					}
				}				
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouveau() {
			this.m_Fichier = null;
			this.m_Modifications = false;
	}

	/**
	 * 
	 * 
	 */
	public boolean verifierSauvegarde() {
		if (this.m_Modifications) {
			String titre = this.m_Fichier == null ? this.m_Fenetre.getTitle() : this.m_Fichier.getName();
			String message = "Voulez-vous sauvegarder les modifications ?";
			int reponse = JOptionPane.showConfirmDialog(this.m_Fenetre, message, titre,
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (reponse == JOptionPane.YES_OPTION) {
				this.enregistrer(this.m_Format);
			} else {
				return reponse != JOptionPane.CANCEL_OPTION;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications() {
		this.m_Modifications = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications(ElementGraphique p_Element) {
		this.reagirModifications();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleTaille() {
		this.reagirModifications();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleCouleurDeFond() {
		this.reagirModifications();
	}

	/**
	 * 
	 * @param p_Message
	 */
	private void afficherErreur(String p_Message) {
		JOptionPane.showMessageDialog(this.m_Fenetre, p_Message,
				this.m_Fichier == null ? this.m_Fenetre.getTitle() : this.m_Fichier.getName(), JOptionPane.OK_OPTION);
	}
}
