package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.ModeleGraphiques;

public interface FormatFichier {

	public void enregistrer(ModeleGraphiques p_Elements, File p_file);
	public void ouvrir(ModeleGraphiques p_graph, File p_file)throws XMLStreamException, FileNotFoundException;
	
}
