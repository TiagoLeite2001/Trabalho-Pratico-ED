package json;

import exceptions.NoManualSimulationsException;
import exceptions.NullElementValueException;
import interfaces.ICenario;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import missoes.Divisao;
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
            Iterator<Divisao> divisoes=current.getTrajeto();
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
//
//JSONObject jDocOrders = new JSONObject();
//        JSONArray jOrders = new JSONArray();
//        for(int i=0;i<this.orders.getNumOrders();i++){
//            IOrder[] orders=this.orders.getOrders();
//            JSONObject jOrder = new JSONObject();
//            jOrder.put("IDOrder",orders[i].getID());
//            IPackage[] packages=orders[i].getPackages();
//            JSONArray jPackages=new JSONArray();
//            for(int j=0;j<orders[i].getNumbPackages();j++){
//                JSONObject jPackageMode=new JSONObject();
//                jPackageMode.put("ID",packages[j].getID());
//                jPackageMode.put("Size",packages[j].getSize().toString());
//                double[] mode=modeWeightItemsperPackage(packages[j]);
//                DecimalFormat df = new DecimalFormat("0.00");
//                if(mode[0]==0) {
//                    jPackageMode.put("Mode", "There is no Mode! ");
//                }
//                else{
//                    int count=0;
//                    JSONArray modaValues=new JSONArray();
//                    while(mode[count]>0){
//                        modaValues.add(df.format(mode[count]));
//                        count++;
//                    }
//                    jPackageMode.put("Mode weight",modaValues);
//                }
//                jPackages.add(jPackageMode);
//                jOrder.put("Packages",jPackages);
//            }
//            jOrders.add(jOrder);
//        }
//        jDocOrders.put("Orders",jOrders);
//
//        try (FileWriter file = new FileWriter("relatorios/"+"Estatistica_ModaPeso "+this.orders.getID()+".json")){
//            file.write(jDocOrders.toJSONString());
//        }
//        String result=jOrders.toJSONString();
//        return result;