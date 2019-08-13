package aps.balanceamento;

public class Stack {

	private StackNode top;

	private class StackNode {

		private Object element;
		private StackNode next;

		private StackNode(Object e, StackNode n) {
			element = e;
			next = n;
		}
	}

	// CONSTRUTORES
	public Stack() {
		top = null;
	}
	// GETS

	public Object getTop() {
		return top.element;
	}

	// METODOS
	public void push(Object elem) {
		top = new StackNode(elem, top);

	}

	public boolean pop() {
		if (isClear()) {
			return false;
		}
		top = top.next;
		return true;
	}

	public Object topAndPop() {
		if (isClear()) {
			return null;
		}
		Object ret = top.element;
		top = top.next;
		return ret;
	}

	public void printStack() {
		if (isClear()) {
			return;
		}
		StackNode ptr = top;
		while (ptr != null) {
			System.out.println(ptr.element);
			ptr = ptr.next;
		}
	}

	public boolean isClear() {
		return top == null;
	}

	public void clearStack() {
		top = null;
	}

	@Override
	public String toString() {
		String ret = "";
		if (isClear()) {
			return null;
		}
		StackNode ptr = top;
		while (ptr != null) {
			ret += ptr.element.toString() + "\n";
			ptr = ptr.next;
		}
		return ret;
	}

}
