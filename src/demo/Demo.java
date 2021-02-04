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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.JsonImporter;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tiago
 */
public class Demo {
    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException, NullElementValueException, ElementNotFoundException, InvalidWeightValueException, InvalidOperationException {
        JsonImporter importer = new JsonImporter();
        IMissao m = null;
        try {
            m = importer.jsonImporter("Mapas/exemplo.json");
        } catch (RepeatedElementException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VersionAlreadyExistException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println( m.toString());
        int i=9;

    }
}
