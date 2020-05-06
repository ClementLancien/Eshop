
public class Person {
	String name;
	int age;
	String ville;
	String situation;
	String avec;

	public Person(String name, int age, String ville, String situation) {
		this.name = name;
		this.age = age;
		this.ville = ville;
		this.situation = situation;
		this.avec = "NA";
	}

	public Person(String name, int age, String ville, String situation, String avec) {
		this.name = name;
		this.age = age;
		this.ville = ville;
		this.situation = situation;
		this.avec = avec;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getAvec() {
		return avec;
	}

	public void setAvec(String avec) {
		this.avec = avec;
	}

	void situationChanger(String newSituation) {
		this.setSituation(newSituation);
		if (newSituation == "celibataire") {
			this.setAvec("NA");
		}

	}

	void situationChanger(String newSituation, String newAvec) {
		this.situationChanger(newSituation);
		this.setAvec(newAvec);
	}

	String nameAffiche() {
		return "nom : " + this.name;
	}

	String ageAffiche() {
		return "age : " + this.age;
	}

	String villeAffiche() {
		return "ville : " + this.ville;
	}

	String avecAffiche() {
		return "avec : " + this.avec;
	}
	
	void afficher() {
		System.out.println(this.nameAffiche());
		System.out.println(this.ageAffiche());
		System.out.println(this.villeAffiche());
		System.out.println(this.avecAffiche());
	}
}
