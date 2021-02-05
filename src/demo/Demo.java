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
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JsonImporter;
import menus.Menu;
import missoes.Missoes;
import org.json.simple.parser.ParseException;
import simulacoes.SimulacaoManual;

/**
 *
 * @author tiago
 */
public class Demo {

    public static void main(String[] args)throws ElementNotFoundException,NullElementValueException, NoManualSimulationsException, NoPathAvailableException {
        
        try {
            Menu.menuPrincipal();
        } catch (RepeatedElementException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidWeightValueException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VersionAlreadyExistException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidOperationException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDocumentException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

