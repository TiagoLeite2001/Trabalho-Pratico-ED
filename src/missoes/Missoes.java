
package missoes;

import exceptions.ElementNotFoundException;
import exceptions.NullElementValueException;
import interfaces.ICenario;
import interfaces.IMissao;
import interfaces.IMissoes;
import java.util.Iterator;
import linkedListSentinela.OrderedLinkedList;
import linkedListSentinela.UnorderedLinkedList;
import simulacoes.SimulacaoManual;

/**
 *Esta classe guarda todas as missões.
 */
public class Missoes implements IMissoes {
    private UnorderedLinkedList<IMissao> missoes;
    private int numMissoes;
    
    /**
     * Construtor para as missões.
     */
    public Missoes(){ 
        this.missoes = new UnorderedLinkedList<>();
    }
    
    /**
     * Apresentar, para uma missão selecionada, os resultados das simulações manuais realizadas.
     * @return iterador das simulações manuais.
     */
    @Override
    public Iterator<SimulacaoManual> apresentarMissoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException{
        
        if(missao == null || !this.missoes.contains(missao)){
            throw new ElementNotFoundException("The mission introduced is not valid or does not exist!");
        }
        
        OrderedLinkedList<SimulacaoManual> listaSimulacoes = new OrderedLinkedList<>();
        
        Iterator<ICenario> cenarios = missao.getVersoes();
        while(cenarios.hasNext()){
            ICenario cenarioTemp = cenarios.next();
            
            Iterator<SimulacaoManual> simulacoes = cenarioTemp.getSimulacoesManuais();
            while(simulacoes.hasNext()){
                listaSimulacoes.add(simulacoes.next());
            }
        }
        
        return listaSimulacoes.iterator();
    }
    
    /**
     * Apresentar, para uma missão selecionada, os resultados das simulações manuais realizadas.
     * @return String das simulações manuais.
     */
    @Override
    public String apresentarResultadosSimulacoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException{
        
        String resultado = " ***** SIMULAÇÕES MANUAIS DA MISSÃO: " + missao.getCodMissao() +" ******\n";
        
        Iterator<SimulacaoManual> simulacoes = apresentarMissoesManuais(missao);
        while(simulacoes.hasNext()){
            resultado += simulacoes.next().toString();
        }
        
        return resultado;
    }

    /**
     * Retorna um iterador com todas as missões.
     * @return Iterador com as missões
     */
    @Override
    public Iterator<IMissao> getMissoes() {
        return this.missoes.iterator();
    }

    /**
     * Retorna o número de missões existentes
     * @return Número de missões.
     */
    @Override
    public int getNumMissoes() {
        return this.missoes.size();
    }
    
    /**
     * Adicionar uma missão à lista de missões.
     * @param missao Missão a ser adicionada
     * @throws NullElementValueException 
     */
    @Override
    public void adicionarMissao(IMissao missao) throws NullElementValueException{
        if(missao==null) throw new NullElementValueException("The mission value is null");
        this.missoes.addToRear(missao);
    }
    
    /**
     * Remover uma missão,se existir, da lista de missões.
     * @param missao Missão a ser removida
     * @return Missão removida
     * @throws ElementNotFoundException
     * @throws NullElementValueException 
     */
    @Override
    public IMissao removerMissao(IMissao missao) throws ElementNotFoundException, NullElementValueException{
        if(missao==null)throw new NullElementValueException("The mission value is null");
        IMissao removed=this.missoes.remove(missao);
        return removed;
    }
    
    
    
}
