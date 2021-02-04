package missoes;

import exceptions.NullElementValueException;
import interfaces.IDivisao;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;

/**
 *
 * Esta classe guarda a informação de uma divisão do edificio.
 */
public class Divisao implements IDivisao{

    private final String nome;
    private final UnorderedLinkedList<Inimigo> inimigos;
    private int dano;

    /**
     * Contrutor para uma divisao.
     *
     * @param nome
     */
    public Divisao(String nome) {
        this.nome = nome;
        this.inimigos = new UnorderedLinkedList<>();
        this.dano = 0;
    }
    
    /**
     * Obter o dano total dos inimigos nesta divisão.
     * @return dano total
     */
    @Override
    public int getDano() {
        return dano;
    }
    
    /**
     * Introduzir dano total dos inimigos nesta divisão.
     * @param dano total
     */
    @Override
    public void setDano(int dano) {
        this.dano = dano;
    }
    
    
    
    /**
     * Obter o nome do inimigo.
     *
     * @return nome do inimigo
     */
    @Override
    public String getNome() {
        return this.nome;
    }

    /**
     * Obter o iterator dos inimigos existentes na divisao.
     *
     * @return iterator
     */
    @Override
    public Iterator<Inimigo> getInimigos() {
        return this.inimigos.iterator();
    }

    /**
     * Adicionar um inimigo na divisao.
     *
     * @param inimigo inimigo a ser adicionado
     * @throws NullElementValueException
     */
    @Override
    public void adicionarInimigo(Inimigo inimigo) throws NullElementValueException {
        if (inimigo == null) {
            throw new NullElementValueException("The element is null!");
        }
        if (!this.inimigos.contains(inimigo)) {
            this.inimigos.addToRear(inimigo);
        }
    }

    /**
     * Verificar se duas divisões são iguais.
     *
     * @return boolean
     * @return true se as divisões forem iguais.
     * @return false se as divisões forem diferentes.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Divisao) {
            Divisao temp = (Divisao) obj;
            if (this.getNome().equals(temp.getNome())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obter a informação do edificio.
     *
     * @return informação do edificio
     */
    @Override
    public String toString() {
        String info = "";
        info += this.nome;
        return info;
    }
}
