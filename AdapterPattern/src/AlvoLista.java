import java.util.ArrayList;


public class AlvoLista {
	private ArrayList<String> itens = new ArrayList<String>();
	
	public boolean inserirItem(String item){
		itens.add(item);
		System.out.println("<" + item + "> Adicionado com sucesso localmente!");
		return true; 
	}
	
	public String consultarItens(){
		String itensTemp = new String();
		for (int i = 0; i < itens.size(); i++) {
			itensTemp += "Item " + i + ":" + itens.get(i);
		}
		return itensTemp;
	}
}
