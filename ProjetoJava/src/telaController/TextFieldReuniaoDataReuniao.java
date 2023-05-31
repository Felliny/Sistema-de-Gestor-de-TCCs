package telaController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;

public class TextFieldReuniaoDataReuniao implements KeyListener
{
	private JFormattedTextField ftDataReuniao;

	public TextFieldReuniaoDataReuniao(JFormattedTextField ftDataReuniao) 
	{
		this.ftDataReuniao = ftDataReuniao;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		char i = e.getKeyChar();
		if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
		{
			ftDataReuniao.setEditable(true);
		}

		else
		{
			ftDataReuniao.setEditable(false);
			return;
		}
		
		String text = ftDataReuniao.getText();
        int keyCode = e.getKeyCode();

        // Verifica se o texto da data já possui uma "/" no terceiro e sexto caractere, para não adicionar duplicado
        if ((text.length() == 2 || text.length() == 5) && !text.endsWith("/")) {
        	ftDataReuniao.setText(text + "/");
        }

        // Verifica se o usuário pressionou a tecla "Backspace" para remover a formatação
        if (keyCode == KeyEvent.VK_BACK_SPACE && (text.length() == 3 || text.length() == 6)) {
        	ftDataReuniao.setText(text.substring(0, text.length() - 1));
        }

		if (ftDataReuniao.getText().length() >= 10)
		{
			ftDataReuniao.setEditable(false);
		}

		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			ftDataReuniao.setEditable(true);
		}
    }

	@Override
	public void keyReleased(KeyEvent e)
	{

	}
}
