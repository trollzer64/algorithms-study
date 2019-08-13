package orderapp;

public class Periodico implements Comparable {

	private String issn;
	private String titulo;
	private String genero;
	private String extrato;

	public Periodico() {
		this.issn = "";
		this.titulo = "";
		this.genero = "";
		this.extrato = "";
	}

	public Periodico(String issn, String titulo, String genero, String extrato) {
		this.issn = issn;
		this.titulo = titulo;
		this.genero = genero;
		this.extrato = extrato;
	}

	public String getIssn() {
		return issn;
	}
	
	public String toCSV(String delimiter){
		return issn + delimiter + titulo + delimiter + genero + delimiter + extrato;
	}

	@Override
	public int compareTo(Object o) {
		Periodico per = (Periodico) o;
		return issn.compareTo(per.issn);
	}

	@Override
	public String toString() {
		return "Periodico{" + "issn=" + issn + ", titulo=" + titulo + ", genero=" + genero + ", extrato=" + extrato + '}';
	}
	

}
