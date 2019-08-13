package EstruturaDados;

public class Lista {
	// Representação

	private Comparable[] elements;	// array de objetos
	private int size;		// número de elementos na lista
	private int capacity = 5;	// capacidade inicial da lista

	// Construtores
	public Lista() {
		elements = new Comparable[capacity];
		size = 0;
	}
	// Métodos

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public void addElement(Comparable elem) {
		increaseCapacityIfNecessary();
		elements[size] = elem;
		size++;
	}

	private void increaseCapacityIfNecessary() {
		if (size == capacity) {
			Comparable[] newElements = new Comparable[capacity * 2];
			System.arraycopy(elements, 0, newElements, 0, size);
			elements = newElements;
			capacity *= 2;
		}
	}

	public void removeElement(Comparable elem) {
		int index = indexOf(elem);
		if (index >= 0) {
			removeElementAt(index);
		}
	}

	public int indexOf(Comparable elem) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(elem)) {
				return i;
			}
		}
		return -1;
	}

	public boolean removeElementAt(int index) {
		if (index < 0 || index > size - 1) {
			return false;
		} else {
			size--;
			for (int i = index; i < size; i++) {
				elements[i] = elements[i + 1];
			}
		}
		return true;
	}

	public void removeAllElements() {
		size = 0;
	}

	public boolean insertElementAt(Comparable elem, int index) {
		if (index < 0 || index > size - 1) {
			return false;
		} else {
			increaseCapacityIfNecessary();
			for (int i = size; i > index; i--) {
				elements[i] = elements[i - 1];
			}
			elements[index] = elem;
		}
		size++;
		return true;
	}
	
	public void sort(){
		if (size > 1) {
			Comparable holder;
			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {
					if (elements[i].compareTo(elements[j]) > 0) {
						holder = elements[i];
						elements[i] = elements[j];
						elements[j] = holder;
					}
				}
			}
		}
	}

	public Comparable ElementAt(int index) {
		if (index < 0 || index > size - 1) {
			return null;
		}
		return elements[index];
	}
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			s = s + elements[i] + "\n";
		}
		return s;
	}
}
