package telaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.fatec.StackObject.StackObject;
import controller.ManterGrupo;
import model.Grupo;
import model.Reuniao;

public class ComboBoxSubAreaConsultaController implements ActionListener
{
	
	private JComboBox<String> cbArea;
	private JComboBox<String> cbSubArea;
	private JTable tableGrupoCad;
	private JLabel lblMensagemGrupoCad;

	public ComboBoxSubAreaConsultaController(JComboBox<String> cbArea, JComboBox<String> cbSubArea, JTable tableGrupoCad, JLabel lblMensagemGrupoCad) 
	{
		this.cbArea = cbArea;
		this.cbSubArea = cbSubArea;
		this.tableGrupoCad = tableGrupoCad;
		this.lblMensagemGrupoCad = lblMensagemGrupoCad;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Consulta();
	}
	
	public String hashCode(int area)
	{
		String posit = Integer.toString(area).substring(0, 2);
		
		return posit;
	}

	private void Consulta() 
	{
		ManterGrupo manterGrupo = new ManterGrupo();
		
		String contentGrupo = null;
		String[] contentReuniao = null;
		try {
			contentGrupo = manterGrupo.getArq(manterGrupo.getArqDiretorio(Constantes.GRUPOS));
			contentReuniao = manterGrupo.getArq(manterGrupo.getArqDiretorio(Constantes.REUINOES)).split("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int tamListReuniao = 0;
		Reuniao[] reunioes = null;
		if (!contentReuniao[0].equals("false"))
		{
			tamListReuniao = contentReuniao.length + 1;
			reunioes = new Reuniao[tamListReuniao];
			reunioes[0] = new Reuniao();
			reunioes[0].setData("");
			for (int i = 1; i < tamListReuniao; i++)
			{
				try {
					String[] aux = contentReuniao[i-1].split(";");
					Reuniao reuniao = new Reuniao();
					reuniao.setCodigoGrupo(Integer.parseInt(aux[0]));
					reuniao.setData(aux[2]);
					reuniao.setStatus(Boolean.valueOf(aux[3]));
					
					reunioes[i] = reuniao;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			reunioes = new Reuniao[1];
			reunioes[0] = new Reuniao();
			reunioes[0].setData("");
		}
		
		if (contentGrupo.equals("false"))
		{
			lblMensagemGrupoCad.setText("O arquivo de grupo não existe!");
			tableGrupoCad.setModel(new DefaultTableModel(
					new Object[0][0],
					new String[] {
							"C\u00F3digo", "Tema", "\u00DAltima Reuniao"
					}));
			tableGrupoCad.getColumnModel().getColumn(0).setPreferredWidth(30);
			tableGrupoCad.getColumnModel().getColumn(1).setPreferredWidth(180);
			tableGrupoCad.getColumnModel().getColumn(2).setPreferredWidth(60);
			return;
		}
		lblMensagemGrupoCad.setText("");
		
		StackObject pilha = new StackObject();
		
		StringTokenizer stPilha = new StringTokenizer(contentGrupo, "\n");
		
		stPilha.nextToken();
		
		while(stPilha.hasMoreElements())
		{
			Grupo grupo = new Grupo();
			String[] stGrupo = stPilha.nextToken().split(";");
			
			grupo.setCodigo(Integer.parseInt(stGrupo[0]));
			grupo.setTema(stGrupo[1]);
			grupo.setStatus(Boolean.valueOf(stGrupo[6]));
			pilha.push(grupo);
		}
		
		Object[][] tabela = null;
		
		int tamPilha = pilha.size();
		
		int tamTabela = 0;
		for (int i = 0; i < tamPilha; i++)
		{
			Grupo grupo = new Grupo();
			int tamReunioes = reunioes.length;
			int contReuniao = 0;
			grupo = (Grupo) pilha.pop();
			
			for (int cont = 1; cont < tamReunioes; cont++)
			{
				if (reunioes[cont].getCodigoGrupo() == grupo.getCodigo() && !reunioes[cont].isStatus())
				{
					contReuniao = cont;
					break;
				}
				contReuniao = 0;
				
			}
			
			if (hashCode(grupo.codigo).substring(0, 1).equals(cbArea.getSelectedItem().toString().substring(0, 1))
					&& hashCode(grupo.codigo).substring(1, 2).equals(cbSubArea.getSelectedItem().toString().substring(0, 1))
					&& !grupo.getStatus())
			{
				tamTabela++;
				Object[][] saveTabela = tabela;
				tabela = new Object[tamTabela][3];
				for (int j = 0; j < tamTabela - 1; j++)
				{
					tabela[j][0] = saveTabela[j][0];
					tabela[j][1] = saveTabela[j][1];
					tabela[j][2] = saveTabela[j][2];
				}
				tabela[tamTabela-1][0] = grupo.getCodigo();
				tabela[tamTabela-1][1] = grupo.getTema();
				tabela[tamTabela-1][2] = reunioes[contReuniao].getData();
			}
		}
		tableGrupoCad.setModel(new DefaultTableModel(
				tabela,
				new String[] {
					"C\u00F3digo", "Tema", "\u00DAltima Reuniao"
				}
			));
		tableGrupoCad.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableGrupoCad.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableGrupoCad.getColumnModel().getColumn(2).setPreferredWidth(60);
	}
	

}
