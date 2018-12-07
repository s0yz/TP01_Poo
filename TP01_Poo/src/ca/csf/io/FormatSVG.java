package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Forme2DFactory;
import ca.csf.modele.ModeleElementGraphique;

public class FormatSVG implements FormatFichier{

	private Forme2DFactory m_factory;

	public FormatSVG() {
	}

	public FormatSVG(Forme2DFactory p_Factory) {
		this.m_factory = p_Factory;
	}
	
	@Override
	public void enregistrer(ModeleElementGraphique p_Modele, File p_Fichier) throws IOException, Exception {
		// TODO Auto-generated method stub
		XMLStreamWriter doc = null;
		FileWriter output = new FileWriter(p_Fichier);
		doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
		doc.writeStartDocument();
		
		doc.writeStartElement("svg");
		doc.writeAttribute("xlmns","http://www.w3.org/2000/svg");
		doc.writeAttribute("version","1.1");
		doc.writeAttribute("width","300");
		doc.writeAttribute("height","200");
		
		doc.writeStartElement("title");
		doc.writeCharacters("hello world");
		doc.writeEndElement();
		
		doc.writeStartElement("desc");
		doc.writeCharacters("vraiment bien");
		doc.writeEndElement();
		
		
		/*
		for (ElementGraphique elementGraphique : p_Modele) {
			doc.writeStartElement(elementGraphique.getNom());
			doc.writeAttribute("X", Integer.toString(elementGraphique.getX()));
			doc.writeAttribute("Y", Integer.toString(elementGraphique.getY()));
			doc.writeAttribute("hauteur", Integer.toString(elementGraphique.getHauteur()));
			doc.writeAttribute("largeur", Integer.toString(elementGraphique.getLargeur()));
			doc.writeAttribute("trait", Integer.toString(elementGraphique.getLargeurTrait()));
			doc.writeAttribute("traitcolor", Integer.toString(elementGraphique.getCouleurTrait().getRGB()));
			if (elementGraphique.getCouleur() != null) {
				doc.writeAttribute("couleur", Integer.toString(elementGraphique.getCouleur().getRGB()));
			} else {doc.writeAttribute("couleur", "null");}
			doc.writeEndElement();}*/
		
		doc.writeEndElement();
		doc.writeEndDocument();
		if (doc != null) {
			doc.flush();
			doc.close();
		}
	}

	@Override
	public void ouvrir(ModeleElementGraphique p_graph, File p_file) throws Exception, FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
