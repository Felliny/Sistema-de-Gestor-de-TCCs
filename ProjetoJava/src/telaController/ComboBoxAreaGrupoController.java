package telaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import br.fatec.ListString.ListString;

public class ComboBoxAreaGrupoController implements ActionListener
{

	private JComboBox<String> selecao;
	private JComboBox<String> subArea;
	private String[] areaLista;
	private ListString[] listSubArea;
	
	
	public ComboBoxAreaGrupoController(JComboBox<String> selecao, JComboBox<String> subArea, String[] area, ListString[] listSubArea) 
	{
		this.selecao = selecao;
		this.subArea = subArea;
		this.areaLista = area;
		this.listSubArea = listSubArea;
	}

	public int hashCode(String area)
	{
		int posit = Integer.parseInt(area.substring(0, 1));
		return posit;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String area = selecao.getSelectedItem().toString();
		String[] subArea = null;
		
		int tamArea = this.areaLista.length;
		
		for (int i = 0; i < tamArea; i++)
		{
			if (area.equals(areaLista[i]))
			{
				int tamListSubArea = this.listSubArea[i].size();
				subArea = new String[tamListSubArea];
				
				for (int j = 0; j < tamListSubArea; j++)
				{
					try {
						subArea[j] = this.listSubArea[i].get(j);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				break;
			}
		}
		this.subArea.setModel(new DefaultComboBoxModel<String>(subArea));
		this.subArea.setEnabled(true);
		this.subArea.setSelectedIndex(0);
	}
}
