package controller;

import model.aluno;
import model.nota;
import dao.NotaDAO;
import dao.ProfessorDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProfessorController {
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final NotaDAO notaDAO = new NotaDAO();

    public ProfessorController() throws SQLException {
    }

    public boolean autenticar(String login, String senha) {
        return professorDAO.autenticar(login, senha);
    }

    public nota registrarNota(int alunoID, int cursoID, double valorNota, Date data) {
        return notaDAO.inserirNota(alunoID, cursoID, valorNota, data);
    }

    public boolean atualizarNota(int notaID, double novoValor) {
        return notaDAO.atualizarNota(notaID, novoValor);
    }

    public List<nota> consultarNotasDosAlunosCurso(int cursoID) {
        return notaDAO.consultarNotasDosAlunosCurso(cursoID);
    }

    public List<aluno> consultarAlunosCurso(int cursoID) {
        return professorDAO.buscarAlunosPorCurso(cursoID);
    }
}
