package dao;

import CadastrodeAluno.util.Database;
import model.aluno;
import model.curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private final Connection connection = Database.getConnection();

    public FuncionarioDAO() throws SQLException {
    }

    public boolean autenticar(String login, String senha) {
        String sql = "SELECT * FROM funcionario WHERE login = ? AND senha = ?";
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

    public aluno cadastrarAluno(aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, login, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getSenha());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) aluno.setAlunoID(rs.getInt(1));
            return aluno;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean atualizarAluno(int id, aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, login = ?, senha = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getSenha());
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public curso cadastrarCurso(curso curso) {
        String sql = "INSERT INTO curso (nome, categoria) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCategoria());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) curso.setCursoID(rs.getInt(1));
            return curso;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean atualizarCurso(int id, curso curso) {
        String sql = "UPDATE curso SET nome = ?, categoria = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCategoria());
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirCurso(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<aluno> listarAlunos() {
        List<aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
                        rs.getBoolean("ativo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<curso> listarCursos() {
        List<curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cursos.add(new curso(
                        rs.getInt("cursoID"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getString("statusCurso"),
                        rs.getInt("professorID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public List<String> gerarRelatorioAcademico(String tipoRelatorio) {
        List<String> relatorio = new ArrayList<>();
        String sql;

        if (tipoRelatorio.equalsIgnoreCase("notas")) {
            sql = "SELECT a.nome AS aluno, c.nome AS curso, n.valor FROM nota n " +
                    "JOIN aluno a ON n.aluno_id = a.id " +
                    "JOIN curso c ON n.curso_id = c.id";
        } else {
            sql = "SELECT nome FROM aluno";
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                relatorio.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatorio;
    }
}
