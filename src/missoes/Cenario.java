/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missoes;

import graph.WeightedAdjMatrixGraph;
import interfaces.ICenario;
import interfaces.ISimulacaoAutomatica;
import interfaces.ISimulacaoManual;
import java.util.Iterator;
import linkedListSentinela.OrderedLinkedList;
import linkedListSentinela.UnorderedLinkedList;
import simulacoes.SimulacaoAutomatica;
import simulacoes.SimulacaoManual;

/**
 *Esta classe guarda a toda a informação de uma versão de uma missão.
 */
public class Cenario implements ICenario{
    private int versao;
    private WeightedAdjMatrixGraph<Divisao> edificio;
    private UnorderedLinkedList<Divisao> entradasSaidas;
    private Alvo alvo;
    private ISimulacaoAutomatica simulacaoAutomatica;
    private OrderedLinkedList<SimulacaoManual> simulacoesManuais;
    
    /**
     * Construtor de um cenário.
     * @param versao versão de uma missão.
     * @param edificio grafo do edificio do cenário.
     * @param entradasEsaidas lista das entradas e saídas do cenário.
     * @param alvo alvo do cenário.
     */
    public Cenario(int versao, WeightedAdjMatrixGraph<Divisao> edificio,
            UnorderedLinkedList<Divisao> entradasSaidas, Alvo alvo){
        this.versao = versao;
        this.edificio = edificio;
        this.entradasSaidas = entradasSaidas;
        this.alvo = alvo;
        this.simulacaoAutomatica = new SimulacaoAutomatica();
        this.simulacoesManuais = new OrderedLinkedList<>() ;
    }
    
    /**
     * Obter a versão do cenário.
     * @return versao.
     */
    @Override
    public int getVersao() {
        return versao;
    }
    
    /**
     * Obter o grafo do edificio.
     * @return edificio.
     */
    @Override
    public WeightedAdjMatrixGraph<Divisao> getEdificio() {
        return edificio;
    }
    
    /**
     * Obter o iterador das entradas e saidas.
     * @return iterador.
     */
    @Override
    public Iterator<Divisao> getEntradasSaidas() {
        return entradasSaidas.iterator();
    }

    /**
     * Obter o alvo do cenário.
     * @return alvo.
     */
    @Override
    public Alvo getAlvo() {
        return alvo;
    }
    
    /**
     * Obter a simulação automática.
     * @return simulação automática.
     */
    @Override
    public ISimulacaoAutomatica getSimulacaoAutomatica() {
        return simulacaoAutomatica;
    }
    
    /**
     * Obter as simulações manuais.
     * @return simulações manuais.
     */
    @Override
    public Iterator<SimulacaoManual> getSimulacoesManuais() {
        return simulacoesManuais.iterator();
    }
    
    /**
     * Iniciar uma simulação manual.
     * @return simulação manual.
     */
    @Override
    public SimulacaoManual iniciarSimulacaoManual(){
        SimulacaoManual sm = new SimulacaoManual();
        
        sm.setVersao(this.versao);
        
        return sm;
    }
    
    /**
     * Iniciar uma simulação automática.
     * @return simulação automática.
     */
    @Override
    public ISimulacaoAutomatica iniciarSimulacaoAutomatica(){
        ISimulacaoAutomatica sm = new SimulacaoAutomatica();
        
        sm.setVersao(this.versao);
        
        return sm;
    }
}
