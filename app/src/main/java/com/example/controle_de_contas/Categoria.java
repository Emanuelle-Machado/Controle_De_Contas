package com.example.controle_de_contas;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable {

    public String descricao;
    public ArrayList<Conta> contas;

    public Categoria(String descricao) {
        this.descricao = descricao;
        this.contas = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public int getQuantidadeContas() {
        return contas.size();
    }

    public double getTotal() {
        double total = 0;
        for (Conta c : contas) {
            total += c.getValor();
        }
        return total;
    }

    public double getTotalPago() {
        double pago = 0;
        for (Conta c : contas) {
            if (c.isPaga()) pago += c.getValor();
        }
        return pago;
    }

    public double getTotalAPagar() {
        return getTotal() - getTotalPago();
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "descricao='" + descricao + '\'' +
                '}';
    }
}
