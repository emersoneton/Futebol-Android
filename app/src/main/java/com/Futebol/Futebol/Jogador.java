package com.bruno.futbol;

public class Jogador {

    private int idjogador;
    private String nomejogador;
    private String posicao;
    private int numerocamiseta;
    private int  idtime;

    public int getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(int idjogador) {
        this.idjogador = idjogador;
    }

    public String getNomejogador() {
        return nomejogador;
    }

    public void setNomejogador(String nomejogador) {
        this.nomejogador = nomejogador;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdtime() {
        return idtime;
    }

    public void setIdtime(int idtime) {
        this.idtime = idtime;
    }

    public int getNumerocamiseta() {
        return numerocamiseta;
    }

    public void setNumerocamiseta(int numerocamiseta) {
        this.numerocamiseta = numerocamiseta;
    }

    @Override
    public String toString(){
        return "Id Jogador:" + this.idjogador + " Nome Jogador: "+this.nomejogador+ " Posição Jogador: "+this.posicao+" Numero Camisa: "+this.numerocamiseta +" ID: "+this.idtime ;
    }
}
