package ca.csf.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;
import ca.csf.ui.SelecteurFichierExtensionUnique;

/**
 * Contrôleur de fichier surveillant les modifications du modèle.
 * 
 * @see ModeleElementGraphique
 */
public class GestionnaireFichier implements GestionnaireIO, EcouteurModeleGraphique {

	private File m_Fichier;
	private JFrame m_Parent;
	private ModeleElementGraphique m_Modele;
	private FormatFichier m_Format;
	private boolean m_Modifications = false;

	/**
	 * 
	 * @param p_Parent parent du GestionnaireFichier
	 * @param p_Modele modèle à gérer.
	 */
	public GestionnaireFichier(JFrame p_Parent, ModeleElementGraphique p_Modele) {
		if (p_Modele == null) {
			throw new IllegalArgumentException("p_Modele est null");
		}
		this.m_Modele = p_Modele;
		this.m_Parent = p_Parent;
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
		String ext = p_Format.getExtension();
		String nom = ext.substring(1).toUpperCase();
		SelecteurFichierExtensionUnique chooser = new SelecteurFichierExtensionUnique(ext, nom);
		if (chooser.showSaveDialog(this.m_Parent) == JFileChooser.APPROVE_OPTION) {
			try {
				File fichier = chooser.getSelectedFile();
				p_Format.enregistrer(this.m_Modele, fichier);
				this.m_Fichier = fichier;
				this.m_Format = p_Format;
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
			if (fc.showOpenDialog(this.m_Parent) == JFileChooser.APPROVE_OPTION) {
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
	 */
	public boolean verifierSauvegarde() {
		if (this.m_Modifications) {
			String titre = this.m_Fichier == null ? this.m_Parent.getTitle() : this.m_Fichier.getName();
			String message = "Voulez-vous sauvegarder les modifications ?";
			int reponse = JOptionPane.showConfirmDialog(this.m_Parent, message, titre,
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
	 * Pour savoir si le fichier est enregistré.
	 * @return faux si des modifications n'ont pas été enregistrées, sinon false.
	 */
	public boolean estEnregistrer() {
		return !this.m_Modifications;
	}

	/**
	 * Pour modifier le fichier.
	 * 
	 * @param p_fichier La nouvelle valeur.
	 * @throws IllegalArgumentException si p_Fichier est null.
	 */
	public void setFichier(File p_fichier) {
		if (p_fichier == null) {
			throw new IllegalArgumentException("p_fichier est null");
		}
		this.m_Fichier = p_fichier;
	}

	/**
	 * Pour modifier le format.
	 * 
	 * @param p_format La nouvelle valeur.
	 */
	public void setFormat(FormatFichier p_format) {
		if (p_format == null) {
			throw new IllegalArgumentException("p_format est null");
		}
		this.m_Format = p_format;
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
	 * Affiche un message d'erreur dans une boîte de dialogue.
	 * 
	 * @param p_Message le message à afficher.
	 */
	private void afficherErreur(String p_Message) {
		JOptionPane.showMessageDialog(this.m_Parent, p_Message,
				this.m_Fichier == null ? this.m_Parent.getTitle() : this.m_Fichier.getName(), JOptionPane.OK_OPTION);
	}
}
