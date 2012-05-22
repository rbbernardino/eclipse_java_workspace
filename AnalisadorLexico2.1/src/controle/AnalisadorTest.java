package controle;

import junit.framework.TestCase;

public class AnalisadorTest extends TestCase {

	public void testErro(){
		AnalisadorLexico al = new AnalisadorLexico();
		String programa = (".");
		assertEquals(false, al.gerarTabelas(programa));
	}
	
	public void testeAnalisador(){
		AnalisadorLexico al = new AnalisadorLexico();
		String programa = ("inicio");
		al.gerarTabelas(programa);
		assertEquals("token    valor\n" +
					 "inicio   \n", al.getTabTokens());
	}

	public void testeAnalisador2(){
		AnalisadorLexico al = new AnalisadorLexico();
		String programa = ("inicio 57 +  66 - 27 && || \"eu %ásou\"  #asijaiosja1452$% #fimse se, senao: ; string imprima leia > <=  ][{} <-");

		assertEquals(true, al.gerarTabelas(programa));
		assertEquals("token    valor\n" +
					 "inicio   \n" +
					 "int   57\n"+
					 "op_mat   +\n"+
					 "int   66\n" +
					 "op_mat   -\n"+
					 "int   27\n" +
					 "op_log   &&\n" +
					 "op_log   ||\n"+
					 "string   eu %ásou\n" +
					 "op_cond   fimse\n" +
					 "op_cond   se\n" +
					 ",   \n" +
					 "op_cond   senao\n" +
					 ":   \n" +
					 ";   \n" +
					 "tipo_var   string\n" +
					 "comando   imprima\n" +
					 "comando   leia\n" +
					 "op_rel   >\n" +
					 "op_rel   <=\n" +
					 "]   \n" +
					 "[   \n" +
					 "{   \n" +
					 "}   \n" +
					 "<-   \n", al.getTabTokens());
	}
	
	public void testeAnalisador3(){
		AnalisadorLexico al = new AnalisadorLexico();
		String programa = ("idade peso idade");

		assertEquals(true, al.gerarTabelas(programa));
		assertEquals("token    valor\n" +
					 "id   1\n" +
					 "id   2\n" +
					 "id   1\n", al.getTabTokens());

		assertEquals("id    lexema\n" +
					 "1   idade\n" +
					 "2   peso\n", al.getTabSimb());
	}
	
	public void main (String args[]){
		junit.textui.TestRunner.run(AnalisadorTest.class);
	}
}
