package telaController;

import br.fatec.ListObject.ListObject;
import controller.ManterPassos;
import model.Reuniao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassosBtnSalvarPassos implements ActionListener
{
    private final JLabel lblMensagem;
    private final JFormattedTextField campoCodGrupo, campoAssunto;
    private final JTextArea campoPassos;
    private final JCheckBox checkBoxStatus;
    private final JButton btnSalvarPassos;
    private boolean valorDoCheckBox;
    private String valorDoCampoCodigo, valorDoCampoPassos;
    private ManterPassos manterPassos = new ManterPassos();
    private ListObject reunioes;

    public PassosBtnSalvarPassos(JLabel lblMensagem, JFormattedTextField campoCodGrupo,
                                 JFormattedTextField campoAssunto, JTextArea campoPassos,
                                 JCheckBox checkBoxStatus, JButton btnSalvarPassos)
    {
        this.lblMensagem = lblMensagem;
        this.campoCodGrupo = campoCodGrupo;
        this.campoAssunto = campoAssunto;
        this.campoPassos = campoPassos;
        this.checkBoxStatus = checkBoxStatus;
        this.btnSalvarPassos = btnSalvarPassos;
    }

    private String getArquivoReunioes()
    {
        return Constantes.H_REUINOES;
    }


    private void validaCampos() throws Exception {
        valorDoCampoCodigo = campoCodGrupo.getText();
        valorDoCampoPassos = campoPassos.getText();

        if (valorDoCampoCodigo.length() < 4)
            throw new Exception("Código Inválido!");
        if (valorDoCampoPassos.length() == 0)
            throw new Exception("Nenhum passo inserido!");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try
        {
            this.valorDoCheckBox = checkBoxStatus.isSelected();
            this.valorDoCampoCodigo = campoCodGrupo.getText();
            campoAssunto.getText();
            this.valorDoCampoPassos = campoPassos.getText();
            this.reunioes = manterPassos.getReunioes(getArquivoReunioes());

            validaCampos();
            ListObject reunioesGrupo = getReuinioesGrupo(valorDoCampoCodigo, reunioes);
            manterPassos.salvarDados(valorDoCampoPassos, valorDoCheckBox, reunioesGrupo);
            campoAssunto.setText("");
            campoPassos.setText("");
            campoCodGrupo.setText("");
            checkBoxStatus.setSelected(false);
            btnSalvarPassos.setEnabled(false);
            lblMensagem.setText("Passos definidos");

        } catch (Exception e) {
            lblMensagem.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    private ListObject getReuinioesGrupo(String valorCodigoGrupo, ListObject reunioes) throws Exception {
        ListObject reunioesGrupo = new ListObject();
        int size = reunioes.size();

        for (int index = 0; index < size; index++)
        {
            Reuniao reuniao = (Reuniao) reunioes.get(index);
            int codigo = reuniao.getCodigoGrupo();
            int codigoGrupo = Integer.parseInt(valorCodigoGrupo);

            if (codigoGrupo == codigo)
                reunioesGrupo.addFirst(reuniao);
        }

        if (reunioesGrupo.isEmpty())
            throw new Exception("Grupo não tem renuiões");

        return reunioesGrupo;
    }
}
