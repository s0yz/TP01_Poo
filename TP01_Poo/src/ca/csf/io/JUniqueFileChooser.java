package ca.csf.io;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * @author Cedric Mariage
 *
 */
public class JUniqueFileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6807044687230376943L;
	
	public JUniqueFileChooser(String p_Extension) {
		this.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return String.format("Fichier SVG (*.%s)", p_Extension.toLowerCase());
			}
			@Override
			public boolean accept(File p_Fichier) {
				return p_Fichier.isDirectory() || p_Fichier.getPath().endsWith(".svg");
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getSelectedFile() {
		return this.getFileFilter().accept(this.getSelectedFile()) ?
				this.getSelectedFile() : new File(this.getSelectedFile() + ".svg");
	}
}
