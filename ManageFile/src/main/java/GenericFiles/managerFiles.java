package GenericFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class managerFiles {
	String ruta = "";
	private File file;
	
	public managerFiles(String nombre) {

		file = new File(ruta.concat(nombre));
		
		/*
		file.createNewFile();
		file.delete();
		file.deleteOnExit();
		file.exists();
		file.getAbsoluteFile();
		file.getName();
		file.getParent();
		file.getTotalSpace();
		file.isDirectory();
		file.isHidden();
		file.isFile();
		file.list();
		file.listFiles();
		file.mkdir();
		file.renameTo(file); 
		*/
	}
		public void crearFileConPrinter(File f) {
			FileWriter fw = null;
			PrintWriter pw = null;
			
			try {
				
				fw = new FileWriter(f);
				
			}
			catch (FileNotFoundException ex) {
				
			
				Logger.getLogger( managerFiles.class.getName() ).log( Level.WARNING, null, ex );
				ex.printStackTrace();
				
			}
		 catch (IOException e) {
			 
	
			Logger.getLogger( managerFiles.class.getName() ).log( Level.WARNING, null, e );
			e.printStackTrace();
			
		} finally {
	}
	}
	
	public void crearFileConPrintStream(File f)
	{
		FileOutputStream fos = null;
		PrintStream fs = null;
		
		try {
			if(!f.exists())
				try {
					
				}catch(){
					
				}
		
			fos = new FileOutputStream(f);
			fs = new PrintStream(fos);
			
			//escritores
			fs.println("manada enter");
			fs.print("primer renglon");
			fs.append("escribi con append");
			fs.write( 56 );
			
			//asegurar la recepcion del archivo
			fs.flush();
			
			
			
		} catch (FileNotFoundException e) {
			Logger.getLogger( managerFiles.class.getName() ).log( Level.WARNING, null, e );
			e.printStackTrace();
		} finally {
			try {
			if (fs!=null) fs.close();
			if (fos!=null) fos.close();
			}catch(IOException ex) {
				Logger.getLogger( managerFiles.class.getName() ).log( Level.WARNING, null, ex );
				
			}
		}
	}
	
	
	public File getFile() {
		return this.file;
	}
	
}

