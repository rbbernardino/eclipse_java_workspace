package persistencia;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import controle.Constantes;
import controle.Workspace;
import fronteira.desenho.Aresta;
import fronteira.desenho.PainelGraficos;
import fronteira.desenho.Vertice;

public class Exportar implements Constantes {

	public static void main(String args[]) {

	}

	private static String getFormato(Vertice v) {
		String tipo = new String();
		switch (v.getTipo()) {
		case CIRCULO:
			tipo = "ellipse";
			break;
		case QUADRILATERO:
			tipo = "box";
		default:
			break;
		}
		return tipo;
	}

	private static String getColor(Vertice v) {
		if (v.getCor() == Color.BLACK){
			return "black";
		}
		if (v.getCor() == Color.BLUE){
			return "blue";
		}
		if (v.getCor() == Color.CYAN){
			return "cyan";
		}
		if (v.getCor() == Color.ORANGE){
			return "orange";
		}
		if (v.getCor() == Color.RED){
			return "red";
		}
		if (v.getCor() == Color.WHITE){
			return "white";
		}
		if (v.getCor() == Color.YELLOW){
			return "yellow";
		}
		if (v.getCor() == Color.GRAY){
			return "gray";
		}
		if (v.getCor() == Color.GREEN){
			return "green";
		}
		if (v.getCor() == Color.MAGENTA){
			return "magenta";
		}
		if (v.getCor() == Color.PINK){
			return "pink";
		}
		return "black";
	}

	public static void exportar(Workspace w, String caminho) {
		PainelGraficos painel = w.getPainel();

		ArrayList<Vertice> vertices = painel.getVertices();
		CopyOnWriteArrayList<Aresta> arestas = painel.getArestas();

		String arquivoDOT = new String();
		arquivoDOT = "graph {\r\n";
		String tipo = new String();
		String cor = new String();
		for (Vertice v : vertices) {
			tipo = getFormato(v);
			cor = getColor(v);
			// le o label de cada vertice e incrementa na string de saida
			arquivoDOT += "  " + v.getLabel() + "[shape = " + tipo
					+ ", label = " + v.getLabel() + ", color = " + cor
					+ "];\r\n";
		}
		for (Aresta a : arestas) {
			arquivoDOT += "  " + a.getVert1().getLabel() + "--" + a.getVert2().getLabel() + ";\r\n";
		}

		arquivoDOT += "}";
		ArquivoEscrita arqOUT = new ArquivoEscrita();
		arqOUT.abrir(caminho);
		arqOUT.escrever(arquivoDOT);
		arqOUT.fechar();
	}
}
