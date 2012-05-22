/*
 * JogoVelhaGUI.java
 *
 * 
 */

package view;
import java.awt.Button;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import model.Jogador;
import model.JogoVelha;
import model.Tabuleiro;
import javax.swing.JButton;



// a interação esta dentro da interface, entao inicia-se aqui
public class JogoVelhaGUI extends javax.swing.JFrame {
    
    // criar os jogadores
    Jogador jogador1 = new Jogador('X');
    Jogador jogador2 = new Jogador('0');
    
    // criar o jogo
    JogoVelha jogoVelha = new JogoVelha(jogador1,jogador2);
    
    
    /** Creates new form JogoVelhaGUI */
    public JogoVelhaGUI() {
        initComponents();
        setSize(400,400);          
    }
    
    public void limparTela(){
    // limpa a tela
        for (int count=0;count < pnJogadas.getComponentCount(); count++){
            if (pnJogadas.getComponent(count) instanceof JButton){
                JButton botao = (JButton)(pnJogadas.getComponent(count));
                botao.setText(" ");
            }
        }
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mnGrupo = new javax.swing.ButtonGroup();
        lbJogo = new javax.swing.JLabel();
        pnBotoes = new javax.swing.JPanel();
        btIniciar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        pnJogadas = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnJogo = new javax.swing.JMenu();
        jmnIniciar = new javax.swing.JMenuItem();
        jmnTipo = new javax.swing.JMenu();
        jmnPP = new javax.swing.JRadioButtonMenuItem();
        jmnPC = new javax.swing.JRadioButtonMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jmnSair = new javax.swing.JMenuItem();
        jmnRanking = new javax.swing.JMenu();
        jmnMostrarRanking = new javax.swing.JMenuItem();
        jmnZerarRanking = new javax.swing.JMenuItem();
        jmnSobre = new javax.swing.JMenu();
        jmnJogoVelha = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Velha");

        lbJogo.setFont(new java.awt.Font("Bernard MT Condensed", 0, 36)); // NOI18N
        lbJogo.setForeground(new java.awt.Color(0, 51, 153));
        lbJogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbJogo.setText("Jogo da Velha");
        getContentPane().add(lbJogo, java.awt.BorderLayout.NORTH);

        pnBotoes.setLayout(new java.awt.GridLayout(1, 0));

        btIniciar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btIniciar.setText("Iniciar Jogo");
        btIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarJogo(evt);
            }
        });
        pnBotoes.add(btIniciar);

        btSair.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btSair.setText("Sair do Jogo");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sair(evt);
            }
        });
        pnBotoes.add(btSair);

        getContentPane().add(pnBotoes, java.awt.BorderLayout.SOUTH);

        pnJogadas.setLayout(new java.awt.GridLayout(3, 3));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setName("00");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setName("01");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton2);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setName("02");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton3);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setName("10");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton4);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 0, 0));
        jButton5.setName("11");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton5);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setName("12");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton6);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 0, 0));
        jButton7.setName("20");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton7);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 0, 0));
        jButton8.setName("21");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton8);

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 0, 0));
        jButton9.setName("22");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar(evt);
            }
        });
        pnJogadas.add(jButton9);

        getContentPane().add(pnJogadas, java.awt.BorderLayout.CENTER);

        jmnJogo.setText("Jogo");

        jmnIniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jmnIniciar.setText("Iniciar");
        jmnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarJogo(evt);
            }
        });
        jmnJogo.add(jmnIniciar);

        jmnTipo.setText("Tipo de jogo");

        mnGrupo.add(jmnPP);
        jmnPP.setSelected(true);
        jmnPP.setText("Pessoa vs Pessoa");
        jmnPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pessoaClick(evt);
            }
        });
        jmnTipo.add(jmnPP);

        mnGrupo.add(jmnPC);
        jmnPC.setText("Pessoa vs Computador");
        jmnPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computadorClick(evt);
            }
        });
        jmnTipo.add(jmnPC);

        jmnJogo.add(jmnTipo);
        jmnJogo.add(jSeparator1);

        jmnSair.setText("Sair");
        jmnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sair(evt);
            }
        });
        jmnJogo.add(jmnSair);

        jMenuBar1.add(jmnJogo);

        jmnRanking.setText("Ranking");

        jmnMostrarRanking.setText("Mostrar Ranking");
        jmnMostrarRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarRanking(evt);
            }
        });
        jmnRanking.add(jmnMostrarRanking);

        jmnZerarRanking.setText("Zerar Ranking");
        jmnZerarRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zerarRanking(evt);
            }
        });
        jmnRanking.add(jmnZerarRanking);

        jMenuBar1.add(jmnRanking);

        jmnSobre.setText("Sobre");

        jmnJogoVelha.setText("Jogo da Velha");
        jmnJogoVelha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAbout(evt);
            }
        });
        jmnSobre.add(jmnJogoVelha);

        jMenuBar1.add(jmnSobre);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pessoaClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pessoaClick
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null,"Se você confirmar estará encerrando o Jogo Atual e recomeçando um novo jogo\n Tem certeza que deseja prosseguir?") == 0){
            jogoVelha.iniciar(false);
            
            // limpa a tela
            this.limparTela();
            
        }else{
            mnGrupo.setSelected(jmnPC.getModel(),true);
        }
    }//GEN-LAST:event_pessoaClick

    private void computadorClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computadorClick
        // TODO add your handling code here:
        
        if (JOptionPane.showConfirmDialog(null,"Se você confirmar estará encerrando o Jogo Atual e recomeçando um novo jogo\n Tem certeza que deseja prosseguir?") == 0){
            jogoVelha.iniciar(true);
            // limpa a tela
            this.limparTela();
        }else{
            mnGrupo.setSelected(jmnPP.getModel(),true);
        }
    }//GEN-LAST:event_computadorClick
    
    private void mostrarRanking(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarRanking
        
        String rank;
        rank = "Vitorias do Jogador 1:  "+jogador1.getVitorias()+"\n"+
                "Vitorias do Jogador 2:  "+jogador2.getVitorias()+"\n"+
                "Empates :  "+jogoVelha.getContaEmpates();
        JOptionPane.showMessageDialog(null,rank, "Ranking", 1);
    }//GEN-LAST:event_mostrarRanking
    
    private void zerarRanking(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zerarRanking
        jogador1.zerarVitorias();
        jogador2.zerarVitorias();
        jogoVelha.zeraEmpates();
    }//GEN-LAST:event_zerarRanking
    
    private void showAbout(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAbout
        
        String sobre = "Jogo da Velha\nDesenvolvido por: Josiane rodrigues\n                                   Lídia Lizziane\n"+
                "                                   Rodrigo Bernardino \n                                   Silvia Oliveira\n                                   William Belleza\n\n";
        JOptionPane.showMessageDialog(null,sobre);
    }//GEN-LAST:event_showAbout
    
    private void iniciarJogo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarJogo
        jogoVelha.iniciar(this.jogoVelha.isSncpu());
        // limpa a tela
        this.limparTela();
    }//GEN-LAST:event_iniciarJogo
    
    private void sair(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sair
        System.exit(0);
    }//GEN-LAST:event_sair


    private void jogar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogar
        
        // pegar a linha e a coluna do botao clicado
        if (!this.jogoVelha.isFimDoJogo()){
            JButton botao = (JButton) evt.getSource();
            int linha = Integer.parseInt(botao.getName().substring(0,1));
            int coluna = Integer.parseInt(botao.getName().substring(1,2));

            executaJogada(botao, linha, coluna);


            String cmp = jogoVelha.performJogada();
            if (cmp != null) {
                botao = jogadaComputador(cmp);
                if (botao != null) {
                    linha = Integer.parseInt(botao.getName().substring(0,1));
                    coluna = Integer.parseInt(botao.getName().substring(1,2));
                    executaJogada(botao, linha, coluna);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"Jogo encerrado", "Aviso", 2);
            
        }
        
    }//GEN-LAST:event_jogar
    
    public void executaJogada(JButton botao, int linha, int coluna) {
        
        Tabuleiro tabuleiro = this.jogoVelha.getTabuleiro();
        
        // corrige bug ao clicar encima da peca ja clicada
        if (tabuleiro.estaLivre(linha, coluna)) {
            // encaminhar os dados da jogada para o modelo
            char simbolo = jogoVelha.fazerJogada(linha, coluna);
            // mostrar o simbolo que o jogador jogou
            botao.setText(String.valueOf(simbolo));
            if (jogoVelha.isFimDoJogo()){
                if (jogoVelha.getVencedor() != null) {
                    if (jogoVelha.getVencedor() == jogoVelha.getJogador1()){
                        JOptionPane.showMessageDialog(null,"O Jogador 1 foi o vencedor");
                    }else{
                        JOptionPane.showMessageDialog(null,"O Jogador 2 foi o vencedor");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"O jogo empatou!");
                }
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "OCUPADO: "+ linha + ", " + coluna);
        }
        
    }
    
    private JButton jogadaComputador(String cmp) {
        if (cmp != null) {
            JButton lista[] = {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9};
           
            for (int i=0;i<lista.length;i++){
                 if (cmp.equals(lista[i].getName())){
                    return lista[i];
                }
            } 
            return(null);
        }  else {
            return(null);
        } 
    }
    
    
 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JogoVelhaGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btIniciar;
    private javax.swing.JButton btSair;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem jmnIniciar;
    private javax.swing.JMenu jmnJogo;
    private javax.swing.JMenuItem jmnJogoVelha;
    private javax.swing.JMenuItem jmnMostrarRanking;
    private javax.swing.JRadioButtonMenuItem jmnPC;
    private javax.swing.JRadioButtonMenuItem jmnPP;
    private javax.swing.JMenu jmnRanking;
    private javax.swing.JMenuItem jmnSair;
    private javax.swing.JMenu jmnSobre;
    private javax.swing.JMenu jmnTipo;
    private javax.swing.JMenuItem jmnZerarRanking;
    private javax.swing.JLabel lbJogo;
    private javax.swing.ButtonGroup mnGrupo;
    private javax.swing.JPanel pnBotoes;
    private javax.swing.JPanel pnJogadas;
    // End of variables declaration//GEN-END:variables

}
