package fronteira.desenho;

import java.awt.Color;
import java.awt.geom.RectangularShape;

import controle.Constantes;

public class Vertice implements Constantes{
	// Esse id será necessário na hora de salvar!
	// As arestas contêm dois vértices, temos que saber
	// quais são esses vértices! Só é possível salvar texto, então
	// para não perder a referência a esses vértices, é needed o id.
	// Ao recuperar o arquivo salvo, basta instanciar todos os objetos
	// e depois setar os vertices de cada aresta baseado nos ids salvos
	// de cada vertice.
	private int id;
	
	// não é necessário saber a quais arestas os vertices estão conectados!!!! 8DDD
	// Aresta[] arestas
	
	// de acordo com as constantes QUADRILATERO E CIRCULO
	private int tipo;
	
	private Color cor;
	
	private String label = new String();
	
	public Vertice (int id, Color cor, String label, int tipo){
		this.id = id;
		this.cor = cor;
		setLabel(label);
		this.tipo = tipo;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	
	public Color getCor(){
		return cor;
	}
	
	public int getId(){
		return id;
	}
	
	public String getLabel(){
		return label;
	}
	public void setLabel(String label){
		this.label = label;
	}
	
	public void setCor(Color cor){
		this.cor = cor;
	}
	
	public RectangularShape getGeom(){
		return null;
	}
}