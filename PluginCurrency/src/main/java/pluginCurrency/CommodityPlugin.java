package pluginCurrency;

import org.example.program.entities.bills.Observer;
import org.example.program.entities.commodities.Commodity;

import java.util.List;

public class CommodityPlugin extends CommodityDecorator{
    public CommodityPlugin(Commodity c){
        super(c);
    }

    public Integer getId() {return super.getId();}

    public Integer getStock() { return super.getStock();}
    public String getName() { return super.getName();}

    public Double getPurchasePrice() {
        Converter conv = Converter.getInstance();
        String curr = conv.getCurrentCurrency();
        Double mult = conv.getMultiplier(curr);
        return super.getPurchasePrice() / mult;
    }
    public Double getPrice() {
        Converter conv = Converter.getInstance();
        String curr = conv.getCurrentCurrency();
        Double mult = conv.getMultiplier(curr);
        return super.getPrice() / mult;
    }
    public String getCategory() { return super.getCategory();}

    public String getImage() {return super.getImage();}

    public Boolean getActive() { return super.getActive();}

    public void setID(Integer i) {super.setID(i);}

    public void setStock(Integer newStock) {super.setStock(newStock);}

    public void setName(String newName) {super.setName(newName);}

    public void setPurchasePrice(Double newPurchasePrice) {
        Converter conv = Converter.getInstance();
        String curr = conv.getCurrentCurrency();
        Double mult = conv.getMultiplier(curr);
        super.setPrice(newPurchasePrice*mult);
    }

    public void setPrice(Double newPrice) {
        Converter conv = Converter.getInstance();
        String curr = conv.getCurrentCurrency();
        Double mult = conv.getMultiplier(curr);
        super.setPrice(newPrice*mult);
    }

    public void setCategory(String newCategory){super.setCategory(newCategory);}

    public void setImage(String newImage){super.setImage(newImage);}

    public void setActive(Boolean active) {super.setActive(active);}

    public void display() {super.display();}

    public String getCurrency() {
        Converter conv = Converter.getInstance();
        return conv.getCurrentCurrency();
    }

    public void registerObserver(Observer observer) {super.registerObserver(observer);}

    public void removeObserver(Observer observer){super.removeObserver(observer);}

    public void notifyObservers(){super.notifyObservers();}

    public void setObservers(List<Observer> observers) { super.setObservers(observers);};

    public List<Observer> getObservers() {return super.getObservers();}


}
