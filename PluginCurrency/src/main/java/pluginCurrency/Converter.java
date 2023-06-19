package pluginCurrency;

import org.example.program.containers.ClientContainer;
import org.example.program.containers.InventoryContainer;
import org.example.program.containers.Manager;
import org.example.program.containers.TransactionContainer;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static Converter instance = null;

    private String currentCurrency;
    private final Map<String, Double> convertTable;

    private Converter(){
        convertTable = new HashMap<String,Double>() {{
            put("IDR", 1.0);
            put("USD", 14674.8);
            put("GBP", 18547.48);
            put("EUR", 16173.00);
            put("JPY", 108.84);
            put("CNY", 2123.52);
            put("KRW", 11.13);
        }};
        currentCurrency = "IDR";
    }

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public static Converter getInstance(){
        if (instance == null){
            instance = new Converter();
        }
        return instance;
    }

    public Map<String,Double> getTable(){
        return convertTable;
    }

    public Double getMultiplier(String k){
        return convertTable.get(k);
    }
}
