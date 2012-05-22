package jogo_velha;

public class MenuJogoVelha {

	public static final int TIPO_INTERFACE = 1;
	public static final int SAIR = 2;
	public static final int INTERFACE_GRAFICA = 1;
	public static final int INTERFACE_TEXTO = 2;
	public static final int OPONENTE_HUMANO = 1;
	public static final int OPONENTE_ROBO = 2;
	public static final int VOLTAR = 3;
	public static final int FACIL = 1;
	public static final int DIFICIL = 2;
	public static final int VOLTARNIVEL = 3;

	private static String menuStr = "\nJOGO DA VELHA JLW\n\n" + 
									"Digite a opcao desejada:\n" + 
									"1 - Tipo de interface\n" + 
									"2 - Sair\n";
	
	private static String menuInterfaceStr = "\nEscolha o tipo de interface\n" + 
											 "1 - Interface Grafica (Não implementado)\n" + 
											 "2 - Interface Texto\n"+ 
											 "3 - Voltar\n";
	
	private static String menuOponente = "\nEscolha o tipo de oponente:\n" + 
	 										"1 - Oponente Humano\n" + 
	 										"2 - Oponente Robo\n"+ 
	 										"3 - Voltar\n";
	
	private static String menuNiveis = "\nEscolha o tipo de nivel:\n" + 
	   										"1 - Facil\n" + 
	   										"2 - Dificil\n" +
	   										"3 - Voltar\n";


	
	
	public static void mostrarMenuPrincipal() {

		System.out.println(menuStr);
	}
	
	public static void mostrarMenuInterface() {

		System.out.println(menuInterfaceStr);
	}
	
	public static void mostrarMenuOponente() {

		System.out.println(menuOponente);
	}
	
	public static void displayNiveis() {

		System.out.println(menuNiveis);
	}
	

	
}