module net._4werners.jenkov.tutorial {
    requires javafx.controls;
    requires javafx.fxml;

    opens net._4werners.jenkov.tutorial to javafx.fxml;
    exports net._4werners.jenkov.tutorial;
}