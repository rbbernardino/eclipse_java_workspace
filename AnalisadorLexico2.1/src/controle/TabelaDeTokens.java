package controle;

public class TabelaDeTokens {
	private String tabTokenOut = "token    valor\n";
	
	public void addToken(String token, String valor){
		// adiciona na lista de tokens
		tabTokenOut += token+"          "+valor+'\n';
	}
	
	public String getTabTokens(){
		return tabTokenOut;
	}
}
