package telaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import controller.ManterGrupo;

public class ComboBoxSubAreaGrupoController implements ActionListener
{
	private JComboBox<String> area;
	private JComboBox<String> subArea;
	private JFormattedTextField cod;
	private ManterGrupo manterGrupo = new ManterGrupo();
	
	public ComboBoxSubAreaGrupoController(JComboBox<String> area, JComboBox<String> subArea, JFormattedTextField cod) 
	{
		this.area = area;
		this.subArea = subArea;
		this.cod = cod;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String selectArea = area.getSelectedItem().toString();
		String selectSubArea = subArea.getSelectedItem().toString();
		
		String codSelecao = selectArea.substring(0, 1) + selectSubArea.substring(0, 1);
		
		String[] content = {};
		try {
			content = manterGrupo.getArq(manterGrupo.getArqDiretorio(Constantes.GRUPOS)).split("\n");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String numDisponivel;
		int cont = 0;
		int tam = content.length;
		boolean test = false;
		
		for (int i = 1; i < tam; i++)
		{
			for (int j = 1; j < tam; j++)
			{
				numDisponivel = content[j].substring(2, 4);
				if (cont == Integer.parseInt(numDisponivel))
				{
					test = false;
					break;
				}
			}
			if(test)
			{
				break;
			}
			cont++;
			test = true;
		}
		numDisponivel = Integer.toString(cont);
		
		if (numDisponivel.length() == 1)
			numDisponivel = "0" + numDisponivel;
		
		codSelecao += numDisponivel;
		cod.setText(codSelecao);
	}
}
