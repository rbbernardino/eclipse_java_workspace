package entidade;

import java.util.ArrayList;

public class FilaDeProntos {

	public static ArrayList<Processo> p = new ArrayList<Processo>();
	private static final int CABECA = 0;
	
	// Coloca um processo no final da fila
	public void enfileirar(Processo processo) {
		p.add(processo);
	}
	
	// retira um processo da cabeca da fila 
	public Processo getPrimeiro() {
		Processo primeiro = p.get(CABECA);

		p.remove(CABECA);

		return primeiro;
	}
	
	// verifica se a lista esta vazia
	public boolean estaVazio(){
		return p.isEmpty();
	}
	
}
