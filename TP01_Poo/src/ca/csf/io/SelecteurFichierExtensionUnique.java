package ca.csf.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 
 */
public class SelecteurFichierExtensionUnique extends JFileChooser {
	
	private static final long serialVersionUID = 6807044687230376943L;
	
	/**
	 * Expression utilisée la validation des extensions.
	 */
	public static final String EXT_REGEX = "^(\\.[A-Za-z]+)+$";
	
	/**
	 * Extension supportée par {@code SelecteurFichierExtensionUnique}.
	 */
	private String m_Extension;
	
	/**
	 * Construit un selecteur de fichier acceptant l'extension spécifiée.
	 * 
	 * @param p_Extension l'extension, incluant le point. ex : ".png".
	 * @param p_Nom nom de l'extension. ex : "PNG".
	 */
	public SelecteurFichierExtensionUnique(String p_Extension, String p_Nom) {
		if (p_Extension == null || !p_Extension.matches(EXT_REGEX)) {
			throw new IllegalArgumentException("Extension invalide : " + p_Extension);
		}
		this.m_Extension = p_Extension;
		this.setAcceptAllFileFilterUsed(false);
		this.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return String.format("Fichier %s (*%s)", p_Nom, p_Extension.toLowerCase());// Format (*SVG)
			}
			@Override
			public boolean accept(File p_Fichier) {
				return p_Fichier.isDirectory() || p_Fichier.getPath().endsWith(p_Extension);
			}
		});
	}
	
	/**
	 * Retourne le fichier sélectionné en ajoutant l'extension ni nécessaire.
	 * 
	 * @return le fichier sélectionné.
	 */
	@Override
	public File getSelectedFile() {
		File fichier = super.getSelectedFile();
		if (fichier != null && !this.accept(fichier)) {
			fichier = new File(super.getSelectedFile() + this.m_Extension);
		}				
		return fichier;
	}
}
