
package model;

import javax.swing.JOptionPane;
import view.JogoVelhaGUI;


public class JogoVelha {
    
    // atributos
    // contem os jogadores do jogo
    Jogador jogador1;
    Jogador jogador2;
    
    // contem o tabuleiro do jogo
    Tabuleiro tabuleiro = new Tabuleiro(3,3);
    
    // contem se � ou nao a fez do primeiro jogador
    boolean vezDoJogador1;
    
    // conta o numero de jogadas em uma partida
    int contaJogadas;
    
    // conta o numero de partidas empatadas
    int contaEmpates;
    
    // contem se e ou nao o fim do jogo
    boolean fimDoJogo;
    
    // contem o jogador que venceu o jogo
    Jogador vencedor;
    
    boolean sncpu;
    
    JogadorComputador computador; 
    
    /** Creates a new instance of JogoVelha */
    public JogoVelha(Jogador jogador1, Jogador jogador2) {
        // recebe os jogadores
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.sncpu = false;
        
    }
    
    /* Define as configura��es de inicializacao do jogo */
    public void iniciar() {
        this.iniciar(false);
    }

    public boolean isSncpu() {
        return this.sncpu;
    }

    public void setSncpu(boolean sncpu) {
        this.sncpu = sncpu;
    }
    
    public void iniciar(boolean sncpu) {
        this.vezDoJogador1 = true;
        this.contaJogadas = 0;
        this.fimDoJogo = false;
        this.vencedor = null;
        this.tabuleiro.esvaziarTabuleiro();
        this.sncpu = sncpu;
        
        if (sncpu == true){
            this.computador = new JogadorComputador('O');
        }
    }
    
    /* Retorna o jogador o jogador que esta na vez de jogar */
    public Jogador jogadorDaVez() {
        Jogador jogadorvez = null;
    
        if (this.vezDoJogador1 ){
            jogadorvez = jogador1;           
        }else{
            jogadorvez = jogador2;
        }
        return jogadorvez;
    }
    
    public String performJogada() {
        if ((!this.vezDoJogador1) && this.isSncpu()){
            String ret = null;
            ret = computador.fazerJogada(this.getTabuleiro());
            return(ret);
        } else {
            return (null);
        }
    }
 
    
    /* Retorna se uma determinada jogada � valida ou nao */
    public boolean ehJogadaValida(int linha, int coluna) {
        return ( linha >= 0 && linha <= 2 &&
                coluna >= 0 && coluna <= 2 &&
                this.tabuleiro.estaLivre(linha,coluna));
    }
    
    /* Efetivar uma jogada e retornar o simbolo do jogador que jogou */
    public char fazerJogada(int linha, int coluna) {
        
        // se a jogada � valida
        if (ehJogadaValida(linha,coluna)) {
            // pegar o simbolo do jogador da vez
            char simbolo = jogadorDaVez().getSimbolo();
            
            // colocar o simbolo no tabuleiro do jogador da vez
            tabuleiro.setPeca(linha,coluna,simbolo);
            
            // contar o numero de jogadas
            contaJogadas++;
            
            // verificar se o jogo nao terminou
            if (!verificaFimDoJogo()) { 
                // muda de jogador
                vezDoJogador1 = !vezDoJogador1;
            } else {
                this.fimDoJogo = true;
                if (this.vencedor != null) { // caso empatou..
                    this.vencedor.incrementaVitorias();
                }
            }
            // retornar o simbolo do jogador
            return simbolo;
        }
        return ' ';
    }
    
    public void verificaVencedor(){
         if (this.vezDoJogador1){
            this.vencedor  = this.jogador1;
        }else{
            this.vencedor  = this.jogador2;
        }
    }
    
    
    /* Verificar se o jogo terminou e definir o vencedor ou empate*/
    public boolean verificaFimDoJogo() {
        
       
        
        if (this.contaJogadas >= 5) {
            
            int linha;
            int coluna;
            char c1,c2,c3;
            
            // verifica em linha
            coluna = 0;
            for (linha=0; linha<3; linha++) {
                c1 = tabuleiro.getPeca(linha,coluna);
                c2 = tabuleiro.getPeca(linha,coluna+1);
                c3 = tabuleiro.getPeca(linha,coluna+2);
                
                if ((c1==c2) && (c1==c3) && (c1!= ' ')){
                    this.verificaVencedor();
                    return true;
                }
            }
            
            // verifica em coluna
            linha=0;
            for (coluna=0; coluna<3; coluna++) {
                c1 = tabuleiro.getPeca(linha,coluna);
                c2 = tabuleiro.getPeca(linha+1,coluna);
                c3 = tabuleiro.getPeca(linha+2,coluna);
                if ((c1==c2) && (c1==c3) && (c1!= ' ')){
                    this.verificaVencedor();
                    return true;
                }
            }
            
            
            // verifica em diagonal
            linha=0;
            coluna=0;
            c1 = tabuleiro.getPeca(linha,coluna);
            c2 = tabuleiro.getPeca(linha+1,coluna+1);
            c3 = tabuleiro.getPeca(linha+2,coluna+2);
            if ((c1==c2) && (c1==c3) && (c1!= ' ')){
                this.verificaVencedor();
                return true;
            }
             
            
            
            linha=0;
            coluna=0;
            c1 = tabuleiro.getPeca(linha,coluna+2);
            c2 = tabuleiro.getPeca(linha+1,coluna+1);
            c3 = tabuleiro.getPeca(linha+2,coluna);
            if ((c1==c2) && (c1==c3) && (c1!= ' ')){
                this.verificaVencedor();
                return true;
            }
            
            if (this.contaJogadas == 9 ){
                contaEmpates++;
                return true;
            }
        }
        // caso contrario...
        return false;
    }
    
    
    /* Informa se houve vencedor */
    public boolean houveVencedor() {
        return (vencedor != null);
    }
    
    /* Zerar o numero de partidas empatadas */
    public void zeraEmpates() {
        contaEmpates = 0;
    }
    
    /* Retornar o vencedor do jogo */
    public boolean isFimDoJogo() {
        return fimDoJogo;
    }
    
    /* Retornar o vencedor */
    public Jogador getVencedor() {
        return vencedor;
    }
    
    public Tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }
    
    /* Retornar o jogador1 */
    public Jogador getJogador1() {
        return jogador1;
    }
    
    /* Retornar o jogador2 */
    public Jogador getJogador2() {
        return jogador2;
    }

    public int getContaEmpates() {
        return contaEmpates;
    }

 
}
