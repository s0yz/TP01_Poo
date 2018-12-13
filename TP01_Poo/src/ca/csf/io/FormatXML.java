package ca.csf.io;

import java.awt.Color;
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
import ca.csf.formes.UsineElementGraphique;
import ca.csf.modele.ModeleElementGraphique;

public class FormatXML implements FormatFichier {

	private UsineElementGraphique m_factory;

	public FormatXML(UsineElementGraphique p_Factory) {
		if (p_Factory == null) {
			throw new IllegalArgumentException("p_factory est null, constructeur FormatXML");
		}
		this.m_factory = p_Factory;
	}

	public UsineElementGraphique getFactory() {
	return m_factory;
	}
	
	@Override
	public void enregistrer(ModeleElementGraphique p_Modele, File p_Fichier) throws IOException, XMLStreamException {
		XMLStreamWriter doc = null;
		FileWriter output = new FileWriter(p_Fichier);
		doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
		doc.writeStartDocument();
		doc.writeStartElement("dessin");
		doc.writeStartElement("fond");
		doc.writeAttribute("hauteur", Double.toString(p_Modele.getHauteur()));
		doc.writeAttribute("largeur", Double.toString(p_Modele.getLargeur()));
		doc.writeAttribute("couleur", Integer.toString(p_Modele.getArrierePlan().getRGB()));

		doc.writeStartElement("forme");
		for (ElementGraphique elementGraphique : p_Modele) {
			doc.writeStartElement(elementGraphique.getNom());
			doc.writeAttribute("X", Double.toString(elementGraphique.getX()));
			doc.writeAttribute("Y", Double.toString(elementGraphique.getY()));
			doc.writeAttribute("hauteur", Double.toString(elementGraphique.getHauteur()));
			doc.writeAttribute("largeur", Double.toString(elementGraphique.getLargeur()));
			doc.writeAttribute("trait", Integer.toString(elementGraphique.getLargeurTrait()));
			doc.writeAttribute("traitcolor", Integer.toString(elementGraphique.getCouleurTrait().getRGB()));
			if (elementGraphique.getCouleur() != null) {
				doc.writeAttribute("couleur", Integer.toString(elementGraphique.getCouleur().getRGB()));
			} else {
				doc.writeAttribute("couleur", "null");
			}
			doc.writeEndElement();
		}
		doc.writeEndElement();
		doc.writeEndElement();
		doc.writeEndElement();
		doc.writeEndDocument();
		if (doc != null) {
			doc.flush();
			doc.close();
		}
	}

	@Override
	public void ouvrir(ModeleElementGraphique p_Modele, File p_Fichier)
			throws XMLStreamException, FileNotFoundException {
		XMLStreamReader doc = null;
		ArrayList<ElementGraphique> temp = new ArrayList<>();
		FileReader input = new FileReader(p_Fichier);
		doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
		doc.next();
		if (!doc.getLocalName().equals("dessin")) {
			throw new XMLStreamException("Pas le bon element racine : " + doc.getLocalName());
		}
		doc.next();
		double haut = Double.parseDouble(doc.getAttributeValue("", "hauteur"));
		double larg = Double.parseDouble(doc.getAttributeValue("", "largeur"));
		Color c = new Color(Integer.parseInt(doc.getAttributeValue("", "couleur")));
		doc.next();
		doc.next();
		while (doc.isStartElement()) {
			ElementGraphique EG = m_factory.getForme(doc.getLocalName());
			String X = doc.getAttributeValue("", "X");
			String Y = doc.getAttributeValue("", "Y");
			EG.setPosition(Double.parseDouble(X), Double.parseDouble(Y));
			String hauteur = doc.getAttributeValue("", "hauteur");
			String largeur = doc.getAttributeValue("", "largeur");
			EG.setDimension(Double.parseDouble(largeur), Double.parseDouble(hauteur));
			String trait = doc.getAttributeValue("", "trait");
			Color col = new Color(Integer.parseInt(doc.getAttributeValue("", "traitcolor")));
			EG.setLargeurTrait(Integer.parseInt(trait));
			EG.setCouleurTrait(col);
			if (doc.getAttributeValue("", "couleur").compareTo("null") != 0) {
				Color co = new Color(Integer.parseInt(doc.getAttributeValue("", "couleur")));
				EG.setCouleur(co);
			}
			doc.next();
			doc.next();
			temp.add(EG);
		}
		p_Modele.remplir(temp);
		p_Modele.setDimension(larg, haut);
		p_Modele.setArrierePlan(c);
	}
}
