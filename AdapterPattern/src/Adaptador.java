public class Adaptador extends AlvoLista {
	private AdaptadoWebLista webLista;

	public Adaptador(AdaptadoWebLista webLista) {
		this.webLista = webLista;
	}

	public boolean inserirItem(String item) {
		webLista.metodoPost(item);
		return true;
	}
	
	public String consultarItens() {
		return webLista.metodoGet();
	}
}
