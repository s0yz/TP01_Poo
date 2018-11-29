package ca.csf.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.csf.modele.ModeleGraphiques;

/**
 * @author Cedric Mariage
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = -4586998190495167383L;
	//temporaire
	public ModeleGraphiques m_Modele;
	private EspaceTravail m_Espace;
	
	public FenetrePrincipale() {
		super("TP01 - Poo");
		this.parametrer();
		this.initialiserComposant();
		super.pack();
	}
	
	public void parametrer() {
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setLocationRelativeTo(null);
	}
	
	public void initialiserComposant() {
		//
		// center
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center.setOpaque(true);
		center.setBackground(Color.gray);
		super.add(center, BorderLayout.CENTER);
		//
		// m_Modele
		this.m_Modele = new ModeleGraphiques();
		//
		// m_Espace
		this.m_Espace = new EspaceTravail(this.m_Modele, 640, 360);
		center.add(this.m_Espace);
		//
		//
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent p_e) {
				FenetrePrincipale.this.dispose();
			}
		});
	}
}
