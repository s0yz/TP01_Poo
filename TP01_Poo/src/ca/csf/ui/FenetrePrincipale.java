package ca.csf.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.junit.experimental.theories.Theories;

import com.sun.prism.Image;

import ca.csf.formes.Rectangle;
import ca.csf.modele.ModeleGraphiques;

/**
 * @author Cedric Mariage
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = -4586998190495167383L;
	
	private ModeleGraphiques m_Modele;
	private EspaceTravail m_Espace;
	
	private Consumer<MouseEvent> m_action = me -> {
		Rectangle rectangle = new  Rectangle(me.getX(), me.getY(),50,60);
		rectangle.setCouleur(Color.BLACK);
		this.m_Modele.ajouter(rectangle);
	};
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenu selection = new JMenu("Selection");
	JMenu formes = new JMenu("Formes");
	
	JButton boutonSelection = new JButton("");
	JButton boutonElipse = new JButton("");
	JButton boutonRect= new JButton("");
	JButton boutonLine = new JButton("");
	
	JMenuItem ouvrir = new JMenuItem("Ouvrir");
	JMenuItem enregistrer = new JMenuItem("Enregistrer");
	JMenuItem exporter = new JMenuItem("Exporter");
	JMenuItem quitter = new JMenuItem("Quitter");
	
	JMenuItem couleur = new JMenuItem("Couleur");
	JMenuItem couleurTrait = new JMenuItem("Couleur de Trait");
	JMenuItem epaisTrait = new JMenuItem("Epaisseur Trait");
	
	
	
	
	public FenetrePrincipale() {
		super("TP01 - Poo");
		this.parametrer();
		this.initialiserComposant();
		super.pack();
		
	}
	public void createMenu() {
		fichier.add(ouvrir);
		fichier.add(enregistrer);
		fichier.add(exporter);
		fichier.addSeparator();
		fichier.add(quitter);
		menuBar.add(fichier);
		
		selection.add(couleur);
		selection.add(couleurTrait);
		selection.add(epaisTrait);
		menuBar.add(selection);
		
		this.setJMenuBar(menuBar);
		
		ImageIcon img_cursor = new ImageIcon(this.getClass().getResource("/res/cursor.png"));
		boutonSelection.setIcon(img_cursor);
		
		ImageIcon img_elipse = new ImageIcon(this.getClass().getResource("/res/circle.png"));
		boutonElipse.setIcon(img_elipse);
		
		ImageIcon img_rect= new ImageIcon(this.getClass().getResource("/res/rect.png"));
		boutonRect.setIcon(img_rect);
		boutonRect.addActionListener(e ->{
			this.m_action = me -> {
				this.m_Modele.ajouter(new Rectangle(me.getX(),me.getY(),50,60));
			};
		});
		
		
		ImageIcon img_line= new ImageIcon(this.getClass().getResource("/res/line.png"));
		boutonLine.setIcon(img_line);
		
		JPanel j = new JPanel(new GridLayout(2,1));
		JPanel jPanel = new JPanel(new GridLayout(4,1));
		jPanel.add(boutonSelection);
		jPanel.add(boutonElipse);
		jPanel.add(boutonRect);
		jPanel.add(boutonLine);
		j.add(jPanel);
		super.add(j,BorderLayout.EAST);
		
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
		this.m_Espace.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("allo");
				FenetrePrincipale.this.m_action.accept(e);
			}
		});
		center.add(this.m_Espace);
		//
		//
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent p_e) {
				FenetrePrincipale.this.dispose();
			}
		});
		
		createMenu();
	}
}
