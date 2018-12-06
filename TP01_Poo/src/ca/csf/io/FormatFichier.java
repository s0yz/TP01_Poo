package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import ca.csf.modele.ModeleDessin;

public interface FormatFichier {
	public void enregistrer(ModeleDessin p_Elements, File p_file);
	public void ouvrir(ModeleDessin p_graph, File p_file) throws XMLStreamException, FileNotFoundException;
}
