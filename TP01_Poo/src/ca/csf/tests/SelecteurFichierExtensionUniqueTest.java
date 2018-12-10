package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import ca.csf.io.SelecteurFichierExtensionUnique;

/**
 * Teste la class SelecteurFichierExtensionUniqueTest.
 */
class SelecteurFichierExtensionUniqueTest {
	
	@Test
	void extensionsRegex() {
		String regex = SelecteurFichierExtensionUnique.EXT_REGEX;
		
		assertTrue(".xml".matches(regex));
		assertTrue(".png".matches(regex));
		assertTrue(".tar.gz".matches(regex));
		
		assertFalse("xml".matches(regex));
		assertFalse(" .xml".matches(regex));
		assertFalse(".xml ".matches(regex));
		assertFalse(".png.".matches(regex));
		assertFalse(".t ar.gz".matches(regex));
	}
	
//	@Test()
//	void constructeur() {		
//		SelecteurFichierExtensionUnique selecteur = new SelecteurFichierExtensionUnique(".xml", "XML");
//	}
	
	@Test()
	void constructeurInvalide() {
		assertThrows(IllegalArgumentException.class, () -> new SelecteurFichierExtensionUnique("", "XML"));
	}
	
	@Test()
	void accept() {
		SelecteurFichierExtensionUnique selecteur = new SelecteurFichierExtensionUnique(".xml", "XML");
		File dossier = new File("Dossier Test");
		dossier.mkdirs();
		
		assertTrue(selecteur.accept(dossier));
		assertTrue(selecteur.accept(new File("data.xml")));
		assertTrue(selecteur.accept(new File("data.data.xml")));
		
		assertFalse(selecteur.accept(new File("data.xml.txt")));
		assertFalse(selecteur.accept(new File(".xml.data")));
		assertFalse(selecteur.accept(new File("data")));
		
		dossier.delete();
	}
}
