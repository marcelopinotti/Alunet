package model;

public class aluno {
    private int alunoID;
    private String nome;
    private final String cpf;
    private String telefone;
    private String endereco;
    private String login;
    private String senha;
    private boolean ativo;

    public aluno(int alunoID, String nome, String cpf, String telefone, String endereco, String login, String senha, boolean ativo) {
        this.alunoID = alunoID;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
    }


    public void setAlunoID(int alunoID) {this.alunoID = alunoID;}

    public int getAlunoID() {return alunoID;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public boolean isAtivo() {return ativo;}

    public void setAtivo(boolean ativo) {this.ativo = ativo;}

}
