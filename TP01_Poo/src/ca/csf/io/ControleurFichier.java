package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.xml.stream.XMLStreamException;

import ca.csf.formes.*;
import ca.csf.modele.ModeleDessin;

public class ControleurFichier {

	//private ArrayList<ElementGraphique> m_Elements;
	private ModeleDessin m_graph;
	
	public ControleurFichier(ModeleDessin p_modele) {
		// TODO Auto-generated constructor stub
		m_graph = p_modele;
		/* for (ElementGraphique elementGraphique : p_modele) {
			m_Elements.add(elementGraphique);
		}*/
		 
			
	}
	
	public void enregistrer(FormatFichier p_format, File p_file) {
		
		p_format.enregistrer(m_graph, p_file);
	}
	
	public void ouvrir(FormatFichier p_format, File p_file) {
		
		try {
			p_format.ouvrir(m_graph,p_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
