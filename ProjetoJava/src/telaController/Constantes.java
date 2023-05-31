package telaController;

import java.io.File;

public class Constantes
{
    public static final String HOME = System.getProperty("user.home") + File.separator +
            "TEMP" + File.separator;
    public static final String ALUNO = "Alunos.csv";
    public static final String REUINOES = "Reunioes.csv";
    public static final String AREAS = "ProfessorAreasdeAtuacao.csv";
    public static final String GRUPOS = "Grupos.csv";
    public static final String H_ALUNO = HOME + ALUNO;
    public static final String H_REUINOES = HOME + REUINOES;
    public static final String H_AREAS = HOME + AREAS;
    public static final String H_GRUPOS = HOME + GRUPOS;
}
