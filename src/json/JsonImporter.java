/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import exceptions.ElementNotFoundException;
import exceptions.InvalidOperationException;
import exceptions.InvalidWeightValueException;
import exceptions.NullElementValueException;
import exceptions.RepeatedElementException;
import exceptions.VersionAlreadyExistException;
import graph.WeightedAdjMatrixDiGraph;
import interfaces.ICenario;
import interfaces.IDivisao;
import interfaces.IMissao;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import linkedListSentinela.UnorderedLinkedList;
import missoes.Alvo;
import missoes.Cenario;
import missoes.Divisao;
import missoes.Inimigo;
import missoes.Missao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tiago
 */
public class JsonImporter {

    /**
     * Constructor for JsonImporter.
     */
    public JsonImporter() {}

    /**
     * Import JSON file.
     * @return IOrders
     */
    public IMissao jsonImporter(String path) throws IOException, ParseException, 
            FileNotFoundException, NullElementValueException, RepeatedElementException, 
            ElementNotFoundException, InvalidWeightValueException, InvalidOperationException, 
            VersionAlreadyExistException {
        IMissao missao = null;
        missao = importFile(path);
        return missao;
    }

    /**
     * Import JSON file.
     * @return IOrders
     */
    private IMissao importFile(String path) throws FileNotFoundException, IOException, 
            ParseException, NullElementValueException, RepeatedElementException, 
            ElementNotFoundException, InvalidWeightValueException, InvalidOperationException, 
            VersionAlreadyExistException{
        //String id = numbOrders.toString();
        

        JSONObject resultObject;
        JSONParser parser = new JSONParser();

        Reader reader = new FileReader(path);
        resultObject = (JSONObject) parser.parse(reader);
        
        String jCod = (String) resultObject.get("cod-missao");
        
        long jVersao = (long) resultObject.get("versao");
        
        JSONArray jEdificio = (JSONArray) resultObject.get("edificio");
     
        JSONArray jLigacoes = (JSONArray) resultObject.get("ligacoes");
        
        JSONArray jInimigos = (JSONArray) resultObject.get("inimigos");
        
        JSONArray jEntradasSaidas = (JSONArray) resultObject.get("entradas-saidas");
        
        JSONObject jAlvo = (JSONObject) resultObject.get("alvo");
                
       
        IMissao missao = new Missao(jCod);
        
        WeightedAdjMatrixDiGraph<IDivisao> edificio = new WeightedAdjMatrixDiGraph<>();
        IDivisao divisaoParaIterator = null;
        
        for (int i = 0; i < jEdificio.size(); i++) {
            IDivisao divisao = new Divisao(jEdificio.get(i).toString());
            divisaoParaIterator = divisao;
            
            for (int j = 0; j < jInimigos.size(); j++) {
                JSONObject jInimigo = (JSONObject) jInimigos.get(j);
                
                IDivisao divisaoInimigo = new Divisao(jInimigo.get("divisao").toString());
                
                if(divisao.equals(divisaoInimigo)){
                    Inimigo inimigo = new Inimigo(jInimigo.get("nome").toString(), (int)((long)jInimigo.get("poder")));
                    divisao.setDano(divisao.getDano() + (int)((long)jInimigo.get("poder")));
                    divisao.adicionarInimigo(inimigo);
                }
            }
            edificio.addVertex(divisao);
        }
        

        for(int i=0; i<jLigacoes.size();i++){
            JSONArray jLigacao = (JSONArray) jLigacoes.get(i);
            
            String jVertex1 = (String) jLigacao.get(0);
            IDivisao divisao1 = new Divisao(jVertex1);
            
            Iterator<IDivisao> iterator1 = edificio.iteratorBFS(divisaoParaIterator);
            
            IDivisao divisaoTemp =  null;
            
            boolean found = false;
            
            while(iterator1.hasNext() && !found){
                divisaoTemp = iterator1.next();
                if(divisaoTemp.equals(divisao1)){
                    found = true;
                    divisao1 = divisaoTemp;
                }
            }
            
            String jVertex2 = (String) jLigacao.get(1);
            IDivisao divisao2 = new Divisao(jVertex2);
            
            Iterator<IDivisao> iterator2 = edificio.iteratorBFS(divisaoParaIterator);
            
            found = false;
            
            while(iterator2.hasNext() && !found){
                divisaoTemp = iterator2.next();
                if(divisaoTemp.equals(divisao2)){
                    found = true;
                    divisao2 = divisaoTemp;
                }
            }
            
            edificio.addEdge(divisao1, divisao2, divisao2.getDano());
            edificio.addEdge(divisao2, divisao1, divisao1.getDano());
        }
        
        UnorderedLinkedList<IDivisao> entradasSaidas = new UnorderedLinkedList<>();
        
        for(int i=0; i<jEntradasSaidas.size();i++){
            
            IDivisao divisao = new Divisao(jEntradasSaidas.get(i).toString());
            entradasSaidas.addToRear(divisao);
        }
        
        IDivisao alvoDivisao = new Divisao(jAlvo.get("divisao").toString());
        
        Alvo alvo = new Alvo(alvoDivisao, jAlvo.get("tipo").toString());
        
        ICenario cenario = new Cenario((int) jVersao, edificio, entradasSaidas, alvo);
        
        missao.adicionarVersão(cenario);
        
        return missao;
    }

    /**
     * Validate imported JSON file.
     * @return true if document is correct.
     * @return false if document does not follow base struct.
     */
//    private boolean validateJSONFile(Orders orders) {
//
//        IOrder[] listaOrders = orders.getOrders();
//
//        for (int i = 0; i < orders.getNumOrders(); i++) {
//            if (listaOrders[i].getID() == null || listaOrders[i].getCostumer() == null || listaOrders[i].getPackages() == null || listaOrders[i].getReceiver() == null) {
//                return false;
//            }
//
//            ICostumer costumer = listaOrders[i].getCostumer();
//            if (costumer.getName() == null || costumer.getNif() < 0 || costumer.getNif() > 999999999) {
//                return false;
//            }
//
//            ICostumer receiver = listaOrders[i].getReceiver();
//            if (receiver.getName() == null || receiver.getNif() < 0 | costumer.getNif() > 999999999) {
//                return false;
//            }
//
//            IAddress cAdrress = costumer.getAdress();
//            if (cAdrress.getCity() == null || cAdrress.getCountry() == null || cAdrress.getNumberDoor() < 0 || cAdrress.getStreet() == null || cAdrress.getPostalCode() == null) {
//                return false;
//            }
//
//            IAddress rAdrress = receiver.getAdress();
//            if (rAdrress.getCity() == null || rAdrress.getCountry() == null || rAdrress.getNumberDoor() < 0 || rAdrress.getStreet() == null || rAdrress.getPostalCode() == null) {
//                return false;
//            }
//
//            IPackage[] listaPackages = listaOrders[i].getPackages();
//
//            for (int j = 0; j < listaOrders[i].getNumbPackages(); j++) {
//                if (listaPackages[j].getID() == null || listaPackages[j].getItems() == null || listaPackages[j].getSize() == null) {
//                    return false;
//                }
//                IItem[] listaItens = listaPackages[j].getItems();
//                for (int k = 0; k < listaPackages[j].getNumItens(); k++) {
//                    if (listaItens[k].getReference() == null) {
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }

}
