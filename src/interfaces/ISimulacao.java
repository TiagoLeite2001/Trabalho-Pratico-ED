package interfaces;

import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;
import missoes.Divisao;

/**
 * Interface para uma simulacao de uma missão.
 */
public interface ISimulacao {

    /**
     * Õbter o trajeto percorrido numa simulaçao.
     *
     * @return
     */
    public Iterator<IDivisao> getTrajeto();

    /**
     * Verificar se uma missão foi bem sucedida.
     *
     * @return
     */
    public boolean missaoSucedida();

    /**
     * Obter pontos de vida no final da missão.
     *
     * @return pontos de vida
     */
    public int getPontosVida();

    /**
     * Introduzir pontos de vida.
     *
     * @param pontosVida
     */
    public void setPontosVida(int pontosVida);

    /**
     * Introduzir a versao da missão.
     *
     * @param versão.
     */
    public void setVersao(int versao);

    /**
     * Introduzir se a missão foi bem sucedida ou não.
     *
     * @param sucesso
     */
    public void setSucesso(boolean sucesso);

    /**
     * Trajeto efetuado na simulação.
     *
     * @param trajeto
     */
    public void setTrajeto(UnorderedLinkedList<IDivisao> trajeto);
}
