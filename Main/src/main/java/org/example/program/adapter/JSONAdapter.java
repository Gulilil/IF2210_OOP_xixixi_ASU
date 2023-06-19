package org.example.program.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.program.entities.commodities.Commodity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.example.program.containers.ClientContainer;
import org.example.program.containers.InventoryContainer;
import org.example.program.containers.TransactionContainer;
import org.example.program.entities.bills.Bill;
import org.example.program.entities.commodities.Product;
import org.example.program.entities.bills.ReceiptInfo;
import org.example.program.entities.bills.Time;
import org.example.program.entities.clients.Client;
import org.example.program.entities.clients.Customer;
import org.example.program.entities.clients.Member;
import org.example.program.entities.clients.VIP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JSONAdapter implements Adapter{
    private static final String clientDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\json\\Client.json";
    private static final String inventoryDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\json\\Inventory.json";
    private static final String transactionDatabasePath = new java.io.File("").getAbsolutePath() + "\\src\\main\\datastore\\json\\Transaction.json";

    // CLIENT DATA
    public void readDataClient(ClientContainer cc) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(clientDatabasePath))
        {
            cc.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray clientList = (JSONArray) obj;

            //Iterate over client array
            clientList.forEach( client -> parseClientObject( cc, (JSONObject) client ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void writeDataClient(ClientContainer cc) {

        JSONArray clientsArr = new JSONArray();
        for (Client c : cc.getBuffer()) {
            JSONObject clientObj = new JSONObject();
            clientObj.put("id", c.getId());
            clientObj.put("transactionHistory", c.getTransactionHistory());
            if (c.getType() instanceof Customer){
                clientObj.put("type", "customer");
            } else {
                if (c.getType() instanceof Member){ clientObj.put("type", "member"); }
                else if (c.getType() instanceof VIP){ clientObj.put("type", "vip"); }
                clientObj.put("name", c.getName());
                clientObj.put("phoneNumber",c.getPhoneNumber());
                clientObj.put("point", c.getPoint());
                clientObj.put("active", c.getActiveStatus());
            }
            clientsArr.add(clientObj);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(clientDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(clientsArr));
            file.write(gson.toJson(clientsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseClientObject(ClientContainer cc, JSONObject c) {
        // Get every element
        String status = (String) c.get("type");
        Integer id = ((Long) c.get("id")).intValue();
        JSONArray arr = (JSONArray) c.get("transactionHistory");
        List<Integer> arrTransaction = new ArrayList<>();
        if (!(arr == null)){
            arr.forEach( i -> arrTransaction.add( ((Long) i).intValue() ));
        }

        Client client = new Client(id, arrTransaction, null);

        if (status.equals("customer")){
            client.makeClientACustomer();
        }
        else {
            String name = (String) c.get("name");
            String phoneNumber = (String) c.get("phoneNumber");
            Double point = ((Double) c.get("point"));
            Boolean active = ((Boolean) c.get("active")).booleanValue();
            if (status.equals("member")){
                client.makeClientAMember(name, phoneNumber, point, active);
            } else {
                client.makeClientAVIP(name, phoneNumber, point, active);
            }
        }
        cc.getBuffer().add(client);
        cc.increaseAmount();

    }

    // INVENTORY DATA
    public void readDataInventory(InventoryContainer ic) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inventoryDatabasePath))
        {
            ic.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray inventoryList = (JSONArray) obj;

            //Iterate over client array
            inventoryList.forEach( product -> parseProductObject( ic, (JSONObject) product ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseProductObject(InventoryContainer ic, JSONObject p) {
        // Get every element
        Integer id = ((Long) p.get("id")).intValue();
        Integer stock = ((Long) p.get("stock")).intValue();
        String name = (String) p.get("name");
        Double price = (Double) p.get("price");
        Double purchasePrice = (Double) p.get("purchasePrice");
        String category = (String) p.get("category");
        String image = (String) p.get("image");
        Boolean active = (Boolean) p.get("active");

        Product pr = new Product(new ArrayList<>(), id, stock, name, price, purchasePrice, category, image, active);
        ic.getBuffer().add(pr);
        ic.increaseAmount();

    }

    public void writeDataInventory(InventoryContainer ic) {
        // Make array
        JSONArray productsArr = new JSONArray();
        for (Commodity p : ic.getBuffer()) {
            Product pr = (Product) p;
            JSONObject productObj = new JSONObject();
            productObj.put("id", pr.getId());
            productObj.put("stock", pr.getStock());
            productObj.put("name", pr.getName());
            productObj.put("price", pr.getPrice());
            productObj.put("purchasePrice", pr.getPurchasePrice());
            productObj.put("category", pr.getCategory());
            productObj.put("image", pr.getImage());
            productObj.put("active", pr.getActive());

            productsArr.add(productObj);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(inventoryDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(productsArr));
            file.write(gson.toJson(productsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TRANSACTION DATA
    public void readDataTransaction(TransactionContainer tc) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(transactionDatabasePath))
        {
            tc.reset();
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray transactionList = (JSONArray) obj;

            //Iterate over client array
            transactionList.forEach( bill -> parseBillObject( tc, (JSONObject) bill ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void parseBillObject(TransactionContainer tc, JSONObject b) {
        // Get every element
        Integer idBill = ((Long) b.get("idBill")).intValue();
        Integer idClient = ((Long) b.get("idClient")).intValue();

        List<ReceiptInfo> receipt = new ArrayList<>();
        JSONArray arr = (JSONArray) b.get("receipt");
        for (Object o: arr) {
            ReceiptInfo info = getParseReceiptArr((JSONObject) o);
            receipt.add(info);
        }

        Double totalPrice = (Double) b.get("totalPrice");
        Boolean isFixed = (Boolean) b.get("isFixed");
        String time = (String) b.get("transactionTime");
        Time transactionTime = new Time();
        transactionTime.setStringTime(time);
        transactionTime.parseTimeFromString();

        Bill bi = new Bill(idBill, idClient, receipt, totalPrice, isFixed, transactionTime);
        tc.getBuffer().add(bi);
        tc.increaseAmount();
    }

    public ReceiptInfo getParseReceiptArr(JSONObject o) {
        Integer idP = ((Long) o.get("idProduct")).intValue();
        Integer quantity = ((Long) o.get("quantity")).intValue();
        Double subtotal = (Double) o.get("subtotal");
        Boolean valid = (Boolean) o.get("isValid");
        ReceiptInfo info = new ReceiptInfo(idP, quantity, subtotal, valid);
        return info;
    }


    public void writeDataTransaction(TransactionContainer tc) {
        JSONArray transactionsArr = new JSONArray();
        for (Bill b : tc.getBuffer()) {
            JSONObject billObj = new JSONObject();
            billObj.put("idBill", b.getIdBill());
            billObj.put("idClient", b.getIdClient());

            JSONArray receiptArr = new JSONArray();
            for (ReceiptInfo r : b.getReceipt()){
                JSONObject rec = new JSONObject();
                rec.put("idProduct", r.getProductID());
                rec.put("quantity", r.getQuantity());
                rec.put("subtotal", r.getSubtotal());
                rec.put("isValid", r.getIsValid());
                receiptArr.add(rec);
            }
            billObj.put("receipt", receiptArr);
            billObj.put("totalPrice", b.getTotalPrice());
            billObj.put("isFixed", b.getIsFixedBill());
            billObj.put("transactionTime", b.getTransactionTime().getStringTime());
            transactionsArr.add(billObj);

        }

        //Write JSON file
        try (FileWriter file = new FileWriter(transactionDatabasePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(transactionsArr));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
