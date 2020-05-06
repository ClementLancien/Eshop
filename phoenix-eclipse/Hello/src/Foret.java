/*
 * creation d'une foret dans laquelle on met le feu a un arbre puis on suit la propagation du feu en fonction du vent
 * arbre : 0
 * arbre en feu : 1
 * arbre mort : 2
 */
public class Foret {
	int i, j;
	int[][] foret;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int[][] getForet() {
		return foret;
	}

	public void setForet(int i, int j) {
		this.foret = new int[i][j];
	}

	public Foret(int i, int j) {
		this.i = i;
		this.j = j;
		this.setForet(i, j);
		for (int v = 0; v < i; v++) {
			for (int w = 0; w < j; w++) {
				this.changeCase(v, w, 0);
			}
		}

	}

	public void treeAfficher() {
		for (int i = 0; i < this.getForet().length; i++) {
			for (int j = 0; j < this.getForet().length; j++) {
				if ((j + 1) == this.getForet().length) {
					System.out.println(this.getCase(i, j));
				} else {
					System.out.print(this.getCase(i, j) + " | ");
				}
			}
		}
	}

	public void changeCase(int i, int j, int value) {
		this.foret[i][j] = value;
	}

	public int getCase(int i, int j) {
		return this.foret[i][j];
	}

	public void mettreFeu(int i, int j) {
		if (i >= 0 && i < this.getI() && j >= 0 && j < this.getJ()) {
			this.changeCase(i, j, 1);
		} else {
			System.out.println("Out of range");
		}
	}

	public int[] getCaseEnFeu() {
//		int[] result = new int[2];
		for (int i = 0; i < this.getI(); i++) {
			for (int j = 0; j < this.getJ(); j++) {
				if (this.getCase(i, j) == 1) {
//					result[0]=i;
//					result[1]=j;
					return new int[] { i, j };
				}

			}
		}
//		return new int[] {i,j};
		return new int[] { -1, -1 };
	}

	public void propagation() {

	}

	public static void main(String[] args) {
		Foret arbre = new Foret(4, 4);
		arbre.treeAfficher();
		arbre.mettreFeu(0, 3);
		System.out.println();
		arbre.treeAfficher();
	}
}
