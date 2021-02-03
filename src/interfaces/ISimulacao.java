
package interfaces;

import java.util.Iterator;
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
    public Iterator<Divisao> getTrajeto();

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
}
