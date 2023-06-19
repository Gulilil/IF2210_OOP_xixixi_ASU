package pluginCurrency;

import org.example.program.entities.bills.Observer;
import org.example.program.entities.commodities.Commodity;

import java.util.List;

public class CommodityDecorator implements Commodity {
    private Commodity commodity;

    public CommodityDecorator(Commodity c){
        commodity = c;
    }
    public Integer getId() {return commodity.getId();}

    public Integer getStock() { return commodity.getStock();}
    public String getName() { return commodity.getName();}

    public Double getPurchasePrice() {return commodity.getPurchasePrice();}
    public Double getPrice() {return commodity.getPrice();}
    public String getCategory() { return commodity.getCategory();}

    public String getImage() {return commodity.getImage();}

    public Boolean getActive() { return commodity.getActive();}

    public void setID(Integer i) {commodity.setID(i);}

    public void setStock(Integer newStock) {commodity.setStock(newStock);}

    public void setName(String newName) {commodity.setName(newName);}

    public void setPurchasePrice(Double newPurchasePrice) { commodity.setPurchasePrice(newPurchasePrice);}

    public void setPrice(Double newPrice) {commodity.setPrice(newPrice);}

    public void setCategory(String newCategory){commodity.setCategory(newCategory);}

    public void setImage(String newImage){commodity.setImage(newImage);}

    public void setActive(Boolean active) {commodity.setActive(active);}

    public void display() {commodity.display();}

    public String getCurrency() {return commodity.getCurrency();}

    public void registerObserver(Observer observer) {commodity.registerObserver(observer);}

    public void removeObserver(Observer observer){commodity.removeObserver(observer);}

    public void notifyObservers(){commodity.notifyObservers();}

    public void setObservers(List<Observer> observers) { commodity.setObservers(observers);};

    public List<Observer> getObservers() {return commodity.getObservers();}

}
