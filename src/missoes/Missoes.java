
package missoes;

import exceptions.ElementNotFoundException;
import exceptions.NullElementValueException;
import interfaces.IMissao;
import interfaces.IMissoes;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;

/**
 *Esta classe guarda todas as missões.
 */
public class Missoes implements IMissoes {
    private UnorderedLinkedList<IMissao> missoes;
    private int numMissoes;
    
    /**
     * Construtor para as missões.
     */
    public Missoes(){        
    }

    /**
     * Retorna um iterador com todas as missões.
     * @return Iterador com as missões
     */
    @Override
    public Iterator<IMissao> getMissoes() {
        return this.missoes.iterator();
    }

    /**
     * Retorna o número de missões existentes
     * @return Número de missões.
     */
    @Override
    public int getNumMissoes() {
        return this.missoes.size();
    }
    
    /**
     * Adicionar uma missão à lista de missões.
     * @param missao Missão a ser adicionada
     * @throws NullElementValueException 
     */
    @Override
    public void adicionarMissao(IMissao missao) throws NullElementValueException{
        if(missao==null) throw new NullElementValueException("The mission value is null");
        this.missoes.addToRear(missao);
    }
    
    /**
     * Remover uma missão,se existir, da lista de missões.
     * @param missao Missão a ser removida
     * @return Missão removida
     * @throws ElementNotFoundException
     * @throws NullElementValueException 
     */
    @Override
    public IMissao removerMissao(IMissao missao) throws ElementNotFoundException, NullElementValueException{
        if(missao==null)throw new NullElementValueException("The mission value is null");
        IMissao removed=this.missoes.remove(missao);
        return removed;
    }
    
    
    
}
