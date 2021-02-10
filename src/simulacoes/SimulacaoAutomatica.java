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
 *Esta classe guarda a informação de uma simulação automática.
 */
public class SimulacaoAutomatica extends Simulacao implements ISimulacaoAutomatica{
    
    /**
     * Construtor para uma simulação automática.
     */
    public SimulacaoAutomatica(){
        super();
    }
    
    /**
     * Retorna a informação de uma simulação automática.
     * @return Informação da simulação automática.
     */
    @Override
    public String toString() {
        String info = "\nSimulação Automática:";
        info += "\n  *Pontos de Vida: " + this.getPontosVida() + "\n  *Missão Sucedida: " + this.missaoSucedida()
                + "\n  *Versão: " + this.getVersao();

        if (this.getTrajeto() != null) {
            Iterator<IDivisao> trajeto = this.getTrajeto();
            info += "\n  *Trajeto: \n";
            while (trajeto.hasNext()) {
                info += trajeto.next().toString();
                if (trajeto.hasNext()) {
                    info += " --> ";
                }
            }
        }
        return info;
    }
    
    
    
}
