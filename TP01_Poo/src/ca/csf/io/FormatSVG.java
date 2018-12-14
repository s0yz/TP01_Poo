package ca.csf.io;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.UsineElementGraphique;
import ca.csf.modele.ModeleElementGraphique;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Représente une stratégie d'écriture et lecteur de fichier SVG pour des
 * {@link ModeleElementGraphique}
 */
public class FormatSVG implements FormatFichier {

	// Ouvrir n'est pas implémentée, alors m_factory est inutilisée.
	@SuppressWarnings("unused")
	private UsineElementGraphique m_factory;

	/**
	 * Construit un FormatSVG sans {@link UsineElementGraphique}. L'objet construit
	 * sera dans l'impossibilité de lire un fichier.
	 */
	public FormatSVG() {
	}
	
	/**
	 * 
	 * @param p_Factory
	 */
	public FormatSVG(UsineElementGraphique p_Factory) {
		if (p_Factory == null) {
			throw new IllegalArgumentException("p_factory est null");
		}
		this.m_factory = p_Factory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enregistrer(ModeleElementGraphique p_Modele, File p_Fichier) throws IOException, Exception {
		XMLStreamWriter doc = null;
		FileWriter output = new FileWriter(p_Fichier);

		doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
		doc.writeStartDocument();

		doc.writeStartElement("svg");
		doc.writeAttribute("xmlns", "http://www.w3.org/2000/svg");
		doc.writeAttribute("version", "1.1");
		doc.writeAttribute("width", Double.toString(p_Modele.getLargeur()));
		doc.writeAttribute("height", Double.toString(p_Modele.getHauteur()));

		doc.writeStartElement("title");
		doc.writeCharacters(p_Fichier.getName().replaceAll(".svg", ""));
		doc.writeEndElement();

		// Arrière-Plan
		doc.writeStartElement("rect");
		doc.writeAttribute("width", "100%");
		doc.writeAttribute("height", "100%");
		doc.writeAttribute("x", "0");
		doc.writeAttribute("y", "0");
		doc.writeAttribute("fill", FormatSVG.convertirCouleur(p_Modele.getArrierePlan()));
		doc.writeEndElement();

		for (ElementGraphique e : p_Modele) {
			if (e.getNom() == "Ligne") {
				doc.writeStartElement("line");
				doc.writeAttribute("x1", Double.toString(e.getX()));
				doc.writeAttribute("y1", Double.toString(e.getY()));
				doc.writeAttribute("x2", Double.toString(e.getX() + e.getLargeur()));
				doc.writeAttribute("y2", Double.toString(e.getY() + e.getHauteur()));
			} else {
				if (e.getNom() == "Rectangle") {
					doc.writeStartElement("rect");
					doc.writeAttribute("width", Double.toString(e.getLargeur()));
					doc.writeAttribute("height", Double.toString(e.getHauteur()));
					doc.writeAttribute("x", Double.toString(e.getX()));
					doc.writeAttribute("y", Double.toString(e.getY()));
				} else if (e.getNom() == "Ellipse") {
					double rx = e.getLargeur() * 0.5;
					double ry = e.getHauteur() * 0.5;
					doc.writeStartElement("ellipse");
					doc.writeAttribute("rx", Double.toString(rx));
					doc.writeAttribute("ry", Double.toString(ry));
					doc.writeAttribute("cx", Double.toString(e.getX() + rx));
					doc.writeAttribute("cy", Double.toString(e.getY() + ry));
				}
				doc.writeAttribute("fill", FormatSVG.convertirCouleur(e.getCouleur()));
				doc.writeAttribute("fill-opacity", Double.toString(e.getCouleur().getAlpha() / 255));
			}
			doc.writeAttribute("stroke-width", Integer.toString(e.getLargeurTrait()));
			doc.writeAttribute("stroke", FormatSVG.convertirCouleur(e.getCouleurTrait()));
			doc.writeAttribute("stroke-opacity", Double.toString(e.getCouleurTrait().getAlpha() / 255));
			doc.writeEndElement();
		}
		doc.writeEndElement();
		doc.writeEndDocument();
		if (doc != null) {
			doc.flush();
			doc.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ouvrir(ModeleElementGraphique p_graph, File p_file) throws Exception, FileNotFoundException {
		throw new NotImplementedException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getExtension() {
		return ".svg";
	}
	
	/**
	 * Pour convertir une couleur en sa représentation SVG.
	 * 
	 * @param p_Couleur couleur à convertir
	 * @return un String représentant la couleur.
	 */
	private static String convertirCouleur(Color p_Couleur) {
		if (p_Couleur == null) {
			return "none";
		} else {
			StringBuilder sb = new StringBuilder("rgb(");
			sb.append(p_Couleur.getRed()).append(",").append(p_Couleur.getGreen()).append(",")
					.append(p_Couleur.getBlue()).append(")");
			return sb.toString();
		}
	}
}
