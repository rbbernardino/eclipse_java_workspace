package controle.sintatico;

import java.util.ArrayList;

import persistencia.ArquivoLeitura;

public class AnalisadorSintatico {

	// private int linhaAtual = 1;
	private int indiceTokenAtual = 0;
	private ArrayList<String> listaTokens = new ArrayList<String>();

	public boolean analisarTabelaTokens(ArquivoLeitura tabelaTokens)
			throws Exception {

		criarListaTokens(tabelaTokens);

		analisar();

		return true;
	}

	private void analisar() throws Exception {
		statePrograma();
	}

	private void statePrograma() throws Exception {
		if (!getTokenAtual().equals("inicio")) {
			throw new Exception("Esperava-se o token \"inicio\" ");
		}
		incrementar();
		if (!getTokenAtual().equals("[")) {
			throw new Exception("Esperava-se o token \"[\"  ");
		}
		incrementar();

		stateDeclVars();

		stateBlkCmd();

		if (!getTokenAtual().equals("]")) {
			throw new Exception("Esperava-se o token \"]\" ");
		}
		incrementar();

		if (!getTokenAtual().equals("fim")) {
			throw new Exception("Esperava-se o token \"fim\" ");
		}
	}

	private void stateBlkCmd() throws Exception {
		if (!getTokenAtual().equals("{")) {
			throw new Exception(
					"Esperava-se o token \"{\" no bloco de comandos");
		}
		incrementar();
		while (true) {
			if (getTokenAtual().equals("}")) {
				incrementar();
				break;
			}
			stateComando();
			if (!getTokenAtual().equals(";")) {
				throw new Exception("Esperava-se o token \";\" ");
			}
			incrementar();
		}
		return;
	}

	private void stateComando() throws Exception {

		// inicia o comando de atribuição
		if (getTokenAtual().equals("id")) {
			incrementar();
			if (!getTokenAtual().equals("<-")) {
				throw new Exception("Esperava-se o token \"<-\" ");
			}
			incrementar();
			if (getTokenAtual().equals("cadchar")) {
				incrementar();
				return;
			} else {
				stateExpressao();
				return;
			}
		}

		// inicia o comando leia
		if (getTokenAtual().equals("leia")) {
			incrementar();
			if (!getTokenAtual().equals("(")) {
				throw new Exception("Esperava-se o token \"(\" ");
			}
			incrementar();
			if (!getTokenAtual().equals("id")) {
				throw new Exception("Esperava-se o token \"id\" ");
			}
			incrementar();
			while (true) {
				if (getTokenAtual().equals(")")) {
					incrementar();
					break;
				}
				if (!getTokenAtual().equals(",")) {
					throw new Exception("Esperava-se o token \",\" ");
				}
				incrementar();
				if (!getTokenAtual().equals("id")) {
					throw new Exception("Esperava-se o token \"id\" ");
				}
				incrementar();
			}

		}

		// inicia o comando imprima
		if (getTokenAtual().equals("imprima")) {
			incrementar();
			if (!getTokenAtual().equals("(")) {
				throw new Exception("Esperava-se o token \"(\" ");
			}
			incrementar();
			if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num")
					&& !getTokenAtual().equals("cadchar")) {
				throw new Exception(
						"Esperava-se \"um identificador ou um numero ou um cadchar\" ");
			}
			incrementar();
			while (true) {
				if (getTokenAtual().equals(")")) {
					incrementar();
					break;
				}
				if (!getTokenAtual().equals(",")) {
					throw new Exception("Esperava-se o token \",\" ");
				}
				incrementar();
				if (!getTokenAtual().equals("id")
						&& !getTokenAtual().equals("num")
						&& !getTokenAtual().equals("cadchar")) {
					throw new Exception(
							"Esperava-se \"um identificador ou um numero ou um cadchar\" ");
				}
				incrementar();
			}
			return;
		}

		// inicia o comando de condição
		if (getTokenAtual().equals("se")) {
			incrementar();
			if (!getTokenAtual().equals("(")) {
				throw new Exception("Esperava-se o token \"(\" ");
			}
			incrementar();
			stateCondicao();

			if (!getTokenAtual().equals(")")) {
				throw new Exception("Esperava-se o token \")\" ");
			}
			incrementar();
			if (!getTokenAtual().equals("entao")) {
				throw new Exception("Esperava-se o token \"entao\" ");
			}
			incrementar();
			stateBlkCmd();
			
			if (getTokenAtual().equals("fimse")) {
				incrementar();
				return;
			} else {
				if (!getTokenAtual().equals("senao")) {
					throw new Exception("Esperava-se o token \"senao\" ");
				}
				incrementar();
				stateBlkCmd();
				
				if (!getTokenAtual().equals("fimse")) {
					throw new Exception("Esperava-se o token \"fimse\" ");
				} else {
					incrementar();
					return;
				}
			}
		}
	}

	private void stateExpressao() throws Exception {
		if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num")) {
			stateParenExpr();
		}  else{
			incrementar();
		}
		stateExpressao2();
	}

	private void stateExpressao2() throws Exception {
		if (!getTokenAtual().equals("op_mat")) {
			return;
		}
		incrementar();
		if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num")) {
			stateParenExpr();
		} else{
			incrementar();
		}
		stateExpressao2();
	}

	private void stateParenExpr() throws Exception {
		if (!getTokenAtual().equals("(")){
			throw new Exception(
				"Esperava-se o token \"(\" no incio de expressao entre parenteses ou falta expressao dentro de parenteses");
		}
		incrementar();
		stateExpressao();
		if (!getTokenAtual().equals(")")){
			throw new Exception(
				"Esperava-se o token \")\" no final de expressao entre parenteses");
		}
		incrementar();
	}
	
	private void stateCondicao() throws Exception {
		if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num")
				&& !getTokenAtual().equals("cadchar")) {
			throw new Exception("Esperava-se um identificador ou um numero ");
		}
		incrementar();

		if (!getTokenAtual().equals("op_rel")) {
			throw new Exception("Esperava-se um operador relacional ");
		}
		incrementar();

		if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num")
				&& !getTokenAtual().equals("cadchar")) {
			throw new Exception(
					"Esperava-se o token \"um identificador ou um numero\" ");
		}
		incrementar();
		stateCondicao4();
	}

	private void stateCondicao4() throws Exception {
		if (!getTokenAtual().equals("op_log")) {
			return;
		}
		incrementar();

		if (!getTokenAtual().equals("(")) {
			throw new Exception("Esperava-se o token \"(\" ");
		}
		incrementar();

		if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num") && !getTokenAtual().equals("cadchar")) {
			throw new Exception(
					"Esperava-se \"um identificador ou um numero ou um cadchar!\" ");
		}
		incrementar();

		if (!getTokenAtual().equals("op_rel")) {
			throw new Exception(
					"Esperava-se o token \"um operador relacional\" ");
		}
		incrementar();

		if (!getTokenAtual().equals("id") && !getTokenAtual().equals("num") && !getTokenAtual().equals("cadchar")) {
			throw new Exception(
					"Esperava-se \"um identificador ou um numero ou um cadchar\" ");
		}
		incrementar();

		if (!getTokenAtual().equals(")")) {
			throw new Exception("Esperava-se o token \")\" ");
		}
		incrementar();
		stateCondicao4();
	}

	private void stateDeclVars() throws Exception {
		if (!getTokenAtual().equals("int") && !getTokenAtual().equals("string")) {
			return;
		}
		incrementar();

		if (!getTokenAtual().equals(":")) {
			throw new Exception("Esperava-se o token \":\" ");
		}
		incrementar();

		stateVariaveis();

		if (!getTokenAtual().equals(";")) {
			throw new Exception("Esperava-se o token \";\" ");
		} else {
			incrementar();
			stateDeclVars();
		}
	}

	private void stateVariaveis() throws Exception {
		if (!getTokenAtual().equals("id")) {
			throw new Exception("Esperava-se o token \"id\" ");
		}
		incrementar();
		if (!getTokenAtual().equals(",")) {
			return;
		} else {
			incrementar();
			stateVariaveis();
		}

	}

	private void criarListaTokens(ArquivoLeitura tabelaTokens) {

		tabelaTokens.lerLinha();

		// recebe buffer do arq tabela de tokens e cria lista de tokens
		while (true) {
			String linha = tabelaTokens.lerLinha();
			if (linha == null) {
				break; // final arquivo
			}
			String tokenAtual = extrairToken(linha);
			listaTokens.add(tokenAtual);
		}

	}

	private String extrairToken(String linha) {
		int i = 0;
		String tokenAtual = new String();
		while (linha.charAt(i) != ' ') {
			tokenAtual += linha.charAt(i);
			i++;
		}
		return tokenAtual;

	}

	private void incrementar() {
		indiceTokenAtual++;
	}

	private String getTokenAtual() {
		return listaTokens.get(indiceTokenAtual);
	}

}