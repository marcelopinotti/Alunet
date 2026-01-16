package model;

public class funcionario {
    private int funcionarioID;
    private String nome;
    private String login;
    private String senha;
    private boolean ativo;

    public funcionario(int funcionarioID, String nome, String login, String senha, boolean ativo) {
        this.funcionarioID = funcionarioID;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }


    public int getFuncionarioID() {
        return funcionarioID;
    }

    public void setFuncionarioID(int funcionarioID) {
        this.funcionarioID = funcionarioID;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public boolean isAtivo() {return ativo;}

    public void setAtivo(boolean ativo) {this.ativo = ativo;}

}
