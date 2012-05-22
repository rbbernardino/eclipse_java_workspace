package ui;

import controle.*;
import persistencia.*;

public class AnalisLexPrincipal {

	public static void main(String[] args) {

		//String arq_fonte = args[0];

		// arg[1] = arquivo de saida

		ArquivoLeitura arqProg = new ArquivoLeitura();
		arqProg.abrir("/home/rodrigo/temp/programa");
		//arqProg.abrir(arq_fonte);

		String programa = arqProg.lerTudo();
		arqProg.fechar();

		AnalisadorLexico al = new AnalisadorLexico();
		if (al.gerarTabelas(programa)) {
				
			ArquivoEscrita arqTabTokens = new ArquivoEscrita();
			arqTabTokens.abrir("/home/rodrigo/temp/tabelaToken");
			//arqTabTokens.abrir("tabelaTokens.txt");
			arqTabTokens.escrever(al.getTabTokens());
			arqTabTokens.fechar();

			ArquivoEscrita arqTabSimbolos = new ArquivoEscrita();
			arqTabSimbolos.abrir("/home/rodrigo/temp/tabelaSimb");
			//arqTabSimbolos.abrir("tabelaSimb.txt");
			arqTabSimbolos.escrever(al.getTabSimb());
			arqTabSimbolos.fechar();
			
		}else{
			//erro...
		}
	}

}
