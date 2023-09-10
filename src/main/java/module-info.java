module com.zy.card {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.zy.card to javafx.fxml;
    exports com.zy.card;
}