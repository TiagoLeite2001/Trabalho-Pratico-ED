/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import graph.WeightedAdjMatrixGraph;
import java.util.Iterator;
import missoes.Alvo;
import missoes.Divisao;
import simulacoes.SimulacaoManual;


/**
 *
 * @author JoaoLopes 8190221
 */
public interface ICenario {
    
    /**
     * Obter a versão do cenário.
     * @return 
     */
    public int getVersao();

    public WeightedAdjMatrixGraph<Divisao> getEdificio();

    public Iterator<Divisao> getEntradasSaidas();

    /**
     * 
     * @return 
     */
    public Alvo getAlvo();

    public ISimulacaoAutomatica getSimulacaoAutomatica();

    public Iterator<SimulacaoManual> getSimulacoesManuais();
}
