module net._4werners.SCD {
    requires transitive javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens net._4werners.SCD to javafx.fxml;
    exports net._4werners.SCD;
}