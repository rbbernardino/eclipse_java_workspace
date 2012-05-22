
public class Facade {
	private Saldo consulta;
	private Extrato extrato;
	private Pagamento pagamento;
	private Transferencia transferencia;
	
	public Facade(){
		consulta = new Saldo();
		extrato = new Extrato();
		pagamento = new Pagamento();
		transferencia = new Transferencia();
	}
	
	public void consultaDoSaldo(){
		consulta.getSaldo();		
	}
	public void impressaoDoExtrato(){
		extrato.getExtrato();
	}
	public void realizacaoDoPagamento(){
		pagamento.doPagamento();
	}
	public void transferirValor(){
		transferencia.doTransferencia();
	}
}
