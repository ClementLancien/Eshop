public class MainException {
	
	public static void main(String[] args) {
		new MainException().process();
	}
	
	public String process() {
		try {
			String filename = "readme.txt";
			String content = readFile(filename);
			System.out.println("Fin du traitement !");
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			System.out.println("Terminé !");
		}
//		System.out.println("Terminé !"); // code unreachable
	}
	
	private String readFile(String filename) {
		//String str = null;
		//str.charAt(0);
		return null;
	}
}
