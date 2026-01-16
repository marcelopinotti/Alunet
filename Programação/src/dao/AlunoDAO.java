
package dao;

import CadastrodeAluno.util.Database;
import model.aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final Connection connection = Database.getConnection();

    public AlunoDAO() throws SQLException {
    }

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM aluno WHERE login = ? AND senha = ? AND autenticado = TRUE";
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

    public boolean atualizar(int alunoID, aluno novosDados) {
        String sql = "UPDATE aluno SET nome = ?, login = ?, senha = ? WHERE alunoID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novosDados.getNome());
            stmt.setString(2, novosDados.getLogin());
            stmt.setString(3, novosDados.getSenha());
            stmt.setInt(4, alunoID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> consultarNotas(int alunoID) {
        List<String> notas = new ArrayList<>();
        String sql = "SELECT c.nome AS curso, n.valor, n.data FROM nota n JOIN curso c ON n.curso_id = c.cursoID WHERE n.aluno_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, alunoID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String notaInfo = "Curso: " + rs.getString("curso") +
                        ", Nota: " + rs.getDouble("valor") +
                        ", Data: " + rs.getDate("data");
                notas.add(notaInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notas;
    }
}