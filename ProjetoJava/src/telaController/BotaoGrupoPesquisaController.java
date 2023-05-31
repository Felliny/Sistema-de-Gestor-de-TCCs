package telaController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;


import br.fatec.ListString.ListString;
import controller.ManterGrupo;
import model.Aluno;
import model.Area;
import model.Grupo;

public class BotaoGrupoPesquisaController implements ActionListener
{
	
	private JFormattedTextField pesq;
	private JLabel mensagemGrupo;
	private JLabel mensagemGrupoCad;
	private int conf;
	private String arquivoGrupo;
	private Aluno aluno;
	private Grupo grupo;
	private ManterGrupo manterGrupo = new ManterGrupo();
	private ListString[] listaSubArea;
	private String[] areas;
	private JFormattedTextField[] RA;
	private JFormattedTextField ftTema;
	private JComboBox<String> cbArea;
	private JComboBox<String> cbSubArea;
	private JButton btnSalvaAlteraGrupos;
	private JButton btnExcluirGrupos;
	private JLabel[] lblMensagemRAGrupo;

	public BotaoGrupoPesquisaController(JFormattedTextField pesq, JLabel mensagemGrupo, int conf)
	{
		this.pesq = pesq;
		this.mensagemGrupo = mensagemGrupo;
		this.conf = conf;
		this.arquivoGrupo = manterGrupo.getArqDiretorio(Constantes.GRUPOS);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		buscar();
	}
	
	public void setList(ListString[] subArea, String[] area)
	{
		this.areas = area;
		this.listaSubArea = subArea;
	}
	
	public void setCommands(JFormattedTextField[] RA, JFormattedTextField ftTema, JComboBox<String> cbArea, JComboBox<String> cbSubArea, JLabel mensagemGrupoCad, JButton btnSalvaAlteraGrupos, JButton btnExcluirGrupos, JLabel[] lblMensagemRAGrupo)
	{
		this.RA = RA;
		this.ftTema = ftTema;
		this.cbArea = cbArea;
		this.cbSubArea = cbSubArea;
		this.mensagemGrupoCad = mensagemGrupoCad;
		this.btnSalvaAlteraGrupos = btnSalvaAlteraGrupos;
		this.btnExcluirGrupos = btnExcluirGrupos;
		this.lblMensagemRAGrupo = lblMensagemRAGrupo;
	}

	public int hashCodeArea(String area)
	{
		return (Integer.parseInt(area.substring(0, 1)) - 1);
	}

	public int hashCodeSubArea(String area)
	{
		return (Integer.parseInt(area.substring(1, 2)));
	}

	private boolean validaCampo(JFormattedTextField campo)
    {
        if (campo.getText().length() != 13 && conf == 0 || campo.getText().length() != 4 && conf == 1)
        {
            mensagemGrupo.setText("x");
        	mensagemGrupo.setForeground(Color.RED);
        	if (conf == 1)
        	{
        		cbSubArea.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
        		for (int i = 0; i < 4; i++)
        		{
        			RA[i].setText("");
        		}
        		ftTema.setText("");
        	}
        }
        return (campo.getText().length() == 13) && conf == 0 || (campo.getText().length() == 4) && conf == 1;
    }
	
	private void buscar() 
	{
		if (!validaCampo(pesq))
            return;
		
		try {
				mensagemGrupo.setText("");
				if (conf == 0)
				{
					String ra = pesq.getText();
					
					aluno = manterGrupo.buscarAluno(ra);
					
					if (aluno == null)
					{
			            mensagemGrupo.setText("x");
			        	mensagemGrupo.setForeground(Color.RED);
					}
				}
				else if (conf == 1)
				{
					String cod = pesq.getText();
					String[] procGrupo = manterGrupo.getArq(arquivoGrupo).split("\n");
					int tamProcGrupo = procGrupo.length;
					boolean test = false;
					String[] aux;
					for (int i = 1; i < tamProcGrupo; i++)
					{
						aux = procGrupo[i].split(";");
						if (Boolean.valueOf(aux[6]) && cod.equals(aux[0]))
						{
							this.mensagemGrupoCad.setText("O grupo já foi concluído!");
							return;
						}
						if (cod.equals(aux[0]))
						{
							test = true;
							break;
						}
					}
					if (!test)
					{
						mensagemGrupo.setText("x");
			        	mensagemGrupo.setForeground(Color.RED);
			        	mensagemGrupoCad.setText("O código não existe.");
			        	btnExcluirGrupos.setVisible(false);
			        	btnSalvaAlteraGrupos.setText("Salvar");
			        	for (int i = 0; i < 4; i++)
						{
							RA[i].setText("");
						}
			        	for (JLabel msg : lblMensagemRAGrupo)
							msg.setText("");
			        	ftTema.setText("");
			        	cbArea.setSelectedIndex(0);
			        	cbSubArea.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
			        	cbSubArea.setEnabled(false);
			        	pesq.setText(cod);
			        	return;
					}

					btnExcluirGrupos.setVisible(true);
					btnSalvaAlteraGrupos.setText("Alterar");
					mensagemGrupoCad.setText("");
					grupo = manterGrupo.buscarGrupo(procGrupo, cod);
					int hash = hashCodeArea(cod);
					Area area = new Area();
					area.setNome(areas[hash]);
					area.setSubArea(listaSubArea[hash].get(hashCodeSubArea(cod)));
					grupo.setArea(area);
					Aluno[] aluno = grupo.getAlunos();
					int tamAluno = aluno.length - 1;
					for (int i = 0; i < tamAluno; i++)
						RA[i].setText(aluno[i].getRa());

					for (int i = 3; i >= tamAluno; i--)
						RA[i].setText("");

					for (JLabel msg : lblMensagemRAGrupo)
						msg.setText("");


					ftTema.setText(grupo.getTema());
					cbArea.setSelectedIndex(hash);
					cbSubArea.setSelectedIndex(hashCodeSubArea(cod));
					pesq.setText(cod);
				}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
