package jogo_velha;

import java.util.Random;

public class Entidade {

	static char[][] matriz = new char[5][5];

	public static char[][] getMatriz() {
		return matriz;
	}

	public static void inicializarTabuleiro(char matriz[][]) {
		for (int i = 0; i < 5; i = i + 2) {
			matriz[i][1] = '|';
			matriz[i][3] = '|';
		}
		for (int i = 0; i < 5; i++) {
			matriz[1][i] = '-';
			matriz[3][i] = '-';
		}
		limparPosicoes(matriz);
	}

	public static void limparPosicoes(char matriz[][]) {
		for (int i = 0; i < 5; i = i + 2) {
			for (int j = 0; j < 5; j = j + 2) {
				matriz[i][j] = ' ';
			}

		}

	}

	public void marcarJogada(int i, int j, char simbolo) {
		i = (i - 1) * 2;
		j = (j - 1) * 2;
		matriz[i][j] = simbolo;
	}

	public boolean verificaPosicaoOcupada(int i, int j) {// vai verificar se a
		// posicao esta
		// ocupada
		i = (i - 1) * 2;
		j = (j - 1) * 2;
		if ((matriz[i][j] == 'x') || (matriz[i][j] == 'o'))
			return true; // ou seja nao pode ser usado
		else
			return false;
	}

	public boolean verificarTabuleiroOcupado() {// vai verificar se o
		// tabuleiro esta
		// todo ocupado (Forca Bruta)
		if ((matriz[0][0] == 'o' || matriz[0][0] == 'x')
				&& (matriz[0][2] == 'o' || matriz[0][2] == 'x')
				&& (matriz[0][4] == 'o' || matriz[0][4] == 'x')
				&& (matriz[2][0] == 'o' || matriz[2][0] == 'x')
				&& (matriz[2][2] == 'o' || matriz[2][2] == 'x')
				&& (matriz[2][4] == 'o' || matriz[2][4] == 'x')
				&& (matriz[4][0] == 'o' || matriz[4][0] == 'x')
				&& (matriz[4][2] == 'o' || matriz[4][2] == 'x')
				&& (matriz[4][4] == 'o' || matriz[4][4] == 'x'))
			return true;
		else
			return false;
	}

	public static boolean verificarFimJogo() {
		if (((matriz[0][0] != ' ') && (matriz[0][0] == matriz[0][2]) && (matriz[0][2] == matriz[0][4]))
				|| ((matriz[2][0] != ' ') && (matriz[2][0] == matriz[2][2]) && (matriz[2][2] == matriz[2][4]))
				|| ((matriz[4][0] != ' ') && (matriz[4][0] == matriz[4][2]) && (matriz[4][2] == matriz[4][4]))
				|| ((matriz[0][0] != ' ') && (matriz[0][0] == matriz[2][0]) && (matriz[2][0] == matriz[4][0]))
				|| ((matriz[0][2] != ' ') && (matriz[0][2] == matriz[2][2]) && (matriz[2][2] == matriz[4][2]))
				|| ((matriz[0][4] != ' ') && (matriz[0][4] == matriz[2][4]) && (matriz[2][4] == matriz[4][4]))
				|| ((matriz[0][0] != ' ') && (matriz[0][0] == matriz[2][2]) && (matriz[2][2] == matriz[4][4]))
				|| ((matriz[4][0] != ' ') && (matriz[4][0] == matriz[2][2]) && (matriz[2][2] == matriz[0][4])))

			return true;
		else
			return false;

	}

	public static boolean verificarRoboGanhador() {
		if (((matriz[0][0] == 'o') && (matriz[0][0] == matriz[0][2]) && (matriz[0][2] == matriz[0][4]))
				|| ((matriz[2][0] == 'o') && (matriz[2][0] == matriz[2][2]) && (matriz[2][2] == matriz[2][4]))
				|| ((matriz[4][0] == 'o') && (matriz[4][0] == matriz[4][2]) && (matriz[4][2] == matriz[4][4]))
				|| ((matriz[0][0] == 'o') && (matriz[0][0] == matriz[2][0]) && (matriz[2][0] == matriz[4][0]))
				|| ((matriz[0][2] == 'o') && (matriz[0][2] == matriz[2][2]) && (matriz[2][2] == matriz[4][2]))
				|| ((matriz[0][4] == 'o') && (matriz[0][4] == matriz[2][4]) && (matriz[2][4] == matriz[4][4]))
				|| ((matriz[0][0] == 'o') && (matriz[0][0] == matriz[2][2]) && (matriz[2][2] == matriz[4][4]))
				|| ((matriz[4][0] == 'o') && (matriz[4][0] == matriz[2][2]) && (matriz[2][2] == matriz[0][4])))

			return true;
		else
			return false;

	}

	public void realizarJogadaRobo() {
		Random r = new Random();
		int i = (0 + r.nextInt(3)) + 1;
		int j = (0 + r.nextInt(3)) + 1;

		while (verificaPosicaoOcupada(i, j) != false) {
			i = (0 + r.nextInt(3)) + 1;
			j = (0 + r.nextInt(3)) + 1;
		}
		marcarJogada(i, j, 'o');

	}

	public void realizarJogadaRoboDificil() {// dentro do for vao estar os
		// seis passos na ordem devida....
		for (;;) {
			if (completarJogadaRobo() == true) // completa 1o. passo
				break;
			if (bloquearJogadaHumano() == true) // completa 2o. passo
				break;
			if (construirTriangulo() == true) // completa 3o. passo
				break;
			if (bloquearTriangulo() == true) // completa 4o. passo
				break;
			if (jogarCentro() == true) // completa 5o. passo
				break;
			if (jogarCanto() == true) // completa 6o. passo
				break;
			if (jogarLateral() == true) // completa 7o. passo
				break;
		}

	}

	public boolean completarJogadaRobo() {// marca as jogadas para ganhar-
		// primeiro passo

		if ((matriz[0][0] == 'o') && (matriz[0][2] == 'o')
				&& (matriz[0][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[0][4] == 'o')
				&& (matriz[0][2] == ' ')) {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if ((matriz[0][2] == 'o') && (matriz[0][4] == 'o')
				&& (matriz[0][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[2][0] == 'o') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == ' ')) {
			marcarJogada(2, 3, 'o');
			return true;
		}
		if ((matriz[2][0] == 'o') && (matriz[2][4] == 'o')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'o') && (matriz[2][4] == 'o')
				&& (matriz[2][0] == ' ')) {
			marcarJogada(2, 1, 'o');
			return true;
		}
		if ((matriz[4][0] == 'o') && (matriz[4][2] == 'o')
				&& (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[4][0] == 'o') && (matriz[4][4] == 'o')
				&& (matriz[4][2] == ' ')) {
			marcarJogada(3, 2, 'o');
			return true;
		}
		if ((matriz[4][2] == 'o') && (matriz[4][4] == 'o')
				&& (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[2][0] == 'o')
				&& (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[4][0] == 'o')
				&& (matriz[2][0] == ' ')) {
			marcarJogada(2, 1, 'o');
			return true;
		}
		if ((matriz[2][0] == 'o') && (matriz[4][0] == 'o')
				&& (matriz[0][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[0][2] == 'o') && (matriz[2][2] == 'o')
				&& (matriz[4][2] == ' ')) {
			marcarJogada(3, 2, 'o');
			return true;
		}
		if ((matriz[0][2] == 'o') && (matriz[4][2] == 'o')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'o') && (matriz[4][2] == 'o')
				&& (matriz[0][2] == ' ')) {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if ((matriz[0][4] == 'o') && (matriz[2][4] == 'o')
				&& (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[0][4] == 'o') && (matriz[4][4] == 'o')
				&& (matriz[2][4] == ' ')) {
			marcarJogada(2, 3, 'o');
			return true;
		}
		if ((matriz[2][4] == 'o') && (matriz[4][4] == 'o')
				&& (matriz[0][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[2][2] == 'o')
				&& (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[4][4] == 'o')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'o') && (matriz[4][4] == 'o')
				&& (matriz[0][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[4][0] == 'o') && (matriz[2][2] == 'o')
				&& (matriz[0][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[4][0] == 'o') && (matriz[0][4] == 'o')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'o') && (matriz[0][4] == 'o')
				&& (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}

		return false;
	}

	public boolean bloquearJogadaHumano() {// marca as jogadas para ganhar-
		// primeiro passo

		if ((matriz[0][0] == 'x') && (matriz[0][2] == 'x')
				&& (matriz[0][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[0][4] == 'x')
				&& (matriz[0][2] == ' ')) {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if ((matriz[0][2] == 'x') && (matriz[0][4] == 'x')
				&& (matriz[0][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[2][0] == 'x') && (matriz[2][2] == 'x')
				&& (matriz[2][4] == ' ')) {
			marcarJogada(2, 3, 'o');
			return true;
		}
		if ((matriz[2][0] == 'x') && (matriz[2][4] == 'x')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'x') && (matriz[2][4] == 'x')
				&& (matriz[2][0] == ' ')) {
			marcarJogada(2, 1, 'o');
			return true;
		}
		if ((matriz[4][0] == 'x') && (matriz[4][2] == 'x')
				&& (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[4][0] == 'x') && (matriz[4][4] == 'x')
				&& (matriz[4][2] == ' ')) {
			marcarJogada(3, 2, 'o');
			return true;
		}
		if ((matriz[4][2] == 'x') && (matriz[4][4] == 'x')
				&& (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[2][0] == 'x')
				&& (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[4][0] == 'x')
				&& (matriz[2][0] == ' ')) {
			marcarJogada(2, 1, 'o');
			return true;
		}
		if ((matriz[2][0] == 'x') && (matriz[4][0] == 'x')
				&& (matriz[0][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[0][2] == 'x') && (matriz[2][2] == 'x')
				&& (matriz[4][2] == ' ')) {
			marcarJogada(3, 2, 'o');
			return true;
		}
		if ((matriz[0][2] == 'x') && (matriz[4][2] == 'x')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'x') && (matriz[4][2] == 'x')
				&& (matriz[0][2] == ' ')) {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if ((matriz[0][4] == 'x') && (matriz[2][4] == 'x')
				&& (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[0][4] == 'x') && (matriz[4][4] == 'x')
				&& (matriz[2][4] == ' ')) {
			marcarJogada(2, 3, 'o');
			return true;
		}
		if ((matriz[2][4] == 'x') && (matriz[4][4] == 'x')
				&& (matriz[0][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[2][2] == 'x')
				&& (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[4][4] == 'x')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'x') && (matriz[4][4] == 'x')
				&& (matriz[0][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[4][0] == 'x') && (matriz[2][2] == 'x')
				&& (matriz[0][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[4][0] == 'x') && (matriz[0][4] == 'x')
				&& (matriz[2][2] == ' ')) {
			marcarJogada(2, 2, 'o');
			return true;
		}
		if ((matriz[2][2] == 'x') && (matriz[0][4] == 'x')
				&& (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}

		return false;
	}
	
	public boolean construirTriangulo() {

		if ((matriz[0][0] == ' ') && (matriz[0][2] == ' ')
				&& (matriz[0][4] == 'o') && (matriz[2][0] == ' ')
				&& (matriz[2][2] == 'x') && (matriz[4][0] == 'o')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[0][2] == ' ')
				&& (matriz[0][4] == ' ') && (matriz[2][2] == 'x')
				&& (matriz[2][4] == ' ') && (matriz[4][4] == 'o')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'o') && (matriz[2][0] == ' ')
				&& (matriz[2][2] == 'x') && (matriz[4][0] == ' ')
				&& (matriz[4][2] == ' ') && (matriz[4][4] == 'o')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][4] == 'o') && (matriz[2][2] == 'x')
				&& (matriz[2][4] == ' ') && (matriz[4][0] == 'o')
				&& (matriz[4][2] == ' ') && (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		return false;
	}

	public boolean bloquearTriangulo() {

		if ((matriz[0][0] == ' ') && (matriz[0][2] == ' ')
				&& (matriz[0][4] == 'x') && (matriz[2][0] == ' ')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == 'x')) {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[0][2] == ' ')
				&& (matriz[0][4] == ' ') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == ' ') && (matriz[4][4] == 'x')) {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if ((matriz[0][4] == 'x') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == ' ') && (matriz[4][0] == 'x')
				&& (matriz[4][2] == ' ') && (matriz[4][4] == ' ')) {
			marcarJogada(2, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[2][0] == ' ')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == ' ')
				&& (matriz[4][2] == ' ') && (matriz[4][4] == 'x')) {
			marcarJogada(2, 1, 'o');
			return true;
		}
		
		
		if ((matriz[0][0] == ' ') && (matriz[0][2] == 'x')
				&& (matriz[0][4] == ' ') && (matriz[2][0] == 'x')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == ' ') && (matriz[0][2] == 'x')
				&& (matriz[0][4] == ' ') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == 'x') && (matriz[4][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][4] == ' ') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == 'x') && (matriz[4][0] == ' ')
				&& (matriz[4][2] == 'x') && (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == ' ') && (matriz[2][0] == 'x')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == ' ')
				&& (matriz[4][2] == 'x') && (matriz[4][4] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		
		
		if ((matriz[0][0] == 'x') && (matriz[2][2] == 'o')
				&& (matriz[4][2] == 'x') && (matriz[2][0] == ' ')
				&& (matriz[4][4] == ' ') && (matriz[4][0] == ' ')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == 'x') && (matriz[0][2] == ' ')
				&& (matriz[0][4] == ' ') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == 'x') && (matriz[4][4] == ' ')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == ' ') && (matriz[0][2] == ' ')
				&& (matriz[0][4] == 'x') && (matriz[2][0] == 'x')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == ' ')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[0][4] == 'x') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == ' ') && (matriz[4][0] == ' ')
				&& (matriz[4][2] == 'x') && (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		
		
		if ((matriz[0][0] == ' ') && (matriz[0][2] == 'x')
				&& (matriz[0][4] == ' ') && (matriz[2][0] == ' ')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == 'x')) {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if ((matriz[0][4] == ' ') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == 'x') && (matriz[4][0] == 'x')
				&& (matriz[4][2] == ' ') && (matriz[4][4] == ' ')) {
			marcarJogada(3, 3, 'o');
			return true;
		}
		if ((matriz[0][0] == ' ') && (matriz[2][0] == 'x')
				&& (matriz[2][2] == 'o') && (matriz[4][0] == ' ')
				&& (matriz[4][2] == ' ') && (matriz[4][4] == 'x')) {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if ((matriz[0][0] == ' ') && (matriz[0][2] == 'x')
				&& (matriz[0][4] == ' ') && (matriz[2][2] == 'o')
				&& (matriz[2][4] == ' ') && (matriz[4][4] == 'x')) {
			marcarJogada(1, 3, 'o');
			return true;
		}
		return false;
	}

		public boolean jogarCentro() {

		if (matriz[2][2] == ' ') {
			marcarJogada(2, 2, 'o');
			return true;
		}
		return false;

	}

	public boolean jogarCanto() {
		if (matriz[0][0] == ' ') {
			marcarJogada(1, 1, 'o');
			return true;
		}
		if (matriz[0][4] == ' ') {
			marcarJogada(1, 3, 'o');
			return true;
		}
		if (matriz[4][0] == ' ') {
			marcarJogada(3, 1, 'o');
			return true;
		}
		if (matriz[4][4] == ' ') {
			marcarJogada(3, 3, 'o');
			return true;
		}
		return false;
	}

	public boolean jogarLateral() {
		if (matriz[0][2] == ' ') {
			marcarJogada(1, 2, 'o');
			return true;
		}
		if (matriz[2][0] == ' ') {
			marcarJogada(2, 1, 'o');
			return true;
		}
		if (matriz[2][4] == ' ') {
			marcarJogada(2, 3, 'o');
			return true;
		}
		if (matriz[4][2] == ' ') {
			marcarJogada(3, 2, 'o');
			return true;
		}
		return false;
	}
}
