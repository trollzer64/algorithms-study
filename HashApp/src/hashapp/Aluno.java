package hashapp;

public class Aluno
		implements Hashable, Comparable {

	private String nome;
	private String matricula;

	public Aluno(String nome, String matricula) {
		this.nome = nome;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean equals(Object o, int tableSize) {
		return this.hash(tableSize) == ((Aluno) o).hash(tableSize);
	}

	@Override
	public String toString() {
		return matricula + " - " + nome;
	}

	@Override
	public boolean equals(Object o) {
		return this.matricula.equals(((Aluno) o).matricula);
	}

	@Override
	public int compareTo(Object o) {
		return this.matricula.compareTo(((Aluno) o).matricula);
	}

	@Override
	public int hash(int tableSize) {
		String key = matricula;
		
		int hashValue = 0;
		int multi = 10;
		for (int i = 0; i < key.length(); i++) {
			hashValue = multi * hashValue + key.charAt(i);
		}
		hashValue %= tableSize;
		if (hashValue < 0) {
			hashValue += tableSize;
		}
		return hashValue;
	}
}
