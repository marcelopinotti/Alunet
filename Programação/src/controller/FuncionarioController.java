package controller;

import model.aluno;
import model.curso;
import dao.FuncionarioDAO;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public FuncionarioController() throws SQLException {
    }

    public boolean autenticar(String login, String senha) {
        return funcionarioDAO.autenticar(login, senha);
    }

    public aluno cadastrarAluno(aluno aluno) {
        return funcionarioDAO.cadastrarAluno(aluno);
    }

    public boolean atualizarAluno(int alunoID, aluno alunoData) {
        return funcionarioDAO.atualizarAluno(alunoID, alunoData);
    }

    public boolean excluirAluno(int alunoID) {
        return funcionarioDAO.excluirAluno(alunoID);
    }

    public curso cadastrarCurso(curso curso) {
        return funcionarioDAO.cadastrarCurso(curso);
    }

    public boolean atualizarCurso(int cursoID, curso curso) {
        return funcionarioDAO.atualizarCurso(cursoID, curso);
    }

    public boolean excluirCurso(int cursoID) {
        return funcionarioDAO.excluirCurso(cursoID);
    }

    public List<aluno> listarAlunos() {
        return funcionarioDAO.listarAlunos();
    }

    public List<curso> listarCursos() {
        return funcionarioDAO.listarCursos();
    }

    public List<String> gerarRelatorioAcademico(String tipoRelatorio) {
        return funcionarioDAO.gerarRelatorioAcademico(tipoRelatorio);
    }
}
