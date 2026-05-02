package gestdb.Graphics;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltriFile extends FileFilter {
    
    private String extension;
    private String description;
    
	public FiltriFile(String extension, String descprition) {
		this.extension=extension;
		this.description=descprition;
	}
	
	@Override
	public boolean accept(File f) {
		if(f.isFile())
	            return f.getName().endsWith(extension);
		else
		    return false;
	}

	@Override
	public String getDescription() {
	   return description + String.format(" (*%s)", extension);
	}       
}