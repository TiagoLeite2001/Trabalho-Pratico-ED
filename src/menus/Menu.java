/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

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
import missoes.Missoes;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tiago
 */
public class Menu {
    public Menu(){ }
    
    public static void menuPrincipal() throws NullElementValueException, RepeatedElementException, 
            InvalidWeightValueException, ElementNotFoundException, IOException, FileNotFoundException, 
            ParseException, VersionAlreadyExistException, InvalidOperationException, InvalidDocumentException{
                System.out.println("\nTrabalho Prático de Estrutura de Dados"
                + "\n     Realizado por: "
                + "\n     João Lopes - 8190228"
                + "\n     Tiago Leite - 8190338\n");
        
        Scanner inputS = new Scanner(System.in, "latin1");
        int input = -1;
        
        IMissoes missoes = new Missoes();
        
        while(input != 0){
            
            System.out.println("\n"
                    + "\nImprobable Mission Force - Mission simulator"
                    + "\n"
                    + "\nBem vindo!!"
                    + "\n"
                    + "\n 1 - Importar um mapa de uma missão."
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
                    adicionarMissao(missoes);
                    break;
                case (2):
                    removerMissao();
                    break;
                case (3):
                    simulacoes(missoes);
                case (4):
                    intrucoes();
            }

        }
    }
    
    
    private static void adicionarMissao(IMissoes missoes) throws IOException, NullElementValueException, 
            RepeatedElementException, ElementNotFoundException, InvalidWeightValueException, 
            InvalidOperationException, FileNotFoundException, ParseException, 
            VersionAlreadyExistException, InvalidDocumentException{
        
        Scanner inputS = new Scanner(System.in, "latin1");
        
        System.out.println("\n*Adicionar nova missão*"
                + "\n"
                + "\nIntroduza o caminho(path) onde se encontra o mapa a adicionar."
                + "\n Exempo: Mapas/exemplo.json");
        
        String path = inputS.nextLine();
        
       // missoes.importarMissao(path);
        
        System.out.println("\n Missão importada com sucesso!");
        
    }
    
    private static void removerMissao(){
        
    }
    
    private static void intrucoes(){
        
    }
    
    private static void simulacoes(IMissoes missoes){
        System.out.println("\nEscolher tipo de simulação e o cenário da missão desejado");
        
        System.out.println("\nCenários disponíveis:");
        
        Iterator<IMissao> itMissoes = missoes.getMissoes();
        
        while(itMissoes.hasNext()){
            
            IMissao missaoTemp = itMissoes.next();
            
            Iterator<ICenario> itCenarios = missaoTemp.getVersoes();
            
        }
        
    }
    
}
