package CadastrodeAluno.main;

import CadastrodeAluno.util.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriadorTabela {

    public static void createTables() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();

            System.out.println("DatabaseInitializer: Iniciando a criação das tabelas...");

            // Tabela Funcionario
            String createFuncionarioTable = "CREATE TABLE IF NOT EXISTS funcionario (" +
                    "funcionarioID INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(75) NOT NULL," +
                    "cargo VARCHAR(75) NOT NULL," +
                    "login VARCHAR(75) NOT NULL," +
                    "senha VARCHAR(30) NOT NULL," +
                    "autenticado BOOLEAN DEFAULT FALSE" +
                    ")";
            stmt.execute(createFuncionarioTable);
            System.out.println("DatabaseInitializer: Tabela 'funcionario' criada ou já existe.");

            // Tabela Professor
            String createProfessorTable = "CREATE TABLE IF NOT EXISTS professor (" +
                    "professorID INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(75) NOT NULL," +
                    "login VARCHAR(75) NOT NULL," +
                    "senha VARCHAR(30) NOT NULL," +
                    "autenticado BOOLEAN DEFAULT FALSE" +
                    ")";
            stmt.execute(createProfessorTable);
            System.out.println("DatabaseInitializer: Tabela 'professor' criada ou já existe.");

            // Tabela Curso
            String createCursoTable = "CREATE TABLE IF NOT EXISTS curso (" +
                    "cursoID INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(75) NOT NULL," +
                    "categoria VARCHAR(75) NOT NULL," +
                    "duracao INT NOT NULL," +
                    "statusCurso VARCHAR(75) NOT NULL," +
                    "professorID INT," +
                    "FOREIGN KEY (professorID) REFERENCES professor(professorID)" +
                    ")";
            stmt.execute(createCursoTable);
            System.out.println("DatabaseInitializer: Tabela 'curso' criada ou já existe.");

            // Tabela Aluno
            String createAlunoTable = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "alunoID INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(75) NOT NULL," +
                    "cpf VARCHAR(14) UNIQUE NOT NULL," +
                    "telefone VARCHAR(20) NOT NULL," +
                    "email VARCHAR(75) NOT NULL," +
                    "endereco VARCHAR(150) NOT NULL," +
                    "login VARCHAR(75) NOT NULL," +
                    "senha VARCHAR(30) NOT NULL," +
                    "autenticado BOOLEAN DEFAULT FALSE" +
                    ")";
            stmt.execute(createAlunoTable);
            System.out.println("DatabaseInitializer: Tabela 'aluno' criada ou já existe.");

            // Tabela Nota
            String createNotaTable = "CREATE TABLE IF NOT EXISTS nota (" +
                    "notaID INT PRIMARY KEY AUTO_INCREMENT," +
                    "valorNota DOUBLE NOT NULL," +
                    "dataLancamento DATE NOT NULL," +
                    "alunoID INT NOT NULL," +
                    "cursoID INT NOT NULL," +
                    "FOREIGN KEY (alunoID) REFERENCES aluno(alunoID)," +
                    "FOREIGN KEY (cursoID) REFERENCES curso(cursoID)" +
                    ")";
            stmt.execute(createNotaTable);
            System.out.println("DatabaseInitializer: Tabela 'nota' criada ou já existe.");

            // Tabela associativa Professor ao Curso
            String createProfessorCursoTable = "CREATE TABLE IF NOT EXISTS professor_curso (" +
                    "professorID INT NOT NULL," +
                    "cursoID INT NOT NULL," +
                    "PRIMARY KEY (professorID, cursoID)," +
                    "FOREIGN KEY (professorID) REFERENCES professor(professorID)," +
                    "FOREIGN KEY (cursoID) REFERENCES curso(cursoID)" +
                    ")";
            stmt.execute(createProfessorCursoTable);
            System.out.println("DatabaseInitializer: Tabela 'professor_curso' criada ou já existe.");

            // Tabela Administrador
            String createAdministradorTable = "CREATE TABLE IF NOT EXISTS administrador (" +
                    "administradorID INT PRIMARY KEY," +
                    "FOREIGN KEY (administradorID) REFERENCES funcionario(funcionarioID)" +
                    ")";
            stmt.execute(createAdministradorTable);
            System.out.println("DatabaseInitializer: Tabela 'administrador' criada ou já existe.");

            System.out.println("DatabaseInitializer: Todas as tabelas foram processadas com sucesso!");

        } catch (SQLException e) {
            System.err.println("DatabaseInitializer - ERRO ao criar tabelas: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("DatabaseInitializer - ERRO ao fechar Statement: " + e.getMessage());
                }
            }
            Database.closeConnection();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando o teste de criação de tabelas...");
            createTables();
            System.out.println("Teste de criação de tabelas concluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro no teste de criação de tabelas: " + e.getMessage());
        }
    }
}