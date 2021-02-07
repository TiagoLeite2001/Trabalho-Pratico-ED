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
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import interfaces.IMissao;
import interfaces.IMissoes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Menu;
import missoes.Missoes;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tiago
 */
public class DemoFinal {

    public static void main(String[] args) {

        Menu menu = new Menu();
        
        try {
            menu.menuPrincipal();
        } catch (NullElementValueException | RepeatedElementException | 
                InvalidWeightValueException | ElementNotFoundException | 
                IOException | ParseException | VersionAlreadyExistException | 
                InvalidOperationException | InvalidDocumentException ex) {
        }
    }

}
