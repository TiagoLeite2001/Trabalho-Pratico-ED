
package simulacoes;

import interfaces.IDivisao;
import interfaces.ISimulacaoManual;
import java.util.Iterator;

/**
 * Esta classe guarda a informação de uma simulação manual.
 */
public class SimulacaoManual extends Simulacao implements ISimulacaoManual,Comparable<SimulacaoManual> {

    /**
     * Construtor para uma simulação manual.
     */
    public SimulacaoManual(){
        super();
    }
    
 /**
  * Compara duas simulações manuais. 
  * @param o Objeto a ser comparado.
  * @return -1 Se os pontos de vida da atual forem maiores que o objeto a ser comparado.
  * @return 0 Se os pontos de vida da atual forem iguais que o objeto a ser comparado.
  * @return 1 Se os pontos de vida da atual forem menores que o objeto a ser comparado.
  */
    @Override
    public int compareTo(SimulacaoManual o) {
        if (this.getPontosVida() > o.getPontosVida()) {
            return -1;
        } else if (this.getPontosVida() == o.getPontosVida()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Retorna a informção da simulação manual.
     * @return Informação da simulação manual.
     */
    @Override
    public String toString() {
        String info = "\nSimulação Manual: ";
        info += "\n  *Pontos de Vida: " + this.getPontosVida() + "\n  *Missão Sucedida: " + this.missaoSucedida()
                + "\n  *Versão: " + this.getVersao();

        Iterator<IDivisao> trajeto = this.getTrajeto();
        info += "\n Trajeto: \n";
        while (trajeto.hasNext()) {
            info += trajeto.next().toString();
            if (trajeto.hasNext()) {
                info += " --> ";
            }
        }
        return info;
    }

}
