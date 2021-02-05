
package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.NullElementValueException;
import java.util.Iterator;
import simulacoes.SimulacaoManual;

/**
 * Interface das missões armazenadas.
 */
public interface IMissoes {
    
    /**
     * Apresentar, para uma missão selecionada, os resultados das simulações manuais realizadas.
     * @return Iterador das simulações.
     */
    public Iterator<SimulacaoManual> apresentarMissoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException;

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
}
