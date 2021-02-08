package demo;

import exceptions.ElementNotFoundException;
import exceptions.InvalidDocumentException;
import exceptions.InvalidOperationException;
import exceptions.InvalidWeightValueException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import java.io.IOException;
import menu.Menu;
import org.json.simple.parser.ParseException;

/**
 *Demo do projeto.
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
