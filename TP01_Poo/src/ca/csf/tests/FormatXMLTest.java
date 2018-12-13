package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Rectangle;
import ca.csf.formes.UsineForme;
import ca.csf.io.FormatXML;
import ca.csf.modele.ModeleDessin;

class FormatXMLTest {

	@Test
	void constructeurDefault() {
		UsineForme uf = UsineForme.getInstance();
		FormatXML fx = new FormatXML(uf);

		assertEquals(uf, fx.getFactory());
		assertThrows(IllegalArgumentException.class, () -> new FormatXML(null));
	}

	@Test
	void enregistrer() throws IOException, XMLStreamException {
		UsineForme uf = UsineForme.getInstance();
		FormatXML fx = new FormatXML(uf);
		ModeleDessin md = new ModeleDessin();
		md.ajouter(new Rectangle(5, 6, 7, 8));
		md.get(0).setCouleurTrait(Color.BLUE);
		md.get(0).setCouleur(Color.RED);
		File f = new File("cool");

		fx.enregistrer(md, f);

		XMLStreamReader doc = null;
		FileReader input = new FileReader(f);
		doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
		doc.next();

		assertEquals("dessin", doc.getLocalName());
		doc.next();
		assertEquals("fond", doc.getLocalName());
		assertEquals("360.0", doc.getAttributeValue("", "hauteur"));
		assertEquals("640.0", doc.getAttributeValue("", "largeur"));
		assertEquals("-1", doc.getAttributeValue("", "couleur"));
		doc.next();
		assertEquals("forme", doc.getLocalName());
		doc.next();
		assertEquals("Rectangle", doc.getLocalName());
		assertEquals("5.0", doc.getAttributeValue("", "X"));
		assertEquals("6.0", doc.getAttributeValue("", "Y"));
		assertEquals("8.0", doc.getAttributeValue("", "hauteur"));
		assertEquals("7.0", doc.getAttributeValue("", "largeur"));
		assertEquals("0", doc.getAttributeValue("", "trait"));
		assertEquals(Integer.toString(Color.BLUE.getRGB()), doc.getAttributeValue("", "traitcolor"));
		assertEquals(Integer.toString(Color.RED.getRGB()), doc.getAttributeValue("", "couleur"));

		f.delete();
	}

	@Test
	void enregistrer_couleur_null() throws IOException, XMLStreamException {
		UsineForme uf = UsineForme.getInstance();
		FormatXML fx = new FormatXML(uf);
		ModeleDessin md = new ModeleDessin();
		md.ajouter(new Rectangle(5, 6, 7, 8));
		md.get(0).setCouleurTrait(Color.BLUE);
		File f = new File("cool");

		fx.enregistrer(md, f);

		XMLStreamReader doc = null;
		FileReader input = new FileReader(f);
		doc = XMLInputFactory.newInstance().createXMLStreamReader(input);
		doc.next();
		doc.next();
		doc.next();
		doc.next();

		assertEquals("null", doc.getAttributeValue("", "couleur"));

		f.delete();
	}

	@Test
	void ouvrir_throw_XMLStreamException() throws IOException, XMLStreamException {
		UsineForme uf = UsineForme.getInstance();
		FormatXML fx = new FormatXML(uf);
		ModeleDessin md = new ModeleDessin();
		md.ajouter(new Rectangle(5, 6, 7, 8));
		md.get(0).setCouleurTrait(Color.BLUE);
		File f = new File("cool");

		XMLStreamWriter doc = null;
		FileWriter output = new FileWriter(f);
		doc = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
		doc.writeStartDocument();
		doc.writeStartElement("crazy");
		doc.writeEndDocument();
		doc.writeEndDocument();

		assertThrows(XMLStreamException.class, () -> fx.ouvrir(md, f));

	}

	@Test
	void Ouvrir() throws XMLStreamException, IOException {
		UsineForme uf = UsineForme.getInstance();
		FormatXML fx = new FormatXML(uf);
		ModeleDessin md = new ModeleDessin();
		md.ajouter(new Rectangle(5, 6, 7, 8));
		md.get(0).setCouleurTrait(Color.BLUE);
		md.get(0).setCouleur(Color.RED);
		File f = new File("cool");

		fx.enregistrer(md, f);

		ModeleDessin md2 = new ModeleDessin();
		fx.ouvrir(md2, f);

		assertEquals(md.get(0), md2.get(0));

		f.delete();

	}

}
