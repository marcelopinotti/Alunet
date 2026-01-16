package controller;

import dao.AdministradorDAO;
import model.funcionario;
import java.sql.SQLException;
import java.util.List;

public class AdministradorController {
    private final AdministradorDAO administradorDAO = new AdministradorDAO();

    public AdministradorController() throws SQLException {
    }

    public funcionario cadastrarFuncionario(funcionario funcionario) {
        return administradorDAO.cadastrarFuncionario(funcionario);
    }

    public List<funcionario> listarFuncionarios() {
        return administradorDAO.listarFuncionarios();
    }

    public boolean editarFuncionario(int id, funcionario novoData) {
        return administradorDAO.editarFuncionario(id, novoData);
    }

    public boolean alterarStatusFuncionario(int id, boolean ativo) {
        return administradorDAO.alterarStatus(id,ativo);
    }

    public boolean associarProfessorACurso(int professorID, int cursoID) {
        return administradorDAO.associarProfessorACurso(professorID, cursoID);
    }
}