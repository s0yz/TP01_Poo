package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import ca.csf.modele.ModeleGraphiques;

public interface FormatFichier {

	public void enregistrer(ModeleGraphiques p_modele);
	public String ouvrir(File p_Fichier)throws XMLStreamException, FileNotFoundException;
	
}
