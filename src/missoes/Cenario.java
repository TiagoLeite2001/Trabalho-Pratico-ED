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
        this.simulacaoAutomatica = null;
        this.simulacoesManuais = null;
    }
    
    /**
     * Obter a versão do cenário.
     * @return 
     */
    @Override
    public int getVersao() {
        return versao;
    }

    @Override
    public WeightedAdjMatrixGraph<Divisao> getEdificio() {
        return edificio;
    }

    @Override
    public Iterator<Divisao> getEntradasSaidas() {
        return entradasSaidas.iterator();
    }

    /**
     * 
     * @return 
     */
    @Override
    public Alvo getAlvo() {
        return alvo;
    }

    @Override
    public ISimulacaoAutomatica getSimulacaoAutomatica() {
        return simulacaoAutomatica;
    }

    @Override
    public Iterator<SimulacaoManual> getSimulacoesManuais() {
        return simulacoesManuais.iterator();
    }
}
