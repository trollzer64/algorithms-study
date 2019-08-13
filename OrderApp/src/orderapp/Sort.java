package orderapp;

import java.util.*;
import java.util.function.Function;

public class Sort {

	public static long selectionSort(Comparable[] vet) {
		long count = 0;
		for (int ref = 0; ref < vet.length - 1; ref++) {
			int min = ref;
			for (int i = ref + 1; i < vet.length; i++) {
				count++;
				if (vet[i].compareTo(vet[min]) > 0) {
					min = i;
				}
			}
			if (min != ref) {
				Comparable tmp = vet[min];
				vet[min] = vet[ref];
				vet[ref] = tmp;
			}
//			System.out.println(vet[ref]);
		}
		return count;
	}

	public static long insertSort(Comparable[] vet) {
		int i;
		long count = 0;
		for (int j = 0; j < vet.length; j++) {
			Comparable ref = vet[j];
			for (i = j; i > 0 && ref.compareTo(vet[i - 1]) < 0; i--) {
				vet[i] = vet[i - 1];
				count++;
			}
			vet[i] = ref;

		}
//		for (int j = 0; j < vet.length; j++) {
//			System.out.println(vet[j]);
//		}
		return count;
	}

	public static long shellSort(Comparable vet[]) {
		long count = 0;
		for (int gap = vet.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < vet.length; i++) {
				Comparable tmp = vet[i];
				int j = i;
				for (; j >= gap && tmp.compareTo(vet[j - gap]) < 0; j -= gap) {
					vet[j] = vet[j - gap];
					count++;
				}
				vet[j] = tmp;
			}
		}
//		for (int i = 0; i < vet.length; i++) {
//			System.out.println(vet[i]);
//		}
		return count;
	}

	private static Comparable[] merge(Comparable[] a, Comparable[] b) {
		Comparable[] ret = new Comparable[a.length + b.length];
		int countA = 0, countB = 0;
		for (int i = 0; i < ret.length; i++) {
			if (countA >= a.length) {
				ret[i] = b[countB++];
			} else if (countB >= b.length) {
				ret[i] = a[countA++];
			} else {
				if (a[countA].compareTo(b[countB]) < 0) {
					ret[i] = a[countA++];
				} else {
					ret[i] = b[countB++];
				}
			}
		}
		return ret;
	}

	public static long oldPigeonHoleSort(int arr[], int n) {
		long count = 0;
		int min = arr[0];
		int max = arr[0];
		int range, i, j, index;

		for (int a = 0; a < n; a++) {
			if (arr[a] > max) {
				max = arr[a];
			}
			if (arr[a] < min) {
				min = arr[a];
			}
			count++;
		}

		range = max - min + 1;
		int[] phole = new int[range];
		Arrays.fill(phole, 0);

		for (i = 0; i < n; i++) {
			phole[arr[i] - min]++;
			count++;
		}

		index = 0;
		for (j = 0; j < range; j++) {
			while (phole[j]-- > 0) {
				arr[index++] = j + min;
			}
			count++;
		}
		return count;
	}

	public static <T> long pigeonholeSort(T[] tosort, Function<T, Integer> f, int max) {
		return pigeonholeSort(tosort, f, 0, max);
	}

	public static <T> long pigeonholeSort(T[] tosort, Function<T, Integer> f, int min, int max) {
		long count = 0;
		// Buracos com lista encadeada 
		LinkedList<T>[] holes = (LinkedList<T>[]) new LinkedList[max - min + 1];
		for (int i = 0; i < max - min + 1; i++) {
			holes[i] = new LinkedList<T>();
		}
		// Adiciona os itens na lista
		for (T t : tosort) {
			if (f.apply(t) >= min && f.apply(t) <= max) {
				holes[f.apply(t) - min].add(t);
				count++;
			}
		}
		// Adicionar os itens
		int i;
		int j;
		for (i = 0, j = 0; i < max - min + 1; i++) {
			while (holes[i].size() > 0) {
				tosort[j++] = holes[i].removeLast();
			}
			count++;
		}
		// Elimina itens não desejados (fora do range estabelecido)
		for (; j < tosort.length; j++) {
			tosort[j] = null;
		}
		return count;
	}
}
