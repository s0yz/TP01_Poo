package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Forme2DFactory;
import ca.csf.modele.ModeleElementGraphique;

public class FormatXML implements FormatFichier {

	private Forme2DFactory m_factory;

	public FormatXML() {
	}

	public FormatXML(Forme2DFactory p_Factory) {
		this.m_factory = p_Factory;
	}

	@Override
	public void enregistrer(ModeleElementGraphique p_Modele, File p_Fichier) throws IOException, XMLStreamException {
		XMLStreamWriter doc = null;
		FileWriter output = new FileWriter(p_Fichier);
		doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
		doc.writeStartDocument();
		doc.writeStartElement("forme");
		for (ElementGraphique elementGraphique : p_Modele) {
			doc.writeStartElement(elementGraphique.getNom());
			doc.writeAttribute("X", Integer.toString(elementGraphique.getX()));
			doc.writeAttribute("Y", Integer.toString(elementGraphique.getY()));
			doc.writeAttribute("hauteur", Integer.toString(elementGraphique.getHauteur()));
			doc.writeAttribute("largeur", Integer.toString(elementGraphique.getLargeur()));
			if (elementGraphique.getCouleur() != null) {
				doc.writeAttribute("couleur", elementGraphique.getCouleur().toString());
			}
			doc.writeEndElement();
		}
		doc.writeEndElement();
		doc.writeEndDocument();
		if (doc != null) {
			doc.flush();
			doc.close();
		}
	}

	@Override
	public void ouvrir(ModeleElementGraphique p_Modele, File p_Fichier) throws XMLStreamException, FileNotFoundException {
		XMLStreamReader doc = null;
		ArrayList<ElementGraphique> temp = new ArrayList<>();
		FileReader input = new FileReader(p_Fichier);
		doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
		// Pour passer par-dessus le Start Document
		doc.next();
		// Le document doit commencer par un <forme>
		if (!doc.getLocalName().equals("forme")) {
			throw new XMLStreamException("Pas le bon element racine : " + doc.getLocalName());
		}
		// Pour passer par-dessus <forme>
		doc.next();
		while (doc.isStartElement()) {
			ElementGraphique EG = m_factory.getForme(doc.getLocalName());
			String X = doc.getAttributeValue("", "X");
			String Y = doc.getAttributeValue("", "Y");
			EG.setPosition(Integer.parseInt(X), Integer.parseInt(Y));
			String hauteur = doc.getAttributeValue("", "hauteur");
			String largeur = doc.getAttributeValue("", "largeur");
			EG.setDimension(Integer.parseInt(largeur), Integer.parseInt(hauteur));
			doc.next();
			doc.next();
			temp.add(EG);
		}
		p_Modele.remplir(temp);
	}
}
