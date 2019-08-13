package EstruturaDados;

public class TestaLista {

	public static void main(String[] args) {
		
		ListaEncadeada list1 = new ListaEncadeada();
		
		list1.addElement(new Aluno("João", "123"));
		list1.addElement(new Aluno("Maria", "023"));
		list1.addElement(new Aluno("João", "021"));
		list1.addElement(new Aluno("Jonas", "000"));
		list1.addElement(new Aluno("Tobias", "678"));
		System.out.println(list1);
		System.out.println(list1);
	}
}
