package controle;

import java.util.ArrayList;

public class TabelaDeSimbolos {
	private int idAtual = 0;
	
	private ArrayList<Simbolo> simbolosArray = new ArrayList<Simbolo>();
	
	public int addSimbolo(String simbIn){

		for (int i = 0; i < simbolosArray.size(); i++) {
			Simbolo simbTemp = simbolosArray.get(i);
			if(simbIn.equals(simbTemp.nome)){
				return simbTemp.id;
			}
		}
		
		// se sair do for é porque não existe simbolo
		idAtual++;
		Simbolo novoSimb = new Simbolo(idAtual, simbIn);
		simbolosArray.add(novoSimb);
		
		return idAtual;
	}

	// -------------------DESATIVADO-------------------
	// verifica se simbolo já existe na lista
//	private boolean hasSimbolo(String simb){
//				return true;
//	}
	
	public String getTabSimb(){
		String tabSimbOut = "id    lexema\n";

		for (int i = 0; i < simbolosArray.size(); i++) {
			Simbolo simbTemp = simbolosArray.get(i);
			tabSimbOut += simbTemp.id + "   " +simbTemp.nome + "\n";
		}
		
		return tabSimbOut;
	}
	
	private class Simbolo{
		public int id;
		public String nome;

		public Simbolo(int id, String nome){
			this.id = id;
			this.nome = nome;
		}
	}
}
