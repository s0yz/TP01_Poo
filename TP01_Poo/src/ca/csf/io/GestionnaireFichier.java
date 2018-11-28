package ca.csf.io;

import java.io.File;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.ModeleGraphiques;
import ca.csf.ui.FenetrePrincipale;

/**
 * 
 * 
 * @author
 *
 */
public class GestionnaireFichier  implements GestionnaireIO {
	
	public GestionnaireFichier(ModeleGraphiques p_Modele) {
		
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enregistrer() {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showSaveDialog(this.FenetrePrincipale());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ouvrir(File p_Fichier) {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showOpenDialog(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications() {
		// TODO
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelElement(ElementGraphique p_Element) {
		// TODO
	}
}
