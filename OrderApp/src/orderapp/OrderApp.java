package orderapp;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Vetor\tComparações");
		// LEITURA DO CSV
		String file_location = "C:\\Users\\franc\\source\\repos\\Data Structure\\OrderApp\\src\\orderapp\\";
		String COMMA_DELIMITER = ";";
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file_location.concat("QualisCapes.csv")))) {
			String line;
			// Aqui é feito uma vez para saber o valor do header e não botar na array list
			line = br.readLine();
			String header = line;
			// Leitura para uma array list pois não se sabe a quantidade de elementos
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
				records.add(Arrays.asList(values));
			}
			int lenght_file = records.size();

			// Sabendo a quantidade, criando vetor da classe Periodicos
			Periodico[] periodicos = new Periodico[lenght_file];
			// Preenchendo o vetor de periódicos
			for (int i = 0; i < lenght_file; i++) {
				periodicos[i] = new Periodico(records.get(i).get(0), records.get(i).get(1), records.get(i).get(2), records.get(i).get(3));
			}
			// Tentativa de liberação de memória pelo garbage collector (vamos precisar)
			line = null;
			records = null;
			System.gc();
			// Multiplos de 5000
			int multi = 5000;
			// Explicado mais a frente, Pigeonholesort não consegue organizar
			// tudo de uma vez, esse é o incremento
			// 0000-0000 a 1999-9999, 2000-0000 a 3999-9999, etc, até o valor máximo
			int increment = toInt("2000-0000");
			// Saída das comparaçõess
			long out = 0;
			// Multiplos de 5000 controlados por esse for
			for (int i = 1; i * multi < lenght_file; i++) {
				// Criação do arquivo designado
				FileWriter csvWriter = new FileWriter(file_location + "Ordered_" + i * multi + ".csv");
				// Inserção de hearders
				csvWriter.append(header.concat("\n"));
				
				// Criação de array com o tamanho especificado
				Periodico[] periodicos_copia = new Periodico[multi * i];
				// O pigeonhole sort tem que ser usado em pedaços menores, pois
				// delacaração de arrays de magnitude maior não é possível
				// O maior issn é 9999-9999
				for (int j = 0; j + increment <= toInt("10000-0000"); j += increment) {
					// tirar uma seção do vetor original
					for (int k = 0; k < periodicos_copia.length; k++) {
						periodicos_copia[k] = periodicos[k];
					}
					// Sort com range limitado, senão não será alocada memória o suficiente
					out += Sort.pigeonholeSort(periodicos_copia,
							x -> (Integer) (toInt(x.getIssn())),
							j, j + increment - 1);
					// 0000-0000 a 1999-0000
					// Preenchimento após a ordenação
					for (int k = 0; k < periodicos_copia.length; k++) {
						// Devido à limitação do pigeonhole, uso excessivo de
						// Vetores e memória nos forçou a substituir os valores
						// Fora do range por null
						if (periodicos_copia[k] == null) {
							break;
						}
						csvWriter.append(periodicos_copia[k].toCSV(COMMA_DELIMITER).concat("\n"));
					}
				}
				// Fechamento do arquivo
				csvWriter.flush();
				csvWriter.close();
				// Apresentação das comparações feitas
				System.out.print(multi * i + "\t");
				System.out.println(out);
			}
		}
	}

	public static int toInt(String str) {
		if (str.length() == 0) {
			return 0;
		}
		str = str.toUpperCase();
		int number = 0;
		int expo = 0;
		int count;
		char[] table = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X'};
		// Int somente suporta 9 dígitos cheios, se a string tiver mais de 9 dígitos
		// Retornará número negativo (indesejável para o pigeonhole sort)
		for (int i = str.length() - 1; i >= 0; i--) {
			count = -1;
			do {
				count++;
				if (count == table.length) {
					count = -1;
					break;
				}
			} while (str.charAt(i) != table[count]);
			if (count > -1) {
				if (count == 10) { // X é 0
					count = 0;
				}
				number += (int) Math.pow(10, expo) * count; // converter para base 10
				expo++;
			}
		}
		// Prevenindo números maiores que limite do int
		if (number < 0) {
			number = Integer.MAX_VALUE;
		}
		return number;
	}

}
