package ui;

import controle.*;
import persistencia.*;

public class AnalisadorSintaticoPrincipal {

	public static void main(String[] args) {

		//String nomeArqTabelaTokens = args[0];

		// arg[1] = arquivo de saida
		ArquivoLeitura arqTabelaTokens = new ArquivoLeitura();
		//arqTabelaTokens.abrir("/home/corei5/Documentos/teste/tabelaToken");
		//arqProg.abrir(nomeArqTabelaTokens);
		arqTabelaTokens.abrir("/home/rodrigo/temp/tabelaToken");

		AnalisadorSintatico as = new AnalisadorSintatico();
		try {
			as.analisarTabelaTokens(arqTabelaTokens);
			System.out.println("Analise sintatica OK!");
		} catch (Exception e) {
			String erro = e.getMessage();
			System.out.println("Erro na Analise Sintatica: " + erro);
		}
	}
}
