package dao;

import CadastrodeAluno.util.Database;
import model.aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    private final Connection connection = Database.getConnection();

    public ProfessorDAO() throws SQLException {
    }

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM professor WHERE login = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<aluno> buscarAlunosPorCurso(int cursoID) {
        List<aluno> alunos = new ArrayList<>();
        String sql = "SELECT DISTINCT a.* FROM aluno a " +
                "JOIN nota n ON a.alunoID = n.alunoID " +
                "WHERE n.cursoID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cursoID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alunos.add(new aluno(
                        rs.getInt("alunoID"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getBoolean("autenticado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}
