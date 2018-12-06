package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.csf.modele.ModeleElementGraphique;

public interface FormatFichier {
	public void enregistrer(ModeleElementGraphique p_Elements, File p_file) throws IOException, Exception;
	public void ouvrir(ModeleElementGraphique p_graph, File p_file) throws Exception, FileNotFoundException;
}
