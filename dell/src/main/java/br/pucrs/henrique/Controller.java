package br.pucrs.henrique;

import java.util.*;

//classe para ter um controle dos cadastros

public class Controller {
    private Cadastrar cadastro;

    public Controller(int limite){
        this.cadastro = new Cadastrar(limite);
    }

    public List<Startup> getStartups() {
        return cadastro.getStartups();
    }

    public boolean cadastraStartup(Startup novaStartup) {
        return cadastro.cadastrarNovaStartup(novaStartup);
    }

    public int getTotalCadastradas() {
        return cadastro.getTotalCadastradas();
    }

    public int getLimite() {
        return cadastro.getLimite();
    }
}