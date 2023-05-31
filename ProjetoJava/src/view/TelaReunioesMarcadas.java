package view;

import telaController.BTReunioesMarcadasBuscaController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaReunioesMarcadas extends JFrame {
    public static void setElements (JPanel pReuniaoMarcado){
        JLabel lblCodGrupoReunMarcada = new JLabel("Código do grupo:");
        lblCodGrupoReunMarcada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCodGrupoReunMarcada.setBounds(37, 58, 109, 20);
        pReuniaoMarcado.add(lblCodGrupoReunMarcada);

        JFormattedTextField ftPesquisarReuniao = new JFormattedTextField();
        ftPesquisarReuniao.setBounds(156, 60, 121, 18);
        pReuniaoMarcado.add(ftPesquisarReuniao);

        JButton btnPesquisarReuniao = new JButton("Pesquisar");
        btnPesquisarReuniao.setBounds(286, 55, 100, 30);
        pReuniaoMarcado.add(btnPesquisarReuniao);

        JLabel lblReuniao = new JLabel("Reunião");
        lblReuniao.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblReuniao.setBackground(Color.WHITE);
        lblReuniao.setBounds(30, 11, 74, 20);
        pReuniaoMarcado.add(lblReuniao);


        JScrollPane spReuniaoMarcada = new JScrollPane();
        spReuniaoMarcada.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spReuniaoMarcada.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        spReuniaoMarcada.setBounds(20, 89, 523, 170);
        pReuniaoMarcado.add(spReuniaoMarcada);


        JTable tableReuniaoMarcada = new JTable();
        spReuniaoMarcada.setViewportView(tableReuniaoMarcada);
        tableReuniaoMarcada.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null}
                },
                new String[] {
                        "C\u00F3d. Grupo", "Tema", "Data", "Status"
                }
        ));
        tableReuniaoMarcada.setEnabled(false);
        tableReuniaoMarcada.getColumnModel().getColumn(3).setPreferredWidth(64);


        JLabel lblMensagemReuniaoMarcada = new JLabel("");
        lblMensagemReuniaoMarcada.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagemReuniaoMarcada.setBounds(411, 0, 132, 75);
        pReuniaoMarcado.add(lblMensagemReuniaoMarcada);

        BTReunioesMarcadasBuscaController BTReunioesMarcadasBusca= new BTReunioesMarcadasBuscaController(ftPesquisarReuniao, lblMensagemReuniaoMarcada, tableReuniaoMarcada);

        btnPesquisarReuniao.addActionListener(BTReunioesMarcadasBusca);
    }
}
