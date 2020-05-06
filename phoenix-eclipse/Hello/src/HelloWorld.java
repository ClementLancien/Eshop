public class HelloWorld {
	public static void main(String[] args) {
		String hello = "Hello World";
		// System.out.println(hello);

		char euro = '\u20AC';
		// System.out.println(euro);

		float db = 12.5f;
		// System.out.println(db);
		
		long entier = 15_245_879_651L;
		
		int i = 2, j = 3;
		int k = i * j;
		int a = 100 * i / j;
		int b = i / j * 100;
		int r = 5 % j;
		System.out.println(k);
		System.out.println(a);
		System.out.println(b);
		System.out.println(r);
		var msg = "Hello";
		var age =10;
		
		System.out.println(hello);
		System.out.println(euro);
		System.out.println(db);
		System.out.println(entier);
		System.out.println(msg);
		System.out.println(age);
		
//		var age2; // ne fonctionne pas
	}
}