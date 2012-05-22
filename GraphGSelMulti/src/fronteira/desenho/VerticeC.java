package fronteira.desenho;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class VerticeC extends Vertice {
	private Ellipse2D g;
	
	public VerticeC(int id, String label, int x, int y, int w, int h, Color cor) {
		super(id, cor, label, CIRCULO);
		g = new Ellipse2D.Float();
		g.setFrame(x, y, w, h);
	}
	
	public Ellipse2D getGeom(){
		return g;
	}
}