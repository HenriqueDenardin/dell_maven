package br.pucrs.henrique;

public class Startup {

    //inicialização de variaveis utilizando o private para um codigo mais limpo

    private String nome;
    private String slogan;
    private int ano_fundacao;
    private int pontuacao;
    private int pitches;
    private int bugs;
    private int tracoes;
    private int investidoresIrritados;
    private int fake;

    //metodo construtor para objeto Startup
    
    public Startup (String nome, String slogan, int ano_fundacao) {
        this.nome = nome;
        this.slogan = slogan;
        this.ano_fundacao = ano_fundacao;
        this.pontuacao = 70;
    }

    //metodo para adicionar pontuações nas batalhas

    public void adicionarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    //aqui registra os eventos utilizando um switch case, que ao receber a String por exemplo "pitch" retornando a contagem de eventos e a pontuação que soma ao time
    //utilização de lambda para um switch mais limpo e compreensivo

    public void registrarEvento(String tipo) {
        switch (tipo) {
            case "1" : pitches++; pontuacao += 6; break;
            case "2" : bugs++; pontuacao -= 4; break;
            case "3" : tracoes++; pontuacao += 3; break;
            case "4": investidoresIrritados++; pontuacao -= 6; break;
            case "5" : fake++; pontuacao -= 8; break;
        }
    }

    //metodo toString para printar as Startups

    @Override
    public String toString(){
        return "Startup [Nome=" + nome + ", Pontuação=" + pontuacao + "]";
    }

    //metodos getters

    public int getPontuacao() {
        return pontuacao;
    }

    public String getNome() {
       return nome;
    }

    public String getSlogan() {
        return slogan;
    }
    
    public int getPitches() {
        return pitches;
    }
    
    public int getBugs() {
        return bugs;
    }
    
    public int getTracoes() {
        return tracoes;
    }
    
    public int getInvestidoresIrritados() {
        return investidoresIrritados;
    }
    
    public int getfake() {
        return fake;
    }

    public int getAno_fundacao() {
        return ano_fundacao;
    }
}