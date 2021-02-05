
package simulacoes;

import interfaces.IDivisao;
import interfaces.ISimulacaoManual;
import java.util.Iterator;

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
    
    @Override
    public String toString(){
        String info="\nSimulação Manual: ";
        info+="\n Pontos de Vida: " + this.getPontosVida() + "\n Missão Sucedida: "+this.missaoSucedida()+
                "\n Versão: " + this.getVersao();
        
        Iterator<IDivisao> trajeto=this.getTrajeto();
        info+="\n Trajeto: \n";
        while(trajeto.hasNext()){
            info+=trajeto.next().toString();         
            if(trajeto.hasNext())info+=" --> ";
        }
        return info;
    }
    
}
