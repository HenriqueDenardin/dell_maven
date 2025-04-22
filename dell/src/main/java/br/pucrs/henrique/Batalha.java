package br.pucrs.henrique;

public class Batalha {

    //declaração de variaveis em private para um codigo mais limpo

    private Startup star1;
    private Startup star2;
    private boolean concluida;

    //construtor para as batalhas

    public Batalha(Startup star1, Startup star2) {
        this.star1 = star1;
        this.star2 = star2;
        this.concluida = false;
    }

    //getters para as startups


    public Startup getStar1() { return star1; }
    public Startup getStar2() { return star2; }

    //metodo para retornar se a batalha foi concluida   

    public boolean isConcluida() {
        return concluida;
    }

    //metodo para concluir a batalha

    public void concluir() {
        this.concluida = true;
    }

    //metodo para verificar qual startup foi a vencedora da rodada e se caso tiver empate ele retorna null  

    public Startup getVencedor() {
        if (star1 == null) return star2;
        if (star2 == null) return star1;
        if (star1 == star2) return star1;

        if (star1.getPontuacao() > star2.getPontuacao()) return star1;
        if (star2.getPontuacao() > star1.getPontuacao()) return star2;

        return null;
    }

    //toString para retornar quais startups estão batalhando

    @Override
    public String toString() {
        return star1.getNome() + " X " + star2.getNome();
    }
}