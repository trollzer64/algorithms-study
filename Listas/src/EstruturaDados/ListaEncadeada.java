package EstruturaDados;

public class ListaEncadeada {

	// Representação
	private ListNode head;
	private ListNode tail;
	private ListNode pointer;
	private int size;

	private class ListNode {

		private Object element;
		private ListNode next;

		private ListNode(Object e, ListNode n) {
			element = e;
			next = n;
		}
	}

	// Construtores
	public ListaEncadeada() {
		size = 0;
		head = tail = pointer = null;
	}

	// Encapsulamento
	public int getSize() {
		return size;
	}

	// Métodos
	public boolean isEmpty() {
		return (head == null);
	}

	public void addElement(Object elem) {
		ListNode newElement = new ListNode(elem, null);
		if (isEmpty()) {
			head = newElement;
		} else {
			tail.next = newElement;
		}
		tail = newElement;
		size++;
	}

	public int indexOf(Object elem) {
		pointer = head;
		for (int i = 0; i < size; i++) {
			if (pointer.element.equals(elem)) {
				return i;
			} else {
				pointer = pointer.next;
			}
		}
		return -1;
	}

	private void findPrevious(int index) {
		pointer = head;
		for (int i = 0; i < index; i++) {
			pointer = pointer.next;
		}
	}

	public Object ElementAt(int index) {
		if (index < 0 || index > size - 1) {
			return null;
		}
		if (index == 0) {
			return head.element;
		}
		findPrevious(index);
		return pointer.next.element;
	}

	public void removeElement(Object elem) {
		if (isEmpty() || indexOf(elem) < 0) {
			return;
		}
		if (size == 1) {
			head = tail = null;
			return;
		}
		pointer = null;
		ListNode ptr = head;
		for (int i = 0; i < size; i++) {
			if (ptr.element.equals(elem)) {
				if(ptr == head){
					head = ptr.next;
				}
				pointer.next = ptr.next;
				break;
			}
			pointer = ptr;
			ptr = ptr.next;
		}
		size--;
	}

	public void removeAllElements() {
		head = tail = null;
		size = 0;
	}

	public void insertElementAt(Object elem, int index) {
		if (index < 0 || index > size - 1) {
			return;
		} else {
			if (index == 0) {                 //insere o elemento na posicao head
				if (head == null) {        // insere em uma lista vazia
					ListNode newElement = new ListNode(elem, null);
					head = tail = newElement;
				} else {                  // insere em uma lista com um ou mais elementos
					ListNode newElement = new ListNode(elem, head);
					head = newElement;
				}
			} else {
				findPrevious(index);
				ListNode newElement = new ListNode(elem, pointer.next);
				pointer.next = newElement;
				if (newElement.next == null) // o novo elemento e o novo tail
				{
					tail = newElement;
				}
			}
		}
		size++;
	}

	public boolean removeElementAt(int index) {
		if (index < 0 || index > size - 1) {
			return false;
		} else {
			if (index == 0) { //remove o elemento na posicao head
				if (head == tail) // remove de uma lista com um unico elemento
				{
					head = tail = null;
				} else // remove de uma lista com mais de um elemento
				{
					head = head.next;
				}
			} else { // remove um elemento diferente do head
				findPrevious(index);
				if (pointer.next == tail) // considera a remocao do no tail
				{
					tail = pointer;
				}
				pointer.next = pointer.next.next;
			}
			size--;
		}
		return true;
	}

	public void printList() {
		if (this.isEmpty()) {
			return;
		}
		ListNode itr = head;
		while (itr != null) {
			System.out.println(itr.element);
			itr = itr.next;
		}
	}

	public void printListv2() {
		for (ListNode itr = head; itr != null; itr = itr.next) {
			System.out.println(itr.element);
		}
	}
}
