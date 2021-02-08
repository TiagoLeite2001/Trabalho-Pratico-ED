package interfaces;

import exceptions.NullElementValueException;
import java.util.Iterator;
import missoes.Inimigo;

/**
 *Interface para uma divisao do edificio.
 */
public interface IDivisao {
    
     /**
     * Obter o dano total dos inimigos nesta divisão.
     * @return dano total
     */
    public int getDano();
    
    /**
     * Introduzir dano total dos inimigos nesta divisão.
     * @param dano total
     */
    public void setDano(int dano);

    /**
     * Obter o nome do inimigo.
     * @return nome do inimigo
     */
    public String getNome();

    /**
     * Obter o iterator dos inimigos existentes na divisao.
     * @return iterator
     */
    public Iterator<Inimigo> getInimigos();

    /**
     * Adicionar um inimigo na divisao.
     * @param inimigo inimigo a ser adicionado
     * @throws NullElementValueException
     */
    public void adicionarInimigo(Inimigo inimigo) throws NullElementValueException;

    /**
     * Verificar se duas divisões são iguais.
     * @return boolean
     */
    public boolean equals(Object obj);

    /**
     * Obter a informação do edificio.
     * @return informação do edificio
     */
    public String toString();
}
