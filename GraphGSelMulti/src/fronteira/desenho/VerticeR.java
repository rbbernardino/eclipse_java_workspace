package fronteira.desenho;

import java.awt.Color;
import java.awt.Rectangle;

public class VerticeR extends Vertice {
	private Rectangle g;
	
	public VerticeR(int id, String label, int x, int y, int w, int h, Color cor) {
		super(id, cor, label, QUADRILATERO);
		g = new Rectangle();
		g.setFrame(x, y, w, h);
	}

	public Rectangle getGeom(){
		return g;
	}
}