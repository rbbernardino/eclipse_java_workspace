package entidade;

public class Cronometro {
	private long valorInicial;
	private long valorFinal;
	private long duracao;

	// Inicia a contagem temporal
	public void inicio() {
		valorFinal = 0;
		duracao = 0;
		valorInicial = System.currentTimeMillis();
	}

	// Calcula a diferença temporal
	public void fim() {
		valorFinal = System.currentTimeMillis();
		duracao = valorFinal - valorInicial;
	}

	// Retorna o diferença de tempo medida tempo em milisegundos
	public long duracao() {
		return duracao;
	}
}
