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
import interfaces.ICenario;
import interfaces.IMissao;
import interfaces.ISimulacaoAutomatica;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JsonExporter;
import json.JsonImporter;
import missoes.Divisao;
import org.json.simple.parser.ParseException;
import simulacoes.SimulacaoManual;

/**
 *
 * @author tiago
 */
public class Demo {

    public static void main(String[] args)throws ElementNotFoundException,NullElementValueException, NoManualSimulationsException {
        
        try {
            JsonImporter importer = new JsonImporter();
            IMissao m;
            System.out.println("asfassadfasfsadfgasdfsFASSDFASFDASDFSADF");
            m = importer.jsonImporter("Mapas/exemplo.json");
            //ISimulacaoAutomatica t=m.getVersoes().next().iniciarSimulacaoAutomatica();
            //System.out.println(t);
            
            SimulacaoManual sm = m.getVersoes().next().iniciarSimulacaoManual("Heliporto");
            SimulacaoManual sm2 = m.getVersoes().next().iniciarSimulacaoManual("Garagem");
            
            JsonExporter.exportSimulacoesManuais(m.getCodMissao(), m.getVersoes().next());
            
        } catch (IOException | ParseException |InvalidDocumentException |
                RepeatedElementException | InvalidWeightValueException | InvalidOperationException | VersionAlreadyExistException ex) {
            System.out.println(ex);}  

    }
}

