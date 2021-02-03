package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.NullElementValueException;
import exceptions.VersionAlreadyExistException;
import java.util.Iterator;

/**
 * Interface da missao.
 */
public interface IMissao {

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
     * Retornar a informação da missão.
     *
     * @return Informação da missão.
     */
    public String toString();

}
