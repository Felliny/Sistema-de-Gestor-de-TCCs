package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.fatec.ListString.ListString;
import telaController.BotaoGrupoExcluirController;
import telaController.BotaoGrupoPesquisaController;
import telaController.BotaoGrupoSalvarController;
import telaController.ComboBoxAreaGrupoController;
import telaController.ComboBoxController;
import telaController.ComboBoxSubAreaGrupoController;

public class TelaCadastroGrupo extends JFrame
{
	public static void setElements (JPanel pCadGrupo)
	{
		ListString[] listaSubArea = null;
		
		JLabel lblNewLabel_2 = new JLabel("Dados Grupo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(30, 11, 100, 20);
		pCadGrupo.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("RA:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(30, 57, 43, 14);
		pCadGrupo.add(lblNewLabel_1_2);
		
		JFormattedTextField ftRA_1 = new JFormattedTextField();
		ftRA_1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) 
			{
				char i = e.getKeyChar();
				if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
				{
					ftRA_1.setEditable(true);
				}
				else
				{
					ftRA_1.setEditable(false);
				}
			}
		});
		ftRA_1.setBounds(64, 57, 100, 20);
		pCadGrupo.add(ftRA_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("RA:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(30, 83, 43, 14);
		pCadGrupo.add(lblNewLabel_1_2_1);
		
		JFormattedTextField ftRA_2 = new JFormattedTextField();
		ftRA_2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) 
			{
				char i = e.getKeyChar();
				if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
				{
					ftRA_2.setEditable(true);
				}
				else
				{
					ftRA_2.setEditable(false);
				}
			}
		});
		ftRA_2.setBounds(64, 83, 100, 20);
		pCadGrupo.add(ftRA_2);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("RA:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2.setBounds(30, 109, 43, 14);
		pCadGrupo.add(lblNewLabel_1_2_2);
		
		JFormattedTextField ftRA_3 = new JFormattedTextField();
		ftRA_3.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) 
			{
				char i = e.getKeyChar();
				if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
				{
					ftRA_3.setEditable(true);
				}
				else
				{
					ftRA_3.setEditable(false);
				}
			}
		});
		ftRA_3.setBounds(64, 109, 100, 20);
		pCadGrupo.add(ftRA_3);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("RA:");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_3.setBounds(30, 135, 43, 14);
		pCadGrupo.add(lblNewLabel_1_2_3);
		
		JFormattedTextField ftRA_4 = new JFormattedTextField();
		ftRA_4.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) 
			{
				char i = e.getKeyChar();
				if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
				{
					ftRA_4.setEditable(true);
				}
				else
				{
					ftRA_4.setEditable(false);
				}
			}
		});
		ftRA_4.setBounds(64, 135, 100, 20);
		pCadGrupo.add(ftRA_4);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("Código:");
		lblNewLabel_1_2_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4.setBounds(280, 60, 51, 16);
		pCadGrupo.add(lblNewLabel_1_2_4);
		
		JLabel lblNewLabel_1_2_4_1 = new JLabel("Tema:");
		lblNewLabel_1_2_4_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4_1.setBounds(280, 85, 51, 16);
		pCadGrupo.add(lblNewLabel_1_2_4_1);
		
		JLabel lblNewLabel_1_2_4_2 = new JLabel("Área");
		lblNewLabel_1_2_4_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_4_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4_2.setBounds(280, 112, 51, 16);
		pCadGrupo.add(lblNewLabel_1_2_4_2);
		
		JLabel lblNewLabel_1_2_4_3 = new JLabel("SubÁrea");
		lblNewLabel_1_2_4_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_4_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4_3.setBounds(280, 137, 51, 16);
		pCadGrupo.add(lblNewLabel_1_2_4_3);
		
		JFormattedTextField ftCodGrupo = new JFormattedTextField();
		ftCodGrupo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				char i = e.getKeyChar();
				if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
				{
					ftCodGrupo.setEditable(true);
				}
				else
				{
					ftCodGrupo.setEditable(false);
				}
			}
		});
		ftCodGrupo.setBounds(341, 57, 100, 20);
		pCadGrupo.add(ftCodGrupo);
		
		JFormattedTextField ftTema = new JFormattedTextField();
		ftTema.setBounds(341, 82, 213, 20);
		pCadGrupo.add(ftTema);
		
		ComboBoxController cbControll = new ComboBoxController(listaSubArea);
		String[] area = {};
		try {
			area = cbControll.area();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		listaSubArea = cbControll.pegaList();

		
		JComboBox<String> cbArea = new JComboBox<String>();
		cbArea.setModel(new DefaultComboBoxModel<String>(area));
		cbArea.setSelectedIndex(0);
		cbArea.setBounds(341, 108, 213, 20);
		pCadGrupo.add(cbArea);
		
		JComboBox<String> cbSubArea = new JComboBox<String>();
		cbSubArea.setEnabled(false);
		cbSubArea.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		cbSubArea.setBounds(341, 133, 213, 20);
		pCadGrupo.add(cbSubArea);
		
		JButton btnSalvaAlteraGrupos = new JButton("Salvar");
		btnSalvaAlteraGrupos.setBounds(442, 217, 100, 30);
		pCadGrupo.add(btnSalvaAlteraGrupos);
		
		JButton btnBuscarRA1 = new JButton("Buscar");
		btnBuscarRA1.setBounds(191, 57, 79, 23);
		pCadGrupo.add(btnBuscarRA1);
		
		JButton btnBuscarRA2 = new JButton("Buscar");
		btnBuscarRA2.setBounds(191, 83, 79, 23);
		pCadGrupo.add(btnBuscarRA2);
		
		JButton btnBuscarRA3 = new JButton("Buscar");
		btnBuscarRA3.setBounds(191, 109, 79, 23);
		pCadGrupo.add(btnBuscarRA3);
		
		JButton btnBuscarRA4 = new JButton("Buscar");
		btnBuscarRA4.setBounds(191, 135, 79, 23);
		pCadGrupo.add(btnBuscarRA4);
		
		JButton btnBuscarCodGrupo = new JButton("Buscar");
		btnBuscarCodGrupo.setBounds(463, 57, 79, 23);
		pCadGrupo.add(btnBuscarCodGrupo);
		
		JLabel lblMensagemRA1Grupo = new JLabel("");
		lblMensagemRA1Grupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagemRA1Grupo.setBounds(166, 57, 23, 20);
		pCadGrupo.add(lblMensagemRA1Grupo);
		
		JLabel lblMensagemRA2Grupo = new JLabel("");
		lblMensagemRA2Grupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagemRA2Grupo.setBounds(166, 83, 23, 20);
		pCadGrupo.add(lblMensagemRA2Grupo);
		
		JLabel lblMensagemRA3Grupo = new JLabel("");
		lblMensagemRA3Grupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagemRA3Grupo.setBounds(166, 109, 23, 20);
		pCadGrupo.add(lblMensagemRA3Grupo);
		
		JLabel lblMensagemRA4Grupo = new JLabel("");
		lblMensagemRA4Grupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagemRA4Grupo.setBounds(166, 135, 23, 20);
		pCadGrupo.add(lblMensagemRA4Grupo);
		
		JLabel lblMessageCodGrupo = new JLabel("");
		lblMessageCodGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessageCodGrupo.setForeground(Color.RED);
		lblMessageCodGrupo.setBounds(442, 57, 23, 20);
		pCadGrupo.add(lblMessageCodGrupo);
		
		JLabel lblMensagemGrupo = new JLabel("");
		lblMensagemGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagemGrupo.setBounds(30, 197, 192, 48);
		pCadGrupo.add(lblMensagemGrupo);
		
		JButton btnExcluirGrupos = new JButton("Excluir");
		btnExcluirGrupos.setBounds(329, 217, 100, 30);
		btnExcluirGrupos.setVisible(false);
		pCadGrupo.add(btnExcluirGrupos);
		
		JLabel[] lblMensagemRAGrupo = {lblMensagemRA1Grupo, lblMensagemRA2Grupo, lblMensagemRA3Grupo, lblMensagemRA4Grupo};
		JFormattedTextField[] RA = {ftRA_1, ftRA_2, ftRA_3, ftRA_4};
		BotaoGrupoPesquisaController bRaCont1 = new BotaoGrupoPesquisaController(ftRA_1, lblMensagemRA1Grupo, 0); //Pesquisar RA no grupo
		BotaoGrupoPesquisaController bRaCont2 = new BotaoGrupoPesquisaController(ftRA_2, lblMensagemRA2Grupo, 0);
		BotaoGrupoPesquisaController bRaCont3 = new BotaoGrupoPesquisaController(ftRA_3, lblMensagemRA3Grupo, 0);
		BotaoGrupoPesquisaController bRaCont4 = new BotaoGrupoPesquisaController(ftRA_4, lblMensagemRA4Grupo, 0);
		
		BotaoGrupoPesquisaController bCodCont = new BotaoGrupoPesquisaController(ftCodGrupo, lblMessageCodGrupo, 1); //Pesquisar se o grupo existe

		BotaoGrupoSalvarController bSGrupoCont = new BotaoGrupoSalvarController(RA, ftCodGrupo, ftTema, cbArea, cbSubArea, lblMensagemGrupo); //Salvar grupo e alterar grupo
		BotaoGrupoExcluirController bExGrupoCont = new BotaoGrupoExcluirController(RA, ftCodGrupo, ftTema, cbArea, cbSubArea, lblMensagemGrupo); //Excluir grupo

		ComboBoxAreaGrupoController cbAreaCont = new ComboBoxAreaGrupoController(cbArea, cbSubArea, area, listaSubArea); //ComboBox Area e SubArea do grupo
		ComboBoxSubAreaGrupoController cbSubAreaCont = new ComboBoxSubAreaGrupoController(cbArea, cbSubArea, ftCodGrupo);
		
		bCodCont.setList(listaSubArea, area);
		bCodCont.setCommands(RA, ftTema, cbArea, cbSubArea, lblMensagemGrupo, btnSalvaAlteraGrupos, btnExcluirGrupos, lblMensagemRAGrupo);
		bSGrupoCont.setCommands(btnSalvaAlteraGrupos, btnExcluirGrupos);
		bExGrupoCont.setCommands(btnSalvaAlteraGrupos, btnExcluirGrupos);
		
		btnBuscarRA1.addActionListener(bRaCont1); //Grupo RA
		btnBuscarRA2.addActionListener(bRaCont2);
		btnBuscarRA3.addActionListener(bRaCont3);
		btnBuscarRA4.addActionListener(bRaCont4);

		btnBuscarCodGrupo.addActionListener(bCodCont); //Grupo pesquisar

		btnSalvaAlteraGrupos.addActionListener(bSGrupoCont); //Grupo salvar/alterar
		btnExcluirGrupos.addActionListener(bExGrupoCont); //grupo excluir

		cbArea.addActionListener(cbAreaCont); //Grupo ComboBox
		cbSubArea.addActionListener(cbSubAreaCont);
	}
}
