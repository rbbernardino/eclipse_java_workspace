package persistencia;

import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import controle.Workspace;
import fronteira.desenho.PainelGraficos;
import fronteira.desenho.Vertice;

public class Abrir {

//	public static void main(String args[]){
//		Abrir.abrir("/home/pesquisador/temp/jjh.grg");
//	}
//	
	public static void abrir(String caminho, Workspace w, PainelGraficos painel) {
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("Workspace", WorkspaceSalvavel.class);
		
		ArquivoLeitura arqIN = new ArquivoLeitura();
		arqIN.abrir(caminho);
		String xml = arqIN.lerTudo();
		arqIN.fechar();
		
		WorkspaceSalvavel ws = (WorkspaceSalvavel) xStream.fromXML(xml);
		rebuildWorkspace(ws, w, painel);
	}

	private static Workspace rebuildWorkspace(WorkspaceSalvavel ws, Workspace w, PainelGraficos painel) {
		w.resetWorkspace();

		createVertices(ws.vertices, painel);
		createAndConnectArestas(ws.arestas, painel);

		w.setNomeProjeto(ws.nomeDoProjeto);
		w.setAnotacoes(ws.anotacoes);
		w.setIndiceAtual(ws.indiceAtual);
		w.setState(Workspace.STATE_SELECAO);
		return w;
	}

	private static void createVertices(VerticeSalvavel[] verticesS, PainelGraficos p) {
		for(VerticeSalvavel vertS : verticesS){
			p.adicionarVertice(vertS.id, vertS.tipo, vertS.label, vertS.x, vertS.y);
		}
	}
	private static void createAndConnectArestas(ArestaSalvavel[] arestasS, PainelGraficos p) {
		for(ArestaSalvavel artS : arestasS){
			p.adicionarAresta(findVert(artS.idVert1, p.getVertices()), findVert(artS.idVert2, p.getVertices()));
		}
	}
	private static Vertice findVert(int id, ArrayList<Vertice> vertices){
		for(Vertice v : vertices){
			if(v.getId() == id){
				return v;
			}
		}
		System.out.println("ERRO ao abrir! nao foi possivel achar vertice com o id correspondente para a aresta!");
		return null;
	}
}
