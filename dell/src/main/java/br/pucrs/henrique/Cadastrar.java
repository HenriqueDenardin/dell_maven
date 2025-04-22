package br.pucrs.henrique;

import java.util.*;

//classe para cadastrar startups    

public class Cadastrar {

    //declaração de variaveis em private para um codigo mais limpo

    private List<Startup> cadastros;
    private int limite;

    //metodo construtor     

    public Cadastrar(int limite){
        this.limite = limite;
        this.cadastros = new ArrayList<>();
    }

    //metodo que verifica se ainda possui espaço na lista para cadastrar startups       

    public boolean cadastrarNovaStartup(Startup startupNova) {
        if (cadastros.size() >= limite) {
            System.out.println("Limite de startups atingido (" + limite + ").");
            return false;
        }
        cadastros.add(startupNova);
        return true;
    }

    //getters

    public List<Startup> getStartups() {
        return cadastros;
    }

    public int getLimite() {
        return limite;
    }

    public int getTotalCadastradas() {
        return cadastros.size();
    }
}