package ca.csf.io;

import java.io.*;

import javax.xml.stream.*;



import ca.csf.formes.ElementGraphique;

public class FormatXml implements FormatFichier {

	@Override
	public void enregistrer(Iterable<ElementGraphique> p_Elements) {
		// TODO Auto-generated method stub
		// Declare ici pour le fermer dans le finally
		XMLStreamWriter doc = null;

		try {
			// Mettre en paramètre le chemin et le nom du fichier.
			FileWriter output = new FileWriter(new File("data2.xml"));

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
	public String ouvrir() throws XMLStreamException, FileNotFoundException {
		// TODO Auto-generated method stub
		// Declare ici pour le fermer dans le finally
		XMLStreamReader doc = null;
		String cool = "";
		// Mettre en paramètre le chemin et le nom du fichier.
		FileReader input = new FileReader("data2.xml");

		doc = XMLInputFactory.newInstance().createXMLStreamReader(input);

		// Pour passer par-dessus le Start Document
		doc.next();

		// Le document doit commencer par un <examen>
		if (!doc.getLocalName().equals("forme")) {
			throw new XMLStreamException("Pas le bon element racine : " + doc.getLocalName());
		}

		// Pour passer par-dessus <examen>
		doc.next();

		while (doc.isStartElement()) {
			
			String X = doc.getAttributeValue("", "X");
			String Y = doc.getAttributeValue("", "Y");
			String hauteur = doc.getAttributeValue("", "hauteur");
			String largeur = doc.getAttributeValue("", "largeur");
			
			doc.next();
			
			cool = X +" "+ Y+" " + hauteur+" " + largeur+" ";
		}

		
		return cool;
	}
}
