package dao;

import CadastrodeAluno.util.Database;
import model.curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private final Connection connection = Database.getConnection();

    public CursoDAO() throws SQLException {}

    public curso buscarPorCursoID(int cursoID) {
        String sql = "SELECT * FROM curso WHERE cursoID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cursoID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new curso(
                        rs.getInt("cursoID"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getString("statusCurso"),
                        rs.getInt("professorID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<curso> buscarTodos() {
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

    public curso cadastrarCurso(curso curso) {
        String sql = "INSERT INTO curso (nome, categoria, statusCurso, professorID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCategoria());
            stmt.setString(3, curso.getStatusCurso());
            stmt.setInt(4, curso.getProfessorID());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    curso.setCursoID(rs.getInt(1));
                    return curso;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean atualizarCurso(int cursoID, curso curso) {
        String sql = "UPDATE curso SET nome = ?, categoria = ?, statusCurso = ?, professorID = ? WHERE cursoID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCategoria());
            stmt.setString(3, curso.getStatusCurso());
            stmt.setInt(4, curso.getProfessorID());
            stmt.setInt(5, cursoID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirCurso(int cursoID) {
        String sql = "DELETE FROM curso WHERE cursoID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cursoID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}