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
public class DemoComMenus {

    public static void main(String[] args) {
        IMissoes missoes = new Missoes();

        Menu menu = new Menu(missoes);
        
        try {
            menu.menuPrincipal();
        } catch (NullElementValueException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RepeatedElementException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidWeightValueException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (VersionAlreadyExistException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidOperationException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDocumentException ex) {
            Logger.getLogger(DemoComMenus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int i=0;

    }

}
