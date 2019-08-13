package filas;

public class TestaFilas {

	public static void main(String[] args) {
		FilaArray fila1 = new FilaArray();
		fila1.enqueue("A");
		fila1.enqueue("B");
		fila1.enqueue("C");
		fila1.enqueue("D");
		fila1.enqueue("E");
		fila1.enqueue("F");
		fila1.enqueue("G");
		fila1.enqueue("H");
		fila1.dequeue();
		fila1.dequeue();
		fila1.dequeue();
		fila1.enqueue("I");
		fila1.enqueue("J");
		fila1.enqueue("K");
		fila1.enqueue("L");
		
		System.out.println(fila1);
		System.out.println();
		System.out.println(fila1.arrayStatus());
		
	}
	
}
