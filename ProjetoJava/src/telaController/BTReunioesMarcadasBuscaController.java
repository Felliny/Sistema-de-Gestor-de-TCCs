package telaController;

import br.fatec.ListObject.ListObject;
import br.fatec.StackObject.StackObject;
import controller.ManterReunião;
import model.Grupo;
import model.Reuniao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class BTReunioesMarcadasBuscaController implements ActionListener {


    private JFormattedTextField cod;
    private JLabel mensagem;
    private JTable tabela;


    public BTReunioesMarcadasBuscaController(JFormattedTextField cod, JLabel mensagem, JTable tabela) {
        this.cod = cod;
        this.mensagem = mensagem;
        this.tabela = tabela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!validaCampoCod(cod)){
            mensagem.setForeground(Color.RED);
            return;
        }


        try {
            int codigo= Integer.parseInt(cod.getText());

            File arquivoGrupos= new File(getArquivoGrupos());
            File arquivoReunioes= new File(getArquivoReunioes(), Constantes.REUINOES);

            if (arquivoGrupos.exists()){
                Grupo grupo= ManterReunião.pesquisarCodGrupo(getGrupos(), codigo);

                if (arquivoReunioes.exists()){

                    if (grupo == null) {
                        mensagem.setForeground(Color.RED);
                        mensagem.setText("<html> Grupo não encontrado no Sistema" +
                                "<br> Por favor, digite novamente." +
                                "</html>");
                    }
                    else {
                        mensagem.setForeground(Color.black);
                        StackObject pilha= getReunioes(codigo);
                        if (pilha.size() != 0 && !grupo.getStatus()){
                            mensagem.setText("<html> Ultima reunião Marcada/Concluída: ");
                            Reuniao reuniao= (Reuniao) pilha.pop();

                            String[] vetor= new String[4];
                            vetor[0]= String.valueOf(reuniao.getCodigoGrupo());
                            vetor[1]= reuniao.getAssunto();
                            vetor[2]= reuniao.getData();
                            if (!reuniao.isStatus()){
                                vetor[3]= "Não concluído";
                            }
                            else {
                                vetor[3]= "Concluída";
                            }
                            tabela.setModel(new DefaultTableModel(new Object[][] {{vetor[0], vetor[1], vetor[2], vetor[3]},}, new String[] {"Grupo","Tema","Data","Status"} ));

                        }
                        else if (pilha.size() != 0) {
                            mensagem.setText("<html> Este grupo concluiu seu trabalho" +
                                    "<br> Todas as reuniões realizadas: " +
                                    "</html>");
                            int tamanho= pilha.size();
                            Object dado= 0;
                            Object[][] reunioes= new Object[tamanho][4];
                            for (int x=0; x<tamanho; x++){
                                Reuniao reuniao= (Reuniao) pilha.pop();
                                for (int y=0; y<4; y++){
                                    if (y == 0){
                                        dado= reuniao.getCodigoGrupo();
                                    }
                                    if (y == 1) {
                                        dado= reuniao.getAssunto();
                                    }
                                    if (y == 2){
                                        dado= reuniao.getData();
                                    }
                                    if (y == 3){
                                        if (!reuniao.isStatus()){
                                            dado= "Não concluído";
                                        }
                                        else {
                                            dado= "Concluída";
                                        }
                                    }
                                    reunioes[x][y]= dado;
                                }
                            }
                            String[] colunas= {"Grupo","Tema","Data","Status"};
                            tabela.setModel(new DefaultTableModel(reunioes, colunas));
                        } else {
                            mensagem.setForeground(Color.RED);
                            mensagem.setText("<html> Este grupo não possuí nenhuma reunião marcada" +
                                    "<br> Por favor, digite novamente." +
                                    "</html>");
                        }
                    }
                }
                else {
                    mensagem.setForeground(Color.RED);
                    mensagem.setText("<html> Nenhuma reunião encontrada" +
                            "<br> Por favor, crie uma reunião" +
                            "</html>");
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

    public StackObject getReunioes(int codigo) throws Exception{
        String caminhoArquivo= getArquivoReunioes();
        File dir = new File(caminhoArquivo);
        if (dir.exists() && dir.isDirectory()){
            File file= new File(caminhoArquivo, Constantes.REUINOES);
            FileReader lerFlux = new FileReader(file);
            BufferedReader buffer = new BufferedReader(lerFlux);
            String linha = buffer.readLine();
            StringBuilder content = new StringBuilder();

            while(linha != null)
            {
                content.append(linha).append("\n");
                linha = buffer.readLine();
            }

            String[] reuniaoVet= content.toString().split("\n");

            StackObject pilha= new StackObject();
            int tamanho= reuniaoVet.length;
            for (int i = 0; i < tamanho; i++) {
                Reuniao reuniao= new Reuniao();
                String[] dados= reuniaoVet[i].split(";");
                reuniao.setCodigoGrupo(Integer.parseInt(dados[0]));
                reuniao.setAssunto(dados[1]);
                reuniao.setData(dados[2]);
                reuniao.setStatus(Boolean.parseBoolean(dados[3]));
                if (reuniao.getCodigoGrupo() == codigo){
                    pilha.push(reuniao);
                }
            }
            
            buffer.close();
            
            return pilha;
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