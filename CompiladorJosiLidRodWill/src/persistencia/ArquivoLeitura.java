package persistencia;
import java.io.*;

public class ArquivoLeitura{
	
	FileReader fileStream = null;
	BufferedReader in = null;

	public void abrir(String local) {
      try{
		fileStream = new FileReader(local);
        in = new BufferedReader(fileStream);

		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String lerTudo() {
		try {
			String conteudo = new String();
			
			while(true){
				String temp = in.readLine();
				if(temp == null){
					break;
				}
				conteudo += temp + "\n";
			}
			
			return conteudo;
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	public String lerLinha(){
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void fechar(){
		try {
			in.close();
	
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
}
