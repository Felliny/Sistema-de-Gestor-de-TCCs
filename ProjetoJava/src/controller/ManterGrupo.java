package controller;

import model.Aluno;
import model.Grupo;
import telaController.Constantes;

import java.io.*;
import java.util.StringTokenizer;

import javax.swing.JLabel;

public class ManterGrupo {

	public String getArq(String arquivo) throws Exception
	{
		File arq = new File(arquivo);

		if (arq.exists() && arq.isFile())
		{

	        FileReader lerFlux = new FileReader(arq);
			BufferedReader buffer = new BufferedReader(lerFlux);
			String linha = buffer.readLine();
			StringBuilder content = new StringBuilder();

			while(linha != null)
			{
				content.append(linha).append("\n");
	            linha = buffer.readLine();
			}
			buffer.close();
            lerFlux.close();
			return content.toString();
		}
		return "false";
	}
	
    public String getArqDiretorio(String nomeArq)
    {
        String caminhoRaiz, caminhoArquivo;

        caminhoRaiz = Constantes.HOME;
        caminhoArquivo = caminhoRaiz + nomeArq;

        return caminhoArquivo;
    }

	public Aluno buscarAluno(String ra) throws IOException
	{
		try {
			String arquivoAluno = getArqDiretorio(Constantes.ALUNO);
			String[] alunos = getArq(arquivoAluno).split("\n");

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grupo buscarGrupo(String[] grupos, String cod)
	{
		if (codValido(cod))
		{
			String arqAluno = getArqDiretorio(Constantes.ALUNO);
			String[] alunos = null;
			try {
				alunos = getArq(arqAluno).split("\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
			int tamGrupo = grupos.length;

			Grupo grupo = new Grupo();

			for (int g = 1; g < tamGrupo; g++)
			{
				String[] dados = grupos[g].split(";");

				if (dados[0].equals(cod))
				{
					int tam = dados.length;
					grupo.setCodigo(Integer.parseInt(dados[0]));
					grupo.setTema(dados[1]);
					Aluno[] aluno = new Aluno[tam - 2];
					for (int i = 2; i < tam; i++)
					{
						aluno[i - 2] = new Aluno();
						for (String getAluno: alunos)
						{
							String[] aux = getAluno.split(";");
							if (aux[0].equals(dados[i]))
							{
								String[] auxAluno = getAluno.split(";");
								aluno[i - 2].setRa(auxAluno[0]);
								aluno[i - 2].setNome(auxAluno[1]);
								break;
							}
						}
					}
					grupo.setAlunos(aluno);
					return grupo;
				}
			}
		}


		return null;
	}
	
	private boolean codValido(String cod)
	{
		return (cod.length() == 4);
	}
	
	private boolean raValido(String ra)
	{
		return (ra.length() == 13);
	}
	
	public void salvarGrupo(Grupo grupo)
	{
		try {
			String arqDiretorio = getArqDiretorio(Constantes.GRUPOS);
			File arq = new File(arqDiretorio);
			boolean existe = false;

			String arqContent = getArq(arqDiretorio);
			Aluno[] alunos = grupo.getAlunos();
			int tamAlunos = alunos.length;
			String content = "";
			if (!arq.exists())
			{
				existe = true;
				content = "Código;Tema;Intergrante_1;Intergrante_2;Intergrante_3;Intergrante_4;Status\n";
			}
			
			content += grupo.codigo + ";" + grupo.tema;
			for (int i = 0; i < tamAlunos; i++)
			{
				content += ";" + alunos[i].getRa();
			}
			
			content += ";" + grupo.getStatus();
			
			if (!existe)
			{
				content = arqContent + content;
			}
			
			FileWriter write = new FileWriter(arq, existe);
			PrintWriter arqWriter = new PrintWriter(write);

			arqWriter.write(content);
			arqWriter.close();
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirGrupo(Grupo grupo)
	{
		String arqDiretorio = getArqDiretorio(Constantes.GRUPOS);
		File arq = new File(arqDiretorio);
		String content = "";
		try {
			if (arq.exists() && arq.isFile())
			{
				String arqContent = getArq(arqDiretorio);
				StringTokenizer stContent = new StringTokenizer(arqContent, "\n");

				while(stContent.hasMoreTokens())
				{
					String auxContent = stContent.nextToken() + "\n";
					if (auxContent.substring(0, 4).equals(Integer.toString(grupo.getCodigo())))
						auxContent = "";

					content += auxContent;
				}

				FileWriter write = new FileWriter(arq);
				PrintWriter arqWriter = new PrintWriter(write);

				arqWriter.write(content);
				arqWriter.close();
				write.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verificaGrupoExiste(String arqContent, Grupo grupo, JLabel mensagem)
	{
		try {
			String[] content = arqContent.split("\n");
			Aluno[] alunos = grupo.getAlunos();
			int tam = content.length;
			
			for (int g = 1; g < tam; g++)
			{
				
				String[] formattedContent = content[g].split(";");
				
				if (grupo.getCodigo() == Integer.parseInt(formattedContent[0]) && !mensagem.getText().equals("1"))
				{ 
					mensagem.setText("O grupo ja existe!");
					return false;
				}
				for (int i = 0; i < 4; i++)
				{
					if (alunos == null || alunos[i].getRa().equals(""))
						break;
					
					int tamContent = formattedContent.length;
					
					for (int j = 2; j < tamContent; j++)
					{
						if (alunos[i].getRa().equals(formattedContent[j]) && !Integer.toString(grupo.getCodigo()).equals(formattedContent[0]))
						{
							mensagem.setText("<html>O RA " + alunos[i].getRa() + "<br>já está em um grupo.</html>");
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean verificaAlunoExiste(Aluno[] aluno, JLabel mensagem)
	{
		ManterGrupo manterGrupo = new ManterGrupo();
		String arq = manterGrupo.getArqDiretorio(Constantes.ALUNO);
		String[] content = null;
		try {
			content = manterGrupo.getArq(arq).split("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int tamContent = content.length;
		int tamAluno = aluno.length;
		
		
		for (int i = 0; i < tamAluno; i++)
		{
			for (int j = 1; j < tamContent; j++)
			{
				if (aluno[i].getRa().equals(""))
					break;
					
				String[] formattedContent = content[j].split(";");
				
				if (aluno[i].getRa().equals(formattedContent[0]))
				{
					break;
				}
				if (j == tamContent - 1)
				{
					mensagem.setText("<html>O RA " + aluno[i].getRa() + "<br>não existe no sistema!</html>");
					return false;
				}
			}
		}
		return true;
	}
	
}
