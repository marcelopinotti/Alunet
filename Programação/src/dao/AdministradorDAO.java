package dao;

import CadastrodeAluno.util.Database;
import model.funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    private final Connection connection = Database.getConnection();

    public AdministradorDAO() throws SQLException {}

    public funcionario cadastrarFuncionario(funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cargo, login, senha, autenticado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2,"Administrador");
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setBoolean(5, funcionario.isAtivo());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    funcionario.setFuncionarioID(generatedKeys.getInt(1));
                    return funcionario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<funcionario> listarFuncionarios() {
        List<funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                funcionario func = new funcionario(
                        rs.getInt("funcionarioID"),
                        rs.getString("nome"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getBoolean("autenticado")
                );
                lista.add(func);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean editarFuncionario(int funcionarioID, funcionario novoData) {
        String sql = "UPDATE funcionario SET nome=?, cargo=?, login=?, senha=?, autenticado=? WHERE funcionarioID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoData.getNome());
            stmt.setString(2, ""); // Defina o cargo corretamente
            stmt.setString(3, novoData.getLogin());
            stmt.setString(4, novoData.getSenha());
            stmt.setBoolean(5, novoData.isAtivo());
            stmt.setInt(6, funcionarioID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterarStatus(int funcionarioID, boolean autenticado) {
        String sql = "UPDATE funcionario SET autenticado=? WHERE funcionarioID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, autenticado);
            stmt.setInt(2, funcionarioID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean associarProfessorACurso(int professorID, int cursoID) {
        String sql = "INSERT INTO professor (professorID, cursoID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, professorID);
            stmt.setInt(2, cursoID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}