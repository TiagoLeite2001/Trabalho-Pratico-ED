package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.InvalidOperationException;
import exceptions.NoPathAvailableException;
import exceptions.NullElementValueException;
import exceptions.VersionAlreadyExistException;
import java.util.Iterator;
import linkedListSentinela.OrderedLinkedList;
import linkedListSentinela.UnorderedLinkedList;
import simulacoes.SimulacaoManual;

/**
 * Interface da missao.
 */
public interface IMissao {

    /**
     * Obter o código da missão.
     * @return Código da missão.
     */
    String getCodMissao();
    
    /**
     * Obter um cenário
     * @param versao versão do cenário
     * @return cenario
     * @throws ElementNotFoundException se não existir o cenário
     * @throws NullElementValueException se o input for null
     */
    public ICenario obterCenario(int versao) throws ElementNotFoundException, NullElementValueException;

    /**
     * Introduzir o código da missão.
     *
     * @param codMissao Código da missão
     */
    public void setCodMissao(String codMissao);

    /**
     * Retornar iterador dos conjunto de versões associadas à missão.
     *
     * @return Iterador Versões
     */
    public Iterator<ICenario> getVersoes();

    /**
     * Retornar uma lista de versões associadas à missão.
     *
     * @return OrderedLinkedList<ICenario> Versões
     */
    public OrderedLinkedList<ICenario> getListVersoes();

    /**
     * Adicionar uma versão à missão.
     *
     * @param versao Versão a adicionar.
     * @throws VersionAlreadyExistException
     * @throws NullElementValueException
     */
    public void adicionarVersão(ICenario versao) throws VersionAlreadyExistException, NullElementValueException;

    /**
     * Remover uma versão,se existir, da lista de versões da missão.
     *
     * @param versao
     * @throws ElementNotFoundException
     * @throws NullElementValueException
     */
    public void removerVersao(ICenario versao) throws ElementNotFoundException, NullElementValueException;

    /**
     * Retornar o número de versões associadas à missão.
     *
     * @return Número de versões da missão.
     */
    public int getNumeroVersoes();

    /**
     * Iniciar uma simulação manual.
     *
     * @return simulação manual.
     */
    public SimulacaoManual iniciarSimulacaoManual(int versao, String entrada) throws NullElementValueException,
            ElementNotFoundException, InvalidOperationException;

    /**
     * Iniciar uma simulação automática.
     *
     * @return simulação automática.
     */
    public ISimulacaoAutomatica iniciarSimulacaoAutomatica(int versao) throws InvalidOperationException,
            NullElementValueException, ElementNotFoundException, NoPathAvailableException;

    /**
     * Retornar a informação da missão.
     *
     * @return Informação da missão.
     */
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);
    
    public void setVersoes(OrderedLinkedList<ICenario> versoes);
    
    public String mostrarMapa(int versao) throws NullElementValueException, ElementNotFoundException;
}
