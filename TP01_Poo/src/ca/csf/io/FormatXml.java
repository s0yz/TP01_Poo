package ca.csf.io;

import java.io.*;

import javax.xml.stream.*;



import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Forme;
import ca.csf.formes.Forme2DFactory;
import ca.csf.formes.FormeFactory;
import ca.csf.modele.ModeleGraphiques;

public class FormatXml implements FormatFichier {

	private Forme2DFactory m_factory;
	
	public FormatXml() {
		// TODO Auto-generated constructor stub
	}
	
	public FormatXml(Forme2DFactory p_factory) {
		
		// TODO Auto-generated constructor stub
		this.m_factory = p_factory;
	}
	
	@Override
	public void enregistrer(ModeleGraphiques p_Elements, File p_file) {
		// TODO Auto-generated method stub
		// Declare ici pour le fermer dans le finally
		XMLStreamWriter doc = null;

		try {
			// Mettre en paramètre le chemin et le nom du fichier.
			FileWriter output = new FileWriter(p_file);

			doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);

			// <?xml version="1.0" ?>
			doc.writeStartDocument();

			doc.writeStartElement("forme");

			for (ElementGraphique elementGraphique : p_Elements) {

				doc.writeStartElement(elementGraphique.getNom());

				doc.writeAttribute("X", Integer.toString(elementGraphique.getX()));
				doc.writeAttribute("Y", Integer.toString(elementGraphique.getY()));
				doc.writeAttribute("hauteur", Integer.toString(elementGraphique.getHauteur()));
				doc.writeAttribute("largeur", Integer.toString(elementGraphique.getLargeur()));
				if (elementGraphique.getCouleur() != null) {
					doc.writeAttribute("couleur",elementGraphique.getCouleur().toString());
				}
				

				// </examen>
				doc.writeEndElement();
			}

			doc.writeEndElement();
			doc.writeEndDocument();

		} catch (IOException exp) {
			System.err.println("Erreur d'ecriture : " + exp);

		} catch (XMLStreamException exp) {
			System.err.println("Erreur dans le XML : " + exp);

		} finally {
			// Ici, on va tenter de fermer le fichier.

			if (doc != null) {
				try {
					doc.flush(); // Pour terminer l'ecriture.
					doc.close();

				} catch (XMLStreamException exp) {
					// Oups, un problème durant la fermeture ..
					System.err.println("Erreur lors de la fermeture" + exp);

				}
				doc = null;
			}
		}

	}

	@Override
	public void ouvrir(ModeleGraphiques p_graph,File p_file) throws XMLStreamException, FileNotFoundException {
		// TODO Auto-generated method stub
		// Declare ici pour le fermer dans le finally
		XMLStreamReader doc = null;
		ModeleGraphiques temp = new ModeleGraphiques();
		
		// Mettre en paramètre le chemin et le nom du fichier.
		FileReader input = new FileReader(p_file);

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
			
			temp.ajouter(EG);
			
				
			
			
			}

		p_graph.vider();
		p_graph.ajouter(temp);;
		
		
		
	}
}
