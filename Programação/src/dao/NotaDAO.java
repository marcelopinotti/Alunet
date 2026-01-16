package dao;

import CadastrodeAluno.util.Database;
import model.nota;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotaDAO {
    private final Connection connection = Database.getConnection();

    public NotaDAO() throws SQLException {
    }

    public nota inserirNota(int alunoID, int cursoID, double valorNota, Date data) {
        String sql = "INSERT INTO nota (valorNota, dataLancamento, alunoID, cursoID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, valorNota);
            stmt.setDate(2, new java.sql.Date(data.getTime()));
            stmt.setInt(3, alunoID);
            stmt.setInt(4, cursoID);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new nota(id, valorNota, data, alunoID, cursoID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean atualizarNota(int notaID, double novoValor) {
        String sql = "UPDATE nota SET valorNota = ? WHERE notaID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, novoValor);
            stmt.setInt(2, notaID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<nota> consultarNotasDosAlunosCurso(int cursoID) {
        List<nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM nota WHERE cursoID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cursoID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                notas.add(new nota(
                        rs.getInt("notaID"),
                        rs.getDouble("valorNota"),
                        rs.getDate("dataLancamento"),
                        rs.getInt("alunoID"),
                        rs.getInt("cursoID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notas;
    }
}