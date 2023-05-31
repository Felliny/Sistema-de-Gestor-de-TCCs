package telaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import controller.ManterGrupo;
import model.Aluno;
import model.Area;
import model.Grupo;

public class BotaoGrupoExcluirController implements ActionListener
{
	private JFormattedTextField[] RA;
	private JFormattedTextField cod;
	private JFormattedTextField tema;
	private JComboBox<String> area;
	private JComboBox<String> subArea;
	private ManterGrupo manterGrupo = new ManterGrupo();
	private JLabel mensagem;
	private JButton btnExcluirGrupos;
	private JButton btnSalvaAlteraGrupos;
	
	
	
	public BotaoGrupoExcluirController(JFormattedTextField[] RA, JFormattedTextField cod, JFormattedTextField tema,
			JComboBox<String> area, JComboBox<String> subArea, JLabel mensagem) 
	{
		this.RA = RA;
		this.cod = cod;
		this.tema = tema;
		this.area = area;
		this.subArea = subArea;
		this.mensagem = mensagem;
	}
	
	public void setCommands(JButton btnSalvaAlteraGrupos, JButton btnExcluirGrupos)
	{
		this.btnExcluirGrupos = btnExcluirGrupos;
		this.btnSalvaAlteraGrupos = btnSalvaAlteraGrupos;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Excluir();
	}
	
	public Grupo setGrupo()
	{
		Grupo grupo = new Grupo();
		Aluno[] aluno = new Aluno[4];
		Area area = new Area();
		int tam = aluno.length;
		
		for (int i = 0; i < tam; i++)
		{
			aluno[i] = new Aluno();
			aluno[i].setRa(RA[i].getText());
		}
		
		area.setNome(this.area.getSelectedItem().toString());
		area.setSubArea(this.subArea.getSelectedItem().toString());
		grupo.setCodigo(Integer.parseInt(cod.getText()));
		grupo.setTema(tema.getText());
		grupo.setAlunos(aluno);
		grupo.setArea(area);
		
		return grupo;
	}

	private void Excluir() 
	{
		String arqGrupo = manterGrupo.getArqDiretorio(Constantes.GRUPOS);
		try {
			arqGrupo = manterGrupo.getArq(arqGrupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Grupo grupo = new Grupo();
		
		grupo.setCodigo(Integer.parseInt(cod.getText()));
		
		if (manterGrupo.verificaGrupoExiste(arqGrupo, grupo, this.mensagem))
		{
			this.mensagem.setText("<html>Não existe um grupo <br> com esse código!</html>");
			btnSalvaAlteraGrupos.setText("Salvar");
			btnExcluirGrupos.setVisible(false);
			return;
		}
		
		String cod = this.cod.getText();
		String[] procGrupo = null;
		try {
			procGrupo = manterGrupo.getArq(Constantes.H_GRUPOS).split("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int tamProcGrupo = procGrupo.length;
		String[] aux;
		for (int i = 1; i < tamProcGrupo; i++)
		{
			aux = procGrupo[i].split(";");
			if (Boolean.valueOf(aux[6]) && cod.equals(aux[0]))
			{
				this.mensagem.setText("<html>O grupo que já foi concluído <br>não pode ser excluído!</html>");
				for (int j = 0; j < 4; j++)
				{
					RA[j].setText("");
				}
				this.cod.setText("");
				this.tema.setText("");
				this.area.setSelectedIndex(0);
				this.subArea.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
	        	this.subArea.setEnabled(false);
				btnSalvaAlteraGrupos.setText("Salvar");
				btnExcluirGrupos.setVisible(false);
				return;
			}
		}
		
		manterGrupo.excluirGrupo(grupo);
		
		this.mensagem.setText("Grupo excluido com sucesso!");
		btnSalvaAlteraGrupos.setText("Salvar");
		btnExcluirGrupos.setVisible(false);
		
	}
	
	
}
