package aps.balanceamento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class APSBalanceamento {

	public static void main(String[] args) {
		System.out.println("Caminho do arquivo: ");
		Scanner leitura;	// Escreve o caminho do arquivo
		//leitura = new Scanner(System.in);
		
		// Linha padrão
		leitura = new Scanner("C:\\Users\\franc\\source\\repos\\Data Structure\\APS-Balanceamento\\src\\aps\\balanceamento\\example.java");
		String nome = leitura.nextLine();
		
		try {
			// Abrir arquivo no caminho espeficicado
			FileReader arq = new FileReader(nome);
			BufferedReader ler_arq = new BufferedReader(arq);

			Stack chaves = new Stack();	// Guarda as chaves
			int num_linha = 1;			// Guarda o numero da linha 

			String linha = ler_arq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto

			// REGRAS DA STACK:
			// Comentários de multiplas linhas são guardados como '/'
			// O resto são guardados de forma igual a seu char
			while (linha != null) {
				for (int i = 0; i < linha.length(); i++) {
					// Evitar usar .equals(Object) de uma Stack vazia com .isClear()
					if (chaves.isClear()) {
						// Só pode abrir chaves e comentário quando se não tem nada aberto
						if (linha.length() > 1 && i < linha.length() - 1) {
							// Comentário simples vai até o fim da linha
							if (linha.charAt(i) == '/' && linha.charAt(i + 1) == '/') {
								break;
							} // Abre comentário estilo /*
							else if (linha.charAt(i) == '/' && linha.charAt(i + 1) == '*') {
								chaves.push('/');
							}
						}
						// Abre qualquer chaves
						if (linha.charAt(i) == '(' || linha.charAt(i) == '[' || linha.charAt(i) == '{') {
							chaves.push(linha.charAt(i));
						}

						// Qualquer outro caso são caracteres comuns
					} // A Stack tem objetos e .equals(Object) pode ser utilizado 
					else {
						// Está dentro de String
						if (chaves.getTop().equals('\"')) {
							// Evitar casos de aspas duplas dentro de Strings "Isso é uma aspas duplas: \"." ou "Uma barra: \\"
							if ((linha.charAt(i) == '\"' && linha.charAt(i - 1) != '\\')
								|| (linha.charAt(i) == '\"' && linha.charAt(i - 1) == '\\' && linha.charAt(i - 2) == '\\')) {
								// Fecha String
								chaves.pop();
							}
							// Ultimo caractere da linha não fecha a String aberta 
							if (i == linha.length() - 1 && chaves.getTop().equals('\"')) {
								System.out.println("ASPAS DUPLAS DESBALANCEADAS (String só cabe em uma única linha): linha " + num_linha);
								return;
							}
						} // Está dentro de Char
						else if (chaves.getTop().equals('\'')) {
							// Chars só podem ter tamanho 'a' (3) ou '\n' (4)
							// Isso é verificado quando bota aspas na pilha

							// Evitar casos de aspas dentro de Chars '\'' ou '\\'
							if ((linha.charAt(i) == '\'' && linha.charAt(i - 1) != '\\')
								|| (linha.charAt(i) == '\'' && linha.charAt(i - 1) == '\\' && linha.charAt(i - 2) == '\\')) {
								// Fecha char
								chaves.pop();
							}
							// Ultimo caractere da linha não fecha o char aberto
							if (i == linha.length() - 1 && chaves.getTop().equals('\'')) {
								System.out.println("ASPAS SIMPLES DESBALANCEADAS (char só cabe em uma única linha): linha " + num_linha);
								return;
							}
						} // Está dentro de comentário
						else if (chaves.getTop().equals('/')) {
							// Aguarda o fechamento do comentário */
							if (linha.length() > 1 && i < linha.length() - 1) {
								if (linha.charAt(i) == '*' && linha.charAt(i + 1) == '/') {
									chaves.pop();
								}
							}

						} // Dentro de chaves, qualquer caractere pode ser aberto
						else {
							// ABERTURAS
							if (linha.length() > 1 && i < linha.length() - 1) {
								// Comentário simples vai até o fim da linha
								if (linha.charAt(i) == '/' && linha.charAt(i + 1) == '/') {
									break;
								} // Abre comentário estilo /*
								else if (linha.charAt(i) == '/' && linha.charAt(i + 1) == '*') {
									chaves.push('/');
								}
							}
							switch (linha.charAt(i)) {
							// Abre qualquer chaves
								case '(':
								case '[':
								case '{':
									chaves.push(linha.charAt(i));
									break;
							// Abre aspas simples
								case '\'':
									chaves.push('\'');
									break;
							// Abre aspas duplas
								case '\"':
									chaves.push('\"');
									break;
								default:
									break;
							}
							// FECHAMENTOS
							// Ignora outros caracteres como letras
							if (linha.charAt(i) == ')' || linha.charAt(i) == ']' || linha.charAt(i) == '}') {
								if (chaves.getTop().equals('(') && linha.charAt(i) == ')') {
									chaves.pop();
								} else if (chaves.getTop().equals('[') && linha.charAt(i) == ']') {
									chaves.pop();
								} else if (chaves.getTop().equals('{') && linha.charAt(i) == '}') {
									chaves.pop();
								} else {
									System.out.println("CHAVES DESBALANCEADAS: linha " + num_linha);
								}
							}
						}
					}
				}
				linha = ler_arq.readLine();
				num_linha++;
			}
			if (!chaves.isClear()) {
				System.out.println("CHAVES DESBALANCEADAS: linha " + (num_linha - 1));
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}

}
