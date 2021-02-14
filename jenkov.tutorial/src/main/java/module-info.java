module net._4werners.jenkov.tutorial {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens net._4werners.jenkov.tutorial to javafx.fxml;
    exports net._4werners.jenkov.tutorial;
}