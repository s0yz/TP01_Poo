package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import ca.csf.formes.ElementGraphique;

public interface FormatFichier {

	public void enregistrer(Iterable<ElementGraphique> p_Elements);
	public String ouvrir(File p_Fichier)throws XMLStreamException, FileNotFoundException;
	
}
