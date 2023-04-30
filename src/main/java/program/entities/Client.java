package program.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Client")
@XmlAccessorType (XmlAccessType.FIELD)
abstract public class Client {
    protected Integer id;
    protected List<Integer> transactionHistory;

    // Constructor
    public Client(Integer newID) {
        this.id = newID;
        this.transactionHistory = new ArrayList<>();
    }

    // Methods
    abstract public Double payment(Double n, Double point);
    public void addTransaction(Integer id){
        transactionHistory.add(id);
    }
    public void removeTransaction(Integer id){
        int idx = 0;
        for ( Integer tempID: transactionHistory ) {
            if (tempID.equals(id)){
                transactionHistory.remove(idx);
                break;
            }
            idx++;
        }
    }

    public boolean isTransactionIDInTransaction(Integer id){
        for (Integer tempID: transactionHistory) {
            if (tempID.equals(id)){
                return true;
            }
        }
        return false;
    }
}
