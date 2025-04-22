package br.pucrs.henrique;

import java.util.*;

//classe que faz o manejamento do torneio   

public class Torneio {

    //declaração de listas para guardar os participantes

    private List<Startup> participantes;
    private List<Batalha> batalhasAtuais;
    private int rodada;

    //construtor do torneio 

    public Torneio(List<Startup> participantes) {
        this.participantes = new ArrayList<>(participantes);
        this.batalhasAtuais = new ArrayList<>();
        this.rodada = 1;
    }

    //metodo para iniciar as rodadas das batalhas

    public void iniciarRodada() {
        System.out.println("\n---- Rodada " + rodada + " ----");
        //utilização de uma collection para randomizar quem vai contra quem
        Collections.shuffle(participantes);
        batalhasAtuais.clear();

        //loop para determinar as batalhas que irão ocorrer


        for (int i = 0; i < participantes.size(); i += 2) {
            Startup star1 = participantes.get(i);
            Startup star2 = (i + 1 < participantes.size()) ? participantes.get(i + 1) : null; //utilizei um operador ternario para ficar um codigo mais limpo ao invez de usar um if else 
            batalhasAtuais.add(new Batalha(star1, star2));
        }
        //exibe as batalhas que irão ocorrer
        exibirBatalhas();
    }

    //metodo utilizado para exibir as batalhas  

    public void exibirBatalhas() {
        for (int i = 0; i < batalhasAtuais.size(); i++) {
            System.out.println("(" + i + ") " + batalhasAtuais.get(i));
        }
    }

    //getter para batalhas      

    public List<Batalha> getBatalhas() {
        return batalhasAtuais;
    }

    //verifica com um loop se as batalhas foram todas concluidas, caso ainda tiver batalhas ele retorna false, se já acabaram as batalhas ele retorna true

    public boolean todasConcluidas() {
        for (Batalha batalha : batalhasAtuais) {
            if (!batalha.isConcluida()) return false;
        }
        return true;
    }

    //metodo para avançar as fases de batalha e se tiver algum vencedor ele adiciona 30 pontos para aquele que ganhou   

    public void avancarFase() {
        List<Startup> vencedores = new ArrayList<>();
        for (Batalha batalha : batalhasAtuais) {
            Startup vencedor = batalha.getVencedor();
            if (vencedor != null) {
                vencedor.adicionarPontuacao(30);
                vencedores.add(vencedor);
            }
        }
        this.participantes = vencedores;
        this.rodada++;
    }

    //metodo para retornar se terminou as batalhas  

    public boolean terminou() {
        return participantes.size() <= 1;
    }

    //metodo que mostra qual startup foi a campeã

    public Startup getCampeao() {
        if (participantes.size() == 1) {
            return participantes.get(0);
        }
        return null;
    }
}