module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires json;
    requires json.simple;

    opens com.example.test to javafx.fxml;
    exports com.example.test;
}