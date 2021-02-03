/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import interfaces.IMissao;
import missoes.Missao;

/**
 *
 * @author tiago
 */
public class JsonImporter {
    private String path;
    private Integer numbOrders=0;

    /**
     * Constructor for JsonImporter.
     */
    public JsonImporter(String path) {
        this.path = path;
    }

    /**
     * Import JSON file.
     * @return IOrders
     */
    public IMissao JsonImporter(){
            IMissao missao = null;
            //missao = importFile();
            return missao;
        }
    

    /**
     * Import JSON file.
     * @return IOrders
     */
   // private IMissao importFile(){
        //String id = numbOrders.toString();
        

//        JSONObject resultObject;
//        JSONParser parser = new JSONParser();
//
//        Reader reader = new FileReader(path);
//        resultObject = (JSONObject) parser.parse(reader);
//
//        JSONArray orders = (JSONArray) resultObject.get("orders");
//        
//        IMissao missao = new Missao("shbw");
//        
//        for (int i = 0; i < orders.size(); i++) {
//            JSONObject jorder = (JSONObject) orders.get(i);
//            JSONObject jcostumer = (JSONObject) jorder.get("customer");
//
//            Long l = (long) jcostumer.get("nif");
//            Integer numb = l.intValue();
//
//            Costumer costumer = new Costumer((String) jcostumer.get("name"), numb);
//            JSONObject jaddress = (JSONObject) jcostumer.get("address");
//
//
//            l = (long) jaddress.get("number");
//            numb = l.intValue();
//
//            Address address = new Address((String) jaddress.get("country"), numb,
//                    (String) jaddress.get("street"), (String) jaddress.get("city"),
//                    (String) jaddress.get("postal_code"));
//            costumer.setAddress(address);
//            //-----------------------------------
//            JSONObject jreceiver = (JSONObject) jorder.get("destination");
//
//            l = (long) jreceiver.get("nif");
//            numb = l.intValue();
//
//            Costumer receiver = new Costumer((String) jreceiver.get("name"), numb);
//            JSONObject jreceiverAddress = (JSONObject) jreceiver.get("address");
//
//            l = (long) jreceiverAddress.get("number");
//            numb = l.intValue();
//
//            Address receiverAddress = new Address((String) jreceiverAddress.get("country"), numb,
//                    (String) jreceiverAddress.get("street"), (String) jreceiverAddress.get("city"),
//                    (String) jreceiverAddress.get("postal_code"));
//            receiver.setAddress(receiverAddress);
//
//            String orderID = (String) jorder.get("orderID");
//
//            Order order = new Order(costumer, receiver, orderID);
//
//            JSONArray jpackages = (JSONArray) jorder.get("packages");
//
//            for (int k = 0; k < jpackages.size(); k++) {
//                JSONObject jpackage = (JSONObject) jpackages.get(k);
//                Package pack = new Package(PackageSize.stringToPackageSize((String) jpackage.get("type")), (String) jpackage.get("packageID"));
//
//                JSONArray jitems = (JSONArray) jpackage.get("items");
//
//                for (int j = 0; j < jitems.size(); j++) {
//                    JSONObject jitem = (JSONObject) jitems.get(j);
//
//                    String reference = (String) jitem.get("reference");
//
//                    l = (long) jitem.get("depth");
//                    int depth = l.intValue();
//
//                    l = (long) jitem.get("length");
//                    int length = l.intValue();
//
//                    l = (long) jitem.get("height");
//                    int height = l.intValue();
//
//                    Double d = (double) jitem.get("weight");
//                    float weight = d.floatValue();
//
//                    Item item = new Item(reference, depth, length, height, weight);
//                    pack.addItem(item);
//
//                }
//                order.addPackage(pack);
//            }
//            missao.addOrder(order);
//        }
//
//        if (validateJSONFile((Orders) missao)) {
//            return missao;
//        } else {
//            throw new InvalidDocumentException("O documento JSON inserido não é válido!");
//        }
//    }
//
//    /**
//     * Validate imported JSON file.
//     * @return true if document is correct.
//     * @return false if document does not follow base struct.
//     */
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
    //}

}
