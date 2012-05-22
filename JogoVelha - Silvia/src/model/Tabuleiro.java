

package model;

public class Tabuleiro {
    
    // atributos
    int nrLinhas;  // Define o tamanho do tabuleiro
    int nrColunas; // .......
    char tabuleiro[][]; // define uma matriz para as pe�as do tabuleiro
    
    
    /* Creates a new instance of Tabuleiro */
    public Tabuleiro(int nrLinhas, int nrColunas) {
        // recebe o tamanho do tabuleiro
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        
        // instanciar a matriz
        tabuleiro = new char [nrLinhas][nrColunas];
        
        this.esvaziarTabuleiro();
    }
    
    /* Coloca uma pe�a numa determinada posicao do tabuleiro
     *  @param linha linha onde a pe�a deve ser colocada
     *  @param coluna coluna onde a pe�a deve ser colocada
     *  @param peca simbolo a ser colocado
     */
    public void setPeca (int linha, int coluna, char peca) {
        this.tabuleiro[linha][coluna] = peca;
    }
    
    /* Retorna o simbolo de uma determinada posicao do tabuleiro
     *  @param linha linha da pe�a
     *  @param coluna coluna da peca
     */ 
    public char getPeca (int linha, int coluna) {
        return this.tabuleiro[linha][coluna];
    }
    
    
    /* Retorna se uma determinada posicao do tabuleiro nao tem simbolo */
    public boolean estaLivre (int linha, int coluna) {
        return (this.tabuleiro[linha][coluna] == ' ');
    }
    
    /* Limpa todo o tabuleiro */
    public void esvaziarTabuleiro() {
        
        int i,j;
        for (i=0; i<this.nrLinhas; i++)
            for (j=0; j<this.nrColunas; j++)
                this.tabuleiro[i][j] = ' ';
    }
    
    /* Retorna o numero de Colunas do tabuleiro */
    public int getNrColunas() {
        return nrColunas;
    }
    
    /* Retorna o numero de Linhas do tabuleiro */
    public int getNrLinhas() {
        return nrLinhas;
    }
    
    /* Retorna quantidade de posicoes livres no tabuleiro */
    public int getCountLivres () {
        int i,j,count=0;
            for (i=0; i<this.nrLinhas; i++){
                for (j=0; j<this.nrColunas; j++){
                    if (this.estaLivre(i,j)){
                        count++;
                    }
                }
            }
        return count;
    }
    
    public String[] getLista(boolean bLivres) {
        String lista[];
        if (bLivres) {
            lista = new String[getCountLivres()];
        } else {
            lista = new String[nrColunas*nrLinhas];
        }
        int z = 0;
        //lista os lugares vazios
        for(int i = 0; i < nrLinhas; i++) {
            for(int j = 0; j < nrColunas; j++) {
                if (estaLivre(i,j) || !bLivres) {
                    lista[z] = new String(Integer.toString(i)+Integer.toString((j)));
                    z++;
                }
            }
        }
        return(lista);
    }
    
}
