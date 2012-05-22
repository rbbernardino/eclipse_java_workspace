public class Cliente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AlvoLista lista = new AlvoLista();
		AdaptadoWebLista webLista = new AdaptadoWebLista();

		lista.inserirItem("Fazer trabalho de Engenharia de Software 2");
		System.out.println(lista.consultarItens());
		
		Adaptador adaptador = new Adaptador(webLista);

		adaptador.inserirItem("Fazer trabalho de Compiladores");
		System.out.println(adaptador.consultarItens());
	}

}
