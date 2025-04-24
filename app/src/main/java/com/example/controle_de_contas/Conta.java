package com.example.controle_de_contas;

import java.io.Serializable;
import java.util.Date;

import java.text.SimpleDateFormat;

public class Conta implements Serializable{

    private String descricao;
    private Date vencimento;
    private double valor;
    private boolean paga = false;
    private Categoria categoria;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Conta(String descricao, Date vencimento, double valor, boolean paga) {
        this.descricao = descricao;
        this.vencimento = vencimento;
        this.valor = valor;
        this.paga = paga;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        String dataFormatada = dateFormat.format(vencimento);
        return descricao + " " + (isPaga() ? "Paga" : "Não Paga") + "\n" + dataFormatada + " R$ " + valor;
    }
}
