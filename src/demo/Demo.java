
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
import interfaces.IMissao;
import interfaces.IMissoes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import missoes.Missoes;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tiago
 */
public class Demo {

    public static void main(String[] args)throws ElementNotFoundException,NullElementValueException, NoManualSimulationsException, NoPathAvailableException, IOException, FileNotFoundException, RepeatedElementException, InvalidWeightValueException, InvalidOperationException, ParseException, VersionAlreadyExistException, InvalidDocumentException {
        
        IMissoes missoes=new Missoes();
        
        //Importacao de mapas
        missoes.importarMissao("Mapas/mapaEnunciado.json");
        missoes.importarMissao("Mapas/mapa2.json");
        missoes.importarMissao("Mapas/mapa3.json");
        missoes.importarMissao("Mapas/mapa4.json");
        missoes.importarMissao("Mapas/mapa5.json");
        missoes.importarMissao("Mapas/mapa6.json");
        
        //Executar simulacoes automaticas de cada versao de uma missao
        missoes.obterMissao("pata de coelho").iniciarSimulacaoAutomatica(1);
        missoes.obterMissao("missao impossivel 1").iniciarSimulacaoAutomatica(1);
        missoes.obterMissao("missao impossivel 1").iniciarSimulacaoAutomatica(2);
        missoes.obterMissao("missao impossivel 1").iniciarSimulacaoAutomatica(3);
        missoes.obterMissao("missao impossivel 2").iniciarSimulacaoAutomatica(1);
        missoes.obterMissao("missao impossivel 2").iniciarSimulacaoAutomatica(2);
        
        //Executar simulacoes manuais de cada em cada versao de uma missao
//        missoes.obterMissao("pata de coelho").iniciarSimulacaoManual(1, "");
//        missoes.obterMissao("missao impossivel 1").iniciarSimulacaoManual(1, "");
//        missoes.obterMissao("missao impossivel 1").iniciarSimulacaoManual(2, "");
//        missoes.obterMissao("missao impossivel 1").iniciarSimulacaoManual(3, "");
//        missoes.obterMissao("missao impossivel 2").iniciarSimulacaoManual(1, "");
//        missoes.obterMissao("missao impossivel 2").iniciarSimulacaoManual(2, "");

        System.out.println(missoes.apresentarMissoes());
    
    }
}

