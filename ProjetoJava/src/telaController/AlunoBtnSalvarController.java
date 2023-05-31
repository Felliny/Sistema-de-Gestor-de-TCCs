package telaController;

import controller.ManterAluno;
import model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AlunoBtnSalvarController implements ActionListener
{
    private JFormattedTextField campoRA;
    private JFormattedTextField campoNome;
    private JLabel mensagem;
    private final JButton btnSalvar;
    private final JButton btnExcluir;
    private String arquivoAluno;
    private ManterAluno manterAluno = new ManterAluno();

    public AlunoBtnSalvarController(JFormattedTextField campoRA, JFormattedTextField campoNome,
                                    JLabel mensagem, JButton btnSalvar, JButton btnExcluir)
    {
        this.campoRA = campoRA;
        this.campoNome = campoNome;
        this.mensagem = mensagem;
        this.btnSalvar = btnSalvar;
        this.btnExcluir = btnExcluir;
        this.arquivoAluno = Constantes.H_ALUNO;
    }

    private String[] getAlunos(String arquivoAluno) throws Exception {
        File readFile = new File(arquivoAluno);
        if (!readFile .exists())
            readFile .createNewFile();
        FileReader read = new FileReader(readFile);
        BufferedReader buffer = new BufferedReader(read);

        String line;
        StringBuilder content = new StringBuilder();

        line = buffer.readLine();
        while (line != null)
        {
            content.append(line).append("\n");
            line = buffer.readLine();
        }
        
        buffer.close();
        
        return content.toString().split("\n");
    }

    private boolean validaCampoRA(JFormattedTextField campo)
    {
        if (campo.getText().length() != 13)
            mensagem.setText("RA inválido");
        return (campo.getText().length() == 13);
    }

    private boolean validaCampoNome(JFormattedTextField campo)
    {
        if (campo.getText().length() == 0)
            mensagem.setText("Insira um nome");
        return (campo.getText().length() > 0);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        mensagem.setForeground(Color.black);
        if (!validaCampoRA(campoRA))
            return;
        if (!validaCampoNome(campoNome))
            return;

        try {
            String ra = campoRA.getText();

            String[] alunos = getAlunos(arquivoAluno);
            Aluno aluno = manterAluno.pesquisarAluno(alunos, ra);
            boolean alunoExiste;

            if (aluno != null)
            {
                alunoExiste = true;
                aluno.setNome(campoNome.getText());
            }
            else
            {
                alunoExiste = false;
                aluno = new Aluno();
                aluno.setNome(campoNome.getText());
                aluno.setRa(campoRA.getText());
            }

            manterAluno.salvarDados(aluno, arquivoAluno, alunoExiste, Constantes.HOME, Constantes.ALUNO);
            mensagem.setText(campoNome.getText() + " salvo no sistema!");
            btnSalvar.setEnabled(false);
            btnSalvar.setText("Salvar");
            btnExcluir.setVisible(false);
            btnExcluir.setEnabled(false);
        } catch (Exception e) {
            mensagem.setText("Ocorreu Algum erro.");
            mensagem.setForeground(Color.RED);
            e.printStackTrace();
        }
    }
}
