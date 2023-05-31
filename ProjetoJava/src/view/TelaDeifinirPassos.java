package view;

import telaController.PassosBtnBuscaGrupo;
import telaController.PassosBtnSalvarPassos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaDeifinirPassos extends JFrame {
    public static void setElements(JPanel pAddPassos)
    {
        JLabel lblDefinirPassos = new JLabel("Definir Passos");
        lblDefinirPassos.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDefinirPassos.setBackground(Color.WHITE);
        lblDefinirPassos.setBounds(30, 11, 108, 20);
        pAddPassos.add(lblDefinirPassos);

        JLabel lblNewLabel_1_2_5_3 = new JLabel("Código do grupo:");
        lblNewLabel_1_2_5_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_5_3.setBounds(55, 50, 127, 20);
        pAddPassos.add(lblNewLabel_1_2_5_3);

        JLabel lblNewLabel_1_2_5_3_1 = new JLabel("Assunto da Reunião:");
        lblNewLabel_1_2_5_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_5_3_1.setBounds(55, 81, 127, 20);
        pAddPassos.add(lblNewLabel_1_2_5_3_1);

        JLabel lblNewLabel_1_2_5_3_2 = new JLabel("Passos:");
        lblNewLabel_1_2_5_3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_2_5_3_2.setBounds(55, 112, 61, 20);
        pAddPassos.add(lblNewLabel_1_2_5_3_2);

        JFormattedTextField ftCodGrupoPassos = new JFormattedTextField();
        ftCodGrupoPassos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                char i = e.getKeyChar();
                if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
                {
                    ftCodGrupoPassos.setEditable(true);
                }
                else
                {
                    ftCodGrupoPassos.setEditable(false);
                }
            }
        });
        ftCodGrupoPassos.setBounds(192, 52, 108, 18);
        pAddPassos.add(ftCodGrupoPassos);

        JFormattedTextField ftAssuntoReuniaoPassos = new JFormattedTextField();
        ftAssuntoReuniaoPassos.setBounds(192, 83, 108, 18);
        ftAssuntoReuniaoPassos.setEnabled(false);
        ftAssuntoReuniaoPassos.setDisabledTextColor(Color.black);
        ftAssuntoReuniaoPassos.setSelectedTextColor(Color.black);
        pAddPassos.add(ftAssuntoReuniaoPassos);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(126, 112, 174, 72);
        pAddPassos.add(scrollPane);

        JTextArea taPassos = new JTextArea();
        taPassos.setLineWrap(true);
        scrollPane.setViewportView(taPassos);

        JButton btnSalvarPassos = new JButton("Salvar");
        btnSalvarPassos.setBounds(330, 160, 100, 30);
        btnSalvarPassos.setEnabled(false);
        pAddPassos.add(btnSalvarPassos);

        JButton btnBuscarCodPassos = new JButton("Buscar");
        btnBuscarCodPassos.setBounds(310, 51, 79, 23);
        pAddPassos.add(btnBuscarCodPassos);

        JCheckBox checkBoxStatus_Passos = new JCheckBox("Trabalho concluido");
        checkBoxStatus_Passos.setBounds(19, 232, 243, 23);
        pAddPassos.add(checkBoxStatus_Passos);

        JLabel lblMensagemDefinirPassos = new JLabel("");
        lblMensagemDefinirPassos.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagemDefinirPassos.setBounds(411, 0, 132, 75);
        pAddPassos.add(lblMensagemDefinirPassos);

        PassosBtnBuscaGrupo btnBuscaGrupo =
                new PassosBtnBuscaGrupo(lblMensagemDefinirPassos, ftCodGrupoPassos,
                        ftAssuntoReuniaoPassos, taPassos, btnSalvarPassos);
        PassosBtnSalvarPassos btnSalvarPassosCtrl =
                new PassosBtnSalvarPassos(lblMensagemDefinirPassos, ftCodGrupoPassos,
                        ftAssuntoReuniaoPassos, taPassos, checkBoxStatus_Passos,
                        btnSalvarPassos);
        btnBuscarCodPassos.addActionListener(btnBuscaGrupo);
        btnSalvarPassos.addActionListener(btnSalvarPassosCtrl);
    }
}
