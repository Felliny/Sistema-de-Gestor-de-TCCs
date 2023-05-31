package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.fatec.ListString.ListString;
import telaController.ComboBoxAreaGrupoController;
import telaController.ComboBoxController;
import telaController.ComboBoxSubAreaConsultaController;

public class TelaConsultaGrupo extends JFrame
{
	public static void setElements (JPanel pConsultarGrupos)
	{
		JTable tableGrupoCad;
		ListString[] listaSubArea = null;
		ComboBoxController cbControll = new ComboBoxController(listaSubArea);
		String[] area = {};
		try {
			area = cbControll.area();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		listaSubArea = cbControll.pegaList();
		
		JLabel lblGrupos = new JLabel("Grupos");
		lblGrupos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGrupos.setBackground(Color.WHITE);
		lblGrupos.setBounds(30, 11, 100, 20);
		pConsultarGrupos.add(lblGrupos);
		
		JLabel lblNewLabel_1_2_4_2_1 = new JLabel("Área:");
		lblNewLabel_1_2_4_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_4_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4_2_1.setBounds(30, 50, 51, 16);
		pConsultarGrupos.add(lblNewLabel_1_2_4_2_1);
		
		JComboBox<String> cbAreaConsulta = new JComboBox<String>();
		cbAreaConsulta.setModel(new DefaultComboBoxModel<String>(area));
		cbAreaConsulta.setBounds(75, 49, 189, 20);
		pConsultarGrupos.add(cbAreaConsulta);
		
		JLabel lblNewLabel_1_2_4_2_1_1 = new JLabel("SubÁrea");
		lblNewLabel_1_2_4_2_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_4_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4_2_1_1.setBounds(274, 50, 66, 16);
		pConsultarGrupos.add(lblNewLabel_1_2_4_2_1_1);
		
		JComboBox<String> cbSubAreaConsulta = new JComboBox<String>();
		cbSubAreaConsulta.setEnabled(false);
		cbSubAreaConsulta.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		cbSubAreaConsulta.setBounds(334, 49, 205, 20);
		pConsultarGrupos.add(cbSubAreaConsulta);
		
		JScrollPane spGrupoCad = new JScrollPane();
		spGrupoCad.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spGrupoCad.setBounds(30, 98, 509, 142);
		pConsultarGrupos.add(spGrupoCad);
		
		tableGrupoCad = new JTable();
		spGrupoCad.setViewportView(tableGrupoCad);
		tableGrupoCad.setEnabled(true);
		tableGrupoCad.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Tema", "\u00DAltima Reuniao"
			}
		));
		tableGrupoCad.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableGrupoCad.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableGrupoCad.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableGrupoCad.setCellSelectionEnabled(true);
		tableGrupoCad.setColumnSelectionAllowed(true);
		
		JLabel lblMensagemGrupoCad = new JLabel("");
		lblMensagemGrupoCad.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagemGrupoCad.setBounds(30, 232, 182, 38);
		pConsultarGrupos.add(lblMensagemGrupoCad);
		
		ComboBoxAreaGrupoController cbAreaConsultCont = new ComboBoxAreaGrupoController(cbAreaConsulta, cbSubAreaConsulta, area, listaSubArea);
		ComboBoxSubAreaConsultaController cbSubAreaConsultCont = new ComboBoxSubAreaConsultaController(cbAreaConsulta, cbSubAreaConsulta, tableGrupoCad, lblMensagemGrupoCad);
		
		cbAreaConsulta.addActionListener(cbAreaConsultCont);
		cbSubAreaConsulta.addActionListener(cbSubAreaConsultCont);
	}
}
