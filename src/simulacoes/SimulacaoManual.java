
package simulacoes;

import interfaces.ISimulacaoManual;

/**
 * 
 */
public class SimulacaoManual extends Simulacao implements ISimulacaoManual,Comparable<SimulacaoManual> {

    @Override
    public int compareTo(SimulacaoManual o) {
        if(this.getPontosVida()>o.getPontosVida()){
            return -1;
        }
        else if(this.getPontosVida()==o.getPontosVida()){
            return 0;
        }
        else{
            return 1;
        }
    }
}
