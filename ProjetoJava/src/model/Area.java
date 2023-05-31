package model;

public class Area
{
	public String nome;
	public String subArea;

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Area [Area=" + nome + ", SubArea=" + subArea + "]";
	}
	
	
}
