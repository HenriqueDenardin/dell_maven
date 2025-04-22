package br.pucrs.henrique;

import java.util.*;

//classe app onde chama todas outras classes e faz o codigo funcionar   

public class App {

    //classe main

    public static void main(String[] args) throws InterruptedException //exception para a shark fight 
    {
        Scanner in = new Scanner(System.in);

        //inicio do cadstro das startups, primeiro passo sendo escolher entre 4 ou 8 para a batalha
        int escolha = 0;
        System.out.println("Quantas startups você quer cadastrar?");
        System.out.println("Digite 1 para 4 startups");
        System.out.println("Digite 2 para 8 startups");

        //verifica a escolha do usuario se caso for diferente de 1 ou 2 ele entra no if, e inicia o loop novamente

        while (escolha != 1 && escolha != 2) {
            System.out.print("Sua escolha: ");
            escolha = in.nextInt();
            if (escolha != 1 && escolha != 2) {
                System.out.println("Escolha inválida. Tente novamente.");
            }
        }

        //utilizando um operador ternario para deixar o codigo mais limpo, onde se a escolha for 1 é 4 startups se não é 8 ao escolher 2

        int limite = (escolha == 1) ? 4 : 8;
        Controller controller = new Controller(limite);
        in.nextLine();

        //loop para cadastrar as startups

        for (int i = 0; i < limite; i++) {
            System.out.println("\nCadastro de Startup " + (i + 1));
            System.out.print("Nome: ");
            String nome = in.nextLine();
            System.out.print("Slogan: ");
            String slogan = in.nextLine();
            System.out.print("Ano de fundação: ");
            int ano = in.nextInt();
            in.nextLine();

            Startup startup = new Startup(nome, slogan, ano);
            controller.cadastraStartup(startup);
        }

        //começo do torneio
        
        Torneio torneio = new Torneio(controller.getStartups());

        //enquanto for torneio.terminou for false ele continua, caso for true ele para e avança 

        while (!torneio.terminou()) {
            torneio.iniciarRodada();

            List<Batalha> batalhas = torneio.getBatalhas();

            //loop para manejar as batalhas que estão pendentes

            while (!torneio.todasConcluidas()) {
                System.out.println("\nEscolha a batalha para administrar:");
                for (int i = 0; i < batalhas.size(); i++) {
                    Batalha batalha = batalhas.get(i);
                    String status = batalha.isConcluida() ? "(CONCLUÍDA)" : "(PENDENTE)"; //utilização de um ternario para definir as batalhas como pendentes ou concluidas 
                    System.out.println("[" + i + "] " + batalha + " " + status);
                }

                //escolha de qual batalha será manejada     

                System.out.print("Número da batalha: ");
                int escolhaBatalha = in.nextInt();
                in.nextLine();

                //previnir o usuario de escolher uma batalha invalida e uma que já se concluiu  

                if (escolhaBatalha < 0 || escolhaBatalha >= batalhas.size()) {
                    System.out.println("Escolha inválida.");
                    continue;
                }

                Batalha batalhaSelecionada = batalhas.get(escolhaBatalha);

                if (batalhaSelecionada.isConcluida()) {
                    System.out.println("Essa batalha já foi concluída.");
                    continue;
                }

                administrarBatalha(batalhaSelecionada, in);
            }

            torneio.avancarFase();
        }

        //final do torneio mostrando a campeã e seu slogan 
        Startup campea = torneio.getCampeao();
        System.out.println("\nCAMPEÃ DO TORNEIO: " + campea.getNome());
        mostrarRelatorioFinal(controller.getStartups());
        System.out.println("Slogan: " + campea.getSlogan());

        //funcionalidade complementar, nesta funcionalidade a campeã retorna um pitch final de vitoria, dando assim mais profundidade ao final do torneio   

        mostrarPitchFinal(campea);
        
        

        in.close();
    }

    //metodo para administrar as batalhas

    private static void administrarBatalha(Batalha batalha, Scanner in) throws InterruptedException //exception para a shark fight 
    {
        Startup star1 = batalha.getStar1();
        Startup star2 = batalha.getStar2();
    
        System.out.println("\nAdministrando Batalha ");
        System.out.println(star1.getNome() + " (Pontuação: " + star1.getPontuacao() + ")");
        System.out.println(star2.getNome() + " (Pontuação: " + star2.getPontuacao() + ")");
    
        String evento = "";

        //loop para o usuario decidir qual evento será realizado    
    
        while (!evento.equals("fim")) {
            System.out.println("\nDigite o tipo de evento (1 - pitch convincente +6, 2 - produtos com bug -4, 3 - boa tracao de usuario +3, 4 - investidor irritado -6, 5 - fake news no pitch -8, 0 - fim) ");
            
            //loop para retornar entradas invalidas 
                        
            while (true) {
                System.out.println("\nDigite qual evento será escolhido para ocorrer:");
            
            evento = in.nextLine();

        
            if (evento.equals("0") || evento.equals("1") || evento.equals("2") || 
                evento.equals("3") || evento.equals("4") || evento.equals("5")) {
                    break;
                } 
                else {
            System.out.println("Entrada inválida. Digite um número de 0 a 5.");
            }
        }

            //caso a escolha seja fim, ele da break no loop 
            
            if (evento.equals("0")) break;
            
            //voto para a startup receber o evento ocorrido 

            System.out.print("Qual startup recebeu o evento? (1 para " + star1.getNome() + ", 2 para " + star2.getNome() + "): ");
            int escolha = in.nextInt();
            in.nextLine(); //retirada de quebra de linha    
    
            Startup escolhida = (escolha == 1) ? star1 : star2;
            escolhida.registrarEvento(evento);
        }
    
        //mostra a pontuação após o round   

        System.out.println("\nPontuação final:");
        System.out.println(star1.getNome() + ": " + star1.getPontuacao());
        System.out.println(star2.getNome() + ": " + star2.getPontuacao());
    
        //realização da shark fight caso tenha empate   

        Startup vencedor = batalha.getVencedor();
        if (vencedor == null) {
            System.out.println("\nEmpate! Iniciando SHARK FIGHT!");
            vencedor = sharkFight(star1, star2, in);
        }
        
        //soma pontos para o vencedor do round, como pedido no enunciado

        vencedor.adicionarPontuacao(30);
        System.out.println("\nVencedor da batalha: " + vencedor.getNome() + " (+30 pontos!)");
    
        batalha.concluir();
    }
    
    private static Startup sharkFight(Startup star1, Startup star2, Scanner in) throws InterruptedException //exception para a shark fight
    {
        Random rand = new Random();
        Startup vencedor = rand.nextBoolean() ? star1 : star2;
       
        //loop para deixar a shark fight mais realista, onde utiliza um sleep para fingir que esta decidindo    

        for (int i = 0; i <= 3; i++) {
            Thread.sleep(1000);
            System.out.println("Carregando Shark Fight");
        }
        System.out.println("O vencedor da SHARK FIGHT é: " + vencedor.getNome());
        return vencedor;
    }
    private static void mostrarRelatorioFinal(List<Startup> startups) {
        System.out.println("\nRELATÓRIO FINAL DO TORNEIO");
        System.out.println("------------------------------------------------------------------------");
    
        //ordena por pontuação decrescente

        startups.sort((star1, star2) -> Integer.compare(star2.getPontuacao(), star1.getPontuacao()));
    
        System.out.printf("%-20s %-10s %-7s %-6s %-8s %-12s %-12s\n", 
            "Startup", "Pts", "Pitch", "Bug", "Tração", "Investidores", "Penalidades");
        System.out.println("------------------------------------------------------------------------");
    
        for (Startup star : startups) {
            System.out.printf("%-20s %-10d %-7d %-6d %-8d %-12d %-12d\n",
                star.getNome(),
                star.getPontuacao(),
                star.getPitches(),
                star.getBugs(),
                star.getTracoes(),
                star.getInvestidoresIrritados(),
                star.getfake()
            );
        }
    
        System.out.println("======================================================");
    }

    //metodo para o feature extra

    private static void mostrarPitchFinal(Startup campea) {
        System.out.println("\n Pitch Final da Campeã:");
        System.out.println("Somos a " + campea.getNome() + ", fundada em " + campea.getAno_fundacao() + ".");
        System.out.println("Nosso slogan é: \"" + campea.getSlogan() + "\".");
    
        String bugTexto = (campea.getBugs() == 0) ? "evitamos qualquer bug" : "tivemos " + campea.getBugs() + " bugs";
        String investidorTexto = campea.getInvestidoresIrritados() > 0 ? 
            campea.getInvestidoresIrritados() + " investidores ficaram irritados" : 
            "nenhum investidor ficou irritado";
        String fakeTexto = (campea.getfake() == 0) ? "fake news aqui não!!!" : "mentimos " + campea.getfake() + "mas valeu o esforço";
    
        System.out.println("Ao longo do torneio, fizemos " + campea.getPitches() + " pitches, conseguimos " + 
            campea.getTracoes() + " boas trações, " + bugTexto + ", " + investidorTexto + " e " + fakeTexto + ".");
    
        System.out.println("Conquistamos o título com uma pontuação final de " + campea.getPontuacao() + " pontos.");
        System.out.println("Obrigado, jurados!");
    }
}

        
    