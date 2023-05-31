package view;

import telaController.AlunoBtnBuscaController;
import telaController.AlunoBtnExcluirController;
import telaController.AlunoBtnSalvarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCadastroAluno extends JFrame {
    public static void setElements (JPanel pAluno)
    {
        JLabel lblAlunoDados = new JLabel("Dados Aluno");
        lblAlunoDados.setBounds(30, 11, 100, 31);
        lblAlunoDados.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAlunoDados.setBackground(UIManager.getColor("Button.disabledShadow"));
        pAluno.add(lblAlunoDados);

        JLabel lblAlunoRA = new JLabel("RA:");
        lblAlunoRA.setBounds(105, 118, 68, 14);
        lblAlunoRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pAluno.add(lblAlunoRA);

        JLabel lblAlunoNome = new JLabel("Nome:");
        lblAlunoNome.setBounds(105, 143, 68, 14);
        lblAlunoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pAluno.add(lblAlunoNome);
        JButton btnSalvaAluno = new JButton("Salvar");
        btnSalvaAluno.setBounds(459, 257, 100, 30);
        btnSalvaAluno.setEnabled(false);
        pAluno.add(btnSalvaAluno);

        JFormattedTextField tfRA = new JFormattedTextField();
        tfRA.setBounds(158, 117, 144, 20);
        tfRA.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e)
            {
                char i = e.getKeyChar();
                if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
                {
                    tfRA.setEditable(true);
                }
                else
                {
                    tfRA.setEditable(false);
                }
            }
        });
        pAluno.add(tfRA);

        JFormattedTextField tfNome = new JFormattedTextField();
        tfNome.setBounds(158, 143, 144, 20);
        tfNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c))
                {
                    tfNome.setEditable(true);
                }
                else
                {
                    tfNome.setEditable(false);
                }
            }
        });
        pAluno.add(tfNome);

        JButton btnBuscarAluno = new JButton("Buscar");
        btnBuscarAluno.setBounds(321, 116, 76, 23);
        pAluno.add(btnBuscarAluno);

        JButton btnExcluirAluno = new JButton("Excluir");
        btnExcluirAluno.setBounds(349, 257, 100, 30);
        pAluno.add(btnExcluirAluno);
        btnExcluirAluno.setEnabled(false);
        btnExcluirAluno.setVisible(false);

        JLabel lblMensagemAluno = new JLabel("");
        lblMensagemAluno.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagemAluno.setBounds(300, 21, 280, 70);
        pAluno.add(lblMensagemAluno);

        AlunoBtnBuscaController buscaController = new AlunoBtnBuscaController(tfRA, tfNome,
                lblMensagemAluno, btnExcluirAluno, btnSalvaAluno);
        AlunoBtnSalvarController salvarController = new AlunoBtnSalvarController(tfRA, tfNome,
                lblMensagemAluno, btnSalvaAluno, btnExcluirAluno);
        AlunoBtnExcluirController excluirController = new AlunoBtnExcluirController(tfRA, tfNome,
                lblMensagemAluno, btnExcluirAluno, btnSalvaAluno);
        btnBuscarAluno.addActionListener(buscaController);
        btnSalvaAluno.addActionListener(salvarController);
        btnExcluirAluno.addActionListener(excluirController);
    }
}
