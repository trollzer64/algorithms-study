package estudo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Estudo {

	public static int main(String[] args) {
		System.out.println("Caminho do arquivo: ");
		Scanner leitura = new Scanner(System.in);	// Escreve o caminho do arquivo
		
		String nome = leitura.nextLine();
		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			int count_linha = 0;

			String linha = lerArq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto
			System.out.println("<" + count_linha + ">" + linha);

			Stack balanca = new Stack();
			boolean big_comment = false, quote = false, character = false;

			// ATÉ AS LINHAS ACABAREM
			while (linha != null) {
				// ATÉ A LINHA ACABAR
				for (int i = 0; i < linha.length(); i++) {
					// Prevenir comments
					if (!quote && !character) {
						if ((linha.charAt(i) == '/') && (linha.charAt(i + 1) == '/')) {
							break;
						} else if (((linha.charAt(i) == '/') && (linha.charAt(i + 1) == '*'))) {
							big_comment = true;
						} else if (((linha.charAt(i) == '*') && (linha.charAt(i + 1) == '/'))) {
							big_comment = false;
						}
					}
					// ANALISAR E EMPILHAR SE NÃO FOR COMENTÁRIO
					if ((!big_comment) && !quote) {
						if ((linha.charAt(i) == '\'')&&(linha.charAt(i) != '\\')) {
							character = !character;
							if (!((character) && ((linha.charAt(i + 2) == '\'') || (linha.charAt(i + 3) == '\'')))) {
								System.out.println("DESBALANCEADO, linha " + count_linha);
								return 1;
							}
						}
						if ((linha.charAt(i-1) != '\\' && linha.charAt(i) == '"') && !character) {
							quote = !quote;
							if (quote) {
								balanca.push(linha.charAt(i));
							} else {
								balanca.pop();
							}
						}
						if (!quote) {
							if ((linha.charAt(i) == '(') || (linha.charAt(i) == '[') || (linha.charAt(i) == '{')) {
								balanca.push(linha.charAt(i));
							} else if (((linha.charAt(i) == ')') || (linha.charAt(i) == ']') || (linha.charAt(i) == '}'))) {
								// PARENTESES
								if ((balanca.getTop().equals('{')) && (linha.charAt(i) == '}')) {
									balanca.pop();
								} else if ((balanca.getTop().equals('(')) && (linha.charAt(i) == ')')) {
									balanca.pop();
								} else if ((balanca.getTop().equals('[')) && (linha.charAt(i) == ']')) {
									balanca.pop();
								} else {
									System.out.println("DESBALANCEADO, linha " + count_linha);
									return 1;
								}
							}
						}
					}
					if ((i == linha.length() - 1) && (quote)) {
						System.out.println("DESBALANCEADO, linha " + count_linha);
						return 1;
					}
				}
				linha = lerArq.readLine(); // lê da segunda até a última linha
				count_linha++;
			}
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
				e.getMessage());
		}
		System.out.println();
		return 0;
	}
}
