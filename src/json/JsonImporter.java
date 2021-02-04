/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import exceptions.ElementNotFoundException;
import exceptions.InvalidDocumentException;
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
    public JsonImporter() {
    }

    /**
     * Import JSON file.
     *
     * @return IOrders
     */
    public IMissao jsonImporter(String path) throws IOException, ParseException,
            FileNotFoundException, NullElementValueException, RepeatedElementException,
            ElementNotFoundException, InvalidWeightValueException, InvalidOperationException,
            VersionAlreadyExistException, InvalidDocumentException {
        IMissao missao = null;
        missao = importFile(path);
        return missao;
    }

    /**
     * Import JSON file.
     *
     * @return IOrders
     */
    private IMissao importFile(String path) throws FileNotFoundException, IOException,
            ParseException, NullElementValueException, RepeatedElementException,
            ElementNotFoundException, InvalidWeightValueException, InvalidOperationException,
            VersionAlreadyExistException, InvalidDocumentException {

        IMissao missao = null;

        try {
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

            missao = new Missao(jCod);

            WeightedAdjMatrixDiGraph<IDivisao> edificio = new WeightedAdjMatrixDiGraph<>();
            IDivisao divisaoParaIterator = null;

            for (int i = 0; i < jEdificio.size(); i++) {
                IDivisao divisao = new Divisao(jEdificio.get(i).toString());
                divisaoParaIterator = divisao;

                for (int j = 0; j < jInimigos.size(); j++) {
                    JSONObject jInimigo = (JSONObject) jInimigos.get(j);

                    IDivisao divisaoInimigo = new Divisao(jInimigo.get("divisao").toString());

                    if (divisao.equals(divisaoInimigo)) {
                        Inimigo inimigo = new Inimigo(jInimigo.get("nome").toString(), (int) ((long) jInimigo.get("poder")));
                        int dano = divisao.getDano() + (int) ((long) jInimigo.get("poder"));
                        divisao.setDano(dano);
                        divisao.adicionarInimigo(inimigo);
                    }
                }
                edificio.addVertex(divisao);
            }

            for (int i = 0; i < jLigacoes.size(); i++) {
                JSONArray jLigacao = (JSONArray) jLigacoes.get(i);

                String jVertex1 = (String) jLigacao.get(0);
                IDivisao divisao1 = new Divisao(jVertex1);

                String jVertex2 = (String) jLigacao.get(1);
                IDivisao divisao2 = new Divisao(jVertex2);

                edificio.addEdge(divisao1, divisao2, edificio.getVertex(divisao2).getDano());
                edificio.addEdge(divisao2, divisao1, edificio.getVertex(divisao1).getDano());
            }

            UnorderedLinkedList<IDivisao> entradasSaidas = new UnorderedLinkedList<>();

            for (int i = 0; i < jEntradasSaidas.size(); i++) {

                IDivisao divisao = new Divisao(jEntradasSaidas.get(i).toString());
                entradasSaidas.addToRear(divisao);
            }

            IDivisao alvoDivisao = new Divisao(jAlvo.get("divisao").toString());
            Alvo alvo = new Alvo(alvoDivisao, jAlvo.get("tipo").toString());
            ICenario cenario = new Cenario((int) jVersao, edificio, entradasSaidas, alvo);

            missao.adicionarVersão(cenario);

        } catch (ClassCastException e) {
            throw new InvalidDocumentException("File values are not correct!");
        }
        return missao;
    }

    /**
     * Validate imported JSON file.
     *
     * @return true if document is correct.
     * @return false if document does not follow base structure.
     */
    private boolean validateJSONFile(IMissao missao) throws InvalidDocumentException, InvalidOperationException {

        if (missao.getNumeroVersoes() == 0) {
            throw new InvalidDocumentException("There is none map in the document!");
        }
        ICenario cenario = missao.getListVersoes().removeFirst();

        if ( cenario.getEdificio().size() < 1
                || cenario.getAlvo().getDivisao() == null
                || cenario.getAlvo().getTipo() == null
                || cenario.getEntradasSaidas() == null) {
            throw new InvalidDocumentException("There is something wrong in the document!");
        }
        
        if(!cenario.getEdificio().isConnected()){
            throw new InvalidDocumentException("The building has divisions not connected!");
        }

        Iterator<IDivisao> iterator = cenario.getEntradasSaidas();

        while (iterator.hasNext()) {
            try {
                cenario.getEdificio().getVertex(iterator.next());
            } catch (NullElementValueException | ElementNotFoundException ex) {
                throw new InvalidDocumentException("The entries and exits do  not exist in the building!");
            }
        }

        try {
            cenario.getEdificio().getVertex(cenario.getAlvo().getDivisao());
        } catch (NullElementValueException | ElementNotFoundException ex) {
            throw new InvalidDocumentException("The division of the target does not exist in the building!");
        }

        return true;
    }

}
