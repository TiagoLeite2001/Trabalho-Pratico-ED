/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missoes;

import exceptions.NullElementValueException;
import graph.WeightedAdjMatrixDiGraph;
import interfaces.ICenario;
import interfaces.IDivisao;
import interfaces.ISimulacaoAutomatica;
import java.util.Iterator;
import linkedListSentinela.OrderedLinkedList;
import linkedListSentinela.UnorderedLinkedList;
import simulacoes.SimulacaoAutomatica;
import simulacoes.SimulacaoManual;

/**
 *Esta classe guarda a toda a informação relativamente a uma versão de uma missão.
 */
public class Cenario implements ICenario,Comparable<ICenario>{
    private int versao;
    private WeightedAdjMatrixDiGraph<IDivisao> edificio;
    private UnorderedLinkedList<IDivisao> entradasSaidas;
    private Alvo alvo;
    private ISimulacaoAutomatica simulacaoAutomatica;
    private OrderedLinkedList<SimulacaoManual> simulacoesManuais;
    private int numSimulacoesManuais;
    
    /**
     * Construtor de um cenário.
     * @param versao versão de uma missão.
     * @param edificio grafo do edificio do cenário.
     * @param entradasEsaidas lista das entradas e saídas do cenário.
     * @param alvo alvo do cenário.
     */
    public Cenario(int versao, WeightedAdjMatrixDiGraph<IDivisao> edificio,
            UnorderedLinkedList<IDivisao> entradasSaidas, Alvo alvo){
        this.versao = versao;
        this.edificio = edificio;
        this.entradasSaidas = entradasSaidas;
        this.alvo = alvo;
        this.simulacaoAutomatica = new SimulacaoAutomatica();
        this.simulacoesManuais = new OrderedLinkedList<>() ;
        this.numSimulacoesManuais=0;
    }
    
    /**
     * Contrutor de um cenário.
     * @param versao versão de uma missão.
     */
    public Cenario(int versao){
        this.versao=versao;
        this.edificio = new WeightedAdjMatrixDiGraph<>();
        this.entradasSaidas = new UnorderedLinkedList<>();
        this.alvo = null;
        this.simulacaoAutomatica = new SimulacaoAutomatica();
        this.simulacoesManuais = new OrderedLinkedList<>() ;
        this.numSimulacoesManuais=0;
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
    public WeightedAdjMatrixDiGraph<IDivisao> getEdificio() {
        return edificio;
    }
     
    /**
     * Obter a lista das entradas e saidas.
     * @return iterador.
     */
    @Override
    public UnorderedLinkedList<IDivisao> getListaEntradasSaidas() {
        return entradasSaidas;
    }
    
    /**
     * Obter o iterador das entradas e saidas.
     * @return iterador.
     */
    @Override
    public Iterator<IDivisao> getEntradasSaidas() {
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
     * Obter o número de entradas e saídas.
     * @return número de entradas e saídas.
     */
    @Override
    public int getNumeroEntradasSaidas() {
        return this.entradasSaidas.size();
    }

    /**
     * Verificar se dois cenários são iguais.
     *
     * @return boolean
     * @return true se os cenários forem iguais.
     * @return false se os cenários forem diferentes.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Cenario) {
            Cenario temp = (Cenario) obj;
            if (this.getVersao() == temp.getVersao()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obter número de simulações manuais efetuadas neste cenário.
     * @return Número de Simulações
     */
    @Override
    public int getNumSimulacoesManuais() {
        return numSimulacoesManuais;
    }

    @Override
    public String toString() {
        String info = "\n *******Versão: " + this.versao + "*******";
        info += "\n Alvo: " + this.alvo.toString();

        if (this.numSimulacoesManuais != 0) {
            info+="Simulações Manuais:";
            info += "\n  " + this.simulacoesManuais.toString();
        } else {
            info += "\nSimulação Manual: Sem simulações até ao momento.";
        }

        if (this.simulacaoAutomatica.getTrajeto() != null) {
            info += "\n" + this.simulacaoAutomatica.toString();
        } else {
            info += "\nSimulação Automática: Sem simulações até ao momento.";
        }
        return info;
    }

    /**
     * Adicionar uma simulacao manual ao cenário.
     * @param sim simulação manual
     * @throws NullElementValueException
     */
    @Override
    public void adicionarSimulacaoManual(SimulacaoManual sim) throws NullElementValueException {
        this.simulacoesManuais.add(sim);
        this.numSimulacoesManuais++;
    }
    
     /**
     * Adicionar uma simulacao automática ao cenário.
     * @param sim simulação manual
     * @throws NullElementValueException 
     */
    @Override
    public void adicionarSimulacaoAutomatica(SimulacaoAutomatica sim) throws NullElementValueException{
        sim.setVersao(this.versao);
        this.simulacaoAutomatica=sim;
        this.numSimulacoesManuais++;
    }

    /**
     * Compara-se com outro cenário através da vida restante resultante da sua simulação automática.
     * @param o cenário a comparar
     * @return 1 se a sua vida restante é maior que a vida restante do outro cenário
     * @return 0 se a sua vida restante é igual à vida restante do outro cenário.
     * @return 0 se a sua vida restante é menor que a vida restante do outro cenário.
     */
    @Override
    public int compareTo(ICenario o) {
        if(this.simulacaoAutomatica.getPontosVida()<o.getSimulacaoAutomatica().getPontosVida())
            return 1;
        else if(this.simulacaoAutomatica.getPontosVida()==o.getSimulacaoAutomatica().getPontosVida())
            return 0;
        else{
            return -1;
        }
    }

}
