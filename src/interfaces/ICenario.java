/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import exceptions.NullElementValueException;
import graph.WeightedAdjMatrixDiGraph;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;
import missoes.Alvo;
import simulacoes.SimulacaoAutomatica;
import simulacoes.SimulacaoManual;

/**
 *Interface de um cenário
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
     *Verifica se dois objetos são iguais.
     * @param obj Objeto para ser comparado.
     * @return boolean
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Obter o número de entradas e saídas.
     * @return número de entradas e saídas.
     */
    public int getNumeroEntradasSaidas();

    /**
     * Obter número de simulações manuais efetuadas neste cenário.
     * @return Número de Simulações
     */
    public int getNumSimulacoesManuais();

    /**
     * Obter lista entradas e saidas.
     * @return Lista entradas e saidas
     */
    public UnorderedLinkedList<IDivisao> getListaEntradasSaidas();

    /**
     * Adicionar uma simulacao manual ao cenário.
     * @param sim simulação manual
     */
    public void adicionarSimulacaoManual(SimulacaoManual sim) throws NullElementValueException;

    /**
     * Adicionar uma simulacao automática ao cenário.
     * @param sim simulação manual
     * @throws NullElementValueException
     */
     public void adicionarSimulacaoAutomatica(SimulacaoAutomatica sim) throws NullElementValueException;
}
