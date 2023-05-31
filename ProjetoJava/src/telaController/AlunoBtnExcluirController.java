package telaController;

import controller.ManterAluno;
import model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AlunoBtnExcluirController implements ActionListener
{
    private JFormattedTextField campoRA;
    private JFormattedTextField campoNome;
    private JLabel mensagem;
    private final JButton btnSalvar;
    private String arquivoAluno;
    private ManterAluno manterAluno = new ManterAluno();
    private JButton btnExcluir;

    public AlunoBtnExcluirController (JFormattedTextField campoRA, JFormattedTextField campoNome,
                                      JLabel mensagem, JButton btnExcluir, JButton btnSalvar)
    {
        this.campoRA = campoRA;
        this.campoNome = campoNome;
        this.mensagem = mensagem;
        this.btnSalvar = btnSalvar;
        this.arquivoAluno = Constantes.H_ALUNO;
        this.btnExcluir = btnExcluir;
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

    private Aluno procuraAluno(String[] alunos, String ra) throws Exception {
        Aluno aluno = manterAluno.pesquisarAluno(alunos, ra);

        if (aluno == null)
            throw new Exception("Aluno não encontrado");
        return aluno;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        try {
            String ra = campoRA.getText();

            String[] alunos = getAlunos(arquivoAluno);
            Aluno aluno = procuraAluno(alunos, ra);

            String caminho = Constantes.HOME;
            String arquivo = Constantes.ALUNO;
            manterAluno.excluirDadosAluno(aluno, arquivoAluno, caminho, arquivo);
            campoRA.setText("");
            campoNome.setText("");
            mensagem.setText("Aluno " + aluno.getNome() + " removido do sistema!");
            btnExcluir.setVisible(false);
            btnExcluir.setEnabled(false);
            btnSalvar.setEnabled(false);
            btnSalvar.setText("Salvar");
        } catch (Exception e) {
            mensagem.setText("Ocorreu Algum erro.");
            e.printStackTrace();
        }
    }
}
