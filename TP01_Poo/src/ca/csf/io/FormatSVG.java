package ca.csf.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Ligne;
import ca.csf.formes.UsineElementGraphique;
import ca.csf.modele.ModeleElementGraphique;

public class FormatSVG implements FormatFichier{

	private UsineElementGraphique m_factory;

	public FormatSVG() {
	}

	public FormatSVG(UsineElementGraphique p_Factory) {
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
		doc.writeAttribute("version","1.1");
		doc.writeAttribute("width",Double.toString(p_Modele.getLargeur()));
		doc.writeAttribute("height",Double.toString(p_Modele.getHauteur()));
		doc.writeAttribute("xlmns","http://www.w3.org/2000/svg");
	
	
		
		doc.writeStartElement("title");
		doc.writeCharacters("hello world");
		doc.writeEndElement();
		
		doc.writeStartElement("desc");
		doc.writeCharacters("vraiment bien");
		doc.writeEndElement();
		
		
		
		for (ElementGraphique elementGraphique : p_Modele) {
			
			if (elementGraphique.getNom() == "Rectangle") {
				doc.writeStartElement("rect");
				doc.writeAttribute("width", Double.toString(elementGraphique.getLargeur()));
				doc.writeAttribute("height", Double.toString(elementGraphique.getHauteur()));
				doc.writeAttribute("x", Double.toString(elementGraphique.getX()));
				doc.writeAttribute("y", Double.toString(elementGraphique.getY()));
				doc.writeAttribute("stroke-width", Integer.toString(elementGraphique.getLargeurTrait()));
				doc.writeAttribute("stroke", "rgb("+Integer.toString(elementGraphique.getCouleurTrait().getRed())+","
						+Integer.toString(elementGraphique.getCouleurTrait().getGreen())+","+elementGraphique.getCouleurTrait().getBlue()+")");
				if (elementGraphique.getCouleur() != null) {
					doc.writeAttribute("fill", "rgb("+Integer.toString(elementGraphique.getCouleur().getRed())+","
							+Integer.toString(elementGraphique.getCouleur().getGreen())+","+elementGraphique.getCouleur().getBlue()+")");
				} else {doc.writeAttribute("couleur", "null");}
			}else if (elementGraphique.getNom() == "Ellipse") {
				doc.writeStartElement("circle");
				doc.writeAttribute("width", Double.toString(elementGraphique.getLargeur()));
				doc.writeAttribute("height", Double.toString(elementGraphique.getHauteur()));
				doc.writeAttribute("x", Double.toString(elementGraphique.getX()));
				doc.writeAttribute("y", Double.toString(elementGraphique.getY()));
				doc.writeAttribute("stroke-width", Integer.toString(elementGraphique.getLargeurTrait()));
				doc.writeAttribute("stroke", "rgb("+Integer.toString(elementGraphique.getCouleurTrait().getRed())+","
						+Integer.toString(elementGraphique.getCouleurTrait().getGreen())+","+elementGraphique.getCouleurTrait().getBlue()+")");
				if (elementGraphique.getCouleur() != null) {
					doc.writeAttribute("fill", "rgb("+Integer.toString(elementGraphique.getCouleur().getRed())+","
							+Integer.toString(elementGraphique.getCouleur().getGreen())+","+elementGraphique.getCouleur().getBlue()+")");
				} else {doc.writeAttribute("couleur", "null");}
			}else if (elementGraphique.getNom() == "Ligne") {
				doc.writeStartElement("line");
				Ligne l = (Ligne)elementGraphique;
				doc.writeAttribute("x1", Double.toString(l.getX1()));
				doc.writeAttribute("y1", Double.toString(l.getY1()));
				doc.writeAttribute("x2", Double.toString(l.getX2()));
				doc.writeAttribute("y2", Double.toString(l.getY2()));
				doc.writeAttribute("stroke-width", Integer.toString(elementGraphique.getLargeurTrait()));
				doc.writeAttribute("stroke", "rgb("+Integer.toString(elementGraphique.getCouleurTrait().getRed())+","
						+Integer.toString(elementGraphique.getCouleurTrait().getGreen())+","+elementGraphique.getCouleurTrait().getBlue()+")");
				if (elementGraphique.getCouleur() != null) {
					doc.writeAttribute("fill", "rgb("+Integer.toString(elementGraphique.getCouleur().getRed())+","
							+Integer.toString(elementGraphique.getCouleur().getGreen())+","+elementGraphique.getCouleur().getBlue()+")");
				} else {doc.writeAttribute("couleur", "null");}
			}
		
			doc.writeEndElement();
		
			;}
		
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
