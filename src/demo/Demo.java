/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import exceptions.ElementNotFoundException;
import exceptions.InvalidDocumentException;
import exceptions.InvalidOperationException;
import exceptions.InvalidWeightValueException;
import exceptions.NoManualSimulationsException;
import exceptions.NoPathAvailableException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import interfaces.IMissoes;
import java.io.IOException;
import java.util.Scanner;
import json.JsonImporter;
import missoes.Missoes;
import org.json.simple.parser.ParseException;
import simulacoes.SimulacaoManual;

/**
 *
 * @author tiago
 */
public class Demo {

    public static void main(String[] args)throws ElementNotFoundException,NullElementValueException, NoManualSimulationsException, NoPathAvailableException {
        
        System.out.println("\nTrabalho Prático de Estrutura de Dados"
                + "\n     Realizado por: "
                + "\n     João Lopes - 8190228"
                + "\n     Tiago Leite - 8190338\n");
        
        Scanner myObj = new Scanner(System.in, "latin1");
        
    }
}

