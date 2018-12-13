package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.csf.modele.ModeleElementGraphique;

/**
 * Fournit des méthodes pour d'IO pour des {@link ModeleElementGraphique}.
 */
public interface FormatFichier {

	/**
	 * Pour enregistrer un {@link ModeleElementGraphique}.
	 * 
	 * @param p_Modele modèle à enregistrer.
	 * @param p_file   fichier de sortie.
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void enregistrer(ModeleElementGraphique p_Modele, File p_file) throws IOException, Exception;

	/**
	 * Pour ouvrir un fichier.
	 * 
	 * @param p_Modele modèle où charger le fichier.
	 * @param p_file   fichier à ouvrir.
	 * 
	 * @throws Exception             en cas d'erreur d'écriture.
	 * @throws FileNotFoundException si le fichier n'est pas trouvé.
	 */
	public void ouvrir(ModeleElementGraphique p_Modele, File p_file) throws Exception, FileNotFoundException;

	/**
	 * Pour obtenir l'extension du fichier d'entré/sortie, incluant le premier ".".
	 * exemple : ".txt".
	 * 
	 * @return l'extension du fichier d'entré/sortie, incluant le premier "."
	 */
	public String getExtension();
}
