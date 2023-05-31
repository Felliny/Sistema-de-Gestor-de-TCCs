package controller;

import model.Aluno;

import java.io.*;

public class ManterAluno {
	public Aluno pesquisarAluno(String[] alunos, String ra)
	{
		if (raValido(ra))
		{
			for (String dadosAluno: alunos) {
				Aluno aluno = new Aluno();
				String[] dados = dadosAluno.split(";");
				if (dados.length == 2)
				{
					aluno.setRa(dados[0]);
					aluno.setNome(dados[1]);

					if (aluno.getRa().equals(ra))
						return aluno;
				}
			}
		}
		return null;
	}

	public void salvarDados(Aluno aluno, String caminhoArquivo, boolean alunoExiste,
							String pathName, String fileName) throws Exception
	{
		File readFile = new File(caminhoArquivo);
		FileReader read = new FileReader(readFile);
		BufferedReader buffer = new BufferedReader(read);

		String line;
		StringBuilder content = new StringBuilder();
		line = buffer.readLine();
		if (line==null)
			content.append("RA;Nome do Aluno").append("\n");
		while (line != null)
		{
			content.append(line).append("\n");
			line = buffer.readLine();
		}
		String strAluno = aluno.toString();

		String conteudoArquivoAluno = content.toString();
		if (!alunoExiste)
			conteudoArquivoAluno += strAluno + "\n";
		else
		{
			conteudoArquivoAluno = atualizaDados(conteudoArquivoAluno, strAluno, aluno.getRa());
		}

		File file = new File(pathName, fileName);
		FileWriter write = new FileWriter(file);
		PrintWriter fileWriter = new PrintWriter(write);

		buffer.close();
		
		fileWriter.write(conteudoArquivoAluno);
		fileWriter.flush();
		fileWriter.close();
		write.close();
	}

	public void excluirDadosAluno(Aluno aluno, String caminhoArquivo,
								  String pathName, String fileName) throws Exception {
		File readFile = new File(caminhoArquivo);
		if (!readFile .exists())
			readFile .createNewFile();
		FileReader read = new FileReader(readFile);
		BufferedReader buffer = new BufferedReader(read);

		String line;
		StringBuilder content = new StringBuilder();

		line = buffer.readLine();
		while (line != null)
		{
			content.append(line).append("\n");
			line = buffer.readLine();
		}
		String conteudoArquivoAluno = content.toString();

		conteudoArquivoAluno = removerDado(conteudoArquivoAluno, aluno.getRa());
		File writeFile = new File(pathName, fileName);
		FileWriter write = new FileWriter(writeFile);
		PrintWriter fileWriter = new PrintWriter(write);

		buffer.close();
		
		fileWriter.write(conteudoArquivoAluno);
		fileWriter.flush();
		fileWriter.close();
		write.close();
	}

	private String atualizaDados(String conteudoArquivoAluno, String strAluno, String raAluno) {
		String[] dadosSeparados = conteudoArquivoAluno.split("\n");
		String ra;
		StringBuilder newConteudo = new StringBuilder();
		int len = dadosSeparados.length;

		for (int i = 0; i < len; i++)
		{
			String[] dados = dadosSeparados[i].split(";");
			ra = dados[0];
			String aluno;

			if (ra.equals(raAluno))
				aluno = strAluno;
			else
				aluno = dadosSeparados[i];
			newConteudo.append(aluno).append("\n");
		}
		return newConteudo.toString();
	}

	private String removerDado(String conteudoArquivoAluno, String raAluno) {
		String[] dadosSeparados = conteudoArquivoAluno.split("\n");
		String ra;
		StringBuilder newConteudo = new StringBuilder();
		int len = dadosSeparados.length;

		for (int i = 0; i < len; i++)
		{
			String[] dados = dadosSeparados[i].split(";");
			ra = dados[0];

			if (! ra.equals(raAluno))
				newConteudo.append(dadosSeparados[i]).append("\n");
		}
		return newConteudo.toString();
	}

	private boolean raValido(String ra) {
		return (ra.length() == 13);
	}
}
