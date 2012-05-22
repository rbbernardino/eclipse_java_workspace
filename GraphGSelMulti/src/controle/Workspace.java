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

	private PainelGraficos painel;
	
	// quando um objeto é seecionado,
	// offset é a distância do ponto clicado para a origem da forma selecionada
	// para, ao arrastar, a forma ser recriada a mesma distância do cursor em que iniciou
	private Point offsetPoint = new Point();
	private ArrayList<Point> offsetPointMulti = new ArrayList<Point>();
	
	// ponto onde comecou o quadrado de selecao!
	private Point pointStartSel;
	
	private Aresta arestaTemp;
	
	// um vertice que acabou de ser criado ou selecionado para arraste
	private Vertice verticeTemp;

	// um conjunto de vértices que foram selecionados para arraste
	private ArrayList<Vertice> verticeTempMulti = new ArrayList<Vertice>();
	
	// quando arrastando um vertice ou criando area de selecao
	private boolean dragging = false;

	// quando arrastando varios vertices
	private boolean draggingMulti = false; 
	
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
			if(verticeTempMulti.isEmpty()){
				unselectVert();
				selectVert(v);
				startDrag(p);
			} else{
				startDragMulti(p);
			}
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
		if(verticeTemp != null){
			verticeTemp.setCor(COR_VERT_PADRAO);
			painel.repaint();
		}
	}
	
	private void selectVert(Vertice v) {
		verticeTemp = v;
		verticeTemp.setCor(Color.CYAN);
		painel.repaint();
	}

	private void startDrag(Point p) {
		offsetPoint.x = p.x - (int) verticeTemp.getGeom().getMinX();
		offsetPoint.y = p.y - (int) verticeTemp.getGeom().getMinY();
		dragging = true;
	}

	private void startDragMulti(Point p){
		for(Vertice v : verticeTempMulti){
			int x = p.x - (int) v.getGeom().getMinX();
			int y = p.y - (int) v.getGeom().getMinY();
			Point offs = new Point(x, y);
			offsetPointMulti.add(offs);	
		}
		draggingMulti = true;
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
			pointStartSel = p;
			setState(STATE_DESENHA_SELECAO);
			dragging = true;
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
			case STATE_DESENHA_SELECAO:
				verticeTempMulti = painel.seleciona(pointStartSel, e.getPoint());
				painel.repaint();
				break;
				
			default:
				// estados de vertice!!!
				x = e.getX() - offsetPoint.x;
				y = e.getY() - offsetPoint.y;
				painel.setFig(verticeTemp.getGeom(), x, y);
				painel.repaint();
				break;
			}
		}
		if(draggingMulti){
			for(int i=0; i<verticeTempMulti.size(); i++){
				Vertice v = verticeTempMulti.get(i);
				Point offs  = offsetPointMulti.get(i);

				x = e.getX() - offs.x;
				y = e.getY() - offs.y;
				painel.setFig(v.getGeom(), x, y);
			}
			painel.repaint();
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
							"Deve selecionar um vertice!");
				}
				arestaTemp = null;
				break;
			default:
				// qualquer outro estado simplesmente remove o dragging e anula
				// o vertice
				break;
			}
			dragging = false;
		}
		if (draggingMulti){
			setState(STATE_SELECAO);
			pointStartSel = null;
			draggingMulti = false;
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
		Salvar.salvar(caminho, this);
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
		unselectVert();
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