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
import interfaces.IMissao;
import interfaces.IMissoes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
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
                    + "\nBem vindo!!"
                    + "\n"
                    + "\n 1 - Carregar um mapa de uma missão."
                    + "\n"
                    + "\n 2 - Eliminar um mapa de uma missão."
                    + "\n "
                    + "\n 3 - Simulações."
                    + "\n"
                    + "\n 4 - Visualizar o manual de instruções."
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
                    path = inputS.nextLine();
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
                    missaoo = inputS.nextLine();
                }
            }
        }

        if (erro == false) {
            System.out.println("\n Missão removida com sucesso!");
        } else {

            System.out.println("Nenhuma missão foi removida");
        }
    }

    private static void intrucoes() {

    }

    private void simulacoes() {
        System.out.println("\nEscolher tipo de simulação e o cenário da missão desejado");

        System.out.println("\nCenários disponíveis:");

        Iterator<IMissao> itMissoes = missoes.getMissoes();

        while (itMissoes.hasNext()) {

            IMissao missaoTemp = itMissoes.next();

            Iterator<ICenario> itCenarios = missaoTemp.getVersoes();

            while (itCenarios.hasNext()) {
                System.out.println(itCenarios.next().toString());
            }
        }

    }

}
