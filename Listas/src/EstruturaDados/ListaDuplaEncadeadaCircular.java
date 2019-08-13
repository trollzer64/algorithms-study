package EstruturaDados;

public class ListaDuplaEncadeadaCircular {

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
	public ListaDuplaEncadeadaCircular() {
		head = tail = null;
		this.size = 0;
	}

	// ADD ELEMENT
	private void prepend(Comparable elem) {	// Adiciona no início da lista
		ListNode tmp = new ListNode(tail, elem, head);
		if (size == 0) {
			tail = tmp;
			head = tmp;
			head.next = head.prev = tail;
			tail.next = tail.prev = head;
		} else {
			head.prev = tail.next = tmp;
			head = tmp;
		}
		size++;
	}

	private void append(Comparable elem) {	// Adiciona no início da lista
		ListNode tmp = new ListNode(tail, elem, head);
		if (head == null) {
			tail = tmp;
			head = tmp;
			head.next = head.prev = tail;
			tail.next = tail.prev = head;
		} else {
			tail.next = head.prev = tmp;
			tail = tmp;
		}
		size++;
	}

	public void addElement(Comparable elem) {
		if (size == 0) {
			prepend(elem);
			return;
		}

		ListNode ptr = head;
		while (ptr.element.compareTo(elem) <= 0) {
			ptr = ptr.next;
			if(ptr == head){
				ptr = tail;
				break;
			}
		}

		if (size == 1) {
			if (ptr.element.compareTo(elem) <= 0) {
				prepend(elem);
			} else {
				append(elem);
			}
		} else {
			ListNode tmp = new ListNode(ptr.prev, elem, ptr.prev.next);
			ptr.prev.next = tmp;
			ptr.next.prev.prev = tmp;

			if (ptr == head) {
				head = tmp;
			} else if (ptr == tail) {
				tail = tmp;
			}
		}
		size++;
	}

	@Override
	public String toString() {
		String n = "";
		ListNode ptr = head;
		do {
			n = n + "\n" + ptr.element.toString();
			ptr = ptr.next;
		} while (ptr != tail.next);

		return n;
	}
}
