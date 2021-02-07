package missoes;

import exceptions.ElementNotFoundException;
import exceptions.InvalidOperationException;
import exceptions.NoPathAvailableException;
import exceptions.NullElementValueException;
import exceptions.VersionAlreadyExistException;
import heap.LinkedHeap;
import interfaces.ICenario;
import interfaces.IDivisao;
import interfaces.IMissao;
import interfaces.ISimulacaoAutomatica;
import java.util.Iterator;
import java.util.Scanner;
import linkedListSentinela.OrderedLinkedList;
import linkedListSentinela.UnorderedLinkedList;
import simulacoes.CustoTrajeto;
import simulacoes.SimulacaoAutomatica;
import simulacoes.SimulacaoManual;

/**
 * Esta classe guarda toda a informação relativa a uma missão.
 */
public class Missao implements IMissao,Comparable<IMissao> {
    private final int DEFAULT_LIFE =100;
    private String codMissao;
    private OrderedLinkedList<ICenario> versoes;

    /**
     * Construtor para uma missao.
     *
     * @param cod Código da missão
     */
    public Missao(String cod) {
        this.codMissao = cod;
        this.versoes = new OrderedLinkedList<>();
    }

      /**
     * Construtor para uma missao.
     *
     * @param cod Código da missão.
     * @param versoes Lista das Versões relativa à missão.
     */
    public Missao(String cod, OrderedLinkedList<ICenario> versoes) {
        this.codMissao = cod;
        this.versoes = versoes;
    }
    
    @Override
    public String getCodMissao() {
        return codMissao;
    }

    /**
     * Introduzir o código da missão.
     *
     * @param codMissao Código da missão
     */
    @Override
    public void setCodMissao(String codMissao) {
        this.codMissao = codMissao;
    }

    /**
     * Retornar iterador dos conjunto de versões associadas à missão.
     *
     * @return Iterador Versões
     */
    @Override
    public Iterator<ICenario> getVersoes() {
        return versoes.iterator();
    }

    @Override
    public void setVersoes(OrderedLinkedList<ICenario> versoes) {
        this.versoes = versoes;
    }

    /**
     * Adicionar uma versão à missão.
     *
     * @param versao Versão a adicionar.
     * @throws VersionAlreadyExistException
     * @throws NullElementValueException
     */
    @Override
    public void adicionarVersão(ICenario versao) throws VersionAlreadyExistException, NullElementValueException {
        if (versao == null) {
            throw new NullElementValueException("The version value is null");
        }
        if (this.versoes.contains(versao)) {
            throw new VersionAlreadyExistException("The version already exist in this mission");
        }

        this.versoes.add(versao);
    }

    /**
     * Remover uma versão,se existir, da lista de versões da missão.
     *
     * @param versao
     * @throws ElementNotFoundException
     * @throws NullElementValueException
     */
    @Override
    public void removerVersao(ICenario versao) throws ElementNotFoundException, NullElementValueException {
        if (versao == null) {
            throw new NullElementValueException("The version value is null");
        }
        this.versoes.remove(versao);
    }

    /**
     * Retornar o número de versões associadas à missão.
     *
     * @return Número de versões da missão. 
     */
    @Override
    public int getNumeroVersoes() {
        return this.versoes.size();
    }

    /**
     * Retornar a informação da missão.
     *
     * @return Informação da missão.
     */
    @Override
    public String toString() {
        String info = "\n Missão: ";
        info += "\n Cód.Missão: " + this.codMissao;
        info += "\n Versões:";
        Iterator<ICenario> it = this.getVersoes();
        while (it.hasNext()) {
            info += "\n" + it.next().toString();
        }
        return info;
    }

    @Override
    public OrderedLinkedList<ICenario> getListVersoes() {
        return this.versoes;
    }

    /**
     * Iniciar uma simulação manual.
     *
     * @return simulação manual.
     */
    @Override
    public SimulacaoManual iniciarSimulacaoManual(int versao, String entrada) throws NullElementValueException,
            ElementNotFoundException, InvalidOperationException {
        ICenario target=new Cenario(versao);
        target=this.versoes.getElement(target);
        
        SimulacaoManual sm = new SimulacaoManual();

        IDivisao divisaoAtual = new Divisao(entrada);
        divisaoAtual = target.getEdificio().getVertex(divisaoAtual);
        IDivisao divisaoIntroduzida = null;

        if (!target.getListaEntradasSaidas().contains(divisaoAtual)) {
            throw new ElementNotFoundException("The entrie!");
        }

        int vidaRestante = DEFAULT_LIFE;
        boolean exit = false;
        boolean temAlvo = false;

        vidaRestante = vidaRestante - divisaoAtual.getDano();
        UnorderedLinkedList<IDivisao> trajeto = new UnorderedLinkedList<>();
        System.out.println(this.mostrarMapa(versao));
        trajeto.addToRear(divisaoAtual);
        while (!exit) {
            Scanner myObj = new Scanner(System.in, "latin1");
            String input;
            System.out.println("---------------------------------------------------");
            System.out.println("\nO alvo encontra-se em: " + target.getAlvo().getDivisao());
            System.out.println("\nDivisão onde você se encontra: " + divisaoAtual.getNome());
            System.out.println("\nVida: " + vidaRestante);
            System.out.println("\nIntoduza a divisão desejada: ");

            input = myObj.nextLine();

            try {
                //Obter ligaçoes entre divisoes
                divisaoIntroduzida = new Divisao(input);
                divisaoIntroduzida = target.getEdificio().getVertex(divisaoIntroduzida);

                if (target.getEdificio().isNeighbor(divisaoIntroduzida, divisaoAtual)) {

                    trajeto.addToRear(divisaoIntroduzida);

                    if (target.getAlvo().getDivisao().equals(divisaoIntroduzida)) {
                        temAlvo = true;
                    }
                    vidaRestante = vidaRestante - divisaoIntroduzida.getDano();//retirar a vida ao Tó 
                    if (vidaRestante > 0) {
                        divisaoAtual = divisaoIntroduzida;

                        if (target.getListaEntradasSaidas().contains(divisaoAtual)) {

                            if (temAlvo) {
                                System.out.println("Missão concluída com sucesso!!!");
                                exit = true;
                            } else {
                                System.out.println("Chegou a uma saída, deseja concluir a missão?\n"
                                        + "S/N ");
                                String sairS = "";

                                while (!sairS.equals("N") && !sairS.equals("S")) {
                                    sairS = (String) myObj.nextLine();
                                }

                                if (sairS.equals("S")) {
                                    exit = true;
                                }
                            }
                        }

                        System.out.println("Vida: " + vidaRestante);
                    } else {
                        System.out.println("Tó Cruz ficou sem vida! Missão falhada");
                        exit = true;
                    }
                }
                sm.setPontosVida(vidaRestante);
                sm.setSucesso(temAlvo && vidaRestante > 0);
                sm.setTrajeto(trajeto);

            } catch (NullElementValueException | ElementNotFoundException e) {
                System.out.println("A divisão não é valida ou não existe!");
            }
        }

        sm.setVersao(versao);
        target.adicionarSimulacaoManual(sm);
        return sm;
    }

    /**
     * Iniciar uma simulação automática.
     *
     * @return simulação automática.
     */
    @Override
    public ISimulacaoAutomatica iniciarSimulacaoAutomatica(int versao) throws InvalidOperationException, 
            NullElementValueException, ElementNotFoundException, NoPathAvailableException {
            SimulacaoAutomatica sa = new SimulacaoAutomatica();

            ICenario target=new Cenario(versao);
            target=this.versoes.getElement(target);
            
            Iterator<IDivisao> entradasSaidas = target.getEntradasSaidas();
            LinkedHeap<CustoTrajeto> custoMinimo = new LinkedHeap<>();

            //Calcular o custo minimo dos caminhos entre todas as entradas/saidas e o alvo
            while (entradasSaidas.hasNext()) {
                IDivisao divisaoAtual = target.getEdificio().getVertex(entradasSaidas.next());
                CustoTrajeto trajetoAtual = new CustoTrajeto(divisaoAtual.getDano()
                        + (int) target.getEdificio().shortestPathWeightCost(divisaoAtual, target.getAlvo().getDivisao()),
                        target.getEdificio().shortestPathWeight(divisaoAtual, target.getAlvo().getDivisao()));
                custoMinimo.addElement(trajetoAtual);
            }

            //Obter caminho do custo minimo
            CustoTrajeto trajetoIdeal = custoMinimo.removeMin();

            UnorderedLinkedList<IDivisao> trajetoEntradaAlvo = new UnorderedLinkedList<>();
            Iterator<IDivisao> iterator = trajetoIdeal.getTrajeto();

            UnorderedLinkedList<IDivisao> trajetoFinal = new UnorderedLinkedList<>();
            while (iterator.hasNext()) {
                IDivisao div = iterator.next();
                trajetoEntradaAlvo.addToRear(div);
                trajetoFinal.addToRear(div);
            }

            trajetoEntradaAlvo.removeLast();

            while (!trajetoEntradaAlvo.isEmpty()) {
                trajetoFinal.addToRear(trajetoEntradaAlvo.removeLast());
            }

            sa.setTrajeto(trajetoFinal);
            sa.setPontosVida(100 - (trajetoIdeal.getCusto()) * 2 + target.getAlvo().getDivisao().getDano());
            sa.setSucesso((sa.getPontosVida() == 100));
            target.adicionarSimulacaoAutomatica(sa); 
         return sa;
    }

    public String mostrarMapa(int versao) throws NullElementValueException, ElementNotFoundException {
        ICenario target = new Cenario(versao);
        target = this.versoes.getElement(target);

        String mapa = "**********************************Edificio**********************************"
                + "\n Divisao Origem --Dano do inimigo da divisão de destino--> Divisao Destino";
        for (int i = 0; i < target.getEdificio().size(); i++) {
            IDivisao origin = target.getEdificio().getVertex(i);
            for (int j = 0; j < target.getEdificio().size(); j++) {
                IDivisao destiny = target.getEdificio().getVertex(j);
                if (target.getEdificio().isNeighbor(origin, destiny)) {
                    mapa += "\n" + origin.getNome() + " -- " + target.getEdificio().getEdgeCost(origin, destiny) + " --> " + destiny.getNome();
                }
            }
        }
        mapa += "\n***************************************************************************";
        return mapa;
    }
    
    /**
     * Compara-se com outro objeto e verica a sua igualdade com base no código de missão.
     * @param obj
     * @return Boolean
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Missao){
            if(this.codMissao.equals(((Missao) obj).codMissao)){
                return true;
            }
        }
        return false;
    }

    /**
     * Compara duas missão pelo código de missão.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(IMissao o) {
        return (this.codMissao.compareTo(o.getCodMissao()));
    }
    
    
}
