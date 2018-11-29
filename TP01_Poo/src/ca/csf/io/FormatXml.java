package ca.csf.io;

import java.io.*;

import javax.xml.stream.*;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.ModeleGraphiques;

public class FormatXml implements FormatFichier {

	@Override
	public void enregistrer(ModeleGraphiques p_modele) {
		// TODO Auto-generated method stub
		// Declare ici pour le fermer dans le finally
		XMLStreamWriter doc = null;

		try {
			// Mettre en paramètre le chemin et le nom du fichier.
			FileWriter output = new FileWriter(new File("data.xml"));

			doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);

			// <?xml version="1.0" ?>
			doc.writeStartDocument();

			doc.writeStartElement("forme");

			for (ElementGraphique elementGraphique : p_modele) {

				doc.writeStartElement(elementGraphique.getNom());

				doc.writeAttribute("position", elementGraphique.getPosition().toString());
				doc.writeAttribute("hauteur", Integer.toString(elementGraphique.getHauteur()));
				doc.writeAttribute("largeur", Integer.toString(elementGraphique.getLargeur()));
				doc.writeAttribute("couleur", Integer.toString(elementGraphique.getCouleur()));

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
	public String ouvrir(File p_Fichier) throws XMLStreamException, FileNotFoundException {
		// TODO Auto-generated method stub
		// Declare ici pour le fermer dans le finally
		XMLStreamReader doc = null;

		// Mettre en paramètre le chemin et le nom du fichier.
		FileReader input = new FileReader(new File("data.xml"));

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

			
			
		}

		return "yeah";
	}
}
