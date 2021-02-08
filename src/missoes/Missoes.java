
package missoes;

import exceptions.ElementNotFoundException;
import exceptions.InvalidDocumentException;
import exceptions.InvalidOperationException;
import exceptions.InvalidWeightValueException;
import exceptions.NoManualSimulationsException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import interfaces.ICenario;
import interfaces.IMissao;
import interfaces.IMissoes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import json.JsonExporter;
import json.JsonImporter;
import linkedListSentinela.OrderedLinkedList;
import org.json.simple.parser.ParseException;
import simulacoes.SimulacaoManual;

/**
 *Esta classe guarda todas as missões.
 */
public class Missoes implements IMissoes {
    private OrderedLinkedList<IMissao> missoes;
    private int numMissoes;
    
    /**
     * Construtor para as missões.
     */
    public Missoes(){ 
        this.missoes = new OrderedLinkedList<>();
    }
    
    /**
     * Validar e importar uma missão de um ficheiro do tipo JSON.
     * @param path Caminho do ficheiro JSON.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NullElementValueException
     * @throws ElementNotFoundException
     * @throws RepeatedElementException
     * @throws InvalidWeightValueException
     * @throws InvalidOperationException
     * @throws ParseException
     * @throws VersionAlreadyExistException
     * @throws InvalidDocumentException 
     */
    @Override
    public void importarMissao(String path) throws IOException, FileNotFoundException, 
            NullElementValueException, ElementNotFoundException, RepeatedElementException, 
            InvalidWeightValueException, InvalidOperationException, ParseException, VersionAlreadyExistException, InvalidDocumentException{
        IMissao missao = JsonImporter.jsonImporter(path);
        
        if(this.missoes.contains(missao)){
            ICenario cenario = missao.getVersoes().next();
            
            if(!this.missoes.getElement(missao).getListVersoes().contains(cenario)){
               this.missoes.getElement(missao).getListVersoes().add(cenario);
            }
            else{
                throw new RepeatedElementException("The version of the mission already exists!");
            }
        }
        else{
            this.missoes.add(missao);
            this.numMissoes++;
        }
    }
    
    /**
     * Apresentar para uma missão selecionada, os resultados das simulações manuais realizadas em cada versão, 
     * por ordem descendente de pontos de vida.
     * @return iterador das simulações manuais.
     */
    @Override
    public Iterator<SimulacaoManual> resultadosSimulacoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException{
        
        if(missao == null || !this.missoes.contains(missao)){
            throw new ElementNotFoundException("The mission introduced is not valid or does not exist!");
        }
        
        OrderedLinkedList<SimulacaoManual> listaSimulacoes = new OrderedLinkedList<>();
        
        Iterator<ICenario> cenarios = missao.getVersoes();
        while(cenarios.hasNext()){
            ICenario cenarioTemp = cenarios.next();
            if(cenarioTemp.getNumSimulacoesManuais()==0){
                return null;
            }
            Iterator<SimulacaoManual> simulacoes = cenarioTemp.getSimulacoesManuais();
            while(simulacoes.hasNext()){
                listaSimulacoes.add(simulacoes.next());
            }
        }
        
        return listaSimulacoes.iterator();
    }
    
    /**
     * Apresentar, para uma missão selecionada, os resultados das simulações manuais realizadas por ordem 
     * decrescente de pontos de vida restante.
     * @return String das simulações manuais.
     */
    @Override
    public String apresentarResultadosSimulacoesManuais(IMissao missao) throws NullElementValueException, ElementNotFoundException{
        
        IMissao mmissao = this.missoes.getElement(missao);
        
        String resultado = "\n***** Simulações manuais da missão: " + mmissao.getCodMissao() +" ******\n";
        if(resultadosSimulacoesManuais(mmissao)==null){
            return resultado+="\n Sem simulações até ao momento.";
        }
        Iterator<SimulacaoManual> simulacoes = resultadosSimulacoesManuais(mmissao);
        while(simulacoes.hasNext()){
            resultado += simulacoes.next().toString();
        }
        
        return resultado;
    }

    /**
     * Apresenta toda a informação das simulacoes automaticas armazenadas de todas as missões.
     *
     * @return Informação das simulacoes automaticas
     */
    @Override
    public String apresentarResultadosSimulacoesAutomaticas() throws NullElementValueException, InvalidOperationException {
        if (this.numMissoes == 0) {
            return "Não há missões armazenadas.";
        }
        String resultado = "\n Simulacoes Automáticas:";
        Iterator<IMissao> missoes = this.missoes.iterator();
        while (missoes.hasNext()){
            IMissao missaoAtual=missoes.next();
            resultado+="\n\n Cod.Missao: "+missaoAtual.getCodMissao();
            Iterator<ICenario> cenarios=missaoAtual.getVersoes();
            while(cenarios.hasNext()){
                ICenario cenario=cenarios.next();
                resultado+="\n Versão: "+cenario.getVersao();
                resultado+="\n" + cenario.getSimulacaoAutomatica().toString();
            }
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
        return this.numMissoes;
    }
    
    /**
     * Adicionar uma missão à lista de missões.
     * @param missao Missão a ser adicionada
     * @throws NullElementValueException 
     */
    @Override
    public void adicionarMissao(IMissao missao) throws NullElementValueException{
        if(missao==null) throw new NullElementValueException("The mission value is null");
        this.missoes.add(missao);
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
    
    /**
     * Apresenta toda a informação das missões armazenadas ordenadas pelo código de missão e as suas versões por ordem decrescente
     * de vida restante resultante da simulação automática.
     * @return Informação das missões
     */
    @Override
    public String apresentarResultadosMissoes()throws NullElementValueException,InvalidOperationException{
        if(this.numMissoes==0){
            return "Não há missões armazenadas.";
        }
        OrderedLinkedList<IMissao> missoesOrdenadas=new OrderedLinkedList<>();
        Iterator<IMissao> it=this.missoes.iterator();
        while(it.hasNext()){
            IMissao missao=it.next();
            OrderedLinkedList<ICenario> listaAtual=missao.getListVersoes();
            OrderedLinkedList<ICenario> listaOrdenada=new OrderedLinkedList<>();
            while(!listaAtual.isEmpty()){
                listaOrdenada.add(listaAtual.removeLast());
            }
            missao.setVersoes(listaOrdenada);
            missoesOrdenadas.add(missao);
        }
        this.missoes=missoesOrdenadas;
        return this.toString();
    } 
    
    /**
     * Obter uma missão com basa no seu código de missão.
     * @param codMissao
     * @return Missao 
     * @throws NullElementValueException
     * @throws ElementNotFoundException 
     */
    @Override
    public IMissao obterMissao(String codMissao) throws NullElementValueException, ElementNotFoundException{
        if(codMissao==null){
            throw new NullElementValueException("Mission code has null value");
        }
        if(!this.missoes.contains(new Missao(codMissao)))
            throw new ElementNotFoundException("Invalid mission code");
        
        return this.missoes.getElement(new Missao(codMissao));
    }  
    
    /**
     * Apresentação dos resultados obtidos em todas as simulações efetuadas.
     * @return Informação das missões
     */
    @Override
    public String toString(){
        String info="\nMissões:";
        Iterator<IMissao> resultados=this.missoes.iterator();
        
        while(resultados.hasNext()){
            IMissao atual=resultados.next();
            info+="\n\n---------------------------"+"Cod.Missão: "+atual.getCodMissao()+"---------------------------------";
            Iterator<ICenario> cenarios=atual.getVersoes();
            while(cenarios.hasNext()){
                info+="\n"+cenarios.next().toString();
            }
        }

        return info;
    }
    
    /**
     * Exportar todas as simulacoes manuais de todas as versoes de uma missao.
     * @param codMissao
     * @return Representação dos ficheiros JSON gerados na exportação
     * @throws NullElementValueException
     * @throws ElementNotFoundException
     * @throws NoManualSimulationsException
     * @throws IOException 
     */
    @Override
    public String exportarSimulacoesManuais(String codMissao) throws NullElementValueException,
            ElementNotFoundException, NoManualSimulationsException, IOException{
        if(codMissao==null){
            throw new NullElementValueException("Mission code has null value");
        }
        
        Missao missaoAtual=new Missao(codMissao);
        if(!this.missoes.contains(missaoAtual))
            throw new ElementNotFoundException("Invalid mission code");
        
        Iterator<ICenario> versoes=this.missoes.getElement(missaoAtual).getVersoes();
        String simulacoesManuais="Ficheiros JSON gerados:";
        while(versoes.hasNext()){
            simulacoesManuais+="\n"+JsonExporter.exportSimulacoesManuais(codMissao, versoes.next());
        }
        return simulacoesManuais;
    }
}
