package telaController;

import br.fatec.ListObject.ListObject;
import controller.ManterReunião;
import model.Grupo;
import model.Reuniao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BTReuniaoBuscaCodigoController implements ActionListener {

    private JFormattedTextField cod;
    private JFormattedTextField assunto;
    private JFormattedTextField data;
    private JLabel mensagem;
    private Grupo grupo;

    public BTReuniaoBuscaCodigoController(JFormattedTextField cod, JFormattedTextField assunto, JFormattedTextField data, JLabel mensagem) {
        this.cod = cod;
        this.assunto = assunto;
        this.data = data;
        this.mensagem = mensagem;
    }

    public void actionPerformed(ActionEvent e){
        if (!validaCampoCod(cod)){
            mensagem.setForeground(Color.RED);
            return;
        }


        try {
            mensagem.setForeground(Color.black);
            int codigo= Integer.parseInt(cod.getText());
            ListObject grupos= getGrupos();


            File arquivoGrupos= new File(getArquivoGrupos());
            File arquivoReunioes= new File(getArquivoReunioes(), Constantes.REUINOES);

            if (arquivoGrupos.exists()){
                grupo= ManterReunião.pesquisarCodGrupo(grupos, codigo);

                if (grupo == null){
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("<html> Grupo não encontrado no Sistema" +
                            "<br> Por favor, digite novamente." +
                            "</html>");
                }
                else if (arquivoReunioes.exists()){
                    Reuniao reuniao= ManterReunião.validaReuniao(getReunioes(), codigo);

                    if (grupo.getStatus()) {
                        mensagem.setForeground(Color.RED);
                        mensagem.setText("<html> Este grupo já concluiu o trabalho" +
                                "<br> Por favor, digite novamente." +
                                "</html>");
                    } else if (reuniao != null && !reuniao.isStatus()){
                        mensagem.setForeground(Color.black);
                        mensagem.setText("<html> Grupo já possuí uma Reunião marcada" +
                                "<br> Clique em \"Salvar\" para alterar os dados." +
                                "</html>");
                        assunto.setText(reuniao.getAssunto());
                        data.setText(reuniao.getData());
                    }
                    else {

                        mensagem.setForeground(Color.black);
                        mensagem.setText("<html> Grupo validado </html>");
                    }
                }
                else {
                    mensagem.setForeground(Color.black);
                    mensagem.setText("<html> Grupo validado </html>");;
                }

            }
            else {
                mensagem.setForeground(Color.RED);
                mensagem.setText("<html> Nenhum grupo encontrado" +
                        "<br> Por favor, crie um grupo para continuar." +
                        "</html>");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }

    private boolean validaCampoCod(JFormattedTextField campo){
        if (campo.getText().length() == 0) {
            mensagem.setText("Digite o codigo");
        }
        else if (campo.getText().length() < 4){
            mensagem.setText("Codigo inválido");
        }
        else if (campo.getText().length() > 4){
            mensagem.setText("Codigo inválido");
        }
        return (campo.getText().length() == 4);
    }

    private String getArquivoReunioes(){
        return Constantes.HOME;
    }

    public ListObject getReunioes() throws Exception{
        String caminhoArquivo= getArquivoReunioes();
        File dir = new File(caminhoArquivo);
        if (dir.exists() && dir.isDirectory()){
            File file= new File(caminhoArquivo, Constantes.REUINOES);
            FileReader lerFlux = new FileReader(file);
            BufferedReader buffer = new BufferedReader(lerFlux);
            String linha = buffer.readLine();
            StringBuilder content = new StringBuilder();

            if (linha == null)
            {
            	buffer.close();
            	return null;            	
            }
            while(linha != null)
            {
                content.append(linha).append("\n");
                linha = buffer.readLine();
            }

            String[] reuniaoVet= content.toString().split("\n");

            ListObject lista= new ListObject();
            int tamanho= reuniaoVet.length;
            for (int i = 0; i < tamanho; i++) {
                Reuniao reuniao= new Reuniao();
                String[] dados= reuniaoVet[i].split(";");
                reuniao.setCodigoGrupo(Integer.parseInt(dados[0]));
                reuniao.setAssunto(dados[1]);
                reuniao.setData(dados[2]);
                reuniao.setStatus(Boolean.parseBoolean(dados[3]));
                if (lista.isEmpty()){
                    lista.addFirst(reuniao);
                }
                else {
                    lista.addLast(reuniao);
                }
            }
            
            buffer.close();

            return lista;
        }
        else {
            return null;
        }
    }



    private String getArquivoGrupos(){
        return Constantes.H_GRUPOS;
    }

    public ListObject getGrupos() throws Exception {
        String arquivoGrupos= getArquivoGrupos();
        File arq = new File(arquivoGrupos);
        if (arq.exists() && arq.isFile()) {


            FileReader lerFlux = new FileReader(arq);
            BufferedReader buffer = new BufferedReader(lerFlux);
            String linha = buffer.readLine();
            StringBuilder content = new StringBuilder();

            while(linha != null)
            {
                content.append(linha).append("\n");
                linha = buffer.readLine();
            }

            String[] grupo= content.toString().split("\n");

            ListObject lista= new ListObject();
            int tamanho= grupo.length;
            for (int i = 0; i < tamanho; i++) {
                if (lista.isEmpty()){
                    lista.addFirst(grupo[i]);
                }
                else {
                    lista.addLast(grupo[i]);
                }
            }
            buffer.close();
            
            return lista;
        }
        else {
            return null;
        }

    }
}
