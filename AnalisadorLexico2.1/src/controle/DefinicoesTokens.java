package controle;

public final class DefinicoesTokens {
	public static String getValorDoLexema(String lexema) {
		//------------------ OP LÓGICO --------------------
		if (lexema.equals("&&")) {
			return "&&";
		}
		if (lexema.equals("||")) {
			return "||";
		}
		//------------------ OP ARITMETICO --------------------
		if (lexema.equals("+")) {
			return "+";
		}
		if (lexema.equals("-")) {
			return "-";
		}
		if (lexema.equals("/")) {
			return "/";
		}
		if (lexema.equals("*")) {
			return "*";
		}
		//-------------------OP RELACIONAL ------------------
		if(lexema.equals(">")){
			return ">";
		}
		if(lexema.equals("<")){
			return "<";
		}
		if(lexema.equals("==")){
			return "==";
		}
		if(lexema.equals("<=")){
			return "<=";
		}
		if(lexema.equals(">=")){
			return ">=";
		}
		if(lexema.equals("!=")){
			return "!=";
		}
		// --------------------- SEM VALOR ------------------
		if (isPalavraReservada(lexema)) {
			return "";
		}

		// string -> "[sequencia]"
		// corrigir aki!!!!!! a gente tem que fazer o outro trbalaho!!! >.<
		return "valor de lexema nao encontrado: \"" + lexema + "\"";
	}

	// recebe uma palavra começando com letra e verifica o token (palavra
	// reservada ou identificador)
	public static String getTokenDoLexema(String lexema) {
		if (isPalavraReservada(lexema)){
			return lexema;
		}
		if (lexema.equals("&&") || lexema.equals("||")) {
			return "op_log";
		}
		if (lexema.equals(">") || lexema.equals("<") || lexema.equals(">=")
				|| lexema.equals("<=") || lexema.equals("=")
				|| lexema.equals("!=")) {
			return "op_rel";
		}
		if (lexema.equals("[")) {
			return "[";
		}
		if (lexema.equals("]")) {
			return "]";
		}
		if (lexema.equals(":")) {
			return ":";
		}
		if (lexema.equals(",")) {
			return ",";
		}
		if (lexema.equals(";")) {
			return ";";
		}
		if (lexema.equals("(")) {
			return "(";
		}
		if (lexema.equals(")")) {
			return ")";
		}
		if (lexema.equals("{")) {
			return "{";
		}
		if (lexema.equals("}")) {
			return "}";
		}
		if (lexema.equals("<-")) {
			return "<-";
		}
		// se nao for palavra reservada, é identificador!
		return "id";
	}

	private static boolean isPalavraReservada(String lexema){
		return lexema.equals("inicio") || lexema.equals("fim") || lexema.equals("se") || lexema.equals("entao") ||
		lexema.equals("senao") || lexema.equals("fimse") || lexema.equals("imprima") || lexema.equals("leia")
		|| lexema.equals("int") || lexema.equals("string");
	}
}
