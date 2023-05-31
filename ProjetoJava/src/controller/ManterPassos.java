package controller;

import br.fatec.ListObject.ListObject;
import br.fatec.StackObject.StackObject;
import model.Reuniao;
import telaController.Constantes;

import java.io.*;

public class ManterPassos {



	public void buscarGrupo()
	{
		
	}
	
	public void buscarReuniao()
	{
		
	}

	public ListObject getReunioes(String arquivoReuniao) throws Exception {
		File readFile = new File(arquivoReuniao);
		if (!readFile .exists())
			readFile .createNewFile();
		FileReader read = new FileReader(readFile);
		BufferedReader buffer = new BufferedReader(read);

		String line;
		StringBuilder content = new StringBuilder();

		line = buffer.readLine();
		if (line == null)
		{
			buffer.close();
			return null;			
		}
		while (line != null)
		{
			content.append(line).append("\n");
			line = buffer.readLine();
		}

		String[] linhasArquivo = content.toString().split("\n");
		int quantidadeReunioes = linhasArquivo.length;
		ListObject reunioes = new ListObject();
		
		buffer.close();
		
		if (quantidadeReunioes == 0)
			return null;

		for (int index = 0; index < quantidadeReunioes; index++)
		{
			String[] dadosReuniao = linhasArquivo[index].split(";");
			Reuniao reuniao = montarReuniao(dadosReuniao);

			if (reunioes.isEmpty())
				reunioes.addFirst(reuniao);
			else
				reunioes.addLast(reuniao);
		}
		
		

		return reunioes;
	}

	private Reuniao montarReuniao (String[] dadosReuniao)
	{
		Reuniao reuniao = new Reuniao();

		reuniao.setCodigoGrupo(Integer.parseInt(dadosReuniao[0]));
		reuniao.setAssunto(dadosReuniao[1]);
		reuniao.setData(dadosReuniao[2]);
		boolean status = Boolean.parseBoolean(dadosReuniao[3]);
		reuniao.setStatus(status);
		return reuniao;
	}

	public void salvarDados(String passos, boolean trabalhoFinalizado, ListObject reunioes)
			throws Exception
	{
		Reuniao ultimaReuinaoFeita = (Reuniao) reunioes.get(0);
		ultimaReuinaoFeita.setStatus(true);

		alterarArquivoReunioes(ultimaReuinaoFeita);
		if (trabalhoFinalizado)
			alterarArquivoGrupos(ultimaReuinaoFeita);
		criarArquivoPassos(ultimaReuinaoFeita, trabalhoFinalizado, passos);
	}

	private void criarArquivoPassos(Reuniao reuniao, boolean trabalhoFinalizado, String passos) throws IOException
	{
		String data = reuniao.getData().replace("/", "-");
		int codigo = reuniao.getCodigoGrupo();
		String assunto = reuniao.getAssunto();

		String divisor = " - ";
		String tccConcluido = trabalhoFinalizado ? "TCC Concluido" : "";
		String nomeArquivo = "Grupo: " + codigo + divisor + data + divisor +
				assunto + ".csv";
		if (!tccConcluido.isEmpty())
			nomeArquivo += divisor + tccConcluido;
		String caminhoArquivo = Constantes.HOME;
		divisor = ";";
		String primeiraLinha = codigo + divisor + data + divisor + assunto;
		if (!tccConcluido.isEmpty())
			primeiraLinha += divisor + tccConcluido;

		String segundaLinha = "Passos:\n";
		String conteudoArquivo = primeiraLinha + "\n" + segundaLinha + passos;


		File file = new File(caminhoArquivo, nomeArquivo);
		FileWriter write = new FileWriter(file);
		PrintWriter fileWriter = new PrintWriter(write);

		fileWriter.write(conteudoArquivo);
		fileWriter.flush();
		fileWriter.close();
		write.close();
	}

	private void alterarArquivoReunioes(Reuniao reuniao) throws Exception
	{
		String caminho= Constantes.HOME;
		String arquivo = Constantes.REUINOES;
		File readFile = new File(caminho+arquivo);
		FileReader read = new FileReader(readFile);
		BufferedReader buffer = new BufferedReader(read);

		String line;
		StringBuilder content = new StringBuilder();
		StackObject pReuioes = new StackObject();

		line = buffer.readLine();
		while (line != null)
		{
			pReuioes.push(line);
			line = buffer.readLine();
		}

		int size = pReuioes.size();
		boolean substitue = true;
		while (size > 0)
		{
			String aux = ((String) pReuioes.pop()).toLowerCase();
			if (aux.contains(Integer.toString(reuniao.getCodigoGrupo()))&&substitue)
			{
				aux = aux.replace("false","true");
				substitue = false;
			}
			aux += "\n";
			content.insert(0, aux);
			size--;
		}

		File file = new File(caminho, arquivo);
		FileWriter write = new FileWriter(file);
		PrintWriter fileWriter = new PrintWriter(write);
		
		buffer.close();
		
		fileWriter.write(content.toString());
		fileWriter.flush();
		fileWriter.close();
		write.close();
	}

	private void alterarArquivoGrupos(Reuniao reuniao) throws IOException
	{
		File readFile = new File(Constantes.H_GRUPOS);
		FileReader read = new FileReader(readFile);
		BufferedReader buffer = new BufferedReader(read);

		String line;
		StringBuilder content = new StringBuilder();

		line = buffer.readLine();
		content.append(line).append("\n");
		line = buffer.readLine();
		while (line != null)
		{
			String[] dados = line.split(";");
			StringBuilder novaLinha = new StringBuilder();
			int codigoAux = Integer.parseInt(dados[0]);
			if (codigoAux == reuniao.getCodigoGrupo())
			{
				for (String dado : dados)
				{
					if (dado.contains("false"))
						dado = "true";
					novaLinha.append(dado).append(";");
				}
				line = novaLinha.toString();
			}
			content.append(line).append("\n");
			line = buffer.readLine();

			String caminho= Constantes.HOME;
			String arquivo = Constantes.GRUPOS;
			File file = new File(caminho, arquivo);
			FileWriter write = new FileWriter(file);
			PrintWriter fileWriter = new PrintWriter(write);

			fileWriter.write(content.toString());
			fileWriter.flush();
			fileWriter.close();
			write.close();
		}
		
		buffer.close();

	}

}
