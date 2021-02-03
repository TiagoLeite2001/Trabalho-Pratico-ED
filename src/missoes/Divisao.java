package missoes;

import exceptions.NullElementValueException;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;

/**
 *
 * Esta classe guarda a informação de uma divisão do edificio.
 */
public class Divisao {
    private String nome;
    private UnorderedLinkedList<Inimigo> inimigos;
    
    /**
     * Contrutor para uma divisao.
     * @param nome 
     */
    public Divisao(String nome){
        this.nome=nome;
        this.inimigos=new UnorderedLinkedList<>();
    }

    /**
     * Obter o nome do inimigo.
     * @return nome do inimigo
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Obter o iterator dos inimigos existentes na divisao.
     * @return iterator
     */
    public Iterator<Inimigo> getInimigos() {
        return this.inimigos.iterator();
    }
    
    /**
     * Adicionar um inimigo na divisao.
     * @param inimigo inimigo a ser adicionado
     * @throws NullElementValueException 
     */
    public void adicionarInimigo(Inimigo inimigo) throws NullElementValueException{
        if(inimigo==null) throw new NullElementValueException("The element is null!");
        if(!this.inimigos.contains(inimigo)){
            this.inimigos.addToRear(inimigo);
        }
    }
 
}
