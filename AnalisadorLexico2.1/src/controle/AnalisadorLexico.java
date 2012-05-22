package controle;

public class AnalisadorLexico {

	private int charAtual = 0;
	private String programa;
	private TabelaDeSimbolos tabSimb = new TabelaDeSimbolos();
	private TabelaDeTokens tabTokens = new TabelaDeTokens();
	private String lexema;

	private int linhaAtual = 1;
	
	public boolean gerarTabelas(String programa) {
		// TODO
		// autômatos:
		// - comentário # #
		// - números 123
		// - palavras abc123 ou int etc
		// ......- identificador abc123
		// ......- palavra reservada int, string, etc
		// ............- tipos
		// ............- inicio / fim
		// ......- comandos
		// ......- condição
		// - operadores
		// ......- operador lógico && ||
		// ......- operador aritmético +*/-
		// ......- operador relacional > >= < <= = !=
		// - pontuação [ ] ( ) , ; :
		// - cadeia de caracteres "sdkasds32443dssad"
		// - eliminar delimitadores (espaços, \t, \n)
		
		this.programa = programa;
		while(!isFimPrograma()){
			try {
				estado1();
			} catch (Exception e) {
				System.out.println("%%%%%%%% ERRO %%%%%%%%%%\n" +
								   "Linha: " + linhaAtual + "\n" +
								   "Mensagem: " + e.getMessage() + "\n");
				return false;
			}
		}
		return true;
	}

//	ESTADO 1
//	parada: espaço, \n, \t
//	ação:    nenhuma
//	erro:    caractere fora da linguagem
	private void estado1() throws Exception {
		if((atual() == ' ') || (atual() == '\t')){
			incrementa();
			return;
		}
		if(atual() == '\n'){
			incrementa();
			linhaAtual++;
			return;
		}
		// tratar palavra
		if(isLetra(atual())){
			palavra();
			return;
		}
		// tratar numero
		if(isDigito(atual())){
			numero();
			return;
		}
		// operadores relacionais e atribuição (<-)
		if(isOperadorRelacional(atual())){
			opRelacional();
			return;
		}
		// operadores aritmeticos
		if(isOperadorAritmetico(atual())){
			opAritmetico();
			return;
		}
		// operador logico &&
		if(atual() == '&'){
			opLogAnd();
			return;
		}
		if(atual() == '|'){
			opLogicoOr();
			return;
		}
		// string
		if(atual() == '"'){
			string();
			return;
		}
		// pontuacao
		if(isPontuacao(atual())){
			pontuacao();
			return;
		}
		// comentario
		if(atual() == '#'){
			comentario();
			return;
		}
		// se não for nenhum dos anteriores, não faz parte da linguagem!
		throw new Exception("Caractere invalido encontrado: \""+ atual() +"\"");
	}
	
	// ESTADO 2
//	parada: diferente de letra ou dígito
//	ação:    acumula palavra, adiciona id ou pal_reserv
//	erro:    N/A
	private void palavra(){
		lexema = new String();
		while(isLetra(atual()) || isDigito(atual())){
			lexema += atual();
			incrementa();
			
			if(isFimPrograma()){
				break;
			}
		}
		// ao sair do while, não será nem letra nem dígito, então executa ação final
		String token = DefinicoesTokens.getTokenDoLexema(lexema);
		String valor;
		if(token.equals("id")){
			int id = tabSimb.addSimbolo(lexema);
			valor = Integer.toString(id);
		}else{
			valor = DefinicoesTokens.getValorDoLexema(lexema);
		}
		tabTokens.addToken(token, valor);
	}
	
//	ESTADO 3
//	parada: diferente de dígito, sem ser letra
//	ação:    acumula número, adiciona na tabela
//	erro:    proximo é letra
	private void numero()  throws Exception {
		lexema = new String();
		while (isDigito(atual())){
			lexema += atual();
			incrementa();
			
			if(isFimPrograma()){
				break;
			}
		}
		
		// se após sair do while, atual foi letra, dispara erro!
		if(isLetra(atual())){
			throw new Exception("Identificador deve comecar com letra! \""+ lexema +"\"");
		}
		
		String tokenValue = lexema; //o valor eh o proprio numero...
		tabTokens.addToken("num", tokenValue);				
	}
	
//	ESTADO 4
//	parada: N/A
//	ação:   verifica operador relacional e adciona na tabela de tokens
//	erro:   operador inválido
	private void opRelacional(){
		lexema = new String();
		lexema += atual();
		incrementa();
		
		// verifica atribuição!
		if(atual() == '-'){
			lexema += atual();
			incrementa();
			String token = DefinicoesTokens.getTokenDoLexema(lexema); 
			tabTokens.addToken(token, "");
			return;
		}
		
		if(atual() == '='){
			lexema += atual();
			incrementa();
		}
		
		String tokenValue = DefinicoesTokens.getValorDoLexema(lexema); 
		tabTokens.addToken("op_rel", tokenValue);
	}
	
//	ESTADO 6
//	parada: diferente de operadores matemáticos
//  ação: grava na tabela o operador   
//	erro: -
	
  	private void opAritmetico(){
  		lexema = new String();
  		lexema += atual();
  		String tokenValue = DefinicoesTokens.getValorDoLexema(lexema);
  		tabTokens.addToken ("op_mat", tokenValue);
  		incrementa();
  	}

  	//ESTADO 7
  	//parada: N/A
  	//ação: verifica operador lógico "&" adiciona na tabela 
  	//erro: operador invalido
	private void opLogAnd() throws Exception {
		lexema = new String();
		lexema += atual();
		incrementa();
		
		if(atual() == '&'){
			lexema += '&';
			incrementa();
		}else{
			lexema += atual();
			throw new Exception("Operador logico invalido! \""+ lexema +"\"");
		}
		
		String tokenValue = DefinicoesTokens.getValorDoLexema(lexema);
		tabTokens.addToken("op_log", tokenValue);
	}

  	//ESTADO 9
  	//parada: N/A
  	//ação: verifica operador lógico "|" adiciona na tabela 
  	//erro: operador invalido
	private void opLogicoOr() throws Exception {
		lexema = new String();
		lexema += atual();
		incrementa();
		
		if(atual() == '|'){
			lexema += '|';
			incrementa();
		}else{
			lexema+= atual();
			throw new Exception("Operador logico invalido! \""+ lexema +"\"");
		}
		
		String tokenValue = DefinicoesTokens.getValorDoLexema(lexema);
		tabTokens.addToken("op_log", tokenValue);
	}
	
  	//ESTADO 11
  	//parada: igual de aspas
  	//ação: acumula String e adiciona na tabela 
  	//erro: -
  	
  	private void string() throws Exception {
  		lexema = new String();
  		incrementa(); // ignora o abre aspas!
  		while(atual() != '"'){
  			lexema += atual();
  			incrementa();
  			
  			if(isFimPrograma()){
  				// erro pois nao fechou string
  				throw new Exception("Nao fechou string!");
			}
  		}
  		incrementa(); // pois deve retirar o fecha aspas!
  		tabTokens.addToken("cadchar", lexema);
  	}
  	
  	//ESTADO 12
  	//parada: nao se aplica
  	//ação:   adiciona na lista
  	//erro:   nao se aplica
  	private void pontuacao(){
  		lexema = new String();
  		lexema += atual();
  		incrementa();
  		
  		String token = DefinicoesTokens.getTokenDoLexema(lexema);
  		tabTokens.addToken(token, "");
  	}
  	
  //ESTADO 13
  	//parada: igual de #
  	//ação: ignora comentário
  	//erro: -
  	
	private void comentario() throws Exception {
  		lexema = new String();
  		incrementa(); // ignora o abre #!
  		while(atual() != '#'){
  			lexema += atual();
  			incrementa();
  			
  			if(isFimPrograma()){
  				//erro, pois nao fechou comentario!
  				throw new Exception("Nao fechou comentario!");
			}
  		}
  		incrementa(); // pois deve retirar o fecha #!
  	}
  	
  	
  	
	private boolean isOperadorAritmetico(char atual) {
		return (( atual == '/') ||( atual == '*') ||( atual == '+') ||( atual == '-'));
    }

	private boolean isLetra(char charAt) {
		return (Character.isLowerCase(charAt) || Character.isUpperCase(charAt));
	}

	private boolean isDigito(char atual) {
		return Character.isDigit(atual);
	}
	
	private boolean isPontuacao(char atual){
		return((atual == ',')||(atual == ';')||(atual == ':')||(atual == '[')||(atual == ']')||(atual == '(')||(atual == ')')||(atual == '{')||(atual == '}'));
	}

	private boolean isOperadorRelacional(char atual) {
		return((atual == '>')||(atual == '<')||(atual == '!')||(atual == '='));
	}
	
	private void incrementa(){
		charAtual++;
//		charProx++;estado1();
	}
	
	private boolean isFimPrograma() {
		return charAtual >= programa.length();
	}
	
	private char atual(){
		return programa.charAt(charAtual);
	}
		
	public String getTabTokens() {
		return tabTokens.getTabTokens();
	}
	public String getTabSimb() {
		return tabSimb.getTabSimb();
	}

}
