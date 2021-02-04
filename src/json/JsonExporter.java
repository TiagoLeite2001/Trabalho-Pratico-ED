package json;

import exceptions.NoManualSimulationsException;
import exceptions.NullElementValueException;
import interfaces.ICenario;
import interfaces.IDivisao;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import simulacoes.SimulacaoManual;

public class JsonExporter {

    public static String exportSimulacoesManuais(String codMissao, ICenario versao) throws NullElementValueException, NoManualSimulationsException, IOException {
        if(codMissao==null || versao==null)
            throw new NullElementValueException("Invalid Arguments");
        if(versao.getNumSimulacoesManuais()==0){
            throw new NoManualSimulationsException("There are not manual simulations");
        }
        JSONObject jDoc = new JSONObject();
        jDoc.put("CodMissao", codMissao);
        jDoc.put("Versao", versao.getVersao());
        jDoc.put("NumSimulacoesManuais", versao.getNumSimulacoesManuais());
        JSONArray jSimulacoes=new JSONArray();
        
        Iterator<SimulacaoManual> simulacoes=versao.getSimulacoesManuais();
        //Adicionar as simulacoes manuais
        while(simulacoes.hasNext()){
            SimulacaoManual current=simulacoes.next();
            JSONObject jSimulacaoManual=new JSONObject();
            jSimulacaoManual.put("Sucesso", current.missaoSucedida());
            jSimulacaoManual.put("PontosVida",current.getPontosVida());
            
            JSONArray trajetoPercorrido=new JSONArray();
            Iterator<IDivisao> divisoes=current.getTrajeto();
            //Adicionar cada divisao do trajeto percorrido na simulacao
            while(divisoes.hasNext()){
                trajetoPercorrido.add(divisoes.next().getNome());
            }
            jSimulacaoManual.put("Trajeto",trajetoPercorrido);
            jSimulacoes.add(jSimulacaoManual);
        }

        jDoc.put("Simulacoes", jSimulacoes);

        try (FileWriter file = new FileWriter("CodMissao_"+codMissao+"Versao_"+versao.getVersao()+".json")) {
            file.write(jDoc.toJSONString());
        }
        String result = jDoc.toJSONString();
        return result;
    }
}
