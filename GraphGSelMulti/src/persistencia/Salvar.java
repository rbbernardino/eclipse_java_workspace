package persistencia;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.thoughtworks.xstream.XStream;

import controle.Workspace;
import fronteira.desenho.Aresta;
import fronteira.desenho.Vertice;

public class Salvar {

/*	public static void main(String args[]){
		ArrayList<Vertice> v = new ArrayList<Vertice>();
		v.add(new Vertice(1, "oi", new Point(1, 2), 1, 5));
		v.add(new Vertice(2, "oiii", new Point(6, 10), 1, 5));
		
		ArrayList<Aresta> a = new ArrayList<Aresta>();
		a.add(new Aresta(1));
		
		v.get(0).addAresta(a.get(0));
		v.get(1).addAresta(a.get(0));
		a.get(0).setVert1(v.get(0));
		a.get(0).setVert2(v.get(1));
		
		Salvar.salvar("teste", "", "humm", v, a);
	}
*/	

	public static void salvar(String caminho, Workspace w) {
		WorkspaceSalvavel ws = convertWorkspace(w);
		XStream xStream = new XStream();
		xStream.alias("Workspace", WorkspaceSalvavel.class);

		String xml = xStream.toXML(ws);
		System.out.println(xml);
		
		ArquivoEscrita arqOUT = new ArquivoEscrita();
		arqOUT.abrir(caminho);
		arqOUT.escrever(xml);
		arqOUT.fechar();
	}

	private static WorkspaceSalvavel convertWorkspace(Workspace w) {

		WorkspaceSalvavel ws = new WorkspaceSalvavel();
		ws.nomeDoProjeto = w.getNomeProjeto();
		ws.anotacoes = w.getAnotacoes();
		ws.arestas = convertArestas(w.getArestas());
		ws.vertices = convertVertices(w.getVertices());
		ws.indiceAtual = w.getIndiceAtual();
		return ws;
	}

	private static VerticeSalvavel[] convertVertices(ArrayList<Vertice> vertices) {
		VerticeSalvavel[] verticesS = new VerticeSalvavel[vertices.size()];
		
		for(int i = 0; i < vertices.size(); i++){
			verticesS[i] = convertVertice(vertices.get(i));
		}
		return verticesS;
	}

	private static VerticeSalvavel convertVertice(Vertice vertice) {
		VerticeSalvavel vs = new VerticeSalvavel();

		vs.id = vertice.getId();
		vs.altura = (int) vertice.getGeom().getHeight();
		vs.largura = (int) vertice.getGeom().getWidth();
		vs.x = (int) vertice.getGeom().getX();
		vs.y = (int) vertice.getGeom().getY();
		vs.label = vertice.getLabel();
		vs.tipo = vertice.getTipo();
		
		return vs;
	}

	private static ArestaSalvavel[] convertArestas(CopyOnWriteArrayList<Aresta> arestas) {
		ArestaSalvavel[] arestasS = new ArestaSalvavel[arestas.size()];
		
		for(int i = 0; i < arestas.size(); i++){
			arestasS[i] = convertAresta(arestas.get(i));
		}
		return arestasS;
	}

	private static ArestaSalvavel convertAresta(Aresta aresta) {
		ArestaSalvavel as = new ArestaSalvavel();
		as.idVert1 = aresta.getVert1().getId();
		as.idVert2 = aresta.getVert2().getId();
		return as;
	}
	
	public static void abrirEEscrever(String caminho, String conteudo){
		ArquivoEscrita arqOUT = new ArquivoEscrita();
		arqOUT.abrir(caminho);
		arqOUT.escrever(conteudo);
		arqOUT.fechar();
	}
}