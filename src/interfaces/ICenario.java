/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import graph.WeightedAdjMatrixDiGraph;
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
     * @return versao.
     */
    public int getVersao();
    
    /**
     * Obter o grafo do edificio.
     * @return edificio.
     */
    public WeightedAdjMatrixDiGraph<IDivisao> getEdificio();
    
    /**
     * Obter o iterador das entradas e saidas.
     * @return iterador.
     */
    public Iterator<IDivisao> getEntradasSaidas();

    /**
     * Obter o alvo do cenário.
     * @return alvo.
     */
    public Alvo getAlvo();
    
    /**
     * Obter a simulação automática.
     * @return simulação automática.
     */
    public ISimulacaoAutomatica getSimulacaoAutomatica();
    
    /**
     * Obter as simulações manuais.
     * @return simulações manuais.
     */
    public Iterator<SimulacaoManual> getSimulacoesManuais();
    
    /**
     * Iniciar uma simulação manual.
     * @return simulação manual.
     */
    public SimulacaoManual iniciarSimulacaoManual();
    
    /**
     * Iniciar uma simulação automática.
     * @return simulação automática.
     */
    public ISimulacaoAutomatica iniciarSimulacaoAutomatica();
    
    /**
     * Verificar se dois cenários são iguais.
     *
     * @return boolean
     * @return true se os cenários forem iguais.
     * @return false se os cenários forem diferentes.
     */
    @Override
    public boolean equals(Object obj);
    
    /**
     * Obter o número de entradas e saídas.
     * @return número de entradas e saídas.
     */
    public int getNumeroEntradasSaidas();
}
