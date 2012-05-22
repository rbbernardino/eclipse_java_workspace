public class AdaptadoWebLista { // Acesso remoto

	// inserir item
	public void metodoPost(String item) {
		// acessa internet e envia item para servidor
		System.out.println("Item: <" + item + "> enviado para o servidor com sucesso!");
	}

	// consultar itens
	public String metodoGet() {
		// busca items na internet e imprime na tela
		System.out.println("Itens baixados com sucesso!");
		return "itens...";
	}
}
