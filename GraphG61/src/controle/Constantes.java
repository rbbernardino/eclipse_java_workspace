package controle;

import java.awt.Color;

public interface Constantes {
	public final int STATE_SELECAO = 0;
	public final int STATE_DELETAR = 1;
	public final int STATE_DESENHA_VERTICE_R = 2;
	public final int STATE_DESENHA_VERTICE_C = 3;
	public final int STATE_DESENHA_ARESTA = 4;

	public final int ALTURA_VERTICE_PADRAO = 10;
	public final int LARGURA_VERTICE_PADRAO = 10;
	
	public final Color COR_SELECAO = Color.CYAN;
	
	// constantes do painel graficos
	public final Color COR_VERT_PADRAO = Color.LIGHT_GRAY;

	public final int CIRCULO = 0;
	public final int LARG_C = 50;
	public final int ALT_C = 50;
	
	public final int QUADRILATERO = 1;
	public final int LAR_R = 70;
	public final int ALT_R = 50;
	
	public final int LINHA = 2;	
}
