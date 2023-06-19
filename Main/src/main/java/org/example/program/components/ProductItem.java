package org.example.program.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import lombok.*;
import org.example.program.entities.commodities.Commodity;
import org.example.program.entities.commodities.Product;

@Getter
public class ProductItem extends Button {
    private HBox content;
    private ProductDetails details;

    public ProductItem(Commodity product) {
        this.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * 3 / 8);
        this.setPrefHeight(100);
        this.setStyle("""
                    -fx-background-color: rgb(245, 235, 235, 0.5);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);

        this.details = new ProductDetails(product);

        NewImage productimg = new NewImage(product.getImage());
        productimg.setDimension(80, 80);

        this.content = new HBox();
        this.content.getChildren().add(productimg);
        this.content.setAlignment(Pos.CENTER_LEFT);
        this.content.setSpacing(20);

        VBox infoAlignment = new VBox();
        infoAlignment.getChildren().add(new NewLabel(product.getName(), 22, "#867070", 700));
        infoAlignment.getChildren().add(new NewLabel("ID" + product.getId() + " - " + product.getCategory(), 20, "#867070", 700));
        infoAlignment.setPrefWidth(this.getPrefWidth()/2);
        infoAlignment.setAlignment(Pos.CENTER_LEFT);

        this.content.getChildren().add(infoAlignment);

        NewLabel price =  new NewLabel(product.getCurrency()+ "  "+ String.format("%.2f", product.getPrice()), 20, "#867070", 700);
        price.setAlignment(Pos.CENTER_RIGHT);
        price.setPrefWidth(this.getPrefWidth()/2);
        this.content.getChildren().add(price);

        this.setGraphic(this.content);

        this.setOnMouseEntered(event -> {
            this.setStyle("""
                    -fx-background-color: rgb(245, 235, 235, 1);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
        });

        this.setOnMouseExited(event -> {
            this.setStyle("""
                    -fx-background-color: rgb(245, 235, 235, 0.5);
                    -fx-font-size: 18;
                    -fx-font-weight: bold;
                    -fx-background-radius: 9;
                """);
        });
    }

}
