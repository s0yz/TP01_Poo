package ca.csf.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Forme;
import ca.csf.formes.Rectangle;
import ca.csf.io.ControleurFichier;
import ca.csf.io.FormatFichier;
import ca.csf.io.FormatXML;
import ca.csf.modele.ModeleDessin;
import ca.csf.ui.FenetrePrincipale;

/**
 * @author Cedric Mariage
 *
 */
public class Main {

	public static void main(String[] args) {
		FenetrePrincipale fenetre = new FenetrePrincipale();
		fenetre.setVisible(true);
		
		/*
		 * test pour enregistrer
		ArrayList<ElementGraphique> d = new ArrayList<ElementGraphique>();
		d.add(new Rectangle(8,8,8,8));
	
		
		FormatXml a = new FormatXml();
		a.enregistrer(d);
		
		
		
		try {
			System.out.println(a.ouvrir());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
