/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacoes;

import interfaces.IDivisao;
import interfaces.ISimulacaoAutomatica;
import java.util.Iterator;

/**
 *
 * @author tiago
 */
public class SimulacaoAutomatica extends Simulacao implements ISimulacaoAutomatica{
    
    public SimulacaoAutomatica(){
        super();
    }
    
    @Override
    public String toString(){
        String info="\nSimulação Automática:";
        info+="\n  *Pontos de Vida: " + this.getPontosVida() + "\n  *Missão Sucedida: "+this.missaoSucedida()+
                "\n  *Versão: " + this.getVersao();
        
        Iterator<IDivisao> trajeto=this.getTrajeto();
        info+="\n  *Trajeto: \n";
        while(trajeto.hasNext()){
            info+=trajeto.next().toString();         
            if(trajeto.hasNext())info+=" --> ";
        }
        return info;
    }
    
    
    
}
