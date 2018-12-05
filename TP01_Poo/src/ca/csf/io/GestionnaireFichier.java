package ca.csf.io;

import java.awt.Color;
import java.io.File;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * 
 * 
 * @author
 *
 */
public class GestionnaireFichier implements GestionnaireIO, EcouteurModeleGraphique {
	
	private File m_File;
	private ModeleElementGraphique m_Modele;
	private boolean m_Modifications = false;
	
	public GestionnaireFichier(ModeleElementGraphique p_Modele) {
		this.m_Modele = p_Modele;
		this.m_Modele.ajouterEcouteur(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enregistrer(FormatFichier p_FormatFichier) {
		// TODO
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ouvrir(File p_Fichier) {
		this.m_File = p_Fichier;
		this.m_Modifications = false;
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

	@Override
	public void reagirNouvelleTaille(int p_Hauteur, int p_Largeur) {
		this.reagirModifications();
	}

	@Override
	public void reagirNouvelleCouleurDeFond(Color p_Couleur) {
		this.reagirModifications();
	}

	
}
