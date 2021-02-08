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
import exceptions.NoManualSimulationsException;
import exceptions.NoPathAvailableException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import interfaces.ICenario;
import interfaces.IDivisao;
import interfaces.IMissao;
import interfaces.IMissoes;
import interfaces.ISimulacaoAutomatica;
import interfaces.ISimulacaoManual;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import missoes.Divisao;
import missoes.Missao;
import missoes.Missoes;
import org.json.simple.parser.ParseException;

/**
 * Esta classe guarda a todas as missões assim como todos os menus do programa.
 */
public class Menu {

    IMissoes missoes;

    /**
     * Contrutor da classe Menu.
     */
    public Menu() {
        this.missoes = new Missoes();
    }

    /**
     * Menu principal.
     */
    public void menuPrincipal() throws NullElementValueException, RepeatedElementException,
            InvalidWeightValueException, ElementNotFoundException, IOException, FileNotFoundException,
            ParseException, VersionAlreadyExistException, InvalidOperationException, InvalidDocumentException {
        System.out.println("\nTrabalho Prático de Estrutura de Dados"
                + "\n     Realizado por: "
                + "\n     João Lopes  - 8190221"
                + "\n     Tiago Leite - 8190338\n");

        Scanner inputS = new Scanner(System.in, "latin1");
        int input = -1;

        while (input != 0) {

            System.out.println("\n-------------------------------------------------------------"
                    + "\n"
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
                    + "\n 5 - Visualizar resultados de simulações manuais."
                    + "\n"
                    + "\n 6 - Visualizar resultados de simulações automáticas."
                    + "\n"
                    + "\n 7 - Visualizar resultados de todas as missões."
                    + "\n"
                    + "\n 8 - Exportar resultados de simulações manuais."
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
                    break;
                case (4):
                    visualizarMapasCarregados();
                    break;
                case (5):
                    visualizarSimulacoesManuais();
                    break;
                case (6):
                    visualizarSimulacoesAutomaticas();
                    break;
                case (7):
                    visualizarResultadosMissoes();
                    break;    
                case (8):
                    visualizarExportarSimulacoesManuais();
                    break;
            }
        }
    }
    
    /**
     * Menu exportar simulações manuais.
     */
    private void visualizarExportarSimulacoesManuais() {
        Iterator<IMissao> itMissoes = missoes.getMissoes();
        if (!itMissoes.hasNext()) {
            System.out.println("Não existem missões importadas.");
        } else {
            System.out.println("\nMissões existentes:");
            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next().getCodMissao());
            }

            System.out.println("Introduza a missão: ");
            Scanner inputS = new Scanner(System.in, "latin1");

            boolean adicionado = false;
            boolean erro = false;
            String answer;
            while (!adicionado) {
                String codMissao = inputS.nextLine();
                try {
                    this.missoes.exportarSimulacoesManuais(codMissao);
                    adicionado=true;
                } catch (NullElementValueException | ElementNotFoundException | NoManualSimulationsException | IOException ex) {
                    System.out.println(ex);

                    System.out.println("Erro ao exportar simulações!"
                            + "\nDeseja tentar novamente? (S/N)");
                    answer = inputS.nextLine();

                    if (answer.equals("N")) {
                        adicionado = true;
                        erro = true;
                    } else {
                        System.out.println("Introduza novamente a missão.");
                    }
                }
            }

            if (erro == false) {
                System.out.println("\n Simulações exportadas com sucesso!");
            } else {

                System.out.println("Nenhuma simulação foi exportada!");
            }
        }

    }
   
    /**
     * Menu carregar missão.
     */
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
            	System.out.println(ex);
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

    /**
     * Menu remover missão.
     */
    private void removerMissao() {

        System.out.println("\nMissões existentes:");

        Iterator<IMissao> itMissoes = missoes.getMissoes();

        while (itMissoes.hasNext()) {

            System.out.println(itMissoes.next().getCodMissao());

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
            	System.out.println(e);
                System.out.println("\nErro ao remover missão!"
                        + "\nDeseja tentar novamente? (S/N)");
                answer = inputS.nextLine();

                if (answer.equals("N")) {
                    removido = true;
                    erro = true;
                } else {
                    System.out.println("\nIntroduza novamente o nome da missão a remover.");
                }
            }
        }

        if (erro == false) {
            System.out.println("\n Missão removida com sucesso!");
        } else {

            System.out.println("\nNenhuma missão foi removida");
        }
    }

    /**
     * Menu visualizar mapas carregados.
     */
    private void visualizarMapasCarregados() {

        if (this.missoes.getNumMissoes() > 0) {

            System.out.println("\nNúmero de missões: " + this.missoes.getNumMissoes());

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                IMissao missao = itMissoes.next();
                System.out.println("----------------------------------------------");
                System.out.println("\nMissão: " + missao.getCodMissao());
                
                Iterator<ICenario> itCenarios = missao.getVersoes();
                
                while(itCenarios.hasNext()){
                    ICenario cenarioTemp = itCenarios.next();
                    try {
                        System.out.println("\n"+missao.mostrarMapa(cenarioTemp.getVersao()));
                    } catch (NullElementValueException | ElementNotFoundException ex) {
                    	System.out.println(ex);
                    }
                }
            }
        } else {
            System.out.println("\nNão existem missões carregadas!");
        }
    }

    /**
     * Menu visualizar simulações manuais de uma missão.
     */
    private void visualizarSimulacoesManuais() {
        if (this.missoes.getNumMissoes() > 0) {

            System.out.println("\nMissões disponíveis:");

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next().getCodMissao());
            }

            System.out.println("\nEscolha a missão introduzindo o seu cod name");

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
                	System.out.println(e);
                    System.out.println("\nErro no cod nome da missão!"
                            + "\nDeseja tentar novamente? (S/N)");
                    answer = inputS.nextLine();

                    if (answer.equals("N")) {
                        apresentado = true;
                        erro = true;
                    } else {
                        System.out.println("\nIntroduza novamente o cod nome da missão a remover.");
                    }
                }
            }

        } else {
            System.out.println("\nNão existem missões carregadas!");
        }
    }

    /**
     * Menu visualizar simulações automáticas de todas as missões.
     */
    private void visualizarSimulacoesAutomaticas() {
        
        System.out.println("\nResultados Simulações Automáticas das missões carregadas");

        try {
            System.out.println(this.missoes.apresentarResultadosSimulacoesAutomaticas());
        } catch (NullElementValueException | InvalidOperationException ex) {
        	System.out.println(ex);
        }
    }
    
    /**
     * Menu visualizar resultados de todas as missões.
     */
    private void visualizarResultadosMissoes() {
        
        System.out.println("\nResultados de todas as missões carregadas");

        try {
            System.out.println(this.missoes.apresentarResultadosMissoes());
        } catch (NullElementValueException | InvalidOperationException ex) {
        	System.out.println(ex);
        }
    }

    /**
     * Menu simulações.
     */
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
            System.out.println("\n O número correspondente a uma das simulções está incorreto!"
                    + "\n Tente outra vez.");
            input = inputS.nextInt();
        }

        switch (input) {
            case (1):
                simulacaoAutomatica();
                break;
            case (2):
                simulacaoManual();
                break;
            case (0):
                break;
        }

    }

    /**
     * Menu simulação manual.
     */
    private void simulacaoManual() {
        if (this.missoes.getNumMissoes() == 0) {
            System.out.println("\nNão existem missões carregadas!");
        } else {

            System.out.println("\n Missões disponíveis:");

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next().getCodMissao());
            }

            System.out.println("\nEscolha a missão introduzindo o seu cod name");

            Scanner inputS = new Scanner(System.in, "latin1");

            String missaoo = "";
            String answer = "";

            boolean apresentado = false;
            boolean erro = false;

            while (!apresentado) {
                try {
                    missaoo = inputS.nextLine();

                    IMissao missao = this.missoes.obterMissao(missaoo);

                    System.out.println("\nVersões disponiveis para a missão: " + missaoo);

                    Iterator it = missao.getVersoes();

                    while (it.hasNext()) {
                        System.out.println(it.next().toString());
                    }

                    System.out.println("\nEscolher uma versão:");

                    Scanner input2 = new Scanner(System.in, "latin1");

                    int versao = input2.nextInt();

                    ICenario cenario = missao.obterCenario(versao);

                    if (missao.getListVersoes().contains(cenario)) {
                        String entrada = "";
                        System.out.println("\nEntradas disponiveis para a versão escolhida");
                        cenario = missao.obterCenario(versao);
                        System.out.println(cenario.getListaEntradasSaidas().toString());
                        System.out.println("\nIntroduza a entrada: ");
                        Scanner input3 = new Scanner(System.in, "latin1");
                        entrada = input3.nextLine();
                        IDivisao dEntrada = new Divisao(entrada);
                        boolean contains = false;

                        while (!contains) {
                            if (cenario.getListaEntradasSaidas().contains(dEntrada)) {
                                try {
                                    ISimulacaoManual sm = missao.iniciarSimulacaoManual(versao, entrada);
                                    System.out.println(sm.toString());
                                } catch (InvalidOperationException ex) {
                                	System.out.println(ex);
                                }
                                contains = true;

                            } else {
                                System.out.println("\nA divisão não existe! Introduza novamente a entrada:");
                                entrada = input3.nextLine();
                                dEntrada = new Divisao(entrada);
                            }
                        }
                    }

                    apresentado = true;
                } catch (NullElementValueException | ElementNotFoundException e) {
                	System.out.println(e);
                    System.out.println("\nErro no cod nome da missão!"
                            + "\nDeseja tentar novamente? (S/N)");
                    answer = inputS.nextLine();

                    if (answer.equals("N")) {
                        apresentado = true;
                        erro = true;
                    } else {
                        System.out.println("\nIntroduza novamente o cod nome da missão a remover.");
                    }
                }
            }
        }

    }

    
    
    /**
     * Menu simulação automática.
     */
    private void simulacaoAutomatica() {

        if (this.missoes.getNumMissoes() == 0) {
            System.out.println("\nNão existem missões carregadas!");
        } else {
            System.out.println("\n Missões disponíveis:");

            Iterator<IMissao> itMissoes = missoes.getMissoes();

            while (itMissoes.hasNext()) {
                System.out.println(itMissoes.next().getCodMissao());
            }

            System.out.println("\n Escolha a missão introduzindo o seu cod name");

            Scanner inputS = new Scanner(System.in, "latin1");

            String missaoo = "";
            String answer = "";

            boolean apresentado = false;

            while (!apresentado) {
                try {
                    missaoo = inputS.nextLine();

                    IMissao missao = this.missoes.obterMissao(missaoo);

                    System.out.println("\n Versões disponiveis para a missão: " + missaoo);

                    Iterator it = missao.getVersoes();

                    while (it.hasNext()) {
                        System.out.println(it.next().toString());
                    }

                    apresentado = true;

                    System.out.println("\n Escolher uma versão:");

                    Scanner input2 = new Scanner(System.in, "latin1");

                    int versao = 0;

                    boolean existeCenario = false;

                    while (!existeCenario) {
                        try {
                            versao = input2.nextInt();

                            ISimulacaoAutomatica sa = missao.iniciarSimulacaoAutomatica(versao);
                            existeCenario = true;
                            System.out.println(sa.toString());
                        } catch (InvalidOperationException | NoPathAvailableException ex) {
                        	System.out.println(ex);
                            System.out.println("\n Versão não existe, introduza novamente a versão!");
                        }
                    }

                } catch (NullElementValueException | ElementNotFoundException e) {
                	System.out.println(e);
                    System.out.println("\nErro no cod nome da missão!"
                            + "\nDeseja tentar novamente? (S/N)");
                    answer = inputS.nextLine();

                    if (answer.equals("N")) {
                        apresentado = true;
                    } else {
                        System.out.println("\nIntroduza novamente o cod nome da missão a remover.");
                    }
                }
            }
        }
    }

    
}
