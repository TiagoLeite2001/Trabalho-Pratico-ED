package missoes;

import exceptions.ElementNotFoundException;
import exceptions.NullElementValueException;
import exceptions.VersionAlreadyExistException;
import interfaces.ICenario;
import interfaces.IMissao;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;

/**
 * Esta classe guarda toda a informação relativa a uma missão.
 */
public class Missao implements IMissao {

    private String codMissao;
    private UnorderedLinkedList<ICenario> versoes;

    /**
     * Construtor para uma missao.
     *
     * @param cod Código da missão
     */
    public Missao(String cod) {
        this.codMissao = cod;
        this.versoes = new UnorderedLinkedList<>();
    }

    /**
     * Construtor para uma missao.
     *
     * @param cod Código da missão.
     * @param versoes Lista das Versões relativa à missão.
     */
    public Missao(String cod, UnorderedLinkedList<ICenario> versoes) {
        this.codMissao = cod;
        this.versoes = versoes;
    }

    /**
     * Introduzir o código da missão.
     *
     * @param codMissao Código da missão
     */
    @Override
    public void setCodMissao(String codMissao) {
        this.codMissao = codMissao;
    }

    /**
     * Retornar iterador dos conjunto de versões associadas à missão.
     *
     * @return Iterador Versões
     */
    @Override
    public Iterator<ICenario> getVersoes() {
        return versoes.iterator();
    }

    /**
     * Adicionar uma versão à missão.
     *
     * @param versao Versão a adicionar.
     * @throws VersionAlreadyExistException
     * @throws NullElementValueException
     */
    @Override
    public void adicionarVersão(ICenario versao) throws VersionAlreadyExistException, NullElementValueException {
        if (versao == null) {
            throw new NullElementValueException("The version value is null");
        }
        if (this.versoes.contains(versao)) {
            throw new VersionAlreadyExistException("The version already exist in this mission");
        }

        this.versoes.addToRear(versao);
    }

    /**
     * Remover uma versão,se existir, da lista de versões da missão.
     *
     * @param versao
     * @throws ElementNotFoundException
     * @throws NullElementValueException
     */
    @Override
    public void removerVersao(ICenario versao) throws ElementNotFoundException, NullElementValueException {
        if (versao == null) {
            throw new NullElementValueException("The version value is null");
        }
        this.versoes.remove(versao);
    }

    /**
     * Retornar o número de versões associadas à missão.
     *
     * @return Número de versões da missão. 
     */
    public int getNumeroVersoes() {
        return this.versoes.size();
    }

    /**
     * Retornar a informação da missão.
     *
     * @return Informação da missão.
     */
    @Override
    public String toString() {
        String info = "\n Missão: ";
        info += "\n Cód.Missão: " + this.codMissao;
        info += "\n Versões:";
        Iterator<ICenario> it = this.getVersoes();
        while (it.hasNext()) {
            info += "\n" + it.next().toString();
        }
        return info;
    }


    
    
    
    
    

}
