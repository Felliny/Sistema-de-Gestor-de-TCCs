package model;

import java.util.Arrays;

public class Grupo
{
	public int codigo;
	public String tema;
	public Aluno[] alunos;
	public Area area;
	public Boolean status;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Aluno[] getAlunos() {
		return alunos;
	}

	public void setAlunos(Aluno[] alunos) {
		this.alunos = alunos;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Grupo [codigo=" + codigo + ", tema=" + tema + ", alunos=" + Arrays.toString(alunos) + ", area=" + area
				+ "]";
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
