package model;

public class curso {
    private int cursoID;
    private String nome;
    private String categoria;
    private String statusCurso;
    private int professorID;

    public curso(int cursoID, String nome, String categoria, String statusCurso, int professorID) {
        this.cursoID = cursoID;
        this.nome = nome;
        this.categoria = categoria;
        this.statusCurso = statusCurso;
        this.professorID = professorID;
    }

    public int getCursoID() {return cursoID;}

    public void setCursoID(int cursoID) {this.cursoID = cursoID;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCategoria() {return categoria;}

    public void setCategoria(String categoria) {this.categoria = categoria;}

    public String getStatusCurso() {return statusCurso;}

    public void setStatusCurso(String statusCurso) {this.statusCurso = statusCurso;}

    public int getProfessorID() {return professorID;}

    public void setProfessorID(int professorID) {this.professorID = professorID;}


}
