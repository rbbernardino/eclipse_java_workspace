package fronteira.desenho;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import controle.Constantes;
import fronteira.desenho.Aresta;
import fronteira.desenho.Vertice;
import fronteira.desenho.VerticeC;
import fronteira.desenho.VerticeR;

// TODO mostrar pro pessoal o copyonwritearraylist!!!

public class PainelGraficos extends JPanel implements Constantes {
	private static final long serialVersionUID = 1L;

	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	private CopyOnWriteArrayList<Aresta> arestas = new CopyOnWriteArrayList<Aresta>();

	private Rectangle quadSelecao;
	
	public PainelGraficos() {
		super();
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if(quadSelecao!=null)
			g2.draw(quadSelecao);
		
		for (Aresta aresta : arestas) {
			g2.setPaint(Color.BLACK);

			// se o vertice 1 ou o vertice 2 ainda nao estiver definido (for
			// nulo)
			// as posicoes da aresta foram setadas manualmente após criar
			// a aresta
			if (aresta.getVert1() != null) {
				aresta.setX1(aresta.getVert1().getGeom().getCenterX());
				aresta.setY1(aresta.getVert1().getGeom().getCenterY());
			}
			if (aresta.getVert2() != null) {
				aresta.setX2(aresta.getVert2().getGeom().getCenterX());
				aresta.setY2(aresta.getVert2().getGeom().getCenterY());
			}
			g2.draw(aresta);
		}

		for (Vertice vertice : vertices) {
			RectangularShape s = vertice.getGeom();

			g2.setPaint(vertice.getCor());
			g2.fill(s);

			g2.setPaint(Color.BLACK);
			g2.draw(s);

			drawLabel(g2, vertice.getLabel(), s);
		}
	}

	private void drawLabel(Graphics2D g2, String label, RectangularShape s) {
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Courier New", Font.BOLD, 12));
		int lenght = label.length();
		// para centralizar o texto (label), faz-se o seguinte cálculo:
		float x = (float) (s.getCenterX() - (lenght - 1) * (s.getWidth() / 10));
		float y = (float) (s.getY() + s.getHeight() / 2);
		g2.drawString(label, x, y);
	}

	public void setFig(RectangularShape f, int x, int y) {
		f.setFrame(x, y, f.getWidth(), f.getHeight());
	}

	public Vertice findVertice(Point p) {
		// percorrer inversamente para selecionar o que estiver mais acima
		// pois os últimos criados estão no final do array...
		int l = vertices.size();
		for (int i = l - 1; i >= 0; i--) {
			Vertice vertice = vertices.get(i);
			if (vertice.getGeom().contains(p)) {
				return vertice;
			}
		}
		return null;
	}

	public Vertice adicionarVertice(int id, int tipo, String label, int x, int y) {
		Vertice v;
		switch (tipo) {
		case CIRCULO:
			v = new VerticeC(id, label, x - LARG_C / 2, y - ALT_C / 2, LARG_C,
					ALT_C, COR_VERT_PADRAO);
			vertices.add(v);
			repaint();
			return v;
		case QUADRILATERO:
			v = new VerticeR(id, label, x - LAR_R / 2, y - ALT_R, LAR_R, ALT_R,
					COR_VERT_PADRAO);
			vertices.add(v);
			repaint();
			return v;
		default:
			return null;
		}
	}

	public Aresta adicionarAresta(Vertice vert1) {
		Aresta aresta = new Aresta();
		aresta.setVert1(vert1);
		return aresta;
	}

	public Aresta adicionarAresta(Vertice vert1, Vertice vert2) {
		Aresta aresta = new Aresta();
		aresta.setVert1(vert1);
		aresta.setVert2(vert2);
		arestas.add(aresta);
		repaint();
		return aresta;
	}

	public void remAresta(Aresta arestaTemp) {
		arestas.remove(arestaTemp);
		repaint();
	}

	public void remVertice(Vertice vertice) {
		for (Aresta aresta : arestas) {
			if (aresta.getVert1() == vertice || aresta.getVert2() == vertice) {
				remAresta(aresta);
			}
		}
		vertices.remove(vertice);
		repaint();
	}
	
	public CopyOnWriteArrayList<Aresta> getArestas() {
		return arestas;
	}

	public void clearArestas() {
		arestas.clear();
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public void clearVertices() {
		vertices.clear();
	}

	public ArrayList<Vertice> seleciona(Point pointStartSel, Point point) {
		quadSelecao = new Rectangle(pointStartSel.x, pointStartSel.y, point.x - pointStartSel.x, point.y - pointStartSel.y);
		ArrayList<Vertice> vertSel = new ArrayList<Vertice>();
		for(Vertice v : vertices){
			if(quadSelecao.contains(v.getGeom().getFrame())){
				vertSel.add(v);
				v.setCor(COR_SELECAO);
			}
		}
		return vertSel;
	}


}
