package org.example.program.entities.commodities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import org.example.program.entities.bills.Observer;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Product")
@XmlAccessorType (XmlAccessType.FIELD)

public class Product extends Observable implements Serializable, Subject, Commodity {
    private List<Observer> observers;
    private Integer id;
    private Integer stock;
    private String name;
    private Double price;
    private Double purchasePrice;
    private String category;
    private String image;
    private Boolean active;

    public void display(){
        if (active) {
            System.out.println("ACTIVE PRODUCT");
        } else {
            System.out.println("INACTIVE PRODUCT");
        }
        System.out.println("ID: "+id+", Stock: "+stock);
        System.out.println("Name: "+name);
        System.out.println("Price: "+price +", PurchasePrice: "+purchasePrice);
        System.out.println("Category: "+ category+", Image: "+ image );
    }

    public void increaseStock(Integer n) {
        setStock(getStock()+n);
    }

    public void decreaseStock(Integer n){
        setStock(getStock()-n);
    }

    public Integer getId(){ return id; }
    public Integer getStock(){ return stock;}
    public String getName(){ return name; }

    public Double getPurchasePrice() {return purchasePrice;}
    public Double getPrice(){return price;}
    public String getCategory(){return category;}

    public String getImage(){return image;}

    public void setPurchasePrice(Double newPurchasePrice){
        this.purchasePrice = newPurchasePrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    public Boolean getActive(){return active;}

    public void setID(Integer i){id = i;}

    public void setStock(Integer newStock){stock = newStock;}

    public void setName(String newName) {name = newName;}


    public void setPrice(Double newPrice){ price = newPrice;}

    public void setCategory(String newCategory){category = newCategory;}

    public void setImage(String newImage){image = newImage;}

    public void setActive(Boolean newActive) { this.active = newActive; }

    public String getCurrency() { return "IDR";}

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public List<Observer> getObservers() { return this.observers;}
}
