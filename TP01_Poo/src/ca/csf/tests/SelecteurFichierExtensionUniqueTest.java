package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import ca.csf.ui.SelecteurFichierExtensionUnique;

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
	
	@Test()
	void constructeurInvalide() {
		assertThrows(IllegalArgumentException.class, () -> new SelecteurFichierExtensionUnique("", "XML"));
		assertThrows(IllegalArgumentException.class, () -> new SelecteurFichierExtensionUnique(null, "XML"));
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
	
	@Test()
	void getSelectedFile() {
		SelecteurFichierExtensionUnique s1 = new SelecteurFichierExtensionUnique(".txt", "TXT");
		
		s1.setSelectedFile(new File(""));		
		assertEquals(new File(".txt"), s1.getSelectedFile());
				
		s1.setSelectedFile(new File("test"));
		assertEquals(new File("test.txt"), s1.getSelectedFile());
	}	
}
