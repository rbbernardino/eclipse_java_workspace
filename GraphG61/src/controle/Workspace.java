package controle;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

import fronteira.desenho.Aresta;
import fronteira.desenho.PainelGraficos;
import fronteira.desenho.Vertice;

import persistencia.Abrir;
import persistencia.Exportar;
import persistencia.Salvar;

public class Workspace extends MouseInputAdapter implements Constantes {
	private String nomeProjeto;

	private int state = STATE_SELECAO;

	private String anotacoes = new String();

	private boolean isSaved = false;

	// painel que contem as figuras desenhadas
	private PainelGraficos painel;

	// ao arrastar um vértice, armazena-se a distancia do
	// cursor ao canto superior esquerdo da figura para 
	private Point offset = new Point();

	// vertice que esta sendo arrastado
	private Vertice verticeTemp;
	
	// aresta temporaria antes de se ter o segundo vertice
	private Aresta arestaTemp;
	
	private Vertice verticeSelecionado;
	
	private boolean dragging = false;
	
	// quando um vértice é criado, seta-se uma label padrao,
	// cada vertice com uma label diferente,
	// entao é necessário armazenar esse índice, que é incrementado
	// a cada vértice criado
	private int indiceLabelAtual = 0;

	public Workspace(PainelGraficos painelGraficos) {
		painel = painelGraficos;
		painel.addMouseListener(this);
		painel.addMouseMotionListener(this);
	}

	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		Vertice vertice = painel.findVertice(p);
		if (vertice != null) {
			// entao clicou sobre um vertice!
			tratarCliqueEmVertice(p, vertice);
		}
		// senao clicou sobre area de desenho!
		else {
			tratarCliqueAreaDesenho(p);
		}
	}

	private void tratarCliqueEmVertice(Point p, Vertice v) {
		switch (state) {
		case STATE_DELETAR:
			painel.remVertice(v);
			unselectVert();
			break;
		case STATE_SELECAO:
			unselectVert();
			selectVert(v);
			startDrag(p);
			break;
		case STATE_DESENHA_ARESTA:
			Aresta aresta = painel.adicionarAresta(v, null);
			aresta.setX2(p.x);
			aresta.setY2(p.y);
			painel.repaint();
			arestaTemp = aresta;
			dragging = true;
			unselectVert();
			break;
		default:
			// estados desenha vertice não faz nada!!
			break;
		}
	}

	private void unselectVert(){
		if(verticeSelecionado != null){
			verticeSelecionado.setCor(COR_VERT_PADRAO);
			painel.repaint();
		}
	}
	
	private void selectVert(Vertice v) {
		verticeTemp = verticeSelecionado = v;
		verticeSelecionado.setCor(Color.CYAN);
		painel.repaint();
	}

	private void startDrag(Point p) {
		offset.x = p.x - (int) verticeTemp.getGeom().getMinX();
		offset.y = p.y - (int) verticeTemp.getGeom().getMinY();
		dragging = true;
	}

	private void tratarCliqueAreaDesenho(Point p) {
		switch (state) {
		case STATE_DESENHA_VERTICE_C:
			desenharVertice(STATE_DESENHA_VERTICE_C, p.x, p.y);
			startDrag(p);
			unselectVert();
			break;
		case STATE_DESENHA_VERTICE_R:
			desenharVertice(STATE_DESENHA_VERTICE_R, p.x, p.y);
			startDrag(p);
			unselectVert();
			break;
		case STATE_DESENHA_ARESTA:
			JOptionPane.showMessageDialog(null, "Deve selecionar um vertice!");
			dragging = false;
			unselectVert();
			break;
		case STATE_SELECAO :
			unselectVert();
			break;
		default:
			break;
		}
	}

	// o próximo método é usado ao se instanciar um vértice desenhando-o
	// nesse caso sabe-se apenas as coordenadas
	public void desenharVertice(int state, int x, int y) {
		String label;
		int id = indiceLabelAtual;
		indiceLabelAtual++;
		switch (state) {
		case STATE_DESENHA_VERTICE_C:
			label = "v" + id;
			verticeTemp = painel.adicionarVertice(id, CIRCULO, label, x,
					y);
			break;
		case STATE_DESENHA_VERTICE_R:
			label = "v" + id;
			verticeTemp = painel.adicionarVertice(id, QUADRILATERO,
					label, x, y);
		default:
			break;
		}
	}

	// o proximo método é usado quando for criar um vértice direto
	// do arquivo salvo, ou seja, já se sabe o id, o label e as coordenadas!
	// tipo pode ser CIRCULO ou QUADRILATERO
	public void desenharVertice(int tipo, int id, int x, int y, String label) {
		verticeTemp = painel.adicionarVertice(id, tipo, label, x, y);
	}

	public void mouseDragged(MouseEvent e) {
		int x, y;
		if (dragging) {
			switch (state) {
			case STATE_DESENHA_ARESTA:
				arestaTemp.setX2(e.getX());
				arestaTemp.setY2(e.getY());
				painel.repaint();
				break;
			default:
				// estados de vertice!!!
				x = e.getX() - offset.x;
				y = e.getY() - offset.y;
				painel.setFig(verticeTemp.getGeom(), x, y);
				break;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if (dragging) {
			switch (state) {
			case STATE_DESENHA_ARESTA:
				Vertice v = painel.findVertice(e.getPoint());
				if (v != null) {
					arestaTemp.setVert2(v);
					painel.repaint();
				} else {
					painel.remAresta(arestaTemp);
					JOptionPane.showMessageDialog(null,
								"Vértice não encontrado!");
				}
				arestaTemp = null;
				
				break;
			case STATE_SELECAO :
				break;
			default:
				// qualquer outro estado simplesmente remove o dragging e anula
				// o vertice
				break;
			}
			verticeTemp = null;
			dragging = false;
		}
	}

	public void desenharAresta(Vertice vert1, Vertice vert2) {
		painel.adicionarAresta(vert1, vert2);
	}

	public void salvar(String nomeArquivo, String caminho, String anotacoes) {
		// salvar no local "caminho"
		isSaved = true;
		this.nomeProjeto = nomeArquivo;
		this.anotacoes = anotacoes;
		Salvar.salvar(caminho + ".grg", this);
	}

	public void open(String nome, String caminho, PainelGraficos painel) {
		Abrir.abrir(caminho, this, painel);
		isSaved = true;
	}

	public void exportar(String caminho){
		Exportar.exportar(this, caminho);
	}
	
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public PainelGraficos getPainel(){
		return painel;
	}
	
	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIndiceAtual(){
		return indiceLabelAtual;
	}
	
	public void setIndiceAtual(int indiceLabelAtual){
		this.indiceLabelAtual = indiceLabelAtual;
	}
	
	public void resetWorkspace() {
		nomeProjeto = "Novo Projeto";
		state = STATE_SELECAO;
		anotacoes = "";
		isSaved = false;
		painel.clearArestas();
		painel.clearVertices();
		painel.repaint();
		verticeSelecionado = null;
		verticeTemp = null;
		indiceLabelAtual = 0;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public CopyOnWriteArrayList<Aresta> getArestas() {
		return painel.getArestas();
	}

	public ArrayList<Vertice> getVertices() {
		return painel.getVertices();
	}
	
}