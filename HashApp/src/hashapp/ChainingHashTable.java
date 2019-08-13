package hashapp;

import java.util.LinkedList;

public class ChainingHashTable {

	private LinkedList[] listTable;
	private static final int DEFAULT_TABLE_SIZE = 101;

	public ChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public ChainingHashTable(int size) {
		listTable = new LinkedList[nextPrime(size)];
		for (int i = 0; i < listTable.length; i++) {
			listTable[i] = new LinkedList();
		}
	}

	public void makeEmpty() {
		for (LinkedList list : listTable) {
			list.clear();
		}
	}

	private static int nextPrime(int number) {
		int n = number;
		if (n % 2 == 0) {
			n++;
		}
		for (; !isPrime(n); n += 2)
			;
		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3) {
			return true;
		}
		if (n == 1 || n % 2 == 0) {
			return false;
		}
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void insert(Hashable x) {
		LinkedList whichList = listTable[x.hash(listTable.length)];
		if (whichList.indexOf(x) == -1) {
			whichList.add(0, x);
		}
	}

	public void remove(Hashable x) {
		listTable[x.hash(listTable.length)].remove(x);
	}

	public void printTableLog() {
		int size;
		int empty = 0;
		int full = 0;
		for (int j = 0; j < listTable.length; j++) {
			size = listTable[j].size();
			if (size < 2) {
				if (size == 0) {
					System.out.println("[" + j + "]" + "\t0");
					empty++;
				} else {
					System.out.println("[" + j + "]" + "\t" + listTable[j].size());
				}
			} else {
				System.out.println("[" + j + "]" + "\t" + listTable[j].size());
				full += size;
			}
		}
		System.out.println(full + " COLISÕES | " + empty + " ESPAÇOS");
	}

	public void printResultLog() {
		int size;
		int empty = 0;
		int full = 0;
		for (int j = 0; j < listTable.length; j++) {
			size = listTable[j].size();
			if (size < 2) {
				if (size == 0) {
					empty++;
				}
			} else {
				full += size;
			}
		}
		System.out.println("TAMANHO: " + listTable.length + " | " + full + " COLISÕES | " + empty + " ESPAÇOS VAZIOS");
	}

	@Override
	public String toString() {
		String ret = "";
		for (int j = 0; j < listTable.length; j++) {
			ret = ret + "[" + j + "]"  + listTable[j].toString() + "\n";
		}
		return ret;
	}
	
}
