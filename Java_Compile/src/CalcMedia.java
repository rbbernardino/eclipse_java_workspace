
public class CalcMedia {

	public static void main(String[] args) {
		float a;
		float b;
		float c;
		
		a = Float.parseFloat(args[0]);
		b = Float.parseFloat(args[1]);
		c = (a+b) / 2;
		
		System.out.println("Media: " + c);
	}

 }
