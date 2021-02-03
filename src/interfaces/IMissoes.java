
package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.NullElementValueException;
import java.util.Iterator;

/**
 * Interface das missões armazenadas.
 */
public interface IMissoes {

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
