
package simulacoes;

import interfaces.ISimulacao;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;
import missoes.Divisao;

/**
 *Esta classe guarda os dados realtivos a uma simulação manual relativa a uma versão de
 *uma missão.
 */
public abstract class Simulacao implements ISimulacao {
    private UnorderedLinkedList<Divisao> trajeto;
    private boolean sucesso;
    private int pontosVida;
    private int versao;
    
    /**
     * Construtor para uma simulação.
     */
    public Simulacao(){
    }
    
    /**
     * Obter o trajeto percorrido numa simulação.
     * @return Iterator para o trajeto
     */
    @Override
    public Iterator<Divisao> getTrajeto(){
        return this.trajeto.iterator();
    }
    
    /**
     * Verificar se uma missão foi bem sucedida.
     * @return true se a missão foi cumprida com sucesso 
     * @return false se a missão não foi cumprida com sucesso
     */
    @Override
    public boolean missaoSucedida(){
        return this.sucesso;
    }
    
    /**
     * Obter pontos de vida no final da missão.
     * @return pontos de vida 
     */
    @Override
    public int getPontosVida(){
        return this.pontosVida;
    }

    /**
     * Introduzir pontos de vida.
     * @param pontosVida 
     */
    @Override
    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    /**
     * Introduzir se a missão foi bem sucedida ou não.
     * @param sucesso 
     */
    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
    
    /**
     * Introduzir a versao da missão.
     * @param versão. 
     */
    @Override
    public void setVersao(int versao) {
        this.versao = versao;
    }

}
