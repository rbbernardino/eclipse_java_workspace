package entidade;
import java.io.*;

public class Persistencia {
	
	FileWriter fileStream = null;
	BufferedWriter out = null;

	public void abrir(String local) {
      try{
 
		fileStream = new FileWriter(local);
        out = new BufferedWriter(fileStream);

		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void escrever (String conteudo) {
		try {
			out.write(conteudo);
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void fechar(){
		try {
			out.close();
	
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
}
