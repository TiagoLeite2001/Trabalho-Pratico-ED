/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import exceptions.ElementNotFoundException;
import exceptions.InvalidDocumentException;
import exceptions.InvalidOperationException;
import exceptions.InvalidWeightValueException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import interfaces.ICenario;
import interfaces.IDivisao;
import interfaces.IMissao;
import interfaces.IMissoes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import missoes.Divisao;
import missoes.Missao;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tiago
 */
public class Menu {

    IMissoes missoes;

    public Menu(IMissoes missoes) {
        this.missoes = missoes;
    }

    public void menuPrincipal() throws NullElementValueException, RepeatedElementException,
            InvalidWeightValueException, ElementNotFoundException, IOException, FileNotFoundException,
            ParseException, VersionAlreadyExistException, InvalidOperationException, InvalidDocumentException {
        System.out.println("\nTrabalho Prático de Estrutura de Dados"
                + "\n     Realizado por: "
                + "\n     João Lopes - 8190228"
                + "\n     Tiago Leite - 8190338\n");

        Scanner inputS = new Scanner(System.in, "latin1");
        int input = -1;

        while (input != 0) {

            System.out.println("\n"
                    + "\nImprobable Mission Force - Mission simulator"
                    + "\n"
                    + "\n"
                    + "\n 1 - Carregar um mapa de uma missão."
                    + "\n"
                    + "\n 2 - Eliminar um mapa de uma missão."
                    + "\n "
                    + "\n 3 - Simulações."
                    + "\n"
                    + "\n 4 - Visualizar mapas das missões carregadas."
                    + "\n"
                    + "\n 5 - Visualizar resultados de simuluções manuais."
                    + "\n"
                    + "\n 6 - Visualizar resultados de simulações automáticas."
                    + "\n"
                    + "\n 7 - Visualizar o manual de instruções."
                    + "\n"
                    + "\n 0 - Sair");

            input = inputS.nextInt();

            switch (input) {
                case (0):
                    break;
                case (1):
                    adicionarMissao();
                    break;
                case (2):
                    removerMissao();
                    break;
                case (3):
                    simulacoes();
                case (4):
                    visualizarMapasCarregados();
                case (5):

                    visualizarSimulacoesManuais();
                case (6):
                    visualizarSimulacoesAutomaticas();
                case (7):
                    intrucoes();
            }

        }
    }

    private void adicionarMissao() {

        Scanner inputS = new Scanner(System.in, "latin1");

        System.out.println("\n*Adicionar nova missão*"
                + "\n"
                + "\nIntroduza o caminho(path) onde se encontra o mapa a adicionar."
                + "\n Exempo: Mapas/mapaEnunciado.json");

        String path = "";
        String answer = "";

        boolean adicionado = false;
        boolean erro = false;

        while (!adicionado) {
            try {
                path = inputS.nextLine();
                this.missoes.importarMissao(path);
                adicionado = true;
            } catch (IOException | NullElementValueException | ElementNotFoundException
                    | RepeatedElementException | InvalidWeightValueException
                    | InvalidOperationException | ParseException | VersionAlreadyExistException
                    | InvalidDocumentException ex) {
                System.out.println("Erro ao carregar missão!"
                        + "\nDeseja tentar novamente? (S/N)");
                answer = inputS.nextLine();

                if (answer.equals("N")) {
                    adicionado = true;
                    erro = true;
                } else {
                    System.out.println("Introduza novamente o caminho(path) onde se encontra o mapa a adicionar.");
                }
            }
        }

        if (erro == false) {
            System.out.println("\n Missão importada com sucesso!");
        } else {

            System.out.println("Nenhuma missão foi importada");
        }
    }

    private void removerMissao() {

        System.out.println("\nMissões existentes:");

        Iterator<IMissao> itMissoes = missoes.getMissoes();

        while (itMissoes.hasNext()) {

            System.out.println(itMissoes.next().toString());

        }

        Scanner inputS = new Scanner(System.in, "latin1");

        System.out.println("\n*Remover missão existente*"
                + "\n"
                + "\nIntroduza o nome da missão a remover:");

        String missaoo = "";
        String answer = "";

        boolean removido = false;
        boolean erro = false;

        while (!removido) {
            try {
                missaoo = inputS.nextLine();
                IMissao missao = new Missao(missaoo);
                this.missoes.removerMissao(missao);
                removido = true;
            } catch (NullElementValueException | ElementNotFoundException e) {
                System.out.println("Erro ao remover missão!"
                        + "\nDeseja tentar novamente? (S/N)");
                answer = inputS.nextLine();

                if (answer.equals("N")) {
                    removido = true;
                    erro = true;
                } else {
                    System.out.println("Introduza novamente o nome da missão a remover.");
                }
            }
        }

        if (erro == false) {
            System.out.println("\n Missão removida com sucesso!");
        } else {

            System.out.println("Nenhuma missão foi removida");
        }
    }

    public void visualizarMapasCarregados() {

        if (this.missoes.getNumMissoes() > 0) {

            System.out.println("\nMissões disponíveis:");

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next());
            }
        } else {
            System.out.println("Não existem missões carregadas!");
        }
    }

    public void visualizarSimulacoesManuais() {
        if (this.missoes.getNumMissoes() > 0) {

            System.out.println("\nMissões disponíveis:");

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next().getCodMissao());
            }

            System.out.println("Escolha a missão introduzindo o seu cod name");

            Scanner inputS = new Scanner(System.in, "latin1");

            String missaoo = "";
            String answer = "";

            boolean apresentado = false;
            boolean erro = false;

            while (!apresentado) {
                try {
                    missaoo = inputS.nextLine();
                    IMissao missao = new Missao(missaoo);

                    this.missoes.apresentarResultadosSimulacoesManuais(missao);

                    System.out.println(this.missoes.apresentarResultadosSimulacoesManuais(missao));

                    apresentado = true;
                } catch (NullElementValueException | ElementNotFoundException e) {
                    System.out.println("Erro no cod nome da missão!"
                            + "\nDeseja tentar novamente? (S/N)");
                    answer = inputS.nextLine();

                    if (answer.equals("N")) {
                        apresentado = true;
                        erro = true;
                    } else {
                        System.out.println("Introduza novamente o cod nome da missão a remover.");
                    }
                }
            }

        } else {
            System.out.println("Não existem missões carregadas!");
        }
    }

    public void visualizarSimulacoesAutomaticas() {
        System.out.println("Resultados Simulações Automáticas das missões carregadas");

        try {
            this.missoes.apresentarResultadosMissoes();
        } catch (NullElementValueException | InvalidOperationException ex) {
        }
    }

    private void intrucoes() {

    }

    private void simulacoes() {
        System.out.println("\nEscolher tipo de simulação a realizar");

        System.out.println(" 1 - Simulação Automática"
                + "\n"
                + "\n 2 - Simulação Manual"
                + "\n"
                + "\n 0 - Voltar");

        Scanner inputS = new Scanner(System.in, "latin1");

        int input = inputS.nextInt();

        while (input < 0 || input > 3) {
            System.out.println(" O número correspondente a uma das simulções está incorreto!"
                    + "\n Tente outra vez. (0 Para voltar)");
            input = inputS.nextInt();
        }

        switch (input) {
            case (1):
                simulacaoAutomatica();
            case (2):
                simulacaoManual();
            case (0):
                break;
        }

    }

    private void simulacaoManual() {

            System.out.println("\n Missões disponíveis:");

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next().getCodMissao());
            }

            System.out.println("Escolha a missão introduzindo o seu cod name");

            Scanner inputS = new Scanner(System.in, "latin1");

            String missaoo = "";
            String answer = "";

            boolean apresentado = false;
            boolean erro = false;

            while (!apresentado) {
                try {
                    missaoo = inputS.nextLine();

                    IMissao missao = this.missoes.obterMissao(missaoo);

                    System.out.println("Versões disponiveis para a missão: " + missaoo);

                    Iterator it = missao.getVersoes();

                    while (it.hasNext()) {
                        System.out.println(it.next().toString());
                    }

                    System.out.println("Escolher uma versão:");
                    
                    Scanner input2 = new Scanner(System.in, "latin1");
                    
                    int versao = input2.nextInt();

                    ICenario cenario = missao.obterCenario(versao);

                    if (missao.getListVersoes().contains(cenario)) {
                        try {
                            String entrada = "";

                            System.out.println("Entradas disponiveis para a versão escolhida");

                            cenario = missao.obterCenario(versao);

                            System.out.println(cenario.getListaEntradasSaidas().toString());

                            System.out.println("Introduza a entrada: ");
                            
                            Scanner input3 = new Scanner(System.in, "latin1");
                            
                            entrada = input3.nextLine();

                            IDivisao dEntrada = new Divisao(entrada);

                            boolean contains = false;

                            while (contains) {
                                if (cenario.getListaEntradasSaidas().contains(dEntrada)) {
                                    contains = true;
                                    missao.iniciarSimulacaoManual(versao, entrada);
                                } else {
                                    System.out.println("A divisão não existe!");
                                    entrada = input3.nextLine();
                                    dEntrada = new Divisao(entrada);
                                }
                            }
                            
                        } catch (InvalidOperationException ex) {
                        }
                    }

                    apresentado = true;
                } catch (NullElementValueException | ElementNotFoundException e) {
                    System.out.println("Erro no cod nome da missão!"
                            + "\nDeseja tentar novamente? (S/N)");
                    answer = inputS.nextLine();

                    if (answer.equals("N")) {
                        apresentado = true;
                        erro = true;
                    } else {
                        System.out.println("Introduza novamente o cod nome da missão a remover.");
                    }
                }
            }


    }

    private void simulacaoAutomatica() {

    }

}
