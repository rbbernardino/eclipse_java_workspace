package ui;

import controle.sintatico.AnalisadorSintatico;
import controle.lexico.AnalisadorLexico;
import persistencia.*;

public class CompiladorJLRWPrincipal {	
	public static void main(String[] args) {
		final String SAIDA_TAB_TOKENS = "tabelaTokens.txt";
		//final String SAIDA_TAB_TOKENS = "/home/rodrigo/temp/tabelaToken";
		final String SAIDA_TAB_SIMB  = "tabelaSimb.txt";
		//final String SAIDA_TAB_SIMB  = "/home/rodrigo/temp/tabelaSimb";
		
		String arq_fonte = args[0];
		//String arq_fonte = "/home/rodrigo/temp/programa"; 
		
		ArquivoLeitura arqProg = new ArquivoLeitura();
		arqProg.abrir(arq_fonte);
		String programa = arqProg.lerTudo();
		arqProg.fechar();
		AnalisadorLexico al = new AnalisadorLexico();

		try {
			al.gerarTabelas(programa);
			System.out.println("Analise Lexica OK!");
		} catch (Exception e) {
			System.out.println("%%%%%%%% ERRO ANALISE LEXICA %%%%%%%%%%\n" +
					   "Linha: " + al.getLinhaAtual() + "\r\n" +
					   "Mensagem: " + e.getMessage() + "\r\n");
			return;
		}
		
		ArquivoEscrita arqTabTokens = new ArquivoEscrita();
		arqTabTokens.abrir(SAIDA_TAB_TOKENS);
		arqTabTokens.escrever(al.getTabTokens());
		arqTabTokens.fechar();

		ArquivoEscrita arqTabSimbolos = new ArquivoEscrita();
		arqTabSimbolos.abrir(SAIDA_TAB_SIMB);
		arqTabSimbolos.escrever(al.getTabSimb());
		arqTabSimbolos.fechar();

		
		String nomeArqTabelaTokens = SAIDA_TAB_TOKENS;

		ArquivoLeitura arqTabelaTokens = new ArquivoLeitura();
		arqTabelaTokens.abrir(nomeArqTabelaTokens);

		AnalisadorSintatico as = new AnalisadorSintatico();
		try {
			as.analisarTabelaTokens(arqTabelaTokens);
			System.out.println("Analise sintatica OK!");
		} catch (Exception e) {
			System.out.println("%%%%%%%% ERRO ANALISE SINTATICA %%%%%%%%%%\n" +
					   "Mensagem: " + e.getMessage() + "\n");
		}
	}
}
