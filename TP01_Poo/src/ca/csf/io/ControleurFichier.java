package ca.csf.io;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import ca.csf.modele.ModeleElementGraphique;

public class ControleurFichier {

	private ModeleElementGraphique m_graph;

	public ControleurFichier(ModeleElementGraphique p_modele) {
		m_graph = p_modele;
	}

	public void enregistrer(FormatFichier p_format, File p_file) throws IOException, XMLStreamException {
		try {
			p_format.enregistrer(m_graph, p_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ouvrir(FormatFichier p_format, File p_file) {
		try {
			p_format.ouvrir(m_graph, p_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
