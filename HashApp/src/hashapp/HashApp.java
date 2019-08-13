package hashapp;

import java.io.*;

public class HashApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 100 Alunos na lista
		int size_list = 100;
		// Fator de carga 1, 2, 5, 10, com tamanhos de tabela 100, 50, 20, 10
		ChainingHashTable hashTable_1 = new ChainingHashTable(100);
		ChainingHashTable hashTable_2 = new ChainingHashTable(50);
		ChainingHashTable hashTable_5 = new ChainingHashTable(20);
		ChainingHashTable hashTable_10 = new ChainingHashTable(10);

		// Leitura do arquivo
		File file = new File("C:\\Users\\franc\\source\\repos\\Data Structure\\HashApp\\list.csv");
		BufferedReader br = new BufferedReader(new FileReader(file));
		// Variaveis para guardar aluno e matricula de cada linha
		String st;
		String matricula = new String();
		String nome = new String();
		// br.readLine() sempre lê a próxima linha
		for (int i = size_list; (st = br.readLine()) != null; i++) {
			// Dois for para separar o nome da matricula, ; como separador
			for (int j = 0; j < st.indexOf(';'); j++) {
				if (j == 0) {
					matricula = "" + st.charAt(j);
				} else {
					matricula = matricula + st.charAt(j);
				}
			}
			for (int j = st.indexOf(';') + 1; j < st.length(); j++) {
				if (j == st.indexOf(';') + 1) {
					nome = "" + st.charAt(j);
				} else {
					nome = nome + st.charAt(j);
				}
			}
			// Inserção nas tabelas
			hashTable_1.insert(new Aluno(nome, matricula));
			hashTable_2.insert(new Aluno(nome, matricula));
			hashTable_5.insert(new Aluno(nome, matricula));
			hashTable_10.insert(new Aluno(nome, matricula));
			System.out.println(new Aluno(nome, matricula));
		}
		// Contagem das colisões
		System.out.println("\n\nQuantidade de objetos na tabela");
		System.out.println("\nTABELA 1");
		hashTable_1.printTableLog();
		System.out.println("\nTABELA 2");
		hashTable_2.printTableLog();
		System.out.println("\nTABELA 5");
		hashTable_5.printTableLog();
		System.out.println("\nTABELA 10");
		hashTable_10.printTableLog();
		
		// Teste de remoção e inserção
		System.out.println("\n\nTESTE DE INSERÇÃO");
		ChainingHashTable hashTable = new ChainingHashTable(2);
		hashTable.insert(new Aluno("123151611", "Francismar Condor Palma"));
		hashTable.insert(new Aluno("110014410", "Nelson Cerqueira Neto"));
		hashTable.insert(new Aluno("110014412", "Fabiano Filho"));
		System.out.println(hashTable);
		hashTable.remove(new Aluno("123151611", "Francismar Condor Palma"));
		System.out.println(hashTable);
	}
}
