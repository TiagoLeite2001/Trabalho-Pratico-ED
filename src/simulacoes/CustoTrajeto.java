/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacoes;

import interfaces.IDivisao;
import java.util.Iterator;

/**
 *Esta classe guarda a informção do custo de um camminho entre uma entrada e um alvo
 * assim como o trajeto. 
 */
public class CustoTrajeto implements Comparable<CustoTrajeto>{
    private int custo;
    private Iterator<IDivisao> trajeto;
    
    /**
     * Contrutor da classe CustoTrajeto.
     * @param custo custo do trajeto.
     * @param trajeto iterador do trajeto.
     */
    public CustoTrajeto(int custo, Iterator<IDivisao> trajeto){
        this.custo = custo;
        this.trajeto = trajeto;
    }
    
    /**
     * Obter o custo do trajeto.
     * @return custo.
     */
    public int getCusto() {
        return custo;
    }
    
    /**
     * Obter o iterador do trajeto.
     * @return iterator.
     */
    public Iterator<IDivisao> getTrajeto() {
        return trajeto;
    }
    
    /**
     * Compara duas classes CustoTrajeto.
     * @param o CustoTrajeto
     * @return 1 se o custo deste trajeto for maior do que o recebido.
     * @return 0 se o custo deste trajeto for igual ao recebido.
     * @return -1 se o custo deste trajeto for menor do que o recebido.
     */
    @Override
    public int compareTo(CustoTrajeto o) {
        if(this.custo > o.getCusto()){
            return 1;
        }
        if(this.custo < o.getCusto()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
