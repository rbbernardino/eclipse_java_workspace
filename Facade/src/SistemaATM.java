import javax.swing.JOptionPane;

public class SistemaATM {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		int Flag = 0;
		String Opcao = null;

		Facade facade = new Facade();
		
		Opcao = JOptionPane.showInputDialog("MENU PRINCIPAL SISTEMA ATM: \n"
				+ "1 - Consulta Saldo;\n" + "2 - Imprimir Extrato;\n"
				+ "3 - Realizar Pagamento;\n" + "4 - Fazer Transferência;\n"
				+ "Escolha sua opcao: ");
		if (Opcao.equals("1")) {
			facade.consultaDoSaldo();
			Flag = 1;
		}
		if (Opcao.equals("2")) {
			facade.impressaoDoExtrato();
			Flag = 1;
		}
		if (Opcao.equals("3")) {
			facade.realizacaoDoPagamento();
			Flag = 1;
		}
		if (Opcao.equals("4")) {
			facade.transferirValor();
			Flag = 1;
		}
		if (Flag == 0)
			JOptionPane.showMessageDialog(null, "Opcao Invalida!", "ERRO!", JOptionPane.ERROR_MESSAGE);
	}
}
