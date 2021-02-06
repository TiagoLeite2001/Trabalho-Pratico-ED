
package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.InvalidDocumentException;
import exceptions.InvalidOperationException;
import exceptions.InvalidWeightValueException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.parser.ParseException;
import simulacoes.SimulacaoManual;

/**
 * Interface das missões armazenadas.
 */
public interface IMissoes {
    
    public void importarMissao(String path) throws IOException, FileNotFoundException, 
            NullElementValueException, ElementNotFoundException, RepeatedElementException, 
            InvalidWeightValueException, InvalidOperationException, ParseException, VersionAlreadyExistException, InvalidDocumentException;
    
    /**
     * Apresentar, para uma missão selecionada, os resultados das simulações manuais realizadas.
     * @return Iterador das simulações.
     */
    public Iterator<SimulacaoManual> resultadosSimulacoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException;

     /**
     * Apresentar, para uma missão selecionada, os resultados das simulações manuais realizadas.
     * @return String das simulações manuais.
     */
    public String apresentarResultadosSimulacoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException;
    
    /**
     * Retorna um iterador com todas as missões.
     *
     * @return Iterador com as missões
     */
    public Iterator<IMissao> getMissoes();

    /**
     * Retorna o número de missões existentes
     *
     * @return Número de missões.
     */
    public int getNumMissoes();

    /**
     * Adicionar uma missão à lista de missões.
     *
     * @param missao Missão a ser adicionada
     * @throws NullElementValueException
     */
    public void adicionarMissao(IMissao missao) throws NullElementValueException;

    /**
     * Remover uma missão,se existir, da lista de missões.
     *
     * @param missao Missão a ser removida
     * @return Missão removida
     * @throws ElementNotFoundException
     * @throws NullElementValueException
     */
    public IMissao removerMissao(IMissao missao) throws ElementNotFoundException, NullElementValueException;
    
        /**
     * Obter uma missão com basa no seu código de missão.
     * @param codMissao
     * @return Missao 
     * @throws NullElementValueException
     * @throws ElementNotFoundException 
     */
    public IMissao obterMissao(String codMissao) throws NullElementValueException, ElementNotFoundException;
    
     /**
     * Apresenta as missões armazenadas ordenadas pelo código de missão e as suas versões por ordem decrescente
     * de vida restante resultante da simulação automática.
     * @return Informação das missões
     */
    public String apresentarMissoes()throws NullElementValueException,InvalidOperationException;
}
