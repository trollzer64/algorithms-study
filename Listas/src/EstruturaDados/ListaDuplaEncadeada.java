package EstruturaDados;

public class ListaDuplaEncadeada {

	// Representação
	private ListNode head;
	private ListNode tail;
	private int size;

	// Classe Lista de Nós
	private class ListNode {

		// Representação
		private Comparable element;
		private ListNode next;
		private ListNode prev;

		// Construtores
		private ListNode(ListNode prev, Comparable element, ListNode next) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}

	// Construtores
	public ListaDuplaEncadeada() {
		head = tail = null;
		this.size = 0;
	}

	// Métodos Solicitados
	// ADD ELEMENT
	public void prepend(Comparable elem) {	// Adiciona no início da lista
		ListNode tmp = new ListNode(null, elem, head);
		if (size == 0) {
			tail = tmp;
			head = tmp;
			head.next = head.prev = null;
			tail.next = tail.prev = null;
		} else {
			head.prev = tmp;
			head = tmp;
		}
		size++;
	}

	public void pospend(Comparable elem) {	// Adiciona no final da lista
		ListNode tmp = new ListNode(this.tail, elem, null);
		if (head == null) {
			tail = tmp;
			head = tmp;
			head.next = head.prev = null;
			tail.next = tail.prev = null;
		} else {
			tail.next = tmp;
			tail = tmp;
		}
		size++;
	}
	
	// INSERT ELEMENT AT INDEX POSITION
	public void insertElementAt(Comparable elem, int index) {	// Adiciona na posição index (0 a (size-1))
		if(index <= 0){
			prepend(elem);
			return;
		} else if (index >= size){
			pospend(elem);
			return;
		}
		ListNode ptr = head;
		int count = 0;
		while (ptr != null && count != index) {
			ptr = ptr.next;
			count++;
		}
		ListNode tmp = new ListNode(ptr.prev, elem, ptr.prev.next);
		tmp.next.prev = tmp;
		tmp.prev.next = tmp;
		size++;
	}

	// REMOVE ELEMENT AT INDEX POSITION
	public void removeElementAt(int index) {	// Remover elemento 
		if (size <= 0) {
			return;
		} else if (size == 1) {
			clear();
		}
		ListNode ptr = head;
		int count = 0;
		while (ptr != null && count != index) {
			ptr = ptr.next;
			count++;
		}
		if (ptr == head) {
			ptr.next.prev = ptr.prev;
			head = ptr.next;
		} else if (ptr == tail) {
			ptr.prev.next = ptr.next;
			tail = ptr.prev;
		} else if (ptr == null) {
			throw new IllegalArgumentException("Index not Exist");
		} else {
			ptr.prev.next = ptr.next;
			ptr.next.prev = ptr.prev;
		}
		size--;
	}

	// Métodos adicionais
	public void clear() {	// Limpa Lista
		size = 0;
		head = tail = null;	// prev e next deles deixam de ser acessiveis
	}

	public boolean isEmpty() {	// Verifica se está vazia
		return size == 0;
	}

	public void assign(ListaDuplaEncadeada list) {	// Assimilar (substituição) uma lista
		if (list != this) {
			clear();
			for (ListNode ptr = list.head; ptr != null; ptr = ptr.next) {
				pospend(ptr.element);
			}
		}
	}

	public void exclude(Comparable elem) {	// Retira um objeto da lista
		ListNode ptr = head;
		while (ptr != null && ptr.element.compareTo(elem) != 0) {
			ptr = ptr.next;
		}
		if (ptr == null) {
			throw new IllegalArgumentException("Object not exist");
		}
		if (head == tail) {
			clear();
		}
		if (ptr == head) {
			ptr.next.prev = null;
			head = ptr.next;
		} else if (ptr == tail) {
			ptr.prev.next = null;
			tail = ptr.prev;
		} else {
			ptr.prev.next = ptr.next;
			ptr.next.prev = ptr.prev;
		}
		size--;
	}

	public void printList() {
		for (ListNode ptr = head; ptr != null; ptr = ptr.next) {
			System.out.println(ptr.element);
		}
	}

	public int indexOf(Comparable elem){	// Retorna a posição do objeto na lista que foi solicitado
		ListNode ptr = head;
		int count = 0;
		while (ptr.element.compareTo(elem) != 0 && count != size) {
			ptr = ptr.next;
			count++;
		}
		if (count == size) {
			throw new IllegalArgumentException("Object not exist");
		}
		return count;
	}
	
	// GET
	public int getSize() {
		return this.size;
	}
	// SET
}
