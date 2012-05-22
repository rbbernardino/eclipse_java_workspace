package fronteira.desenho;

import java.awt.geom.Line2D;

// extends Line2D...
public class Aresta extends Line2D.Float {
	private static final long serialVersionUID = 1L;
	
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	private Vertice vert1;
	private Vertice vert2;
	
	public Aresta() {
	}
	
	public Aresta(Vertice vert1, Vertice vert2){
		this.vert1 = vert1;
		this.vert2 = vert2;
	}
	
	public void setVert1(Vertice vert1){
		this.vert1 = vert1;
	}
	public void setVert2(Vertice vert2){
		this.vert2 = vert2;
	}
	public Vertice getVert1(){
		return vert1;
	}
	public Vertice getVert2(){
		return vert2;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX1() {
		return x1;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getX2() {
		return x2;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getY1() {
		return y1;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public double getY2() {
		return y2;
	}
}
