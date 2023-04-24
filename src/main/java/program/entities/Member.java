package program.entities;

import lombok.*;
@Getter
@Setter

public class Member extends RegisteredCustomer{

    public static Double FIXED_POINT_RATE = 0.01;
    public Member(Integer newID, String name, String phoneNumber, Double point, Boolean active){
        super(newID, name, phoneNumber, point, active);
    }


    @Override
    public Double payment(Double n, Double point) {
        Double tempPoint = n*FIXED_POINT_RATE;
        if ((point > getPoint()) || (point < 0)){
            return null;
        } else if (point > n) {
            subtractPoint(n);
            addPoint(tempPoint);
            return 0.0;
        } else {
            subtractPoint(point);
            addPoint(tempPoint);
            return n-point;
        }
    }

    public void display(){
        System.out.println("Member");
        System.out.println("ID: "+id+", Name: "+name);
        System.out.println(transactionHistory);
        System.out.println("Phone Number: "+ phoneNumber);
        System.out.println("Point: "+ point+", Active: "+ active);
    }

}