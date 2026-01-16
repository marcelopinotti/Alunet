package model;


import java.util.Date;

public class nota {
    private int notaID;
    private double valorNota;
    private Date dataLancamento;
    private int alunoID;
    private int cursoID;

    public nota(int notaID, double valorNota, Date dataLancamento, int alunoID, int cursoID) {
        this.notaID = notaID;
        this.valorNota = valorNota;
        this.dataLancamento = dataLancamento;
        this.alunoID = alunoID;
        this.cursoID = cursoID;
    }

    public nota(int id, int alunoID, int cursoID, double valorNota, Date data) {
    }


    public int getNotaID() {return notaID;}

    public void setNotaID(int notaID) {this.notaID = notaID;}

    public double getValorNota() {return valorNota;}

    public void setValorNota(double valorNota) {this.valorNota = valorNota;}

    public Date getDataLancamento() {return dataLancamento;}

    public void setDataLancamento(Date dataLancamento) {this.dataLancamento = dataLancamento;}

    public int getAlunoID() {return alunoID;}

    public void setAlunoID(int alunoID) {this.alunoID = alunoID;}

    public int getCursoID() {return cursoID;}

    public void setCursoID(int cursoID) {this.cursoID = cursoID;}



}
