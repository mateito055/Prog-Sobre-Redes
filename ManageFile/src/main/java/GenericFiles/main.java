package GenericFiles;

public class main {

	public static void main(String[] args) {
		
		managerFiles mF = new managerFiles("probando.txt");
		
		mF.crearFileConPrintStream( mF.getFile() );
	}

}
