package model;

public class professor {
    private final int professorID;
    private String nome;
    private String login;
    private String senha;
    private boolean ativo;

    public professor(int professorID, String nome, String login, String senha, boolean ativo) {
        this.professorID = professorID;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }


    public int getProfessorID() { return professorID; }
    public String getNome() { return nome; }
public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
